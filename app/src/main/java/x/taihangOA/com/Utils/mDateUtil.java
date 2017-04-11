package x.taihangOA.com.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by admins on 2016/4/27.
 */
public class mDateUtil {
    public static String getTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return format.format(date);
    }
}
