package x.taihangOA.com.Utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 论坛天数转换
 * @author zpp
 *
 */
public class Timechange {
	String str;

	public String Time(String time) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
		String str = formatter.format(curDate);
		try {
			Date d1 = df.parse(time);
			long diff = curDate.getTime() - d1.getTime();// 这样得到的差值是微秒级别
			long days = diff / (1000 * 60 * 60 * 24);
			long hours = (diff - days * (1000 * 60 * 60 * 24))
					/ (1000 * 60 * 60);
			long minutes = (diff - days * (1000 * 60 * 60 * 24) - hours
					* (1000 * 60 * 60))
					/ (1000 * 60);
			if ( days == 0 && hours == 0 && minutes == 0) {
				str = "刚刚";
			}else if(days>5){
				str=time.substring(0,10);
			}else if(days<5&&days>0){
				str=days+"天前";
				
			}else if(hours>0){
				str=hours+"小时前";
			}else if(minutes>0){
				str=minutes+"分钟前";
			}else{
				str = "刚刚";
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;

	}
	  public static  int getWordCount(String s)
	  {
	        s = s.replaceAll("[^\\x00-\\xff]", "**");
	        int length = s.length();
	        return length;
	   }

}
