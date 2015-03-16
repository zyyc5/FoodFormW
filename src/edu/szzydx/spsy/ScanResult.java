package edu.szzydx.spsy;

import java.util.Date;

import edu.szzydx.spsy.bean.History;
import edu.szzydx.spsy.data.SqlHelper;
import edu.szzydx.spsy.tool.PubTool;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ScanResult extends Activity
{
	TextView tv_content,tx_content,tt;
	Button back,rescan;
	String ACTION="";
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.scanresult);
		
		TextView titletv=(TextView)findViewById(R.id.titletv);
		tt=(TextView)findViewById(R.id.tt);
		
//		titletv.setText("ɨ����");
		
		back=(Button)findViewById(R.id.backbt);
		rescan=(Button)findViewById(R.id.btrefresh);
		back.setText("����");
		back.setVisibility(View.VISIBLE);
		rescan.setText("����ɨ��");
		rescan.setVisibility(View.VISIBLE);
		back.setOnClickListener(new onclick());
		rescan.setOnClickListener(new onclick());
		
		tv_content=(TextView)findViewById(R.id.tv_content_m);
		tx_content=(TextView)findViewById(R.id.tx_cotent);
		
		String scanres=PubTool.GetValueofIntent(this, "SCAN");
		ACTION=PubTool.GetValueofIntent(this, "ACTION");
		if(ACTION==null)
			ACTION="";
		if(ACTION.equals("SY"))
		{
			titletv.setText("��Դ��ѯ");
			tt.setText("��Դ�룺");
		}
		else
		{
			titletv.setText("ɨ����");
		}
		if(scanres!=null)
		{
			if(ACTION.equals("SY"))
			{
				Serachcode(scanres);
			}
			else
			{
				String title="";
				tx_content.setText("���� :"+scanres);
				if(scanres.length()<=10)
					title=scanres;
				else
					title=scanres.substring(0, 10)+"...";
				tv_content.setText(title);
			}
		}
	}
	
	class onclick implements OnClickListener
	{

		@Override
		public void onClick(View v) 
		{
			switch (v.getId()) {
			case R.id.backbt:
				ScanResult.this.finish();
				break;
			case R.id.btrefresh:
				PubTool.GoTONactivity(ScanResult.this, CaptureActivity.class);
				ScanResult.this.finish();
				break;
			default:
				break;
			}
		}
		
	}
	
	void Serachcode(String scanres)
	{
		tv_content.setText(scanres);
		tx_content.setText("����Ʒ������XXX��˾����");
		SqlHelper sqlh=new SqlHelper(this);
		History his=new History();
		his.code=scanres;
		his.name="��Դ";
		his.data=PubTool.DateToString(new Date(), "yyyy-MM-dd");
		his.type=1;
		sqlh.InsertHis(his);
	}
}
