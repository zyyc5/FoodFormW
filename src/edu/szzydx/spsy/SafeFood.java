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
		titletv.setText("��ȫ��ʶ");
		Button backbt=(Button)findViewById(R.id.backbt);
		backbt.setVisibility(View.VISIBLE);
		backbt.setText(" \t����");
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
		f.Name="���ּ�ðα��ʳƷ�ļ���";
		f.date="2013-09-03";
		String content="\n���ʶ�ѿ�����ù����ʴ߳��Ķ�ѿ�����̻��޸�����ѿ�������������ѿ�۶ϣ��������ˮ��ð�����еĻ������л��ʵ���ζ��"

				+"\n\n�ɶ��������������׷ۣ�����о��ֲڣ������������۵����޵��ԡ�������������ʲ�����ľ��״����ϸ�鿴�ɼ��ֲ�����ɿ�������������"

  				+"  \n\n�Ͷ�����ˮ����ˮ���Ͷ�������в����ֲڡ��е���ɫ����ɫ���ס�����һ����ˮ�ֵ��䡣���ʵ��Ͷ������ܿ�ָ�ԭ������״����ˮ���Ͷ�������һ������ױ��ã����ָܻ�ԭ������״��"

    			+"\n\n�����ˮ������Ϊ���Ӵ�����ڸ�����ˮ���ɽ������𣬾ͻᷢ��������·�����͹���´�����С��ָ������ţ���ת���£���ָ�����ˮ�ͻ�����������"

    			+"\n\n�������ۣ��������۳���С��װ���ۣ�ɫ�󵭺죬�����ӽ��١�������ˮ���ݣ����������۳�����ɫ���ɿ����кܶ������Ƥ��������ӣ�����������ʵ���ɫ�����ÿ���Ⱦ�죬������������ĩ��϶��ɡ�";
		f.Content=content;
		list.add(f);
		f=new Safe();
		f.date="2013-8-31";
		f.Name="������Ⱦɫ����";
		f.Content=content;
		list.add(f);
		f=new Safe();
		f.date="2013-8-31";
		f.Name="�̷�����ⶾ�˾������ģ�";
		f.Content=content;
		list.add(f);
		f=new Safe();
		f.date="2013-8-31";
		f.Name="������һ��ζ����Ӽ��ǵĻ���";
		f.Content=content;
		list.add(f);
		f=new Safe();
		f.date="2013-8-31";
		f.Name="ζ�����Է���ʳ����";
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
