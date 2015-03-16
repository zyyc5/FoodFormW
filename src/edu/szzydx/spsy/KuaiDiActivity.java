package edu.szzydx.spsy;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONObject;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import edu.szzydx.spsy.bean.History;
import edu.szzydx.spsy.bean.KuaiDi;
import edu.szzydx.spsy.data.Kuaidi_adapter;
import edu.szzydx.spsy.data.SqlHelper;
import edu.szzydx.spsy.tool.NetWork;
import edu.szzydx.spsy.tool.PSava;
import edu.szzydx.spsy.tool.PubTool;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

public class KuaiDiActivity extends Activity
{

	ListView lv;
	ArrayList<KuaiDi> list=new ArrayList<KuaiDi>();
	Kuaidi_adapter adapter;
	String com="未知";
	TextView tv_com;
	SqlHelper sqlh;
	String num;
	History his;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.kuaidi);
		
		TextView title=(TextView)findViewById(R.id.titletv);
		title.setText("快递查询");
		tv_com=(TextView)findViewById(R.id.tv_kd_com);
		lv=(ListView)findViewById(R.id.lv_kd_result);
		adapter=new Kuaidi_adapter(this, list);
		lv.setAdapter(adapter);
		
		
		num=PubTool.GetValueofIntent(this, "SCAN");
		sqlh=new SqlHelper(this);
		his=new History();
		his.code=num;
		
		PSava ps=new PSava(KuaiDiActivity.this);
		if(!ps.getinfo("kd", "com").equals(""))
			new doback().execute(num);
		else
			new dobackss().execute("");
				
	}
	
	class doback extends AsyncTask<String, String, String>
	{

		ProgressDialog dialog=PubTool.getDialog(KuaiDiActivity.this, "数据获取中...", true);
		
		@Override
		protected void onPreExecute() 
		{
			super.onPreExecute();
			dialog.show();
		}

		@Override
		protected void onPostExecute(String res) 
		{
			super.onPostExecute(res);
			dialog.dismiss();
			if(res.equals("error"))
			{
				PubTool.ShowToast(KuaiDiActivity.this, "网络或数据错误");
				return;
			}
			if(res.equals(""))
			{
				PubTool.ShowToast(KuaiDiActivity.this, "未找到相关的快递信息，请确保快递单号正确");
				return;
			}
			Gson gson=new Gson();
			Type listtype =new TypeToken<ArrayList<KuaiDi>>(){}.getType();
			ArrayList<KuaiDi> listtemp=gson.fromJson(res, listtype);
			list.clear();
			list.addAll(listtemp);
			adapter.notifyDataSetChanged();
			tv_com.setText(com);
			his.name=com;
			java.util.Date date=new java.util.Date(); 
			String dat =PubTool.DateToString(date, "yyyy-MM-dd");
			his.data=dat;
			his.type=3;
			sqlh.InsertHis(his);
		}


		@Override
		protected String doInBackground(String... p) 
		{
			String rt="";
			String num=p[0];
			String url="http://www.kuaidi100.com/autonumber/auto";
			String durl="http://www.kuaidi100.com/query";
			NetWork net=new NetWork();
			HashMap<String, String> map=new HashMap<String, String>();
			map.put("num", num);
			try 
			{
				String ss=net.get(url, map);
				Log.e("KuaiDi", ss);
				JSONArray arr=new JSONArray(ss);
				if(arr==null||arr.length()==0)
				{
					Log.e("KuaiDi", arr.toString());
					return rt;
				}
				for(int i=0;i<arr.length();i++)
				{
					JSONObject json=arr.getJSONObject(i);
					HashMap<String, String> mapp=new HashMap<String, String>();
					String type=json.getString("comCode");
					mapp.put("type", type);
					mapp.put("postid", num);
					mapp.put("id", "1");
					mapp.put("temp", "0.62957509374246");
					
					sqlh=new SqlHelper(KuaiDiActivity.this);
					String name=Getname(sqlh, type);
					if(!name.equals(""))
						com=name;
					String kdres=net.get(durl, mapp);
//					Log.i("kdress", kdres);
					JSONObject js=new JSONObject(kdres);
					String statue=js.getString("status");
					if(statue.equals("200"))
						return js.getString("data");
				}
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
				rt="error";
			}
			return rt;
		}
		
	}
	
	void Insertdb(SqlHelper dbHelper,ContentValues values)
	{
		SQLiteDatabase sqliteDatabase = dbHelper.getWritableDatabase();
		sqliteDatabase.insert("KD_COM", null, values);
	}
	
	private String Getname(SqlHelper dbHelper,String code)
	{
		String rtname="";
		SQLiteDatabase sqliteDatabase = dbHelper.getReadableDatabase();
		Cursor cursor = sqliteDatabase.query("KD_COM", new String[] { "companyname",  
        }, "code=?", new String[] { code }, null, null, null);  
		while (cursor.moveToNext()) 
		{   
		    rtname = cursor.getString(cursor.getColumnIndex("companyname"));  
		}  
		return rtname;
	}
	
	class dobackss extends AsyncTask<String, String, String>
	{
		ProgressDialog dialog=PubTool.getDialog(KuaiDiActivity.this, "初始化快递公司数据库...", true);
		
		@Override
		protected void onPreExecute() 
		{
			super.onPreExecute();
			dialog.show();
		}

		@Override
		protected String doInBackground(String... params) 
		{
			String url="http://www.kuaidi100.com/js/share/company.js?version=201305161806";
			try 
			{
				NetWork net=new NetWork();
				String kdcoms=net.get(url);
				kdcoms=kdcoms.replace("var jsoncom=", "");
				JSONObject json=new JSONObject(kdcoms);
				JSONArray arr=json.getJSONArray("company");
				sqlh=new SqlHelper(KuaiDiActivity.this);
				if(arr==null)
					return "";
				for(int i=0;i<arr.length();i++)
				{
					JSONObject jj=arr.getJSONObject(i);
					ContentValues values=new ContentValues();
					values.put("companyname", jj.getString("companyname"));
					values.put("shortname", jj.getString("shortname"));
					values.put("tel", jj.getString("tel"));
					values.put("url", jj.getString("url"));
					values.put("code", jj.getString("code"));
					Log.i("companycode", jj.getString("code"));
					Insertdb(sqlh, values);
				}
				return "insert";
				
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
			
			
			return null;
		}
		
		@Override
		protected void onPostExecute(String res) 
		{
			super.onPostExecute(res);
			dialog.dismiss();
			PSava ps=new PSava(KuaiDiActivity.this);
			ps.saveinfo("kd", "com", res);
			new doback().execute(num);
		}
		
	}
}
