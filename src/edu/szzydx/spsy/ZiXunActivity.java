package edu.szzydx.spsy;

import java.util.ArrayList;

import edu.szzydx.spsy.bean.Baoguanglan;
import edu.szzydx.spsy.data.Bal_adapter;
import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

public class ZiXunActivity extends Activity
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
		titletv.setText("�� Ѷ");
//		Button backbt=(Button)findViewById(R.id.backbt);
//		backbt.setVisibility(View.VISIBLE);
//		backbt.setText(" \t����");
//		backbt.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) 
//			{
//				ZiXunActivity.this.finish();
//			}
//		});
		lv=(ListView)findViewById(R.id.lv_bgl);
		Initdata();
		adapter=new Bal_adapter(this, list);
		lv.setAdapter(adapter);
	}

	void Initdata()
	{
		Baoguanglan bal=new Baoguanglan();
		String content="2013��8��2�����磬��ҵ��ͷ����Ȼ������������������ҵ��ͨ�����²��ֲ�Ʒ���ܺ����ⶾ�˾����أ���󣬳�����ҵ�������ͨ����Ҫ�����г���������ܻ��������ӽ������ʵ��\n\n"

				+"2013��8��3�գ�������������ҵ�������������¼�����������Ⱦ�Ĳ�Ʒ���ܱ�����Ӥ���䷽�̷ۺ��˶����ϵ�������"

				+"\n\n����Ȼ��������������Ʒ��˾ִ�ж��¼�������ŵ˵����3��Ũ�����嵰�׳����������⣬��Щ��Ʒ��ȥ��5��������������һ�ҹ��������ģ����ӱ���Ⱦ�Ĳ�Ʒ����Ϊ38�֡���ȾԴ�Ǹù�˾�ڱ��������е��������չ�����һ������Ⱦ�Ĺܵ�������Ȼ����8�ҿͻ��������й�3�ң����Ѳ�Ʒ�ⶾ�˾�����.";
		bal.Content= content;//"2005��4�£�����ʡ������ũҵ�ֲ鴦�ˡ��没��Ӱ����ũҩ�������飬�ü�ũҩ��������ũũ�����޹�˾���죬��ע�������к̼�ũ�����޹�˾�ܾ��������ɻ����л���ũ�ʾ�Ӫ�����ۣ�������5����(��)29����(��)1052��ũ���4373ĶС���ܺ������������գ�ֱ�Ӿ�����ʧ��170����Ԫ��Ŀǰ���������Ѷ��永�ľ�Ӫ�����¾���7�ˣ�ȡ������16�ˣ�1�����ӡ�";
		bal.title="��ҵ��ͷ����Ȼ���ֲ�Ʒ����ⶾ";
		bal.date="2013-07-22";
		bal.YXFW="";
		bal.pic=BitmapFactory.decodeResource(getResources(), R.drawable.htr);
		for(int i=0;i<20;i++)
		{
			list.add(bal);
		}
	}
	
}
