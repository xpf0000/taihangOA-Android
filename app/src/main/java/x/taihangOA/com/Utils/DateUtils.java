package x.taihangOA.com.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by admins on 2015/11/20.
 */
public class DateUtils {
    private static SimpleDateFormat sf;

    /*获取系统时间 格式为："yyyy/MM/dd "*/
    public static String getCurrentDate() {
        Date d = new Date();
        sf = new SimpleDateFormat("yyyy年MM月dd日");
        return sf.format(d);
    }

    /*时间戳转换成字符窜*/
    public static String getDateToString(long time) {
        Date d = new Date(time*1000);
        sf = new SimpleDateFormat("yyyy-MM-dd");
        return sf.format(d);
    }
    public static String getDateToStrings(long time) {
        Date d = new Date(time*1000);
        sf = new SimpleDateFormat("yyyy年MM月dd日");
        return sf.format(d);
    }
    public static String getDateToStringsa(long time) {
        Date d = new Date(time*1000);
        sf = new SimpleDateFormat("yyyy.MM.dd");
        return sf.format(d);
    }
    public static String getDateToStringss(long time) {
        Date d = new Date(time*1000);
        sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sf.format(d);
    }
    public static String getDateToStringsss(long time) {
        Date d = new Date(time*1000);
        sf = new SimpleDateFormat("MM-dd HH:mm");
        return sf.format(d);
    }

    /*将字符串转为时间戳*/
    public static long getStringToDate(String time) {
        sf = new SimpleDateFormat("yyyy年MM月dd日");
        Date date = new Date();
        try {
            date = sf.parse(time);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date.getTime();
    }
    public static String getDateToStringssss(long time) {
        Date d = new Date(time*1000);
        sf = new SimpleDateFormat("yyyyMM");
        return sf.format(d);
    }
}