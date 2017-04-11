package util;

import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.robin.lazy.cache.CacheLoaderManager;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.io.StringBufferInputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import x.taihangOA.com.MyAppService.LocationApplication;
import x.taihangOA.com.R;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class XAPPUtil {

    public static boolean inRangeOfView(View view, MotionEvent ev){
        int[] location = new int[2];
        view.getLocationOnScreen(location);

        int x = location[0];
        int y = location[1];

        XNetUtil.APPPrintln("location x: "+x+" y: "+y);
        XNetUtil.APPPrintln("width: "+view.getWidth()+" height: "+view.getHeight());

        if(ev.getX() < x || ev.getX() > (x + view.getWidth()) || ev.getY() < y || ev.getY() > (y + view.getHeight())){
            return false;
        }
        return true;
    }

    public static Date serverTime()
    {
        Date now = new Date();
        return  new Date(now.getTime()+LocationApplication.serverTimeInterval);
    }

    public static long serverUnix()
    {
        Date now = new Date();
        return  now.getTime()+LocationApplication.serverTimeInterval;
    }

    public static long serverUnixSecond()
    {
        Date now = new Date();
        return  (now.getTime()+LocationApplication.serverTimeInterval)/1000;
    }

    final static public boolean APPCheckIsLogin()
    {
        return !LocationApplication.APPDataCache.User.getId().equals("");
    }

    public static boolean isPhone(String str)
    {
        String telRegex = "[1][3578]\\d{9}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(str)) return false;
        else return str.matches(telRegex);
    }

    public static boolean isPhone(EditText et)
    {
        if(et == null)
        {
            return false;
        }
        boolean b = false;
        if(et.getText() == null)
        {
            XAPPUtil.nope(et);
            return false;
        }

        b = isPhone(et.getText().toString());

        if(!b)
        {
            XAPPUtil.nope(et);
        }

        return b;

    }

    public static boolean isNull(EditText et)
    {
        if(et == null)
        {
            return false;
        }
        boolean b = false;
        if(et.getText() == null)
        {
            XAPPUtil.nope(et);
            return false;
        }

        b = et.getText().toString().trim().length() > 0;

        if(!b)
        {
            XAPPUtil.nope(et);
        }

        return b;

    }



    public static void nope(View view) {
        int delta = view.getResources().getDimensionPixelOffset(R.dimen.spacing_medium);

        PropertyValuesHolder pvhTranslateX = PropertyValuesHolder.ofKeyframe(View.TRANSLATION_X,
                Keyframe.ofFloat(0f, 0),
                Keyframe.ofFloat(.10f, -delta),
                Keyframe.ofFloat(.26f, delta),
                Keyframe.ofFloat(.42f, -delta),
                Keyframe.ofFloat(.58f, delta),
                Keyframe.ofFloat(.74f, -delta),
                Keyframe.ofFloat(.90f, delta),
                Keyframe.ofFloat(1f, 0f)
        );

        ObjectAnimator.ofPropertyValuesHolder(view, pvhTranslateX).
                setDuration(500).start();
    }

    public static <V extends Serializable> boolean SaveAPPCache(String key, V values){

        if(values instanceof String)
        {
            return CacheLoaderManager.getInstance().saveString(key, (String) values,60*24*30*12*5*10);
        }

        if(values instanceof Serializable)
        {
            return CacheLoaderManager.getInstance().saveSerializable(key,values,60*24*30*12*5*10);
        }

        XNetUtil.APPPrintln("key: "+key+" | values: "+values);

        return  false;
    }




    /**
     * 利用BufferedReader实现Inputstream转换成String <功能详细描述>
     *
     * @param in
     * @return String
     */

    public static String Inputstr2Str_Reader(InputStream in, String encode)
    {

        String str = "";
        try
        {
            if (encode == null || encode.equals(""))
            {
                // 默认以utf-8形式
                encode = "utf-8";
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, encode));
            StringBuffer sb = new StringBuffer();

            while ((str = reader.readLine()) != null)
            {
                sb.append(str).append("\n");
            }
            return sb.toString();
        }
        catch (UnsupportedEncodingException e1)
        {
            e1.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return str;
    }

    /**
     * 利用byte数组转换InputStream------->String <功能详细描述>
     *
     * @param in
     * @return
     * @see [类、类#方法、类#成员]
     */

    public static String Inputstr2Str_byteArr(InputStream in, String encode)
    {
        StringBuffer sb = new StringBuffer();
        byte[] b = new byte[1024];
        int len = 0;
        try
        {
            if (encode == null || encode.equals(""))
            {
                // 默认以utf-8形式
                encode = "utf-8";
            }
            while ((len = in.read(b)) != -1)
            {
                sb.append(new String(b, 0, len, encode));
            }
            return sb.toString();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return "";

    }

    /**
     * 利用ByteArrayOutputStream：Inputstream------------>String <功能详细描述>
     *
     * @param in
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String Inputstr2Str_ByteArrayOutputStream(InputStream in,String encode)
    {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] b = new byte[1024];
        int len = 0;
        try
        {
            if (encode == null || encode.equals(""))
            {
                // 默认以utf-8形式
                encode = "utf-8";
            }
            while ((len = in.read(b)) > 0)
            {
                out.write(b, 0, len);
            }
            return out.toString(encode);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 利用ByteArrayInputStream：String------------------>InputStream <功能详细描述>
     *
     * @param inStr
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static InputStream Str2Inputstr(String inStr)
    {
        try
        {
            // return new ByteArrayInputStream(inStr.getBytes());
            // return new ByteArrayInputStream(inStr.getBytes("UTF-8"));
            return new StringBufferInputStream(inStr);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }


    public static String getTime(){

        long time=System.currentTimeMillis()/1000;//获取系统时间的10位的时间戳

        String  str=String.valueOf(time);

        return str;

    }



    public static RequestBody createBody(Object obj)
    {
        if(obj instanceof String)
        {
            return RequestBody.create(MediaType.parse("text/plain"), (String) obj);
        }
        else
        {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ((Bitmap)obj).compress(Bitmap.CompressFormat.JPEG, 50, baos);
            return RequestBody.create(MediaType.parse("image/jpg"), baos.toByteArray());
        }
    }

    /**
     * 判断当前网络是否可用
     *
     * @return
     */
    public static boolean isNetWorkAvailable(Context context) {

        try{
            ConnectivityManager manager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            if (manager != null) {
                NetworkInfo info = manager.getActiveNetworkInfo();
                if (info != null && info.isConnected()) {
                    return true;
                } else {

                    Toast.makeText(context, "无网络连接,请检查网络", Toast.LENGTH_SHORT).show();

                    return false;
                }
            } else {

                Toast.makeText(context, "无网络连接,请检查网络", Toast.LENGTH_SHORT).show();

                return false;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return true;
        }


    }



    public static String getProcessName(Context cxt, int pid) {
        ActivityManager am = (ActivityManager) cxt.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningApps = am.getRunningAppProcesses();
        if (runningApps == null) {
            return null;
        }
        for (ActivityManager.RunningAppProcessInfo procInfo : runningApps) {
            if (procInfo.pid == pid) {
                return procInfo.processName;
            }
        }
        return null;
    }

    /**
     * 返回当前程序版本名
     */
    public static String getAppVersionName(Context context) {
        String versionName = "";
        try {
            // ---get the package info---
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionName = pi.versionName;
            if (versionName == null || versionName.length() <= 0) {
                return "";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return versionName;
    }


    /**
     * 以最省内存的方式读取本地资源的图片
     * @param context
     * @param resId
     * @return
     */
    public static Bitmap readBitMap(Context context, int resId){
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Bitmap.Config.RGB_565;
        opt.inPurgeable = true;
        opt.inInputShareable = true;
        opt.inJustDecodeBounds = true;
        //获取资源图片
        InputStream is = context.getResources().openRawResource(resId);
        Bitmap bitmap = BitmapFactory.decodeStream(is,null, opt);
        try {
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bitmap;
    }


    public static Bitmap readBitMap(String filePath){
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Bitmap.Config.RGB_565;
        opt.inDither=false;
        opt.inPurgeable = true;
        opt.inInputShareable = true;
        opt.inTempStorage = new byte[12 * 1024];
        opt.inJustDecodeBounds = true;
        //获取资源图片

        File file = new File(filePath);

        FileInputStream fs=null;
        Bitmap bmp = null;
        try {
            fs = new FileInputStream(file);
            if(fs != null)
                bmp = BitmapFactory.decodeFileDescriptor(fs.getFD(), null, opt);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bmp;
    }


    public static int calculateInSampleSize(BitmapFactory.Options options,int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            final int heightRatio = Math.round((float) height/ (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        return inSampleSize;
    }

}