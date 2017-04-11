package x.taihangOA.com.MyAppService;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

public class CityServices extends Service {
	private MyReceiver receiver;
	public static final String action = "jason.broadcast";
	String referch;
	int student_id;

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		/**
		 * 利用代码的形式，来注册广播接收器。
		 */
		receiver = new MyReceiver();
		IntentFilter filter = new IntentFilter();
		filter.addAction("com.servicedemo4");
		registerReceiver(receiver, filter);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		/**
		 * 当该服务销毁的时候，销毁掉注册的广播
		 */
		unregisterReceiver(receiver);
		receiver = null;
	}

	/**
	 * 服务中的一个方法
	 */
	public void callInService() {
		Intent intent = new Intent(action);
		intent.putExtra("meeage", student_id);
		sendBroadcast(intent);
	}

	/**
	 * 广播接收器，收到广播后调用服务中的方法。
	 * 
	 * @author Administrator
	 * 
	 */
	private class MyReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			referch = intent.getExtras().getString("getmeeage");
			if(referch.equals("0")){
				student_id=0;
			}else if(referch.equals("1")){
				student_id=1;
			}else if (referch.equals("2")){
				student_id=2;
			}else if (referch.equals("3")){
				student_id=3;
			}else if (referch.equals("4")){
				student_id=4;
			}else if (referch.equals("5")){
				student_id=5;
			}
			else if (referch.equals("6")){
				student_id=6;
			}else if(referch.equals("7"))
			{
				student_id=7;
			}
			else if(referch.equals("8"))
			{
				student_id=8;
			}
			else if(referch.equals("9"))
			{
				student_id=9;
			}
			else if(referch.equals("10"))
			{
				student_id=10;
			}else if (referch.equals("11")){
				student_id=11;
			}

			callInService();
		}

	}
}
