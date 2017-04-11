package x.taihangOA.com.Activity;

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
import com.alibaba.fastjson.JSONObject;
import com.bigkoo.alertview.AlertView;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

import util.XActivityindicator;
import util.XNotificationCenter;
import x.taihangOA.com.R;
import util.BaseHtmlVC;
import util.HandleJSMsg;
import util.XNetUtil;
import x.taihangOA.com.Utils.MyEventBus;

import static x.taihangOA.com.MyAppService.LocationApplication.needShowAccountLogout;

/**
 * Created by Administrator on 2017/4/5 0005.
 */

public class Login extends BaseHtmlVC {

    private WebView web;

    @Override
    protected void setupUi() {

        setContentView(R.layout.xhtmlvc);
        web = (WebView)findViewById(R.id.web);
        //web.setMode(PullToRefreshBase.Mode.DISABLED);
        //WebView webView = web;

        // 设置支持JavaScript等
        WebSettings mWebSettings = web.getSettings();
        mWebSettings.setJavaScriptEnabled(true);
        mWebSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        mWebSettings.setDomStorageEnabled(true);
        mWebSettings.setDatabaseEnabled(false);
        mWebSettings.setGeolocationEnabled(false);
        mWebSettings.setAppCacheEnabled(false);

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

                return false;

            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String u) {

                String url = u.toLowerCase();
                XNetUtil.APPPrintln("url111: "+url);

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
                        HandleJSMsg.handle(obj, Login.this);
                    }
                });

            }

        }, "android");


        String url = "file:///android_asset/html/login.html";
        web.loadUrl(url);

        if(needShowAccountLogout)
        {
            needShowAccountLogout = false;
            try
            {
                XActivityindicator.hide();
                AlertView Alert = new AlertView("提醒", "您的账户已在其他设备登录", null, null,
                        new String[]{"确定"},
                        this, AlertView.Style.Alert, null);
                XActivityindicator.setAlert(Alert);
                Alert.show();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

    }

    @Override
    protected void setupData() {

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
        //clearWebViewCache();
        XNetUtil.APPPrintln("login vc destrory !!!!!!");
    }


    @Override
    public void onBackPressed() {

        exitBy2Click();

    }

    private static Boolean isExit = false;

    private void exitBy2Click() {
        Timer tExit = null;
        if (isExit == false) {
            isExit = true; // 准备退出
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false; // 取消退出
                }
            }, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务

        } else {

            XNotificationCenter.getInstance().postNotice("APPFinish",null);

        }
    }


}

