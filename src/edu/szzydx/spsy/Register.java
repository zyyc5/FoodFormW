package edu.szzydx.spsy;

import java.util.HashMap;

import org.json.JSONObject;
import edu.szzydx.spsy.tool.NetWork;
import edu.szzydx.spsy.tool.PubTool;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class Register extends Activity
{

	EditText etname,etphone,etemail,etpwd,etpwd2;
	RadioButton rbman,rbwoman;
	Button regbt;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		
		TextView title=(TextView)findViewById(R.id.titletv);
		title.setText("用户注册");
		
		etname=(EditText)findViewById(R.id.et_hy);
		etphone=(EditText)findViewById(R.id.ed_tel2);
		etemail=(EditText)findViewById(R.id.ed_email);
		etpwd=(EditText)findViewById(R.id.ed_desc);
		etpwd2=(EditText)findViewById(R.id.ed_jsr);
		rbman=(RadioButton)findViewById(R.id.rb_sex_m);
		rbwoman=(RadioButton)findViewById(R.id.rb_sex_w);
		regbt=(Button)findViewById(R.id.btn_savenewcrm);
		regbt.setOnClickListener(new onclick());
	}
	

	class onclick implements OnClickListener
	{
		@Override
		public void onClick(View arg0) 
		{
			if(etname.getText().toString().equals("")||etname.getText()==null)
			{
				viewBand(etname);
				return;
			}
			if(etphone.getText().toString().equals("")||etphone.getText()==null)
			{
				viewBand(etphone);
				return;
			}
			if(etemail.getText().toString().equals("")||etemail.getText()==null)
			{
				viewBand(etemail);
				return;
			}
			if(etpwd.getText().toString().equals("")||etpwd.getText()==null)
			{
				viewBand(etpwd);
				return;
			}
			if(etpwd2.getText().toString().equals("")||etpwd2.getText()==null)
			{
				viewBand(etpwd2);
				return;
			}
			if(!etpwd.getText().toString().equals(etpwd2.getText().toString()))
			{
				viewBand(etpwd);
				viewBand(etpwd2);
				return;
			}
			
			new backdo().execute(etname.getText().toString(),etphone.getText().toString(),etemail.getText().toString(),etpwd.getText().toString());
		}		
	}
	
	void viewBand(View v)
	{
		 Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
		 v.startAnimation(shake);
	}
	
	class backdo extends AsyncTask<String, String, String>
	{
		ProgressDialog pdialog;
		@Override
		protected String doInBackground(String... p) {
			// TODO Auto-generated method stub
			NetWork net=new NetWork();
			HashMap<String, String> map=new HashMap<String, String>();
			map.put("name", p[0]);
			map.put("sex", "0");
			map.put("phone", p[1]);
			map.put("email", p[2]);
			map.put("pwd", p[3]);
			String res=net.post("http://2.qiufun.sinaapp.com/win8/reg.php", map);
			Log.i("res", res);
			return res;
		}

		@Override
		protected void onPostExecute(String result) 
		{
			super.onPostExecute(result);
			pdialog.cancel();
			try 
			{
				JSONObject json=new JSONObject(result);
				String code=json.getString("rescode");
				String resstr=json.getString("res");
				if(code.equals("0"))
				{
					PubTool.ShowToast(Register.this, "注册成功");
					Register.this.finish();
				}
				else
				{
					PubTool.ShowToast(Register.this, resstr);
				}
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
			
		}

		@Override
		protected void onPreExecute() 
		{
			super.onPreExecute();
			pdialog = new ProgressDialog(Register.this);   
	        pdialog.setCancelable(true);
	        pdialog.setMessage("请稍后...");
	        pdialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
	        pdialog.show();
		}
		
		
	}
	
	
}
