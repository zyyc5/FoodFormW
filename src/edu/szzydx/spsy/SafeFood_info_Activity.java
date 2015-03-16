package edu.szzydx.spsy;

import edu.szzydx.spsy.bean.Safe;
import edu.szzydx.spsy.tool.PubTool;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class SafeFood_info_Activity extends Activity
{

	TextView tv_content;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.safe_info);
		TextView titletv=(TextView)findViewById(R.id.titletv);
		Button backbt=(Button)findViewById(R.id.backbt);
		backbt.setVisibility(View.VISIBLE);
		backbt.setText(" \t·µ»Ø");
		backbt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) 
			{
				SafeFood_info_Activity.this.finish();
			}
		});
		tv_content=(TextView)findViewById(R.id.tv_safe_content);
		Safe f=(Safe)PubTool.obj;
		String title=f.Name;
		String content=f.Content;
		
		if(title.length()>11)
			title=title.substring(0, 9)+"...";
		titletv.setText(title);
		tv_content.setText(content);
		
	}

	
	
}
