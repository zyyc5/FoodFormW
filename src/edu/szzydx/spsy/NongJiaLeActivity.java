package edu.szzydx.spsy;

import java.util.ArrayList;
import java.util.HashMap;
import edu.szzydx.spsy.bean.NongJia;
import edu.szzydx.spsy.tool.PubTool;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class NongJiaLeActivity extends Activity
{

	ListView lv;	
	
	ArrayList<NongJia> list=new ArrayList<NongJia>();
	Adapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.nongjiale);
		lv=(ListView)findViewById(R.id.njl_list);
		TextView titletv=(TextView)findViewById(R.id.titletv);
		titletv.setText("农家乐");
		Button backbt=(Button)findViewById(R.id.backbt);
		backbt.setVisibility(View.VISIBLE);
		backbt.setText(" \t返回");
		backbt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) 
			{
				NongJiaLeActivity.this.finish();
			}
		});
		Init();
		adapter=new Adapter(list);
		lv.setAdapter(adapter);
	}

	void Init()
	{
		ArrayList<NongJia> temp=new ArrayList<NongJia>();
		NongJia nj=new NongJia();
		Bitmap logo1=BitmapFactory.decodeResource(getResources(), R.drawable.sb1);
		nj.logo=logo1;
		nj.Name="FTD农庄";
		temp.add(nj);
		Bitmap logo2=BitmapFactory.decodeResource(getResources(), R.drawable.sb2);
		nj=new NongJia();
		nj.logo=logo2;
		nj.Name="Cooper 农家乐";
		temp.add(nj);
		Bitmap logo3=BitmapFactory.decodeResource(getResources(), R.drawable.sb6);
		nj=new NongJia();
		nj.logo=logo3;
		nj.Name="西山番茄园";
		temp.add(nj);

		list.clear();
		list.addAll(temp);
		list.addAll(temp);
		list.addAll(temp);
		list.addAll(temp);
	}

	class Adapter extends BaseAdapter
	{
		ArrayList<NongJia> list;
		private LayoutInflater inflater;
		viewcach cach=new viewcach();
		public Adapter(ArrayList<NongJia> list)
		{
			this.list=list;
			inflater = (LayoutInflater) NongJiaLeActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
		public long getItemId(int arg0) 
		{
			return arg0;
		}

		@Override
		public View getView(int arg0, View cv, ViewGroup arg2) 
		{
			final NongJia nj=list.get(arg0);
			if(cv==null)
			{
				cv=inflater.inflate(R.layout.bgl_adapter, null);
				
			}
			ImageView im=(ImageView)cv.findViewById(R.id.img_title_bgl);
			TextView tv_yxfw=(TextView)cv.findViewById(R.id.tv_yxfw);
			TextView tv_title_bgl=(TextView)cv.findViewById(R.id.tv_title_t);
			TextView tv_date=(TextView)cv.findViewById(R.id.tv_date_bgl);
			if(nj.logo!=null)
				im.setImageBitmap(nj.logo);
			else
				im.setImageResource(R.drawable.ic_launcher);
			tv_date.setText("");
			tv_yxfw.setText("");
			tv_title_bgl.setText(nj.Name);
			cv.setOnClickListener(new click(nj));
			return cv;
		}
		
		class viewcach
		{
			public TextView tv_yxfw,tv_title_bgl,tv_date;
			public ImageView im;
		}
		 
		class click implements OnClickListener
		{
			NongJia nj;
			
			public click(NongJia nj)
			{
				this.nj=nj;
			}
			@Override
			public void onClick(View v) 
			{
				
				HashMap<String, String> map=new HashMap<String, String>();
				map.put("name", nj.Name);
				PubTool.GoTONactivity(NongJiaLeActivity.this, ZXD_SP_list_Activtiy.class,map);
			}		
		}
	}
	
}
