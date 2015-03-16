package edu.szzydx.spsy;

import java.util.ArrayList;
import java.util.HashMap;

import edu.szzydx.spsy.bean.ZXD_list_sp;
import edu.szzydx.spsy.tool.FileUtils;
import edu.szzydx.spsy.tool.NetWork;
import edu.szzydx.spsy.tool.PubTool;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.ImageView.ScaleType;

public class Sp_info_Activity extends Activity
{

	Gallery gally;
	TextView tv_price,tv_desc;
	RatingBar bar;
	ZXD_list_sp sp;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sp_info);
		Button backbt=(Button)findViewById(R.id.backbt);
		backbt.setVisibility(View.VISIBLE);
		backbt.setText(" \t·µ»Ø");
		backbt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) 
			{
				Sp_info_Activity.this.finish();
			}
		});
		TextView title_tv=(TextView)findViewById(R.id.titletv);
		tv_desc=(TextView)findViewById(R.id.sp_desc);
		tv_price=(TextView)findViewById(R.id.tv_price);
		bar=(RatingBar)findViewById(R.id.ratingBar1);
		
		gally=(Gallery)findViewById(R.id.sp_pics);
		 sp=PubTool.sp;
		PubTool.sp=null;
		title_tv.setText(sp.name);
		tv_desc.setText(sp.price2);
		tv_price.setText("£¤ "+sp.price);
		bar.setNumStars(5);
		bar.setRating(Integer.parseInt("3"));
		ArrayList<Bitmap> blist=new ArrayList<Bitmap>();
		blist.add(sp.img);
		gally.setAdapter(new Badapter(blist));
		
//		 
	}
	
	

	
	class Badapter extends BaseAdapter
	{

		ArrayList<Bitmap> list;
		public Badapter(ArrayList<Bitmap> list)
		{
			this.list=list;
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
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) 
		{
			Bitmap bitmap=list.get(position);
			ImageView iv=new ImageView(Sp_info_Activity.this);
			iv.setScaleType(ImageView.ScaleType.FIT_CENTER);
      	  	iv.setAdjustViewBounds(true);
      	  	iv.setScaleType(ScaleType.FIT_XY);
			iv.setLayoutParams(new Gallery.LayoutParams(-2, -1));
			iv.setImageResource(R.drawable.ic_launcher);
			iv.setImageBitmap(bitmap);
			return iv;
		}
		
	}
	
	class Adapter extends BaseAdapter
	{
		ArrayList<String> list;
		Context ct;
		MessageHandler messageHandler;
		public Adapter(ArrayList<String> list,Context ct)
		{
			this.list=list;
			this.ct=ct;
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
		public View getView(int position, View convertView, ViewGroup parent) 
		{
			ImageView iv=new ImageView(ct);
			iv.setScaleType(ImageView.ScaleType.FIT_CENTER);
      	  	iv.setAdjustViewBounds(true);
      	  	iv.setScaleType(ScaleType.FIT_XY);
			iv.setLayoutParams(new Gallery.LayoutParams(300, 400));
			iv.setImageResource(R.drawable.ic_launcher);
			try
			{
				if(!list.get(position).equals(""))
				{
					String url="http://qiufun.sinaapp.com/scxm/showpic.php?name="+list.get(position);
					final Looper looper = Looper.myLooper();
					loadordown(url, iv, looper);
				}
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			return iv;
		}
		
		private String getcode(String url,String name)
		{
			String rt="";
			String[] ss=url.split("\\?");
			Log.i("size", ss.length+""+ss[0]+ss[1]);
			if(ss.length>=1)
			{
				String kvs=ss[1];
				String[] kvss=kvs.split("\\&");
				Log.i("kvs", kvss.length+"");
				if(kvss.length>0)
				{
					for(String kv : kvss)
					{
						Log.i("kv", kv);
						String[] kk=kv.split("\\=");
						if(kk.length==2)
						{
							Log.i("kv", kk[0]);
							if(kk[0].equals(name))
								rt=kk[1];
						}
					}
				}
			}
			if(rt=="")
			{
				rt=url.replace("/", "_");
		    	rt=rt.replace(":", "");
		    	rt=rt.replace("\\", "");
		    	rt=rt.replace(".", "");
			}
			rt=rt+".jpg";
//			System.out.println(rt);
			return rt;
		}
		
		public void loadordown(final String url,final ImageView img,final Looper looper)
	    {
	    	String locaurl=getcode(url, "name");

	    	FileUtils f=new FileUtils();
	    	
	    	if(f.isFileExist(locaurl))
	    	{
	    		Bitmap bit=f.readbitmap(locaurl);
	    		img.setImageBitmap(bit);
	    	}
	    	else
	    	{
	    		messageHandler = new MessageHandler(looper,img,locaurl);
	    		new Thread(new downloadRun(url)).start();
	    	}
	    }
		
		class downloadRun implements Runnable
		{  
	    	
	   	 	final String url;
	   	 	public downloadRun(final String url)
	        {
	   	 		this.url=url;
	        }
		   	@Override  
		   	public void run() 
		   	{  
		   		NetWork net=new NetWork();
		   		Boolean bitmap=net.downimg(url);
		   		Message message = Message.obtain();
		   		message.obj = bitmap;
		   		messageHandler.sendMessage(message);
		   	}  
	   }; 
	   
	    class MessageHandler extends Handler 
	   {

	   		private ImageView img;
	   		private String localurl;
	   		
			public MessageHandler(final Looper looper,final ImageView img,String localurl) 
	        {
	           super(looper);
	           this.img=img;
	           this.localurl=localurl;
	        }
	       @Override
	       public void handleMessage(Message msg) 
	       {
		       	if((Boolean)msg.obj)
		       	{
		       		FileUtils f=new FileUtils();
		       		img.setImageBitmap(f.readbitmap(localurl));
		       		Adapter.this.notifyDataSetChanged();
		       	}      	
	       }

	   }
		
		
	}
}
