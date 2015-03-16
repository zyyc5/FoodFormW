package edu.szzydx.spsy;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Vector;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.ReaderException;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;

import edu.szzydx.spsy.qrcode.camera.BitmapLuminanceSource;
import edu.szzydx.spsy.qrcode.camera.CameraManager;
import edu.szzydx.spsy.qrcode.camera.PlanarYUVLuminanceSource;
import edu.szzydx.spsy.qrcode.decoding.CaptureActivityHandler;
import edu.szzydx.spsy.qrcode.decoding.DecodeFormatManager;
import edu.szzydx.spsy.qrcode.decoding.InactivityTimer;
import edu.szzydx.spsy.qrcode.view.ViewfinderResultPointCallback;
import edu.szzydx.spsy.qrcode.view.ViewfinderView;
import edu.szzydx.spsy.tool.PubTool;
import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint.Cap;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.SurfaceView;
import android.widget.Button;
import android.widget.TextView;

public class CaptureActivity extends Activity implements Callback {

	private CaptureActivityHandler handler;
	private ViewfinderView viewfinderView;
	private boolean hasSurface;
	private Vector<BarcodeFormat> decodeFormats;
	private String characterSet;
	private TextView txtResult;
	private InactivityTimer inactivityTimer;
	private MediaPlayer mediaPlayer;
	private boolean playBeep;
	private static final float BEEP_VOLUME = 0.10f;
	private boolean vibrate;
	private Button bt_open,bt_input,bt_close,bt_image;
	private Boolean IsLightenable=false;
	private int RequestCode=11;
	
