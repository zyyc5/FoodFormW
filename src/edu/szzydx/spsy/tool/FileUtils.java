package edu.szzydx.spsy.tool;



import java.io.BufferedInputStream;

import java.io.BufferedOutputStream;

import java.io.File;

import java.io.FileInputStream;

import java.io.FileNotFoundException;

import java.io.FileOutputStream;

import java.io.IOException;

import java.io.InputStream;

import java.io.OutputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

public class FileUtils 
{

	private String SDCardRoot;
	private String SDStateString ;
	private final static String ALBUM_PATH  = Environment.getExternalStorageDirectory() + "/yueqiu/";
	
	public FileUtils() 
	{
		//得到当前外部存储设备的目录
		SDCardRoot = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;
		// 获取扩展SD卡设备状态
		SDStateString = Environment.getExternalStorageState();
	}

	public File createFileInSDCard(String dir ,String fileName) throws IOException 
	{
		File file = new File(SDCardRoot+ dir + File.separator + fileName);	
		file.createNewFile();
		return file;
	}

	public File creatSDDir(String dir) 
	{	
		File dirFile = new File(SDCardRoot + dir + File.separator);		
		if( !dirFile.exists() )
		{		
			dirFile.mkdirs();		
		}		
		return dirFile;	
	}

	/**
	 * 文件是否存在
	 * @param fileName
	 * @return
	 */
	public boolean isFileExist(String fileName)
	{	
		File file = new File(ALBUM_PATH + fileName);		
		return file.exists();	
	}

	public String getFilePath(String dir,String fileName)
	{
		return SDCardRoot + dir + File.separator + fileName;
	}

	/**
	 * 获得sd卡可用大小
	 * @return
	 */
	public long getSDAvailableSize(){
	
		if (SDStateString.equals(android.os.Environment.MEDIA_MOUNTED)) 
		{
			File pathFile = android.os.Environment.getExternalStorageDirectory();
			android.os.StatFs statfs = new android.os.StatFs(pathFile.getPath());
			long nBlocSize = statfs.getBlockSize();
			long nAvailaBlock = statfs.getAvailableBlocks();
			long nSDFreeSize = nAvailaBlock * nBlocSize ;
			return nSDFreeSize;
		}	
		return 0;
	}

	public boolean write2SD(String dir,String fileName,byte[] bytes)
	{
		if(bytes == null ){	
		return false;
	}

OutputStream output = null;

try {

// 拥有可读可写权限，并且有足够的容量

if (SDStateString.equals(android.os.Environment.MEDIA_MOUNTED) && bytes.length < getSDAvailableSize() ) {

File file = null;

creatSDDir(dir);

file = createFileInSDCard(dir,fileName);

output = new BufferedOutputStream(new FileOutputStream(file));

output.write(bytes);

output.flush();

return true;

}

} catch (IOException e1) {

// TODO Auto-generated catch block

e1.printStackTrace();

}finally{

try{

if( output != null ){

output.close();

}

}

catch(Exception e){

e.printStackTrace();

}

}

return false;

}

	/**
	 * 得到图片，bitmap类型
	 * @param path
	 * @return
	 */
	public Bitmap readbitmap(String path)
	{
		Bitmap bit = BitmapFactory.decodeFile(ALBUM_PATH+path);
		return bit;
	}

	/**
	 * 得到文件 数组类型
	 * @param dir
	 * @param fileName
	 * @return
	 */
	 public byte[] readFromSD(String dir,String fileName)
	 {
		File file = new File(SDCardRoot + dir + File.separator + fileName);	
		if( !file.exists() )
		{	
			return null;	
		}	
		InputStream inputStream = null;
		
		try 
		{	
			inputStream = new BufferedInputStream(new FileInputStream(file));
			
			byte[] data = new byte[inputStream.available()];
			
			inputStream.read(data);
			
			return data;
		
		} catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		
		} catch (IOException e)
		{
			e.printStackTrace();
		
		}
		finally
		{	
			try 
			{
			
			if( inputStream != null )
			{	
				inputStream.close();	
			}
			
			} 
			catch (IOException e) 
			{
				e.printStackTrace();	
			}
		}	
		return null;	
	}

	public File write2SDFromInput(String dir,String fileName,InputStream input)
	{
		File file = null;
		OutputStream output = null;
		try 
		{
			int size = input.available();
			if (SDStateString.equals(android.os.Environment.MEDIA_MOUNTED) && size < getSDAvailableSize() ) 
			{
				creatSDDir(dir);
				file = createFileInSDCard(dir,fileName);
				output = new BufferedOutputStream(new FileOutputStream(file));
				byte buffer [] = new byte[4*1024];
				int temp ;
				while((temp = input.read(buffer)) != -1)
				{
					output.write(buffer,0,temp);
				}				
				output.flush();		
			}		
		} catch (IOException e1) 
		{
			e1.printStackTrace();
		}
		finally
		{
			try
			{
				if( output != null )
				{
					output.close();
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}		
		return file;	
	}

	/**
	 * 保存图片
	 * @param bm
	 * @param fileName
	 * @throws IOException
	 */
	public void saveFile(Bitmap bm, String fileName) throws IOException 
	{   
		File dirFile = new File(ALBUM_PATH); 
		fileName=fileName.replace("/", "_");
		fileName=fileName.replace(":", "");
		fileName=fileName.replace("\\", "");
	    if(!dirFile.exists())
	    {   
	        dirFile.mkdir();   
	    }   
	    File myCaptureFile = new File(ALBUM_PATH + fileName);   
	    BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile));   
	    bm.compress(Bitmap.CompressFormat.JPEG, 80, bos);   
	    bos.flush();   
	    bos.close(); 
	    System.out.println("保存成功"+bm.getHeight()+"*"+bm.getWidth());
	}
}