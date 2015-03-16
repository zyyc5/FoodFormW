package edu.szzydx.spsy.data;

import java.util.ArrayList;

import edu.szzydx.spsy.R;
import edu.szzydx.spsy.bean.KuaiDi;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class Kuaidi_adapter extends BaseAdapter
{

	private Context ct;
	private ArrayList<KuaiDi> list;
	private LayoutInflater inflater;
	
	public Kuaidi_adapter(Context ct,ArrayList<KuaiDi> list)
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
		KuaiDi kd=list.get(arg0);
		if(cv==null)
		{			
			cv=inflater.inflate(R.layout.kuaidi_adapter, null);
		}
		TextView tv_date=(TextView)cv.findViewById(R.id.tv_date);
		TextView tv_detail=(TextView)cv.findViewById(R.id.tv_kd_detail);
		tv_date.setText(kd.time);
		tv_detail.setText(kd.context);
//		cv.setBackgroundResource(R.drawable.listselecter);
		return cv;
	}

}
