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
	 * ���������
	 * @param name SharedPreferences������
	 * @param key
	 * @param value
	 * @return �Ƿ񱣴�ɹ�
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
	 * ��ȡ������ 
	 * @param name SharedPreferences������
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
