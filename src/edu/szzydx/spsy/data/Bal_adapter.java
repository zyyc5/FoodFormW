package edu.szzydx.spsy.data;

import java.util.ArrayList;

import edu.szzydx.spsy.BaoGuangLan_info;
import edu.szzydx.spsy.R;
import edu.szzydx.spsy.bean.Baoguanglan;
import edu.szzydx.spsy.tool.PubTool;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Bal_adapter extends BaseAdapter
{

	private Context ct;
	private ArrayList<Baoguanglan> list;
	private LayoutInflater inflater;
	viewcach cach=new viewcach();
	
	public Bal_adapter(Context ct,ArrayList<Baoguanglan> list)
	{
		this.ct=ct;
		this.list=list;
		inflater = (LayoutInflater) ct.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
		Baoguanglan kd=list.get(arg0);		
		if(cv==null)
		{			
			cv=inflater.inflate(R.layout.bgl_adapter, null);
			cach.im=(ImageView)cv.findViewById(R.id.img_title_bgl);
			cach.tv_yxfw=(TextView)cv.findViewById(R.id.tv_yxfw);
			cach.tv_title_bgl=(TextView)cv.findViewById(R.id.tv_title_t);
			cach.tv_date=(TextView)cv.findViewById(R.id.tv_date_bgl);
			
		}
		cach.im.setImageBitmap(kd.pic);
		cach.tv_date.setText(kd.date);
		cach.tv_yxfw.setText(kd.YXFW);
		cach.tv_title_bgl.setText(kd.title);
		cv.setOnClickListener(new click(kd));
		return cv;
	}
	
	class viewcach
	{
		public TextView tv_yxfw,tv_title_bgl,tv_date;
		public ImageView im;
	}
	
	class click implements OnClickListener
	{
		Baoguanglan kd;
		public click(Baoguanglan kd)
		{
			this.kd=kd;
		}
		@Override
		public void onClick(View v) 
		{
			PubStatic.kd=kd;
			PubTool.GoTONactivity(ct, BaoGuangLan_info.class);			
		}
		
	}

}
