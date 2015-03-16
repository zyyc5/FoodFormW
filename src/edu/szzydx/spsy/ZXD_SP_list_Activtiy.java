package edu.szzydx.spsy;

import java.util.ArrayList;

import edu.szzydx.spsy.bean.ZXD_list_sp;
import edu.szzydx.spsy.data.ZXD_sp_adapter;
import edu.szzydx.spsy.tool.PubTool;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

public class ZXD_SP_list_Activtiy extends Activity
{

	GridView gv;
	ArrayList<ZXD_list_sp> list=new ArrayList<ZXD_list_sp>();
	ZXD_sp_adapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.zxd_splist);
		TextView tv_title=(TextView)findViewById(R.id.titletv);
		String name=PubTool.GetValueofIntent(this, "name");
		String id=PubTool.GetValueofIntent(this, "id");
		Button backbt=(Button)findViewById(R.id.backbt);
		if(name!=null)
			tv_title.setText(name);
		
		gv=(GridView)findViewById(R.id.grid_sp);
		adapter=new ZXD_sp_adapter(this, list);
		Init2();
		gv.setAdapter(adapter);
		backbt.setVisibility(View.VISIBLE);
		backbt.setText(" \t返回");
		backbt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) 
			{
				ZXD_SP_list_Activtiy.this.finish();
			}
		});
	}

	void Init()
	{
		ZXD_list_sp sp=new ZXD_list_sp();
		sp.id="1";
		sp.name="护肤品";
		sp.price="316.00";
		sp.price2="80.00";
		sp.img=BitmapFactory.decodeResource(getResources(), R.drawable.sb1);
		list.add(sp);
		list.add(sp);
		sp=new ZXD_list_sp();
		sp.id="2";
		sp.name="CoCok";
		sp.price="230.00";
		sp.price2="99.00";
		sp.img=BitmapFactory.decodeResource(getResources(), R.drawable.sb3);
		list.add(sp);
		list.add(sp);
		list.add(sp);
		list.add(sp);
	}
	void Init2()
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

		adapter.notifyDataSetChanged();
	}
	
}
