package edu.szzydx.spsy.data;

import java.util.ArrayList;
import java.util.Date;

import edu.szzydx.spsy.bean.History;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SqlHelper extends SQLiteOpenHelper
{
	private SQLiteDatabase mDb;
	private static final String DB_NAME = "spsy.db"; //数据库名称
    private static final int version = 1; //数据库版本
	
	public SqlHelper(Context context) 
	{
		super(context, DB_NAME, null, version);
		mDb = getWritableDatabase();
	}

	@Override
	public void onCreate(SQLiteDatabase db) 
	{
		String sql = "create table KD_COM (companyname varchar(20) not null , shortname text not null,tel text,url text,code text );"; 
		String historysql="create table HISTORY (ID INTEGER PRIMARY KEY,code text not null,name text not null,type int not null,date text,md1 text,md2 text)";
        db.execSQL(sql);
        db.execSQL(historysql);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
	{
		
	}

	@Override
	public synchronized SQLiteDatabase getReadableDatabase() {
		// TODO Auto-generated method stub
		return super.getReadableDatabase();
	}

	@Override
	public synchronized SQLiteDatabase getWritableDatabase() {
		// TODO Auto-generated method stub
		return super.getWritableDatabase();
	}
	
	public void InsertHis(History h)
	{
		
		ContentValues v=new ContentValues();
		v.put("code", h.code);
		v.put("name", h.name);
		v.put("type", h.type);
		v.put("md1", h.md1);
		v.put("md2", h.md2);
		v.put("date", h.data);
		Log.e("code and type", h.code+"---"+h.type);
		if(IsExit(getHisList("code", h.code),h.type))
			mDb.insert("HISTORY", null, v);
		else			
			UpdateHis(h.code,h.type, v);
				
	}
	
	public ArrayList<History> getHisList(String cv,String type)
	{
		ArrayList<History> list =new ArrayList<History>();
		History h=new History();
		Cursor cursor = mDb.query("HISTORY", null, cv+"=?", new String[] { type }, null, null, null);  
		while (cursor.moveToNext()) 
		{   
		    h.code = cursor.getString(cursor.getColumnIndex("code"));  
		    h.name = cursor.getString(cursor.getColumnIndex("name")); 
		    h.md1 = cursor.getString(cursor.getColumnIndex("md1")); 
		    h.md2 = cursor.getString(cursor.getColumnIndex("md2")); 
		    h.data=cursor.getString(cursor.getColumnIndex("date"));
		    h.type=cursor.getInt(cursor.getColumnIndex("type"));// getString(cursor.getColumnIndex("data"));
		    list.add(h);
		}
		return list;  
	}
	
	public void DeleteHis(int type)
	{
		mDb.delete("HISTORY", "type=?", new String[]{type+""});
	}

	public void UpdateHis(String code,int type,ContentValues v)
	{
		mDb.update("HISTORY", v, "code=? and type=?", new String[]{code,type+""});
	}
	
	private Boolean IsExit(ArrayList<History> list,int t)
	{
		Boolean b=true;;
		if(list==null)
			return b=true;
		if(list.size()==0)
			return true;
		for(History h : list)
		{
			System.out.println(h.type+"---"+h.code+"-------------------"+t);
			if(h.type==t)
			{
				return false;
			}
		}
		return b;	
	}
}
