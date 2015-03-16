package edu.szzydx.spsy.tool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

public class NetWork 
{
	String fileName = ".jpg"; 
	static String s="downpic";
	
	/**
	 * 发送post请求，取回相应结果
	 */
	public String post(String url, HashMap<String, String> map)
	{
		String result=null;
		@SuppressWarnings("rawtypes")
		Set set= map.entrySet();   
        @SuppressWarnings("rawtypes")
		Iterator iterator = set.iterator();
		 HttpEntityEnclosingRequestBase httpRequest =new HttpPost(url);
		 List <NameValuePair> params = new ArrayList <NameValuePair>(); 
		 while(iterator.hasNext())
		{   
			@SuppressWarnings("rawtypes")
			Map.Entry mapentry  = (Map.Entry)iterator.next();   
			System.out.println(mapentry.getKey()+"/"+mapentry.getValue());
			params.add(new BasicNameValuePair(mapentry.getKey().toString(),mapentry.getValue().toString()));
		}   
			 
		 try   
		    {   
		    	httpRequest.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));  
		        HttpResponse httpResponse = new DefaultHttpClient().execute(httpRequest);
		        System.out.println(httpResponse.getStatusLine().getStatusCode() +"：状态码");
		        if(httpResponse.getStatusLine().getStatusCode() >= 200&&httpResponse.getStatusLine().getStatusCode() <=400)    
		        {     
		        	 StringBuffer sd  = new StringBuffer();  
		             HttpEntity entity = httpResponse.getEntity();  
		             InputStream is = entity.getContent();  
		             BufferedReader br = new BufferedReader(  
		                     new InputStreamReader(is,"utf-8"));  
		             String data = "";  

		             while ((data = br.readLine()) != null) {  
		             	sd.append(data);  
		             }  
		             result = sd.toString();  
		        }   
		        else   
		        {   
		         result="rterror";
		        }   

		    }catch (ClientProtocolException e)   
		    {      
		        e.printStackTrace();   
		      }   
		      catch (IOException e)   
		      {      
		        e.printStackTrace();   
		      }   
		      catch (Exception e)   
		      {      
		        e.printStackTrace();  
		        result="error";
		        
		      }
			return result;
		
	}
	
	/**
	 * 根据HttpEntity解析返回结果
	 * @param entity HttpEntity
	 * @param charset 编码格式，utf-8,gbk...
	 * @return
	 */
	public String HttpEntitytoString(HttpEntity entity,String charset)
	{
		String result=null;
		StringBuffer sd  = new StringBuffer();
		 
        BufferedReader br;
		try {
			String data = "";
			InputStream is = entity.getContent(); 
			br = new BufferedReader(new InputStreamReader(is,charset));
			while ((data = br.readLine()) != null) 
			{  
	        	sd.append(data);  
	        }  
	        result = sd.toString(); 
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        return result;
	}
	
	/**
	 * 可添加http头，携带参数进行post请求，取回相应结果
	 * @param url 目标url
	 * @param map 参数map
	 * @param headermap http头数据map
	 * @return 
	 */
	public String postwithheader(String url, HashMap<String, String> map,HashMap<String, String> headermap)
	{
		String result=null;
		 HttpClient httpclient = new DefaultHttpClient();  
		 HttpPost httppost = new HttpPost(url); 
		 //	序列化参数	
		 @SuppressWarnings("rawtypes")
			Set set= map.entrySet();   
	        @SuppressWarnings("rawtypes")
			Iterator iterator = set.iterator();
			 List <NameValuePair> params = new ArrayList <NameValuePair>(); 
			 while(iterator.hasNext())
			{   
				@SuppressWarnings("rawtypes")
				Map.Entry mapentry  = (Map.Entry)iterator.next();   
				System.out.println(mapentry.getKey()+"/"+mapentry.getValue());
				params.add(new BasicNameValuePair(mapentry.getKey().toString(),mapentry.getValue().toString()));
			}  
			 
			 //序列化http头
			 @SuppressWarnings("rawtypes")
				Set headset= headermap.entrySet();   
		        @SuppressWarnings("rawtypes")
				Iterator hiterator = headset.iterator(); 
				 while(hiterator.hasNext())
				{   
					@SuppressWarnings("rawtypes")
					Map.Entry hmapentry  = (Map.Entry)hiterator.next();   
					System.out.println(hmapentry.getKey()+"---"+hmapentry.getValue());
					httppost.addHeader(hmapentry.getKey().toString(), hmapentry.getValue().toString());
				} 

				 try 
				 {
					 httppost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));  
					 HttpResponse response;  
					 response = httpclient.execute(httppost);  
					 //检验状态码，如果成功接收数据   
					 int code = response.getStatusLine().getStatusCode();  
					 if (code == 200) 
					 {   
					    result = EntityUtils.toString(response.getEntity());
					 } 
					 else
					 {
						 Log.w("errorcode", code+"");
					 }
				} catch (Exception e) 
				{
					result="error";
				}
				 
		return result;
		
	}
	
	public String get(String url)
	{

		try
        {
       	 HttpGet httpRequest = new HttpGet(url);
          HttpResponse httpResponse = new DefaultHttpClient().execute(httpRequest);
          if(httpResponse.getStatusLine().getStatusCode() == 200) 
          {
            String strResult =  HttpEntitytoString(httpResponse.getEntity(),"utf_8");
            return strResult;
          }
          else
          {
       	   return null;
          }
        }
        catch (ClientProtocolException e)
        {  
          e.printStackTrace();
        }
        catch (IOException e)
        {          
          e.printStackTrace();
        }
        catch (Exception e)
        { 
          e.printStackTrace(); 
        }
		return null; 
		
	}
	
	/**
	 * 发送get请求，取回相应结果
	 */
	@SuppressWarnings("rawtypes")
	public String get(String url, HashMap map)
	{
		Set set= map.entrySet();   
        Iterator iterator = set.iterator();
		 StringBuilder sb = new StringBuilder();
         sb.append(url).append("?");
         if(map!=null &&map.size()!=0){
		 while(iterator.hasNext())
		{   
			Map.Entry mapentry  = (Map.Entry)iterator.next();   
			System.out.println(mapentry.getKey()       +       "/"       +       mapentry.getValue());
			sb.append(mapentry.getKey()).append("=").append(mapentry.getValue());
            sb.append("&");
		} 
		 sb.deleteCharAt(sb.length()-1);
         } 
         
         try
         {
        	 HttpGet httpRequest = new HttpGet(sb.toString());
           /*发送请求并等待响应*/
           HttpResponse httpResponse = new DefaultHttpClient().execute(httpRequest);
           /*若状态码为200 ok*/
           if(httpResponse.getStatusLine().getStatusCode() == 200) 
           {
             /*读*/
        	   
             String strResult =  HttpEntitytoString(httpResponse.getEntity(),"utf_8");
             return strResult;
           }
           else
           {
        	   return null;
           }
         }
         catch (ClientProtocolException e)
         {  
           e.printStackTrace();
         }
         catch (IOException e)
         {          
           e.printStackTrace();
         }
         catch (Exception e)
         { 
           e.printStackTrace(); 
         }
		return null; 
	
}
	
	/**
	 * 可添加http头，携带参数进行get请求，取回相应结果
	 */
	@SuppressWarnings("rawtypes")
	public String getwithheader(String url, HashMap<String, String> map,HashMap<String, String> headermap)
	{
		String result=null;
		  
		 //	序列化参数	
		 	Set set= map.entrySet();   
	        Iterator iterator = set.iterator();
			 StringBuilder sb = new StringBuilder();
	         sb.append(url).append("?");
	         if(map!=null &&map.size()!=0)
	         {
	        	 while(iterator.hasNext())
	        	 {   
	        		 Map.Entry mapentry  = (Map.Entry)iterator.next();   
	        		 System.out.println(mapentry.getKey() + "/" +mapentry.getValue());
	        		 sb.append(mapentry.getKey()).append("=").append(mapentry.getValue());
	        		 sb.append("&");
	        	 } 
	        	 sb.deleteCharAt(sb.length()-1);
	         } 
	         HttpClient httpclient = new DefaultHttpClient();  
			 HttpGet httpget = new HttpGet(sb.toString()); 
			 //序列化http头
			 if(headermap!=null &&headermap.size()!=0)
	         {
				 Set headset= headermap.entrySet();   
		         Iterator hiterator = headset.iterator(); 
				 while(hiterator.hasNext())
				{   
					Map.Entry hmapentry  = (Map.Entry)hiterator.next();   
					System.out.println(hmapentry.getKey()+"---"+hmapentry.getValue());
					httpget.addHeader(hmapentry.getKey().toString(), hmapentry.getValue().toString());
				} 
	         }
//			 httpget.addHeader("UA", "sdk,8,2.2,sdk-eng 2.2 FRF91 43546 test-keys,Unknown,432*240");
				 try 
				 {
					 
					 HttpResponse response;  
					 response = httpclient.execute(httpget);  
					 //检验状态码，如果成功接收数据   
					 int code = response.getStatusLine().getStatusCode();  
					 if (code == 200) 
					 {   
					    result = HttpEntitytoString(response.getEntity(),"utf_8");
					 } 
					 else
					 {
						 Log.w("errorcode", code+"");
					 }
				} catch (Exception e) 
				{
					result="error";
				}
				 
		return result;
		
	}
	
	/**
	 * 获取本机ip地址，需要ACCESS_WIFI_STATE权限
	 * @return ip地址
	 */
	public String getLocalIpAddress() 
	{     
        try {     
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();)     
            {     
                NetworkInterface intf = en.nextElement();     
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();)     
                {     
                    InetAddress inetAddress = enumIpAddr.nextElement();     
                    if (!inetAddress.isLoopbackAddress()) 
                    {     
                        return inetAddress.getHostAddress().toString();     
                    }     
                }     
            }     
        } catch (SocketException ex) 
        {     
            Log.e("WifiPreference IpAddress", ex.toString());     
        }     
        return null;     
    }
	
	/**
	 * 获取本机mac地址
	 * @param ct
	 * @return mac地址
	 */
	public String getLocalMacAddress(Context ct) 
	{     
        WifiManager wifi = (WifiManager) ct.getSystemService(Context.WIFI_SERVICE);     
        WifiInfo info = wifi.getConnectionInfo();     
        return info.getMacAddress();     
    }   
	
	/**
	 * 下载并保存图片
	 * @param path
	 * @return
	 */
	public Boolean downimg(String path) 
	{
		Boolean b=true;
		Bitmap bitmap=null;
		try 
		{					
			URL url = new URL(path);      
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();      
	        conn.setConnectTimeout(5 * 1000);      
	        conn.setRequestMethod("GET");      
	        InputStream is = conn.getInputStream(); 
	        if(conn.getResponseCode() == HttpURLConnection.HTTP_OK)
	        {       
	        	Log.i(s, "文件下载正常");
	        	bitmap = BitmapFactory.decodeStream(is);
	        }
	        if(bitmap==null||bitmap.getWidth()==0||bitmap.getHeight()==0)
	        {
	        	b=false;
	        	Log.w("bitmap error", bitmap.getWidth()+":width");
	        	return b;
	        }
		} catch (Exception e) 
		{
			bitmap=null;
			b=false;
			Log.e("pic down faile", "图片下载失败！！！"+e.toString());
			return b;
		}
		
		try 
		{
			FileUtils f=new FileUtils();
			f.saveFile(bitmap, path);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
			Log.e(s, "io error 保存失败，原因："+e.toString());
			b=false;
		}
        return b;
	}
	
	
}
