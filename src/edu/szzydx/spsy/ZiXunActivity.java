package edu.szzydx.spsy;

import java.util.ArrayList;

import edu.szzydx.spsy.bean.Baoguanglan;
import edu.szzydx.spsy.data.Bal_adapter;
import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

public class ZiXunActivity extends Activity
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
		titletv.setText("资 讯");
//		Button backbt=(Button)findViewById(R.id.backbt);
//		backbt.setVisibility(View.VISIBLE);
//		backbt.setText(" \t返回");
//		backbt.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) 
//			{
//				ZiXunActivity.this.finish();
//			}
//		});
		lv=(ListView)findViewById(R.id.lv_bgl);
		Initdata();
		adapter=new Bal_adapter(this, list);
		lv.setAdapter(adapter);
	}

	void Initdata()
	{
		Baoguanglan bal=new Baoguanglan();
		String content="2013年8月2日下午，乳业巨头恒天然集团向新西兰初级产业部通报旗下部分产品可能含有肉毒杆菌毒素，随后，初级产业部将情况通报主要海外市场的政府监管机构，并加紧调查核实。\n\n"

				+"2013年8月3日，新西兰初级产业部对外宣布该事件，声称受污染的产品可能被用于婴儿配方奶粉和运动饮料的生产。"

				+"\n\n恒天然集团新西兰奶制品公司执行董事加里罗马诺说，有3批浓缩乳清蛋白出现质量问题，这些产品是去年5月在新西兰本地一家工厂生产的，涉嫌被污染的产品总量为38吨。污染源是该公司在北岛怀卡托地区豪塔普工厂的一根受污染的管道。恒天然向其8家客户（包括中国3家）提醒产品肉毒杆菌问题.";
		bal.Content= content;//"2005年4月，江苏省淮安市农业局查处了“虫病无影”假农药案。经查，该假农药由宜兴益农农化有限公司制造，标注“常州市禾佳农化有限公司总经销”，由淮安市华达农资经营部销售，结果造成5个县(区)29个乡(镇)1052户农民的4373亩小麦受害减产甚至绝收，直接经济损失达170余万元。目前公安机关已对涉案的经营者刑事拘留7人，取保候审16人，1人在逃。";
		bal.title="乳业巨头恒天然部分产品检出肉毒";
		bal.date="2013-07-22";
		bal.YXFW="";
		bal.pic=BitmapFactory.decodeResource(getResources(), R.drawable.htr);
		for(int i=0;i<20;i++)
		{
			list.add(bal);
		}
	}
	
}
