package edu.szzydx.spsy;

import edu.szzydx.spsy.bean.Baoguanglan;
import edu.szzydx.spsy.data.PubStatic;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class BaoGuangLan_info extends Activity
{

	TextView tv_info_title,tv_info_date,tv_info_yxfw,tv_info_content;
	ImageView im_title;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bgl_info);
		
		TextView titletv=(TextView)findViewById(R.id.titletv);
		titletv.setText("Ïê  Çé");
		Button backbt=(Button)findViewById(R.id.backbt);
		backbt.setVisibility(View.VISIBLE);
		backbt.setText(" \t·µ»Ø");
		backbt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) 
			{
				BaoGuangLan_info.this.finish();
			}
		});
		tv_info_title=(TextView)findViewById(R.id.tv_info_title);
		tv_info_date=(TextView)findViewById(R.id.tv_info_date);
		tv_info_yxfw=(TextView)findViewById(R.id.tv_info_yxfw);
		tv_info_content=(TextView)findViewById(R.id.tv_info_content);
		im_title=(ImageView)findViewById(R.id.im_info_title);
		Baoguanglan bgl=PubStatic.kd;
		PubStatic.kd=null;
		
		tv_info_content.setText(bgl.Content);
		tv_info_date.setText(bgl.date);
		tv_info_title.setText(bgl.title);
		tv_info_yxfw.setText(bgl.YXFW);
		im_title.setImageBitmap(bgl.pic);
	}
	
	
	
}
