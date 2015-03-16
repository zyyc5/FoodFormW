package edu.szzydx.spsy;

import java.util.ArrayList;

import edu.szzydx.spsy.bean.ZKDcom;
import edu.szzydx.spsy.data.ZkdAdapter;
import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class ZhiXiaoDianActivity extends Activity
{
	ListView lv;
	ZkdAdapter adapter;
	ArrayList<ZKDcom> list=new ArrayList<ZKDcom>();
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.zhixiaoactivity);
		TextView tv_title=(TextView)findViewById(R.id.titletv);
		tv_title.setText("品牌精选");
		Button backbt=(Button)findViewById(R.id.backbt);
		WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
		int width = wm.getDefaultDisplay().getWidth();//屏幕宽度
		lv=(ListView)findViewById(R.id.comlist);
		Init();
		adapter=new ZkdAdapter(this, list, width);
		lv.setAdapter(adapter);
		
		backbt.setVisibility(View.VISIBLE);
		backbt.setText(" \t返回");
		backbt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) 
			{
				ZhiXiaoDianActivity.this.finish();
			}
		});
	}

	void Init()
	{
		ZKDcom zk=new ZKDcom();
		zk.id="1";
		zk.name="护肤品";
		zk.id2="1";
		zk.name2="护肤品";
		zk.id3="1";
		zk.name3="护肤品";
		zk.pic=BitmapFactory.decodeResource(getResources(), R.drawable.sb1);
		zk.pic2=BitmapFactory.decodeResource(getResources(), R.drawable.sb2);
		zk.pic3=BitmapFactory.decodeResource(getResources(), R.drawable.sb3);
		list.add(zk);
		zk=new ZKDcom();
		zk.id="1";
		zk.name="护肤品";
		zk.id2="1";
		zk.name2="护肤品";
		zk.id3="1";
		zk.name3="护肤品";
		zk.pic=BitmapFactory.decodeResource(getResources(), R.drawable.sb4);
		zk.pic2=BitmapFactory.decodeResource(getResources(), R.drawable.sb5);
		zk.pic3=BitmapFactory.decodeResource(getResources(), R.drawable.sb6);
		list.add(zk);
		zk=new ZKDcom();
		zk.id="1";
		zk.name="护肤品";
		zk.id2="1";
		zk.name2="护肤品";
		zk.id3="1";
		zk.name3="护肤品";
		zk.pic=BitmapFactory.decodeResource(getResources(), R.drawable.sb7);
		zk.pic2=BitmapFactory.decodeResource(getResources(), R.drawable.sb8);
		zk.pic3=BitmapFactory.decodeResource(getResources(), R.drawable.sb9);
		list.add(zk);
	}
	
}
