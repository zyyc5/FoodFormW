package edu.szzydx.spsy;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class AboutActivity extends Activity
{
	TextView tv_bb,tv_about;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about);
		TextView title=(TextView)findViewById(R.id.titletv);
		title.setText("关于我们");
		Button backbt=(Button)findViewById(R.id.backbt);
		backbt.setVisibility(View.VISIBLE);
		backbt.setText(" \t返回");
		backbt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) 
			{
				AboutActivity.this.finish();
			}
		});
		tv_bb=(TextView)findViewById(R.id.tv_bb);
		tv_about=(TextView)findViewById(R.id.tv_about_content);
		tv_bb.setText("版本 ：\t"+versioncode());
		tv_about.setText(R.string.about_us);
	}

	private double versioncode()
	{
		  PackageInfo info;
		  try
		  {
			  info = getPackageManager().getPackageInfo(getPackageName(), 0);  
			  double versionCode = new Double(info.versionName);  
			  Log.e("version", String.valueOf(versionCode));
			  return versionCode;
		  }catch (Exception e) 
		  {
			  Log.e("version", "error-------------------------");
			  return 1.0;
		  }
	
	 }
}
