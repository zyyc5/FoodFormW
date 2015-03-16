package edu.szzydx.spsy;

import edu.szzydx.spsy.tool.PubTool;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class SearchDialog extends Activity
{

	EditText et_q;
	Button bt_ok;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.searchdialog);
		
		et_q=(EditText)findViewById(R.id.et);		

		bt_ok=(Button)findViewById(R.id.bt_s);
		bt_ok.setOnClickListener(new onclick());
	}

	class onclick implements OnClickListener
	{
		@Override
		public void onClick(View v) 
		{
			PubTool.ShowToast(SearchDialog.this, "暂未开放该功能");
		}		
	}
	
}
