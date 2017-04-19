package x.taihangOA.com.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.umeng.update.UmengUpdateAgent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import x.taihangOA.com.R;
import x.taihangOA.com.Utils.MyEventBus;
import util.HandleJSMsg;
import util.XNetUtil;

import static x.taihangOA.com.MyAppService.LocationApplication.APPDataCache;

/**
 * Created by admins on 2015/11/14.
 */
public class MineFragment extends Fragment {
    View view;
    public WebView web;
    Handler handlers = new Handler();

    @Nullable
    @Override
    @SuppressLint({"SetJavaScriptEnabled", "JavascriptInterface","NewApi"})
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.xhtmlvc, container, false);

        EventBus.getDefault().register(this);
        UmengUpdateAgent.update(getActivity());
        setView();

        return view;
    }
    private void setView(){

        web = (WebView)view.findViewById(R.id.web);
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

                        XNetUtil.APPPrintln("js obj: "+obj);

                        HandleJSMsg.handle(obj,getActivity());

                    }
                });

            }

        }, "android");

        String url = "file:///android_asset/html/user_info.html";
        web.loadUrl(url);

        XNetUtil.APPPrintln(APPDataCache.User);


    }

    @Subscribe
    public void getEventmsg(MyEventBus myEventBus) {

        if (myEventBus.getMsg().equals("UserUpdateMobile")) {
            web.reload();
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public void reshowHeader()
    {
        if(web != null)
        {
            String js = "javascript:reshowHeader()";
            XNetUtil.APPPrintln("$$$$$$$$$ js: "+js);
            web.loadUrl(js);
        }

    }


}
