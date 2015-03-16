package edu.szzydx.spsy;

import java.util.ArrayList;

import edu.szzydx.spsy.bean.Baoguanglan;
import edu.szzydx.spsy.data.Bal_adapter;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class BaoGuangLan extends Activity
{

	ListView lv;
	ArrayList<Baoguanglan> list=new ArrayList<Baoguanglan>();
	Bal_adapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.baoguanglan);
		TextView titletv=(TextView)findViewById(R.id.titletv);
		titletv.setText("曝光栏");
		Button backbt=(Button)findViewById(R.id.backbt);
		backbt.setVisibility(View.VISIBLE);
		backbt.setText(" \t返回");
		backbt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) 
			{
				BaoGuangLan.this.finish();
			}
		});
		lv=(ListView)findViewById(R.id.lv_bgl);
		Initdata();
		adapter=new Bal_adapter(this, list);
		lv.setAdapter(adapter);
	}

	
	void Initdata()
	{
		Baoguanglan bal=new Baoguanglan();
		bal.Content="2005年4月，江苏省淮安市农业局查处了“虫病无影”假农药案。经查，该假农药由宜兴益农农化有限公司制造，标注“常州市禾佳农化有限公司总经销”，由淮安市华达农资经营部销售，结果造成5个县(区)29个乡(镇)1052户农民的4373亩小麦受害减产甚至绝收，直接经济损失达170余万元。目前公安机关已对涉案的经营者刑事拘留7人，取保候审16人，1人在逃。";
		bal.title="江苏省淮安市农业局查处“虫病无影”假农药案";
		bal.date="2013-07-22";
		bal.YXFW="影响范围：江苏";
		bal.pic=BitmapFactory.decodeResource(getResources(), R.drawable.bgl_nor);
		for(int i=0;i<20;i++)
		{
			list.add(bal);
		}
	}
}
