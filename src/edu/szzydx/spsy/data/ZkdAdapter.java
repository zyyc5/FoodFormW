package edu.szzydx.spsy.data;

import java.util.ArrayList;
import java.util.HashMap;

import edu.szzydx.spsy.R;
import edu.szzydx.spsy.ZXD_SP_list_Activtiy;
import edu.szzydx.spsy.bean.ZKDcom;
import edu.szzydx.spsy.tool.PubTool;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;


public class ZkdAdapter extends BaseAdapter
{

	Context ct;
	ArrayList<ZKDcom> list;
	int width;
	private LayoutInflater inflater;
	
	private View v1=null,v2=null;
	
	public ZkdAdapter(Context ct,ArrayList<ZKDcom> list,int width)
	{
		this.ct=ct;
		this.list=list;
		this.width=width;
		inflater = (LayoutInflater) ct.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		v1=inflater.inflate(R.layout.zhixiaoadapter1, null);
		v2=inflater.inflate(R.layout.zhixiaoadapter2, null);
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View cv, ViewGroup parent) 
	{
		ZKDcom zk=list.get(position);
		int w=(width-PubTool.dip2px(ct, 25))/3;
		if(v1==null)
			v1=inflater.inflate(R.layout.zhixiaoadapter1, null);
		if(v2==null)
			v2=inflater.inflate(R.layout.zhixiaoadapter2, null);

		if(position%2==0)
			cv=v1;
		else
			cv=v2;
		if(position==0)
			cv=inflater.inflate(R.layout.zhixiaoadapter1, null);
		
		
		ImageView im1=(ImageView)cv.findViewById(R.id.comimg1);
		ImageView im2=(ImageView)cv.findViewById(R.id.comimg2);
		ImageView im3=(ImageView)cv.findViewById(R.id.comimg3);
		im1.getLayoutParams().width=2*w;
		im1.getLayoutParams().height=2*w;
		im2.getLayoutParams().width=w;
		im2.getLayoutParams().height=w;
		im3.getLayoutParams().width=w;
		im3.getLayoutParams().height=w;
		im1.setImageBitmap(zk.pic);
		im2.setImageBitmap(zk.pic2);
		im3.setImageBitmap(zk.pic3);
		im1.setOnClickListener(new onclick(zk.id, zk.name));
		im2.setOnClickListener(new onclick(zk.id2, zk.name2));
		im3.setOnClickListener(new onclick(zk.id3, zk.name3));
		
		return cv;
	}

	
	class onclick implements OnClickListener
	{
		private String id;
		private String name;
		public onclick(String id,String name)
		{
			this.id=id;
			this.name=name;
		}
		@Override
		public void onClick(View v) 
		{
			HashMap<String, String> map=new HashMap<String, String>();
			map.put("id", id);
			map.put("name", name);
			PubTool.GoTONactivity(ct, ZXD_SP_list_Activtiy.class, map);
		}
		
	}
	
}
