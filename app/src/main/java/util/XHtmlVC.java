package util;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.KeyEvent;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.route.BikingRouteResult;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.IndoorRouteResult;
import com.baidu.mapapi.search.route.MassTransitRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRouteResult;
import com.baidu.mapapi.utils.DistanceUtil;
import com.bigkoo.alertview.AlertView;
import com.bigkoo.alertview.OnItemClickListener;
import com.bigkoo.pickerview.TimePickerView;
import com.jph.takephoto.model.TResult;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import model.APPVersionModel;
import okhttp3.RequestBody;
import x.taihangOA.com.R;
import x.taihangOA.com.Utils.MyEventBus;
import x.taihangOA.com.Utils.mDateUtil;

import static x.taihangOA.com.MyAppService.LocationApplication.APPDataCache;
import static x.taihangOA.com.MyAppService.LocationApplication.APPService;

/**
 * Created by X on 2016/11/27.
 */

public class XHtmlVC extends BaseHtmlVC {

    private WebView web;
    private String url;
    TimePickerView pvTime;

    private String mapFlag="";

    private LatLng slatLng;
    private LatLng elatLng;

    public void setMapFlag(String mapFlag) {
        this.mapFlag = mapFlag;
        XNetUtil.APPPrintln("flag: "+mapFlag);
    }

    @Override
    protected void setupUi() {

        setContentView(R.layout.xhtmlvc);

        EventBus.getDefault().register(this);

        web = (WebView)findViewById(R.id.web);
        //web.setMode(PullToRefreshBase.Mode.DISABLED);
        //WebView webView = web;

        // 设置支持JavaScript等
        WebSettings mWebSettings = web.getSettings();
        mWebSettings.setJavaScriptEnabled(true);
        mWebSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        mWebSettings.setDomStorageEnabled(true);
        mWebSettings.setDatabaseEnabled(true);
        mWebSettings.setGeolocationEnabled(true);
        mWebSettings.setAppCacheEnabled(true);

        web.setWebViewClient(new WebViewClient(){

            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
                XNetUtil.APPPrintln("shouldInterceptRequest000 url: "+request.getUrl());
                return super.shouldInterceptRequest(view, request);
            }

            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
                XNetUtil.APPPrintln(view);
                XNetUtil.APPPrintln("shouldInterceptRequest111 url: "+url);
                return super.shouldInterceptRequest(view, url);
            }

            @Override
            public void onLoadResource(WebView view, String url) {
                super.onLoadResource(view, url);
                XNetUtil.APPPrintln("onLoadResource url: "+url);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                String url = request.getUrl().toString().toLowerCase();
                XNetUtil.APPPrintln("url000: "+url);

                if (url.startsWith("tel:")) {
                    Intent intent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse(url));
                    startActivity(intent);
                    return true;
                }

                return false;

            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String u) {
                String url = u.toLowerCase();
                XNetUtil.APPPrintln("url111: "+url);

                if (url.startsWith("tel:")) {
                    Intent intent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse(url));
                    startActivity(intent);
                    return true;
                }

