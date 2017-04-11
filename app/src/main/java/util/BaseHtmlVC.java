package util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.jph.takephoto.app.TakePhotoActivity;

import x.taihangOA.com.R;

import static x.taihangOA.com.MyAppService.LocationApplication.APPDataCache;

/**
 * Created by Administrator on 2017/3/31 0031.
 */

public abstract class BaseHtmlVC extends TakePhotoActivity implements
        Properties {

    private boolean isPush = true;
    protected Handler handlers = new Handler();

    @SuppressLint({"SetJavaScriptEnabled", "JavascriptInterface","NewApi"})
    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setupUi();
        setupData();
        //新页面接收数据
        Bundle bundle = this.getIntent().getExtras();

        if (bundle != null && bundle.containsKey("isPush"))
        {
            isPush = bundle.getBoolean("isPush");
        }

    }

    /**
     * 初始化ui
     */
    protected abstract void setupUi();

    /**
     *
     */
    protected abstract void setupData();



    /**
     * 短消息提示
     *
     * @param msg
     */
    public void doShowToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 长消息提示
     *
     * @param msg
     */
    public void doShowToastLong(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 长消息提示
     *
     * @param msg
     */
    public void doShowToastLong(int msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void doShowMesage(String msg, boolean isActivityRun) {
        if (!isActivityRun) {
            return;
        }
        new AlertDialog.Builder(this).setMessage(msg).setTitle("温馨提示")
                .setNegativeButton("确定", null).show();
    }

    public void doShowMesage(String msg) {
        doShowMesage(msg, true);
    }

    public static void doShowMesage(Activity c, String msg) {
        new AlertDialog.Builder(c).setMessage(msg).setTitle("温馨提示")
                .setNegativeButton("确定", null).show();
    }

    /**
     * 启动另外一个界面通过push动画
     *
     * @param activity
     */
    public void pushVC(Class activity) {

        if(!CountTime.isBeyoundTime("启动界面", 300)){
            return;
        }

        Intent intentActive = new Intent(this, activity);

        //用Bundle携带数据
        Bundle bundle=new Bundle();
        bundle.putBoolean("isPush", true);
        intentActive.putExtras(bundle);

        startActivity(intentActive);
        overridePendingTransition(R.anim.push_left_in,R.anim.push_left_out);
    }


    /**
     * 启动另外一个界面通过push动画
     *
     * @param activity
     * @param bundle
     */
    public void pushVC(Class activity,Bundle bundle) {

        if(!CountTime.isBeyoundTime("启动界面", 300)){
            return;
        }

        Intent intentActive = new Intent(this, activity);
        //用Bundle携带数据
        bundle.putBoolean("isPush", true);
        intentActive.putExtras(bundle);

        startActivity(intentActive);
        overridePendingTransition(R.anim.push_left_in,R.anim.push_left_out);
    }



    /**
     * 启动另外一个界面通过present动画
     *
     * @param activity
     */
    public void presentVC(Class activity) {

        if(!CountTime.isBeyoundTime("启动界面", 300)){
            return;
        }

        Intent intentActive = new Intent(this, activity);
        //用Bundle携带数据
        Bundle bundle=new Bundle();
        bundle.putBoolean("isPush", false);
        intentActive.putExtras(bundle);

        startActivity(intentActive);

        overridePendingTransition(R.anim.push_up_in,R.anim.push_up_out);
    }

    /**
     * 启动另外一个界面通过present动画
     *
     * @param activity
     * @param bundle
     */
    public void presentVC(Class activity,Bundle bundle) {

        if(!CountTime.isBeyoundTime("启动界面", 300)){
            return;
        }

        Intent intentActive = new Intent(this, activity);
        //用Bundle携带数据
        bundle.putBoolean("isPush", false);
        intentActive.putExtras(bundle);

        startActivity(intentActive);

        overridePendingTransition(R.anim.push_up_in,R.anim.push_up_out);
    }

    @Override
    public void finish() {
        super.finish();
        if(isPush)
        {
            overridePendingTransition(R.anim.pop_left_out,R.anim.pop_left_in);
        }
        else
        {
            overridePendingTransition(R.anim.pop_up_out,R.anim.pop_up_in);
        }
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        finish();
    }

    public boolean checkIsLogin()
    {
        XNetUtil.APPPrintln("APPDataCache: "+APPDataCache);
        XNetUtil.APPPrintln("APPDataCache.User: "+APPDataCache.User);

        if(APPDataCache.User.getId().equals(""))
        {

            return false;
        }

        return true;
    }


}

