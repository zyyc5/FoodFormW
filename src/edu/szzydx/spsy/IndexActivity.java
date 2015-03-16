package edu.szzydx.spsy;

import java.util.ArrayList;
import java.util.HashMap;
import edu.szzydx.spsy.bean.ZXD_list_sp;
import edu.szzydx.spsy.tool.PubTool;
import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;

public class IndexActivity extends Activity {

	RadioButton bzbj,ewsy,kdcx,rmcx,zxd,bgl,flss,njl,aqcs;
	Gallery gallery;
	EditText et;
	Button bt_s;
	RelativeLayout head;
	FrameLayout layout;
	ArrayList<ZXD_list_sp> list=new ArrayList<ZXD_list_sp>();
	Adapter adpter;
	Dialog dialog;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_index);
		
		gallery=(Gallery)findViewById(R.id.gl_show);
		et=(EditText)findViewById(R.id.et);
		et.setOnClickListener(new onclick());
		et.setOnFocusChangeListener(new FocuesChange());
		bt_s=(Button)findViewById(R.id.bt_s);
		layout=(FrameLayout)findViewById(R.id.Fylayout);
		
		head=(RelativeLayout)findViewById(R.id.include1);
		
		bzbj=(RadioButton)findViewById(R.id.bt_bzbj);
		ewsy=(RadioButton)findViewById(R.id.bt_ewsy);
		kdcx=(RadioButton)findViewById(R.id.bt_kdcx);
		rmcx=(RadioButton)findViewById(R.id.bt_rmcx);
		zxd=(RadioButton)findViewById(R.id.bt_zxd);
		bgl=(RadioButton)findViewById(R.id.bt_bgl);
		flss=(RadioButton)findViewById(R.id.bt_flss);
		njl=(RadioButton)findViewById(R.id.bt_njl);
		aqcs=(RadioButton)findViewById(R.id.bt_aqcs);
		
		bzbj.setOnClickListener(new onclick());
		ewsy.setOnClickListener(new onclick());
		kdcx.setOnClickListener(new onclick());
		rmcx.setOnClickListener(new onclick());
		zxd.setOnClickListener(new onclick());
		bgl.setOnClickListener(new onclick());
		flss.setOnClickListener(new onclick());
		njl.setOnClickListener(new onclick());
		aqcs.setOnClickListener(new onclick());
		
		getBitMaps();
		adpter=new Adapter(list);
		gallery.setAdapter(adpter);
		gallery.setOnItemClickListener(new ItemClick());
	}

	void getBitMaps()
	{
		ZXD_list_sp sp=new ZXD_list_sp();
		ZXD_list_sp sp1;
		ZXD_list_sp sp2;
		sp.name="KOKO豆";
		sp.price2="商品描述"
				+"\n\n商品描述"
				+"\n\nKokoaHut杏仁巧克力豆是泰国进口的一种巧克力豆,制作工艺精良,精选泰国特浓巧克力和特大杏仁,香浓的巧克力包裹着大杏仁,香脆可口,让您意犹未尽!"
				+"\n\n品牌简介"
				+"\n\nKokoa Hut是泰国知名品牌,它的制作工艺精良,所采用的可可粉和杏仁等配料都是上乘之选,其所生产巧克力豆更是远销海外.";
		Bitmap bm1=BitmapFactory.decodeResource(getResources(), R.drawable.spp1);
		sp.img=bm1;
		sp.price="12.00";
		
		Bitmap bm2=BitmapFactory.decodeResource(getResources(), R.drawable.spp3);
		Bitmap bm3=BitmapFactory.decodeResource(getResources(), R.drawable.bgl_nor);
		list.add(sp);

		sp1=new ZXD_list_sp();
		sp1.name=sp.name;
		sp1.price=sp.price;
		sp1.price2=sp.price2;
		sp1.img=bm2;
		list.add(sp1);

		sp2=new ZXD_list_sp();
		sp2.name=sp.name;
		sp2.price=sp.price;
		sp2.price2=sp.price2;
		sp2.img=bm3;
		list.add(sp2);
		
		System.out.println(list.size()+" size");
	}
	
	class ItemClick implements OnItemClickListener
	{

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) 
		{
			ZXD_list_sp sp=list.get(arg2);
			PubTool.sp=sp;
			PubTool.GoTONactivity(IndexActivity.this, Sp_info_Activity.class);
		}
		
	}
	
	class onclick implements OnClickListener
	{
		@Override
		public void onClick(View v) 
		{
			switch (v.getId()) {
			case R.id.bt_bzbj:
				
				PubTool.GoTONactivity(IndexActivity.this, CaptureActivity.class);
				break;
			case R.id.bt_ewsy:
				HashMap<String, String> mapp=new HashMap<String, String>();
				mapp.put("action", "bj");
				PubTool.GoTONactivity(IndexActivity.this, CaptureActivity.class,mapp);
				break;
			case R.id.bt_kdcx:
				HashMap<String, String> map=new HashMap<String, String>();
				map.put("action", "kd");
				PubTool.GoTONactivity(IndexActivity.this, CaptureActivity.class, map);
				break;
			case R.id.bt_rmcx:
				PubTool.GoTONactivity(IndexActivity.this, RMCXActivity.class);
				break;
			case R.id.bt_zxd:
				PubTool.GoTONactivity(IndexActivity.this, ZhiXiaoDianActivity.class);
				break;
			case R.id.bt_bgl:
				PubTool.GoTONactivity(IndexActivity.this, BaoGuangLan.class);
				break;
			case R.id.bt_flss:
				PubTool.GoTONactivity(IndexActivity.this, FenLeiSearch.class);
				break;
			case R.id.bt_njl:
				PubTool.GoTONactivity(IndexActivity.this, NongJiaLeActivity.class);
				break;
			case R.id.bt_aqcs:
				PubTool.GoTONactivity(IndexActivity.this, SafeFood.class);
				break;
			case R.id.et:
//				showDialog(1);
//				head.setVisibility(View.GONE);
//				layout.setVisibility(View.GONE);
				PubTool.GoTONactivity(IndexActivity.this, SearchDialog.class);
				break;
			default:
				break;
			}
		}
	}
	
	@Override
	protected Dialog onCreateDialog(int id) 
	{
		if(id==1)
		{		
//			if(dialog!=null)
//			{
				dialog = new Dialog(IndexActivity.this,R.style.Transparent);
				dialog.setContentView(R.layout.searchdialog);
				head.setVisibility(View.GONE);
				layout.setVisibility(View.GONE);
				dialog.setOnKeyListener(new keypress());
				dialog.show();
//			}
		}
		return super.onCreateDialog(id);
	}
	
	class keypress implements OnKeyListener
	{

		@Override
		public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) 
		{
			if (keyCode == KeyEvent.KEYCODE_BACK&& event.getRepeatCount() == 0) 
			{
//				InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
				if(getWindow().getAttributes().softInputMode == WindowManager.LayoutParams.SOFT_INPUT_STATE_UNSPECIFIED)
				{
					head.setVisibility(View.VISIBLE);
					layout.setVisibility(View.VISIBLE);
					dialog.dismiss();
				}
				else
				{
					head.setVisibility(View.VISIBLE);
					layout.setVisibility(View.VISIBLE);
					dialog.dismiss();
				}
			}
			return false;
		}
		
	}
	
	class FocuesChange implements OnFocusChangeListener
	{
		@Override
		public void onFocusChange(View arg0, boolean hasFocus) 
		{
			if (hasFocus==true) 
			{
				((EditText) arg0).setInputType(InputType.TYPE_NULL);
				PubTool.GoTONactivity(IndexActivity.this, SearchDialog.class);
//	            hideIM(arg0);
//	            showDialog(1);
	            
	        }  
		}		
	}
	
	void  hideIM(View v)
	{
		InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);    
		imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		getMenuInflater().inflate(R.menu.activity_index, menu);
		return true;
	}

	class Adapter extends BaseAdapter
	{
		ArrayList<ZXD_list_sp> lists;
		public Adapter(ArrayList<ZXD_list_sp> list)
		{
			this.lists=list;
		}
		
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return lists.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return lists.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View cv, ViewGroup parent) 
		{
			ZXD_list_sp bm=lists.get(position);
			
			if(cv==null)
			{
				ImageView i=new ImageView(IndexActivity.this);
				i.setBackgroundColor(0xFFFFFFFF);
	          	i.setScaleType(ImageView.ScaleType.FIT_CENTER);
	          	i.setAdjustViewBounds(true);
	          	i.setScaleType(ScaleType.FIT_XY);
	          	i.setLayoutParams(new Gallery.LayoutParams(
          	    LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
	          	cv=i;
			}
			((ImageView)cv).setImageBitmap(bm.img);
			return cv;
		}
		
		
	}
}