                return false;
            }

            @Override
            public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
                super.onReceivedHttpError(view, request, errorResponse);
                XNetUtil.APPPrintln("request000: "+request.getUrl());
                XNetUtil.APPPrintln("errorResponse000: "+errorResponse.toString());
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                XNetUtil.APPPrintln("request111: "+request.getUrl());
                XNetUtil.APPPrintln("errorResponse111: "+error.toString());
            }
        });

        web.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {

                if (progress == 100) {

                    XNetUtil.APPPrintln("progress ======= 100% !!!!!!!!!!!!");

                    if(url.contains("about"))
                    {
                        String version = "";
                        try {
                            // 获取packagemanager的实例
                            PackageManager packageManager = getPackageManager();
                            // getPackageName()是你当前类的包名，0代表是获取版本信息
                            PackageInfo packInfo = packageManager.getPackageInfo(getPackageName(),0);
                            version = packInfo.versionName;
                        }
                        catch (Exception e)
                        {
                            version = "";
                        }

                        String js = "javascript:OnGetVersionName('"+version+"')";
                        XNetUtil.APPPrintln("$$$$$$$$$ js: "+js);
                        web.loadUrl(js);


                    }
                }
            }

        });

        web.addJavascriptInterface(new Object() {
            @JavascriptInterface
            public void runAndroidMethod(final String str) {
                handlers.post(new Runnable() {

                    @Override
                    public void run() {
                        XNetUtil.APPPrintln("js str: "+str);
                        JSONObject obj = JSON.parseObject(str);
                        HandleJSMsg.handle(obj,XHtmlVC.this);
                    }
                });

            }

        }, "android");


        url = getIntent().getStringExtra("url");

        XNetUtil.APPPrintln("url: "+url);

        web.loadUrl(url);

        if(url.contains("car_apply"))
        {
            initTimePicker(TimePickerView.Type.ALL);

            XNotificationCenter.getInstance().addObserver("Map", new XNotificationCenter.OnNoticeListener() {
                @Override
                public void OnNotice(Object obj) {

                    Map<String,String> map = (Map<String, String>) obj;
                    map.put("flag",mapFlag);

                    String[] maps = map.get("cmap").split(",");
                    double lat = Double.parseDouble(maps[0]);
                    double lng = Double.parseDouble(maps[1]);
                    if(mapFlag.equals("saddress"))
                    {
                        slatLng = null;
                        slatLng = new LatLng(lat,lng);
                    }
                    else
                    {
                        elatLng = null;
                        elatLng = new LatLng(lat,lng);
                    }

                    double dis = 0.0;
                    if(slatLng != null && elatLng != null)
                    {
                        dis = DistanceUtil. getDistance(slatLng, elatLng);
                    }

                    map.put("distance",dis+"");

                    String str=JSON.toJSONString(map);
                    String js = "javascript:OnMapSelect("+str+")";
                    XNetUtil.APPPrintln("$$$$$$$$$ js: "+js);
                    web.loadUrl(js);

                }
            });

        }



        if(url.contains("Office_apply"))
        {

            XNotificationCenter.getInstance().addObserver("ResChoose", new XNotificationCenter.OnNoticeListener() {
                @Override
                public void OnNotice(Object obj) {

                    JSONArray arr = (JSONArray) obj;
                    String str=arr.toJSONString();
                    String js = "javascript:OnResChoose("+str+")";
                    XNetUtil.APPPrintln("$$$$$$$$$ js: "+js);
                    web.loadUrl(js);

                }
            });

        }


        if(url.contains("duban_apply"))
        {
            initTimePicker(TimePickerView.Type.ALL);
            XNotificationCenter.getInstance().addObserver("DaibanChoose", new XNotificationCenter.OnNoticeListener() {
                @Override
                public void OnNotice(Object obj) {

                    JSONObject arr = (JSONObject) obj;
                    String str=arr.toJSONString();
                    String js = "javascript:OnDoUserChoose("+str+")";
                    XNetUtil.APPPrintln("$$$$$$$$$ js: "+js);
                    web.loadUrl(js);

                }
            });

        }

    }


    @Override
    protected void setupData() {

    }


    private void initTimePicker(TimePickerView.Type type)
    {
        pvTime = new TimePickerView(this, type);
        Calendar calendar = Calendar.getInstance();
        pvTime.setRange(calendar.get(Calendar.YEAR) - 70, calendar.get(Calendar.YEAR));
        pvTime.setTime(new Date());
        pvTime.setCyclic(false);
        pvTime.setCancelable(true);
        pvTime.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date) {

                String js = "javascript:OnTimeSelect('"+mDateUtil.getTime(date)+"')";
                XNetUtil.APPPrintln("$$$$$$$$$ js: "+js);
                web.loadUrl(js);

            }
        });
    }

    private static final String APP_CACAHE_DIRNAME ="/webcache";
    /**
     * 清除WebView缓存  在onDestroy调用这个方法就可以了
     */
    public void clearWebViewCache(){

        web.stopLoading();
        web.clearCache(true);
        web.clearHistory();
        web.setWebChromeClient(null);
        web.setWebViewClient(null);
        web.removeJavascriptInterface("android");
        web = null;


        //清理Webview缓存数据库
        try{
            deleteDatabase("webview.db");
            deleteDatabase("webviewCache.db");
        }catch(Exception e) {
            e.printStackTrace();
        }

        //WebView 缓存文件
        File appCacheDir =new File(getFilesDir().getAbsolutePath()+APP_CACAHE_DIRNAME);

        File webviewCacheDir =new File(getCacheDir().getAbsolutePath()+"/webviewCache");

        //删除webview 缓存目录
        if(webviewCacheDir.exists()){
            deleteFile(webviewCacheDir);
        }
        //删除webview 缓存 缓存目录
        if(appCacheDir.exists()){
            deleteFile(appCacheDir);
        }
    }

    /**
     * 递归删除 文件/文件夹
     *
     * @param file
     */
    public void deleteFile(File file) {

        if(file.exists()) {
            if(file.isFile()) {
                file.delete();
            }else if(file.isDirectory()) {
                File files[] = file.listFiles();
                for(int i = 0; i < files.length; i++) {
                    deleteFile(files[i]);
                }
            }
            file.delete();
        }else{

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        if(url.contains("car_apply")){
            XNotificationCenter.getInstance().removeObserver("Map");
        }

        if(url.contains("Office_apply")){
            XNotificationCenter.getInstance().removeObserver("ResChoose");
        }

        if(url.contains("duban_apply")){
            XNotificationCenter.getInstance().removeObserver("DaibanChoose");
        }

        XNetUtil.APPPrintln("xhtml vc destrory !!!!!!");
    }

    @Subscribe
    public void getEventmsg(MyEventBus myEventBus) {
        if (myEventBus.getMsg().equals("LoginSuccess")) {
            finish();
        }

        if (myEventBus.getMsg().equals("showTimePicker")) {

            if(pvTime != null)
            {
                pvTime.show();
            }
        }

        if (myEventBus.getMsg().equals("AddCarTaskSuccess")) {

            if(url.contains("car_list"))
            {
                web.reload();
            }
        }

        if (myEventBus.getMsg().equals("AddResTaskSuccess")) {

            if(url.contains("office_list"))
            {
                web.reload();
            }
        }

        if (myEventBus.getMsg().equals("AddOverseerTaskSuccess")) {

            if(url.contains("duban_list"))
            {
                web.reload();
            }
        }

        if (myEventBus.getMsg().equals("UserUpdateMobile")) {

            if(url.contains("edit_phone") || url.contains("re_phone"))
            {
                finish();
            }

            if(url.contains("setup"))
            {
                web.reload();
            }
        }

        if (myEventBus.getMsg().equals("CheckVersion")) {

            if(url.contains("setup"))
            {
                checkVersion();
            }
        }


    }





    private void checkVersion()
    {
        XNetUtil.Handle(APPService.APPGetLastAPPVersion(), new XNetUtil.OnHttpResult<APPVersionModel>() {
            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onSuccess(APPVersionModel model) {

                checkVersion(model);

            }
        });
    }

    private void checkVersion(final APPVersionModel model)
    {

        try
        {
            double v = Integer.parseInt(model.getVersioncode());

            if(getVersion() < v)
            {
                XActivityindicator.hide();

                AlertView Alert = new AlertView("版本更新 "+model.getVersionname(), model.getDescription().replace("<br />",""), null, null,
                        new String[]{"取消","立即升级"},
                        this, AlertView.Style.Alert, new com.bigkoo.alertview.OnItemClickListener() {
                    @Override
                    public void onItemClick(Object o, int position) {
                        if (position == 1) {

                            Uri uri = Uri.parse(model.getUrl());
                            Intent it = new Intent(Intent.ACTION_VIEW, uri);
                            startActivity(it);

                        }
                    }
                });

                XActivityindicator.setAlert(Alert);

                Alert.show();
            }

        }
        catch (Exception e)
        {

        }

    }


    private int getVersion()
    {
        try {
            // 获取packagemanager的实例
            PackageManager packageManager = getPackageManager();
            // getPackageName()是你当前类的包名，0代表是获取版本信息
            PackageInfo packInfo = packageManager.getPackageInfo(getPackageName(),0);
            int version = packInfo.versionCode;

            return version;
        }
        catch (Exception e)
        {
            return 0;
        }

    }



    @Override
    public void onBackPressed() {

        if(url.contains("reg3"))
        {
            EventBus.getDefault().post(new MyEventBus("logout"));
        }
        else
        {
            super.onBackPressed();
        }

    }



}
