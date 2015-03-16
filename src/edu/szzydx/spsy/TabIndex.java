package edu.szzydx.spsy;


import edu.szzydx.spsy.bean.ExitManager;
import edu.szzydx.spsy.tool.PubTool;
import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TabHost;

public class TabIndex extends TabActivity
{

	AlertDialog dialogg;
	TabHost tabHost;
	//设置5个底部菜单的变量
	private RadioButton main_tab_home;
	private RadioButton main_tab_zhiC;
	private RadioButton main_tab_geRZX;
	private RadioButton main_tab_his;
	private RadioButton main_tab_more;
	private Button BT_scan;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab_index);
		BT_scan=(Button)findViewById(R.id.bt_scan);
		BT_scan.setOnClickListener(new vclick());
		initTab();
        init();
        ExitManager.getInstance().addActivity(this);
	}

	 public void init()
	 {
			main_tab_home = (RadioButton)findViewById(R.id.main_tab_wenzj);
	    	main_tab_zhiC = (RadioButton) findViewById(R.id.main_tab_zhic);
	    	main_tab_geRZX = (RadioButton) findViewById(R.id.main_tab_gerzx);
	    	main_tab_his = (RadioButton) findViewById(R.id.main_tab_zhix);
	    	main_tab_more = (RadioButton) findViewById(R.id.main_tab_shez);
	    	
	    	
	    	main_tab_home.setOnClickListener(new vclick());
	    	main_tab_zhiC.setOnClickListener(new vclick());
	    	main_tab_geRZX.setOnClickListener(new vclick());
	    	main_tab_his.setOnClickListener(new vclick());
	    	main_tab_more.setOnClickListener(new vclick());
	    	main_tab_home.performClick();
	  }
	    
	 class vclick implements OnClickListener
	 {

		@Override
		public void onClick(View v) 
		{
			switch (v.getId()) {
			case R.id.main_tab_wenzj:
				main_tab_home.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.drawable.home_sel), null, null);//setButtonDrawable(R.drawable.home_sel);
				main_tab_home.setTextColor(getResources().getColor(R.color.light_blue));
				whenselect(main_tab_his,R.drawable.home_history_x,R.color.white);
				whenselect(main_tab_more,R.drawable.home_more_x,R.color.white);
				tabHost.setCurrentTabByTag("WenZhuanJia");
				break;
			case R.id.main_tab_zhic:
//				tabHost.setCurrentTabByTag("ZhiCha");
				PubTool.GoTONactivity(TabIndex.this, UserLogin.class);
				break;
			case R.id.main_tab_gerzx:
				
				PubTool.GoTONactivity(TabIndex.this, CaptureActivity.class);
				break;
			case R.id.main_tab_zhix:
				whenselect(main_tab_his,R.drawable.home_history_sel,R.color.light_blue);
				whenselect(main_tab_home,R.drawable.home_home,R.color.white);
				whenselect(main_tab_more,R.drawable.home_more_x,R.color.white);
				tabHost.setCurrentTabByTag("ZhiXun");
				break;
			case R.id.main_tab_shez:
				whenselect(main_tab_his,R.drawable.home_history_x,R.color.white);
				whenselect(main_tab_home,R.drawable.home_home,R.color.white);
				whenselect(main_tab_more,R.drawable.home_more_sel,R.color.light_blue);
				tabHost.setCurrentTabByTag("SheZhi");
				break;
			case R.id.bt_scan:
				PubTool.GoTONactivity(TabIndex.this, CaptureActivity.class);
				break;

			default:
				break;
			}
		}
	}
	 
	 
    public void initTab()
    {
    	// 实例化TabHost
    	tabHost = getTabHost();
    	tabHost.addTab(tabHost.newTabSpec("WenZhuanJia").setIndicator("WenZhuanJia")
				.setContent(new Intent(this, IndexActivity.class)));
    	tabHost.addTab(tabHost.newTabSpec("ZhiCha").setIndicator("ZhiCha")
				.setContent(new Intent(this, IndexActivity.class)));
		tabHost.addTab(tabHost.newTabSpec("GeRenZhongXin").setIndicator("GeRenZhongXin")
				.setContent(new Intent(this, IndexActivity.class)));
		tabHost.addTab(tabHost.newTabSpec("ZhiXun").setIndicator("ZhiXun")
				.setContent(new Intent(this, ZiXunActivity.class)));
		tabHost.addTab(tabHost.newTabSpec("SheZhi").setIndicator("SheZhi")
				.setContent(new Intent(this, MoreActivity.class)));
    }
    
    void whenselect(RadioButton rb,int draw,int color)
    {
    	rb.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(draw), null, null);//setButtonDrawable(R.drawable.home_sel);
		rb.setTextColor(getResources().getColor(color));
    }
    
    @Override
    public boolean dispatchKeyEvent( KeyEvent event) 
    {
    	int keyCode = event.getKeyCode();
    	Log.i("xx", keyCode+"");
    	if (keyCode == KeyEvent.KEYCODE_BACK) 
    	{
    		if(dialogg!=null)
    		{
    			if(!dialogg.isShowing())
    				openOptionDialog();
    		}
    		else
    			openOptionDialog();
    	}
		return true;
	}

    
    public void openOptionDialog()
    {
    	dialogg=new AlertDialog.Builder(this)
		.setTitle("提 示")
		.setMessage("确定退出"+getResources().getString(R.string.app_name)+"？")
		.setNegativeButton("取消", 
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) 
					{
						dialog.cancel();
						dialogg.dismiss();
					}
				})
		.setPositiveButton("确定", 
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) 
					{
						ExitManager.getInstance().exit();
					}
				}).show();
	}
	
}
