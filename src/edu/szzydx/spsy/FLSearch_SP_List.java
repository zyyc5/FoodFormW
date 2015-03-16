package edu.szzydx.spsy;

import java.util.ArrayList;
import edu.szzydx.spsy.bean.ZXD_list_sp;
import edu.szzydx.spsy.tool.PubTool;
import android.app.Activity;
import android.content.Context;
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

public class FLSearch_SP_List extends Activity
{

	ListView lv;
	ArrayList<ZXD_list_sp> list=new ArrayList<ZXD_list_sp>();
	Adapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.baoguanglan);
		TextView titletv=(TextView)findViewById(R.id.titletv);
		titletv.setText("食品列表");
		Button backbt=(Button)findViewById(R.id.backbt);
		backbt.setVisibility(View.VISIBLE);
		backbt.setText(" \t返回");
		backbt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) 
			{
				FLSearch_SP_List.this.finish();
			}
		});
		
		lv=(ListView)findViewById(R.id.lv_bgl);
		adapter=new Adapter(list);
		lv.setAdapter(adapter);
		Init();
	}

	void Init()
	{
		ZXD_list_sp sp=new ZXD_list_sp();
		sp.id="1";
		sp.name="KOKO豆";
		sp.price="16.00";
		sp.price2="8.00";
		sp.img=BitmapFactory.decodeResource(getResources(), R.drawable.spp1);
		list.add(sp);
		
		//list.add(sp);
		sp=new ZXD_list_sp();
		sp.id="2";
		sp.name="Stram巧克力";
		sp.price="230.00";
		sp.price2="99.00";
		sp.img=BitmapFactory.decodeResource(getResources(), R.drawable.spp2);
		list.add(sp);
		
		sp=new ZXD_list_sp();
		sp.id="2";
		sp.name="新赵都冷鲜肉";
		sp.price="20.00";
		sp.price2="9.00";
		sp.img=BitmapFactory.decodeResource(getResources(), R.drawable.spp3);
		
		list.add(sp);
		sp=new ZXD_list_sp();
		sp.id="2";
		sp.name="法国甜甜圈";
		sp.price="30.00";
		sp.price2="19.00";
		sp.img=BitmapFactory.decodeResource(getResources(), R.drawable.spp4);
		list.add(sp);
		
		sp=new ZXD_list_sp();
		sp.id="2";
		sp.name="核桃粉";
		sp.price="23.00";
		sp.price2="9.90";
		sp.img=BitmapFactory.decodeResource(getResources(), R.drawable.spp5);
		list.add(sp);
		
		sp=new ZXD_list_sp();
		sp.id="2";
		sp.name="杨槐花";
		sp.price="250.00";
		sp.price2="99.00";
		sp.img=BitmapFactory.decodeResource(getResources(), R.drawable.spp6);
		list.add(sp);
		list.addAll(list);
		adapter.notifyDataSetChanged();
	}
	
	
	class Adapter extends BaseAdapter
	{

		ArrayList<ZXD_list_sp> list;
		private LayoutInflater inflater;
		public Adapter(ArrayList<ZXD_list_sp> list)
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
			ZXD_list_sp kd=list.get(position);
			if(cv==null)
			{
				cv=inflater.inflate(R.layout.bgl_adapter, null);
			}
			final ImageView im=(ImageView)cv.findViewById(R.id.img_title_bgl);
			final TextView  tv_yxfw=(TextView)cv.findViewById(R.id.tv_yxfw);
			final TextView tv_title_bgl=(TextView)cv.findViewById(R.id.tv_title_t);
			final TextView tv_date=(TextView)cv.findViewById(R.id.tv_date_bgl);
			
			im.setImageBitmap(kd.img);
			tv_date.setText("");
			tv_yxfw.setText("");
			tv_title_bgl.setText(kd.name);
			cv.setOnClickListener(new click(kd));
			return cv;
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
				PubTool.GoTONactivity(FLSearch_SP_List.this, Sp_info_Activity.class );
			}		
		}
	}
	
}
