package edu.szzydx.spsy.tool;

import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import edu.szzydx.spsy.UserLogin;
import edu.szzydx.spsy.bean.ZXD_list_sp;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class PubTool 
{

	public static ZXD_list_sp sp;
	
	public static Object obj;
	
	public static void ShowToast(Context context,String t)
	{
		Toast.makeText(context, t, Toast.LENGTH_SHORT).show();
	}
	
	/**
	 * 携带参数跳转
	 * @param ct
	 * @param cls
	 * @param map
	 */
	public static void GoTONactivity(Context ct,Class<?> cls,HashMap<String, String> map)
    {
    	
    	Intent i=new Intent(ct, cls);
    	if(map!=null&&map.size()>0)
    	{
    		@SuppressWarnings("rawtypes")
    		Set set= map.entrySet();   
    		@SuppressWarnings("rawtypes")
			Iterator iterator = set.iterator();
    		while(iterator.hasNext())
    		{   
    			@SuppressWarnings("rawtypes")
    			Map.Entry mapentry  = (Map.Entry)iterator.next();   
    			i.putExtra(mapentry.getKey().toString(), mapentry.getValue().toString());
    		}
    	}
    	ct.startActivity(i);
//    	((Activity)ct).overridePendingTransition(R.anim.anim_enter, R.anim.anim_exit);
    }
	
	/**
	 * 直接跳转
	 * @param ct
	 * @param cls
	 */
	public static void GoTONactivity(Context ct,Class<?> cls)
    {    	
    	Intent i=new Intent(ct, cls);
    	ct.startActivity(i);
    }
	
	/**
	 * 从getintent中获得键值对
	 * @param ct
	 * @param key
	 * @return
	 */
	public static String GetValueofIntent(Activity ct,String key)
	{
		String value="";
		try 
		{
			Intent i=ct.getIntent();
			value=i.getExtras().getString(key);
			System.out.println("getintent value"+value);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			value="";
		}		
		return value;
	}
	
	public static String md5(String source) 
	{		
		StringBuffer sb = new StringBuffer(32);			
		try 
		{
			MessageDigest md 	= MessageDigest.getInstance("MD5");
			byte[] array 		= md.digest(source.getBytes("utf-8"));				
			for (int i = 0; i < array.length; i++) 
			{
				sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).toUpperCase().substring(1, 3));
			}
		} catch (Exception e) 
		{
			Log.e("Can not encode the string '" + source + "' to MD5!", e.toString());
			return null;
		}			
		return sb.toString();
	}
	
	public static ProgressDialog getDialog(Context ct,String msg,Boolean iscancelable)
	{
		ProgressDialog pdialog = new ProgressDialog(ct);   
        pdialog.setCancelable(iscancelable);
        pdialog.setMessage(msg);
        pdialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		return pdialog;
	}
	
	/**
	 * StringToDate
	 * @param datestr
	 * @return
	 */
	public static Date StringToDate(String datestr,String format)
	{
		Date date = new Date();  
        //注意format的格式要与日期String的格式相匹配  
		format=format.equals("")?"yyyy/MM/dd HH:mm:ss":format;
        DateFormat sdf = new SimpleDateFormat(format);  
        try 
        {  
            date = sdf.parse(datestr);  
//            System.out.println(date.toString());  
        } 
        catch (Exception e) 
        {  
            e.printStackTrace();  
        }
		return date;
	}
	
	public static String DateToString(Date date,String format)
	{
		String dateStr = "";  
        //format的格式可以任意 
		format=format.equals("")?"yyyy年MM月dd日":format;
        DateFormat sdf = new SimpleDateFormat(format);
        
        try 
        {  
            dateStr = sdf.format(date);  
//            System.out.println(dateStr);   
        } 
        catch (Exception e) 
        {  
            e.printStackTrace();  
        }  
		return dateStr; 
	}

	/**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) 
    {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
    
    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) 
    {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
    
	
}
