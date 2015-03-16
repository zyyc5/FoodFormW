package edu.szzydx.spsy;

import java.util.ArrayList;
import java.util.HashMap;

import edu.szzydx.spsy.bean.History;
import edu.szzydx.spsy.data.SqlHelper;
import edu.szzydx.spsy.tool.PubTool;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class History_activity extends Activity
{
	Button bt_sm,bt_ss,bt_kd;
	ListView lv;
	TextView title;
	ArrayList<History> smlist,sslist,kdlist,ulist;
	badapter adapter;
	int type=1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.history_activity);
		
		title=(TextView)findViewById(R.id.titletv);
		title.setText("ÀúÊ·¼ÇÂ¼");
		
		bt_sm=(Button)findViewById(R.id.bt_his_sm);
		bt_ss=(Button)findViewById(R.id.bt_his_ss);
		bt_kd=(Button)findViewById(R.id.bt_his_kd);
		lv=(ListView)findViewById(R.id.lv_his);
		
		bt_sm.setOnClickListener(new onclick());
		bt_kd.setOnClickListener(new onclick());
		bt_ss.setOnClickListener(new onclick());
		
		smlist=new ArrayList<History>();
		sslist=new ArrayList<History>();
		kdlist=new ArrayList<History>();
		ulist=new ArrayList<History>();
		adapter=new badapter(ulist);
		lv.setAdapter(adapter);
		
		
	}

	
	
	@Override
	protected void onResume() 
	{
		super.onResume();
		smlist=getlist(1);
		sslist=getlist(2);
		kdlist=getlist(3);
		ulist.clear();
		ulist.addAll(getlist(type));
		adapter.notifyDataSetChanged();
	}



	private ArrayList<History> getlist(int t)
	{
		ArrayList<History> list=new ArrayList<History>();
		SqlHelper helper=new SqlHelper(History_activity.this);
		list=helper.getHisList("type", t+"");
		return list;
	}
	
	class onclick implements OnClickListener
	{
		@Override
		public void onClick(View v) 
		{
			v.setBackgroundResource(R.drawable.bg_hor_right_sel);
			switch (v.getId()) 
			{
			case R.id.bt_his_sm:				
				bt_kd.setBackgroundResource(R.drawable.bg_hor_right_nor);
				bt_ss.setBackgroundResource(R.drawable.bg_hor_right_nor);
				type=1;
				changedata(smlist);
				break;
			case R.id.bt_his_ss:
				bt_kd.setBackgroundResource(R.drawable.bg_hor_right_nor);
				bt_sm.setBackgroundResource(R.drawable.bg_hor_right_nor);
				type=2;
				changedata(sslist);
				break;
			case R.id.bt_his_kd:
				bt_sm.setBackgroundResource(R.drawable.bg_hor_right_nor);
				bt_ss.setBackgroundResource(R.drawable.bg_hor_right_nor);
				type=3;
				changedata(kdlist);
				break;

			default:
				break;
			}
		}		
	}
	
	void changedata(ArrayList<History> list)
	{
		ulist.clear();
		ulist.addAll(list);
		adapter.notifyDataSetChanged();
		lv.postInvalidate();
	}
	
	class badapter extends BaseAdapter
	{
		ArrayList<History> list;
		private LayoutInflater inflater;
		public badapter(ArrayList<History> list)
		{
			this.list=list;
			inflater = (LayoutInflater) History_activity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}
		@Override
		public int getCount() 
		{
			return list.size();
		}

		@Override
		public Object getItem(int arg0) 
		{
			return list.get(arg0);
		}

		@Override
		public long getItemId(int position) 
		{
			return position;
		}

		@Override
		public View getView(int position, View cv, ViewGroup parent) 
		{
			History his=list.get(position);
			if(cv==null)
			{
				cv=inflater.inflate(R.layout.history_adapter, null);
			}
			TextView tv=(TextView)cv.findViewById(R.id.tv_his_name);
//			if(his.type==3)
				tv.setText(his.name+"("+his.code+")"+"\n"+his.data);
//			else
//				tv.setText(his.name+"\n"+his.data);
				cv.setOnClickListener(new onclck(his));
			return cv;
		}
		
		class onclck implements OnClickListener
		{
			History his;
			public onclck(History his)
			{
				this.his=his;
			}
			@Override
			public void onClick(View v) 
			{
				if(his!=null)
				{
					HashMap<String, String> map=new HashMap<String, String>();
					switch (his.type) {
					case 1:					
						map.put("SCAN", his.code);
						map.put("ACTION", "SY");
						PubTool.GoTONactivity(History_activity.this, ScanResult.class, map);
						break;
					case 2:
						
						break;
					case 3:
						
						map.put("SCAN", his.code);
						PubTool.GoTONactivity(History_activity.this, KuaiDiActivity.class, map);
						break;

					default:
						break;
					}
				}
			}
			
		}
		
	}
	
}
