package edu.szzydx.spsy;

import edu.szzydx.spsy.tool.PubTool;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MoreActivity extends Activity
{

	TextView title;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.more);
		
		title=(TextView)findViewById(R.id.titletv);
		title.setText("更多");
		LinearLayout bbgx=(LinearLayout)findViewById(R.id.bbgx);
		LinearLayout yjfk=(LinearLayout)findViewById(R.id.yjfk);
		LinearLayout gywm=(LinearLayout)findViewById(R.id.gywm);
		bbgx.setOnClickListener(new click());
		yjfk.setOnClickListener(new click());
		gywm.setOnClickListener(new click());
	}

	class click implements OnClickListener
	{
		@Override
		public void onClick(View v) 
		{
			switch (v.getId()) {
			case R.id.bbgx:
				PubTool.ShowToast(MoreActivity.this, "当前已是最新版本");
				break;
			case R.id.yjfk:
				PubTool.ShowToast(MoreActivity.this, "该功能暂未开放");
				break;
			case R.id.gywm:
				PubTool.GoTONactivity(MoreActivity.this, AboutActivity.class);
				break;
			default:
				break;
			}
		}	
	}
	
}
