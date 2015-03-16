package edu.szzydx.spsy;

import java.util.HashMap;

import org.json.JSONObject;

import edu.szzydx.spsy.tool.NetWork;
import edu.szzydx.spsy.tool.PSava;
import edu.szzydx.spsy.tool.PubTool;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UserLogin extends Activity
{

	EditText et_name,et_pwd;
	Button bt_login,bt_reg,bt_f;
	CheckBox ck;
	PSava ps;
	TextView title;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.userlogin);
		
		title=(TextView)findViewById(R.id.titletv);
		title.setText("用户登录");
		ck=(CheckBox)findViewById(R.id.cb_rm);
		et_name=(EditText)findViewById(R.id.et_username);
		et_pwd=(EditText)findViewById(R.id.et_pwd);
		bt_login=(Button)findViewById(R.id.bt_login);
		bt_reg=(Button)findViewById(R.id.bt_grigster);
		bt_f=(Button)findViewById(R.id.bt_f_pwd);
		
		bt_login.setOnClickListener(new onclick());
		bt_reg.setOnClickListener(new onclick());
		bt_f.setOnClickListener(new onclick());
		
		ps=new PSava(this);
		String oldname=ps.getinfo("spsy", "username");
		Log.i("spsy", "oldname :"+oldname);
		et_name.setText(ps.getinfo("spsy", "username"));
		et_pwd.setText(ps.getinfo("spsy", "pwd"));
	}
	
	class onclick implements OnClickListener
	{
		@Override
		public void onClick(View v) 
		{
			switch (v.getId()) {
			case R.id.bt_login:
				login();
				break;
			case R.id.bt_grigster:
				PubTool.GoTONactivity(UserLogin.this, Register.class);
//				UserLogin.this.finish();
				break;
			case R.id.bt_f_pwd:
				
				break;

			default:
				break;
			}
		}		
	}
	
	void login()
	{
		String name=this.et_name.getText().toString().trim();
		String pwd=this.et_pwd.getText().toString().trim();
		if(!name.equals("")&&!pwd.equals(""))
		{
			new doback().execute(name,pwd);
			saveuser(name, pwd);
		}else
		{
			PubTool.ShowToast(this, "用户名和密码不能为空");
		}
	}
	
	void saveuser(String name,String pwd)
	{
		if(ck.isChecked())
		{
			ps.saveinfo("spsy", "username", name);
			ps.saveinfo("spsy", "pwd", pwd);
		}
		else
		{
			ps.saveinfo("spsy", "username", "");
			ps.saveinfo("spsy", "pwd", "");
		}
	}
	
	class doback extends AsyncTask<String, String, String>
	{
		ProgressDialog pdialog;
		
		@Override
		protected void onPreExecute() 
		{
			super.onPreExecute();
			pdialog = new ProgressDialog(UserLogin.this);   
            pdialog.setCancelable(true);
            pdialog.setMessage("登录...");
            pdialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            pdialog.show();
		}

		@Override
		protected String doInBackground(String... p) 
		{
			String name=p[0];
			String pwd=p[1];
			String rtstr="";
			try 
			{
				NetWork net=new NetWork();
				HashMap<String, String> map=new HashMap<String, String>();
				map.put("name", name);
				map.put("pwd", PubTool.md5(pwd));
				rtstr=net.post("http://2.qiufun.sinaapp.com/win8/login.php", map);
			} 
			catch (Exception e) 
			{
				rtstr="error";
			}			
			return rtstr;
		}

		@Override
		protected void onPostExecute(String result) 
		{
			super.onPostExecute(result);
			pdialog.cancel();
			System.out.println(result);
			if(result.equals("error"))
			{
				Toast.makeText(UserLogin.this, "网络错误", Toast.LENGTH_SHORT).show();
				return;
			}
			try
			{
				JSONObject json=new JSONObject(result);
				String key=json.getString("key");
				String res=json.getString("res");
				if(key.equals("0")&&res.equals("ok"))
				{
//					String name=et_name.getText().toString().trim();
//					String pwd=et_pwd.getText().toString().trim();
//					saveuser(name, pwd);
					Toast.makeText(UserLogin.this, "登录成功", Toast.LENGTH_SHORT).show();
				}
				else
				{
					Toast.makeText(UserLogin.this, res, Toast.LENGTH_SHORT).show();
				}
			}
			catch(Exception e)
			{
				Toast.makeText(UserLogin.this, "数据错误", Toast.LENGTH_SHORT).show();
			}
			
		}
		
		
	}
	
	
	
}
