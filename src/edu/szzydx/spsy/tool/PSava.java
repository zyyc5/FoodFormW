package edu.szzydx.spsy.tool;



import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class PSava 
{
	Context ct;
	SharedPreferences preferences;
	public PSava(Context ct)
	{
		this.ct=ct;
	}
	
	/**
	 * 保存简单数据
	 * @param name SharedPreferences的名称
	 * @param key
	 * @param value
	 * @return 是否保存成功
	 */
	public Boolean saveinfo(String name, String key,String value)
	{
		preferences =  ct.getSharedPreferences(name,1);
		Editor editor = preferences.edit();	
		editor.putString(key, value);
        Boolean bres= editor.commit();     
        return bres;	
	}
	
	/**
	 * 读取简单数据 
	 * @param name SharedPreferences的名称
	 * @param key
	 * @return
	 */
	public String getinfo(String name,String key)
	{
		String res="";
		preferences =  ct.getSharedPreferences(name,1);
		res=preferences.getString(key, "");
		return res;
	}
	
	
}
