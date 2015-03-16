package edu.szzydx.spsy;

import java.util.HashMap;

import edu.szzydx.spsy.tool.PubTool;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Scan_input extends Activity
{

	Button bt_sp,bt_kd,bt_ok,bt_trans,bt_cancel;
	EditText et;
	String ACTION="sp";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.input_layout);
		
		TextView title=(TextView)findViewById(R.id.titletv);
		title.setText(" ÷π§ ‰»Î");
		
		et=(EditText)findViewById(R.id.et_code);
		
		bt_sp=(Button)findViewById(R.id.bt_sp);
		bt_kd=(Button)findViewById(R.id.bt_kd);
		bt_ok=(Button)findViewById(R.id.bt_ok);
		bt_trans=(Button)findViewById(R.id.bt_trans);
		bt_cancel=(Button)findViewById(R.id.bt_trans_cancel);
		
		bt_sp.setOnClickListener(new onclick());
		bt_kd.setOnClickListener(new onclick());
		bt_ok.setOnClickListener(new onclick());
		bt_trans.setOnClickListener(new onclick());
		bt_cancel.setOnClickListener(new onclick());
		
	}
	
	class onclick implements OnClickListener
	{

		@Override
		public void onClick(View v) 
		{
			switch (v.getId()) 
			{
				case R.id.bt_sp:
					v.setBackgroundResource(R.drawable.scan_type_bt_sel);
					bt_kd.setBackgroundResource(R.drawable.scan_type_bt_nor);
					ACTION="sp";
					break;
				case R.id.bt_kd:
					v.setBackgroundResource(R.drawable.scan_type_bt_sel);
					bt_sp.setBackgroundResource(R.drawable.scan_type_bt_nor);
					ACTION="kd";
					break;
				case R.id.bt_ok:
					DoAction();
					break;
				case R.id.bt_trans:
					PubTool.GoTONactivity(Scan_input.this, CaptureActivity.class);
					Scan_input.this.finish();
					break;
					
				case R.id.bt_trans_cancel:
					Scan_input.this.finish();
					break;
	
				default:
					break;
			}
			
		}
		
	}
	
	void DoAction()
	{
		String inp=this.et.getText().toString();
		if(inp==null||inp.equals(""))
			return;
		HashMap<String, String> map=new HashMap<String, String>();
		map.put("SCAN", inp);
		if(ACTION.equals("kd"))
			PubTool.GoTONactivity(Scan_input.this, KuaiDiActivity.class, map);
		else if(ACTION.equals("sp"))
		{
			map.put("ACTION", "SY");
			PubTool.GoTONactivity(Scan_input.this, ScanResult.class, map);
		}
	}
	
}
