package util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by X on 2016/10/7.
 */

public class DateUtils {
    private static SimpleDateFormat sf = null;

    /*获取系统时间 格式为："yyyy/MM/dd "*/
    public static String getCurrentDate() {
        Date d = new Date();
        sf = new SimpleDateFormat("yyyy年MM月dd日");
        return sf.format(d);
    }

    public static String unixToStr(String time,String format) {
        SimpleDateFormat sdr = new SimpleDateFormat(format);
        int i = Integer.parseInt(time);
        String times = sdr.format(new Date(i * 1000L));
        return times;

    }

}
