package x.taihangOA.com.Utils;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.util.HashMap;

@SuppressLint("SdCardPath")
public class AsyncImageLoader {
	/**
	 * 内存图片软引用缓冲
	 */
	private HashMap<String, SoftReference<Bitmap>> imageCache = null;

	public AsyncImageLoader() {
		imageCache = new HashMap<String, SoftReference<Bitmap>>();
	}

	public Bitmap loadBitmap(final ImageView imageView, final String imageURL,
			final ImageCallBack imageCallBack) {
		// 在内存缓存中，则返回Bitmap对象

		final Handler handler = new Handler() {
			/*
			 * (non-Javadoc)
			 * 
			 * @see android.os.Handler#handleMessage(android.os.Message)
			 */
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				imageCallBack.imageLoad(imageView, (Bitmap) msg.obj);
			}
		};

		// 如果不在内存缓存中，也不在本地（被jvm回收掉），则开启线程下载图片
		new Thread() {
			/*
			 * (non-Javadoc)
			 * 
			 * @see java.lang.Thread#run()
			 */
			@Override
			public void run() {
				// TODO Auto-generated method stub
				InputStream bitmapIs = HttpUtils.getStreamFromURL(imageURL);

				Bitmap bitmap = BitmapFactory.decodeStream(bitmapIs);
				imageCache.put(imageURL, new SoftReference<Bitmap>(bitmap));
				Message msg = handler.obtainMessage(0, bitmap);
				handler.sendMessage(msg);

				File dir = new File("citycircle/Cache");
				if (!dir.exists()) {
					dir.mkdirs();
				}

				File bitmapFile = new File(
						"citycircle/Cache"
								+ imageURL.substring(imageURL.lastIndexOf("/") + 1));
				if (!bitmapFile.exists()) {
					try {
						bitmapFile.createNewFile();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				FileOutputStream fos;
				try {
					fos = new FileOutputStream(bitmapFile);
					if(bitmap==null){
						System.out.println("网络异常");
					}else{
						bitmap.compress(Bitmap.CompressFormat.PNG.JPEG, 100, fos);	
					}
					
					fos.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}.start();

		return null;
	}
	

	public interface ImageCallBack {
		public void imageLoad(ImageView imageView, Bitmap bitmap);
	}
}