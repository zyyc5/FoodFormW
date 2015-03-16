package edu.szzydx.spsy;

import java.util.ArrayList;

import edu.szzydx.spsy.bean.Baoguanglan;
import edu.szzydx.spsy.data.Bal_adapter;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class BaoGuangLan extends Activity
{

	ListView lv;
	ArrayList<Baoguanglan> list=new ArrayList<Baoguanglan>();
	Bal_adapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.baoguanglan);
		TextView titletv=(TextView)findViewById(R.id.titletv);
		titletv.setText("�ع���");
		Button backbt=(Button)findViewById(R.id.backbt);
		backbt.setVisibility(View.VISIBLE);
		backbt.setText(" \t����");
		backbt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) 
			{
				BaoGuangLan.this.finish();
			}
		});
		lv=(ListView)findViewById(R.id.lv_bgl);
		Initdata();
		adapter=new Bal_adapter(this, list);
		lv.setAdapter(adapter);
	}

	
	void Initdata()
	{
		Baoguanglan bal=new Baoguanglan();
		bal.Content="2005��4�£�����ʡ������ũҵ�ֲ鴦�ˡ��没��Ӱ����ũҩ�������飬�ü�ũҩ��������ũũ�����޹�˾���죬��ע�������к̼�ũ�����޹�˾�ܾ��������ɻ����л���ũ�ʾ�Ӫ�����ۣ�������5����(��)29����(��)1052��ũ���4373ĶС���ܺ������������գ�ֱ�Ӿ�����ʧ��170����Ԫ��Ŀǰ���������Ѷ��永�ľ�Ӫ�����¾���7�ˣ�ȡ������16�ˣ�1�����ӡ�";
		bal.title="����ʡ������ũҵ�ֲ鴦���没��Ӱ����ũҩ��";
		bal.date="2013-07-22";
		bal.YXFW="Ӱ�췶Χ������";
		bal.pic=BitmapFactory.decodeResource(getResources(), R.drawable.bgl_nor);
		for(int i=0;i<20;i++)
		{
			list.add(bal);
		}
	}
}
