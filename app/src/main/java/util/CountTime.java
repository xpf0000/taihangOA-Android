package util;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.util.Date;
import java.util.HashMap;

/**
 * 计时工具
 * 
 * @author Administrator
 * 
 */
public class CountTime {
	private static HashMap<String, Long> map;

	public static void countStart(String key) {
		if (map == null) {
			map = new HashMap<String, Long>();
		}
		map.put(key, System.currentTimeMillis());
	}

	public static void countEnd(String key) {
		Long time = map.get(key);
		if (time != null) {
			logTime(key + ",用时:" + (System.currentTimeMillis() - time) + "毫秒");
		} else {
			logTime("not find this key:" + key);
		}
	}

	private static void logTime(String string) {
		Log.i("log_time", string);
	}

	private static HashMap<String, Long> mapTime;

	/**
	 * 是否超过设置的时间
	 * 
	 * @param key
	 * @return
	 */
	public static boolean isBeyoundTime(final String key, int time) {
		if (mapTime == null) {
			mapTime = new HashMap<String, Long>();
		}
		Long now = (new Date()).getTime();

		XNetUtil.APPPrintln("now unix time: "+now);

		if (!mapTime.containsKey(key)) {
			mapTime.put(key,now);
			return true;
		}
		else
		{
			Long value = mapTime.get(key);
			if(now-value <= time)
			{
				return  false;
			}
			else
			{
				mapTime.remove(key);
				return true;
			}
		}
	}
}