	private String Action="";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.qrcodelayout);
		
		bt_open=(Button)findViewById(R.id.bt_flash_on);
		bt_close=(Button)findViewById(R.id.bt_flash_close);
		bt_input=(Button)findViewById(R.id.bt_flash_input);
		bt_image=(Button)findViewById(R.id.bt_flash_image);
		
		bt_open.setOnClickListener(new onclick());
		bt_close.setOnClickListener(new onclick());
		bt_input.setOnClickListener(new onclick());
		bt_image.setOnClickListener(new onclick());
		
		//初始化 CameraManager
		CameraManager.init(getApplication());

		viewfinderView = (ViewfinderView) findViewById(R.id.viewfinder_view);
		txtResult = (TextView) findViewById(R.id.txtResult);
		hasSurface = false;
		inactivityTimer = new InactivityTimer(this);
		
		Action=PubTool.GetValueofIntent(this, "action");
	}

	@Override
	protected void onResume() {
		super.onResume();
		SurfaceView surfaceView = (SurfaceView) findViewById(R.id.preview_view);
		SurfaceHolder surfaceHolder = surfaceView.getHolder();
		if (hasSurface) 
		{
			initCamera(surfaceHolder);
		} 
		else 
		{
			surfaceHolder.addCallback(this);
			surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		}
		decodeFormats = null;
		characterSet = null;

		playBeep = true;
		AudioManager audioService = (AudioManager) getSystemService(AUDIO_SERVICE);
		if (audioService.getRingerMode() != AudioManager.RINGER_MODE_NORMAL) 
		{
			playBeep = false;
		}
		initBeepSound();
		vibrate = true;
	}

	@Override
	protected void onPause() 
	{
		super.onPause();
		if (handler != null) {
			handler.quitSynchronously();
			handler = null;
		}
		CameraManager.get().closeDriver();
	}

	@Override
	protected void onDestroy() 
	{
		inactivityTimer.shutdown();
		super.onDestroy();
	}

	private void initCamera(SurfaceHolder surfaceHolder) 
	{
		try 
		{
			CameraManager.get().openDriver(surfaceHolder);
		} 
		catch (IOException ioe) 
		{
			return;
		} 
		catch (RuntimeException e) 
		{
			return;
		}
		if (handler == null) 
		{
			handler = new CaptureActivityHandler(this, decodeFormats,characterSet);					
		}
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,int height)
	{

	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) 
	{
		if (!hasSurface) 
		{
			hasSurface = true;
			initCamera(holder);
		}
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) 
	{
		hasSurface = false;
	}

	public ViewfinderView getViewfinderView() {
		return viewfinderView;
	}

	public Handler getHandler() {
		return handler;
	}

	public void drawViewfinder() 
	{
		viewfinderView.drawViewfinder();
	}

	public void handleDecode(Result obj, Bitmap barcode) 
	{
		inactivityTimer.onActivity();
//		viewfinderView.drawResultBitmap(barcode);
		playBeepSoundAndVibrate();
//		txtResult.setText(obj.getBarcodeFormat().toString() + ":"+ obj.getText());
		HashMap<String, String> map=new HashMap<String, String>();
		map.put("SCAN_FORMAT", obj.getBarcodeFormat().toString());
		map.put("SCAN", obj.getText());
		if(Action.equals("kd"))
		{
			PubTool.GoTONactivity(this, KuaiDiActivity.class, map);
		}
		else if(Action.equals("bj"))
		{
			map.put("ACTION", "SY");
			PubTool.GoTONactivity(this, ScanResult.class, map);
		}
		else
		{
			PubTool.GoTONactivity(this, ScanResult.class, map);
		}
		this.finish();
	}

	private void initBeepSound() 
	{
		if (playBeep && mediaPlayer == null) 
		{
			setVolumeControlStream(AudioManager.STREAM_MUSIC);
			mediaPlayer = new MediaPlayer();
			mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
			mediaPlayer.setOnCompletionListener(beepListener);

			AssetFileDescriptor file = getResources().openRawResourceFd(R.raw.beep);
					
			try 
			{
				mediaPlayer.setDataSource(file.getFileDescriptor(),
						file.getStartOffset(), file.getLength());
				file.close();
				mediaPlayer.setVolume(BEEP_VOLUME, BEEP_VOLUME);
				mediaPlayer.prepare();
			} 
			catch (IOException e) 
			{
				mediaPlayer = null;
			}
		}
	}

	private static final long VIBRATE_DURATION = 200L;

	private void playBeepSoundAndVibrate() 
	{
		if (playBeep && mediaPlayer != null) {
			mediaPlayer.start();
		}
		if (vibrate) {
			Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
			vibrator.vibrate(VIBRATE_DURATION);
		}
	}

	/**
	 * When the beep has finished playing, rewind to queue up another one.
	 */
	private final OnCompletionListener beepListener = new OnCompletionListener() 
	{
		public void onCompletion(MediaPlayer mediaPlayer) 
		{
			mediaPlayer.seekTo(0);
		}
	};

	class onclick implements OnClickListener
	{
		@Override
		public void onClick(View v) 
		{
			switch (v.getId()) {
			case R.id.bt_flash_on:
				if(IsLightenable)
				{
					v.setBackgroundResource(R.drawable.scan_flash_off);
					CameraManager.closeLight();
					IsLightenable=false;
				}
				else
				{
					v.setBackgroundResource(R.drawable.scan_flash_on);
					CameraManager.openLight();
					IsLightenable=true;
				}
				
				break;
			case R.id.bt_flash_close:
				CameraManager.closeLight();
				CaptureActivity.this.finish();
				break;
			case R.id.bt_flash_input:
				PubTool.GoTONactivity(CaptureActivity.this, Scan_input.class);
				CaptureActivity.this.finish();
				break;
			case R.id.bt_flash_image:
				getimg();
				break;
			default:
				break;
			}
		}		
	}
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) 
	{
		Log.i("TAG", requestCode+":"+resultCode);
		if(requestCode==RequestCode)
		{
			if(resultCode!=RESULT_CANCELED)
			{
				if(data!=null)
				{
					try 
					{												
						Bitmap photo = null;  
					    Uri photoUri = data.getData();  
					    if (photoUri != null) 
					    {  
					         photo = BitmapFactory.decodeFile(photoUri.getPath());
					         Log.i("TAG", photoUri.toString());
					    }  
					    if (photo == null) 
					    {  
					        Bundle extra = data.getExtras();  
					        if (extra != null) {  
					             photo = (Bitmap)extra.get("data");    
					             ByteArrayOutputStream stream = new ByteArrayOutputStream();    
					             photo.compress(Bitmap.CompressFormat.JPEG, 100, stream);  
					        }    
					    }  
					    
					    new doimgback().execute(photo);
					} 
					catch (Exception e) 
					{
						e.printStackTrace();
					}
					
				}
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	void getimg()
	{
		Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
		intent.setType("image/*");
		intent.putExtra("crop", "true"); // 开启剪切
		intent.putExtra("aspectX", 1); // 剪切的宽高比为1：2
		intent.putExtra("aspectY", 1);
		intent.putExtra("outputX", 80); // 保存图片的宽和高
		intent.putExtra("outputY", 80);
		intent.putExtra("outputFormat", "JPEG");  
        intent.putExtra("noFaceDetection", true);  
        intent.putExtra("return-data", true); 
		startActivityForResult(intent, RequestCode);

	}
	
	class doimgback extends AsyncTask<Bitmap, String, String>
	{

		Result rawResult = null;

		@Override
		protected String doInBackground(Bitmap... p) 
		{
			MultiFormatReader multiFormatReader=new MultiFormatReader();
			try 
			{				
				Hashtable<DecodeHintType, Object> hints=new Hashtable<DecodeHintType, Object>(3);
				decodeFormats = new Vector<BarcodeFormat>();
		    	decodeFormats.addAll(DecodeFormatManager.ONE_D_FORMATS);
		    	decodeFormats.addAll(DecodeFormatManager.QR_CODE_FORMATS);
		    	decodeFormats.addAll(DecodeFormatManager.DATA_MATRIX_FORMATS);
		    	hints.put(DecodeHintType.POSSIBLE_FORMATS, decodeFormats);

			    if (characterSet != null) 
			    {
			      hints.put(DecodeHintType.CHARACTER_SET, characterSet);
			    }
			    hints.put(DecodeHintType.NEED_RESULT_POINT_CALLBACK, new ViewfinderResultPointCallback(CaptureActivity.this.getViewfinderView()));		    
			    multiFormatReader.setHints(hints);
			} catch (Exception e) 
			{
				return null;
			}
			
	
		    try 
		    {
		    	rawResult = multiFormatReader.decodeWithState(new BinaryBitmap(new HybridBinarizer(new BitmapLuminanceSource(p[0]))));
		                                      	                                        
		        if(rawResult!=null)
		        {
		        	return "res";
		        }
		    } 
		    catch (ReaderException re) 
		    {
		        // continue
		    } 
		    finally 
		    {
		        multiFormatReader.reset();
		    }
			return null;
		}

		@Override
		protected void onPostExecute(String result) 
		{
			if(result!=null)
				handleDecode(rawResult, null);
			else
			{
				inactivityTimer.onActivity();
				PubTool.ShowToast(CaptureActivity.this, "无法识别，请重试");
				CaptureActivity.this.finish();
			}
			super.onPostExecute(result);
		}
		
		
	}
	
}