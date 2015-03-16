package edu.szzydx.spsy;

import java.util.ArrayList;
import edu.szzydx.spsy.bean.Safe;
import edu.szzydx.spsy.tool.PubTool;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class SafeFood extends Activity
{

	ListView lv;
	ArrayList<Safe> list=new ArrayList<Safe>();
	Adapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.baoguanglan);
		TextView titletv=(TextView)findViewById(R.id.titletv);
		titletv.setText("安全常识");
		Button backbt=(Button)findViewById(R.id.backbt);
		backbt.setVisibility(View.VISIBLE);
		backbt.setText(" \t返回");
		backbt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) 
			{
				SafeFood.this.finish();
			}
		});
		InitData();
		lv=(ListView)findViewById(R.id.lv_bgl);
		lv.setDivider(new ColorDrawable(R.color.transparent));
		adapter=new Adapter(list);
		lv.setAdapter(adapter);
		
	}

	void InitData()
	{
		Safe f=new Safe();
		f.Name="几种假冒伪劣食品的鉴别";
		f.date="2013-09-03";
		String content="\n化肥豆芽：采用过化肥催长的豆芽，根短或无根，豆芽发蓝。如果将豆芽折断，断面会有水分冒出，有的还残留有化肥的气味。"

				+"\n\n干豆腐掺豆渣或玉米粉：表面感觉粗糙，光泽差，如轻轻折叠，无弹性。豆腐的折裂面呈不规则的锯齿状，仔细查看可见粗糙物，还可看见玉米渣粒。"

  				+"  \n\n油豆腐充水：充水的油豆腐油腻感差，表面粗糙。有的褪色，边色发白。用手一捻，有水分滴落。优质的油豆腐捻后很快恢复原来的形状，充水的油豆腐用力一捻就容易变烂，不能恢复原来的形状。"

    			+"\n\n鲜鱼灌水：表现为肚子大。如果在腹部灌水，可将鱼提起，就会发现鱼肛门下方两侧凸出下垂，用小手指插入肛门，旋转两下，手指抽出，水就会立即流出。"

    			+"\n\n假辣椒粉：假辣椒粉常以小包装出售，色泽淡红，辣椒子较少。如用热水冲泡，优质辣椒粉呈土黄色，可看见有很多的辣椒皮块和辣椒子；假辣椒粉则呈淡红色，多用糠粉染红，加少量的辣椒末混合而成。";
		f.Content=content;
		list.add(f);
		f=new Safe();
		f.date="2013-8-31";
		f.Name="教你辨别染色辣椒";
		f.Content=content;
		list.add(f);
		f=new Safe();
		f.date="2013-8-31";
		f.Name="奶粉里的肉毒杆菌哪来的？";
		f.Content=content;
		list.add(f);
		f=new Safe();
		f.date="2013-8-31";
		f.Name="吃完火锅一身味是添加剂惹的祸吗";
		f.Content=content;
		list.add(f);
		f=new Safe();
		f.date="2013-8-31";
		f.Name="味精可以放心食用吗";
		f.Content=content;
		list.add(f);
		for(int i=0;i<10;i++)
		{
			list.add(f);
		}
	}
	
	class Adapter extends BaseAdapter
	{

		ArrayList<Safe> list;
		private LayoutInflater inflater;
		viewcach cach=new viewcach();
		public Adapter(ArrayList<Safe> list)
		{
			this.list=list;
			inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}
		
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list.size();
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return list.get(arg0);
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return arg0;
		}

		@Override
		public View getView(int arg0, View cv, ViewGroup arg2) 
		{
			Safe f=list.get(arg0);
			if(cv==null)
			{
				cv=inflater.inflate(R.layout.safecs, null);
//				cach.tv_title=(TextView)cv.findViewById(R.id.tv_title_st);
//				cach.tv_date=(TextView)cv.findViewById(R.id.tv_date_safe);
			}
			TextView tv_title=(TextView)cv.findViewById(R.id.tv_title_st);
			TextView tv_date=(TextView)cv.findViewById(R.id.tv_date_safe);
			tv_title.setText(f.Name);
			tv_date.setText(f.date);
			cv.setOnClickListener(new clck(f));
			return cv;
		}
		
		class clck implements OnClickListener
		{
			Safe ff;
			
			public clck(Safe ff)
			{
				this.ff=ff;
			}
			@Override
			public void onClick(View v) 
			{
				PubTool.obj=ff;
				PubTool.GoTONactivity(SafeFood.this, SafeFood_info_Activity.class);
			}
		}
	}
	
	
	
	class viewcach
	{
		public TextView tv_title,tv_date;
	}
}
