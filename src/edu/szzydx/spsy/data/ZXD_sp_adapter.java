package edu.szzydx.spsy.data;

import java.security.PublicKey;
import java.util.ArrayList;

import edu.szzydx.spsy.R;
import edu.szzydx.spsy.Sp_info_Activity;
import edu.szzydx.spsy.bean.ZXD_list_sp;
import edu.szzydx.spsy.tool.PubTool;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ZXD_sp_adapter extends BaseAdapter
{

	ArrayList<ZXD_list_sp> list;
	Context ct;
	private LayoutInflater inflater;
	public ZXD_sp_adapter(Context ct,ArrayList<ZXD_list_sp> list)
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
		ZXD_list_sp sp=list.get(position);
		if(cv==null)
		{
			cv=inflater.inflate(R.layout.zxd_splist_adapter, null);
			viewcach.im=(ImageView)cv.findViewById(R.id.imageView1);
			viewcach.tv_name=(TextView)cv.findViewById(R.id.tv_sp_name);
			viewcach.tv_price=(TextView)cv.findViewById(R.id.tv_sp_jg);
			viewcach.tv_SJ_price=(TextView)cv.findViewById(R.id.tv_sp_sjjg);
			viewcach.v=cv;
		}
//		ImageView im=(ImageView)cv.findViewById(R.id.imageView1);
//		TextView tv_name=(TextView)cv.findViewById(R.id.tv_sp_name);
//		TextView tv_price=(TextView)cv.findViewById(R.id.tv_sp_jg);
//		TextView tv_SJ_price=(TextView)cv.findViewById(R.id.tv_sp_sjjg);
		final View v=viewcach.v;
		viewcach.tv_name.setText(sp.name);
		viewcach.tv_price.setText("¥ "+sp.price);
		viewcach.tv_SJ_price.setText("¥ "+sp.price2);
		viewcach.im.setImageBitmap(sp.img);
		
		
		viewcach.v.setOnClickListener(new click(sp));
		viewcach.im.setOnClickListener(new click(sp));
		return cv;
	}

	static class viewcach
	{
		public static ImageView im;
		public static TextView tv_name,tv_price,tv_SJ_price;
		public static View v;
	}
	
	class click implements OnClickListener
	{
		ZXD_list_sp sp;
		public click(ZXD_list_sp sp)
		{
			this.sp=sp;
		}
		@Override
		public void onClick(View v) 
		{
			PubTool.sp=this.sp;
			PubTool.GoTONactivity(ct, Sp_info_Activity.class );
		}		
	}
	
}
