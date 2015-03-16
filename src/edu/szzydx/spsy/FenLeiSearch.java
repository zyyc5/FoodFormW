package edu.szzydx.spsy;

import java.util.ArrayList;
import java.util.HashMap;

import edu.szzydx.spsy.bean.FoodType;
import edu.szzydx.spsy.tool.PubTool;
import android.app.TabActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

public class FenLeiSearch extends TabActivity
{
	TabHost tabHost;
	Button bt1,bt2,bt3;
	ListView lv1,lv2;
	ArrayList<FoodType> list1=new ArrayList<FoodType>();
	ArrayList<FoodType> list2=new ArrayList<FoodType>();
	Adapter adapter1;
	Adapter adapter2;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		
		tabHost = getTabHost();
        
        LayoutInflater.from(this).inflate(R.layout.flsearch, tabHost.getTabContentView(), true);

        TextView titletv=(TextView)findViewById(R.id.titletv);
		titletv.setText("分类搜索");
		Button backbt=(Button)findViewById(R.id.backbt);
		backbt.setVisibility(View.VISIBLE);
		backbt.setText(" \t返回");
		backbt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) 
			{
				FenLeiSearch.this.finish();
			}
		});
        
        bt1=(Button)findViewById(R.id.tb1);
        bt2=(Button)findViewById(R.id.tb2);
        lv1=(ListView)findViewById(R.id.lv_1);
        lv2=(ListView)findViewById(R.id.lv_2);
        
//        bt3=(Button)findViewById(R.id.tb3);
        InitTab();
        tabHost.getTabWidget().setVisibility(View.GONE);
        bt1.setOnClickListener(new click());
        bt2.setOnClickListener(new click());
//        bt3.setOnClickListener(new click());
        InitList();
        adapter1=new Adapter(list1);
        adapter2=new Adapter(list2);
        lv1.setAdapter(adapter1);
        lv2.setAdapter(adapter2);
	}

	void InitList()
	{
		FoodType type=new FoodType();
		type.Name="饼干";
		list1.add(type);
		type=new FoodType();
		type.Name="糕点";
		list1.add(type);
		type=new FoodType();
		type.Name="糖果";
		list1.add(type);
		type=new FoodType();
		type.Name="水果";
		list1.add(type);
		type=new FoodType();
		type.Name="零食";
		list1.add(type);
		
		type.Name="酒水";
		list2.add(type);
		type=new FoodType();
		type.Name="乳饮料";
		list2.add(type);
		type=new FoodType();
		type.Name="营养品";
		list2.add(type);
		type=new FoodType();
		type.Name="咖啡冲饮";
		list2.add(type);
		type=new FoodType();
		type.Name="茶";
		list2.add(type);
		type=new FoodType();
		type.Name="矿泉水";
		list2.add(type);
	}
	
	void InitTab()
	{
		tabHost.addTab(tabHost.newTabSpec("tab1")
                .setIndicator("tab1")
                .setContent(R.id.lv_1));
        tabHost.addTab(tabHost.newTabSpec("tab2")
                .setIndicator("tab2")
                .setContent(R.id.lv_2));
        tabHost.addTab(tabHost.newTabSpec("tab3")
                .setIndicator("tab3")
                .setContent(R.id.lv_3));
	}
	
	class click implements OnClickListener
    {

		@Override
		public void onClick(View v) 
		{
			View[] vs=new View[]{bt1,bt2};
			v.setBackgroundResource(R.drawable.bg_hor_right_sel);
			for(View vv : vs)
			{
				if(vv!=v)
					vv.setBackgroundResource(R.drawable.bg_hor_right_nor);
			}
			String tag=v.getTag().toString();
			tabHost.setCurrentTabByTag(tag);
		}
    	
    }


	class Adapter extends BaseAdapter
	{

		ArrayList<FoodType> list;
		private LayoutInflater inflater;
		
		public Adapter(ArrayList<FoodType> list)
		{
			this.list=list;
			inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}
		
		@Override
		public int getCount() 
		{
			return list.size();
		}

		@Override
		public Object getItem(int arg0) 
		{
			return list.get(arg0);
		}

		@Override
		public long getItemId(int position) 
		{
			return position;
		}

		@Override
		public View getView(int position, View cv, ViewGroup parent) 
		{
			FoodType f=list.get(position);
			if(cv==null)
			{
				cv=inflater.inflate(R.layout.safecs, null);
			}
			TextView tv_title=(TextView)cv.findViewById(R.id.tv_title_st);
			TextView tv_date=(TextView)cv.findViewById(R.id.tv_date_safe);
			tv_title.setText(f.Name);
			tv_date.setText("");
			cv.setOnClickListener(new click(f));
			return cv;
		}

		class click implements OnClickListener
		{
			FoodType f;
			public click(FoodType f)
			{
				this.f=f;
			}
			@Override
			public void onClick(View v) 
			{
				HashMap<String, String> map=new HashMap<String, String>();
				map.put("ID", f.ID);
				PubTool.GoTONactivity(FenLeiSearch.this, FLSearch_SP_List.class);
			}
			
		}
	}

}
