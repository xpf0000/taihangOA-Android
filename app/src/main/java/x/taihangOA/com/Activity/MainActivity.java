package x.taihangOA.com.Activity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.sdk.android.push.CommonCallback;
import com.alibaba.sdk.android.push.noonesdk.PushServiceFactory;
import com.bigkoo.alertview.AlertView;
import com.bigkoo.alertview.OnItemClickListener;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoFragmentActivity;
import com.jph.takephoto.compress.CompressConfig;
import com.jph.takephoto.model.CropOptions;
import com.jph.takephoto.model.TResult;
import com.umeng.analytics.MobclickAgent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import model.DepartmentModel;
import okhttp3.RequestBody;
import util.HttpResult;
import util.XAPPUtil;
import util.XHtmlVC;
import x.taihangOA.com.Fragment.FoundFragment;
import x.taihangOA.com.Fragment.HomeFragment;
import x.taihangOA.com.Fragment.MineFragment;
import x.taihangOA.com.MyAppService.LocationApplication;
import x.taihangOA.com.R;
import x.taihangOA.com.Utils.GlobalVariables;
import x.taihangOA.com.Utils.MyEventBus;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qzone.QZone;
import cn.sharesdk.wechat.friends.Wechat;
import model.APPVersionModel;
import util.XActivityindicator;
import util.XNetUtil;
import util.XNotificationCenter;

import static x.taihangOA.com.MyAppService.LocationApplication.APPDataCache;
import static x.taihangOA.com.MyAppService.LocationApplication.APPService;
import static x.taihangOA.com.MyAppService.LocationApplication.needShowAccountLogout;

public class MainActivity extends TakePhotoFragmentActivity implements CompoundButton.OnCheckedChangeListener {
    private RadioButton home, daiban, my;
    public static FragmentTransaction transaction;
    String activityStyle = null;
    public HomeFragment HomeFragment;
    public FoundFragment MallFragment;
    public MineFragment MemberFragment;
    TextView badge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(APPDataCache.User.getId().equals(""))
        {
            PushServiceFactory.getCloudPushService().removeAlias("",new CommonCallback() {
                @Override
                public void onSuccess(String s) {
                    XNetUtil.APPPrintln("removeAlias success!!!!!!");
                }

                @Override
                public void onFailed(String s, String s1) {
                    XNetUtil.APPPrintln("removeAlias fail!!!!!! "+s+" | "+s1);
                }
            });
        }

        initComponents();
        EventBus.getDefault().register(this);
        if (activityStyle == null) {

            if (HomeFragment == null) {
                HomeFragment = new HomeFragment();
                transaction.add(R.id.all_content, HomeFragment);
                transaction.commit();
            }

        }
        int a = APPDataCache.land;
        if (a != 0) {
            getJsom();
        } else {
            GlobalVariables.types = false;
        }

        checkVersion();

        XNetUtil.APPPrintln("needShowAccountLogout is "+needShowAccountLogout);

        XNotificationCenter.getInstance().addObserver("DaibanCount", new XNotificationCenter.OnNoticeListener() {
            @Override
            public void OnNotice(Object obj) {
                int count = (int) obj;
                if(count > 0)
                {
                    badge.setVisibility(View.VISIBLE);
                    badge.setText(""+count);
                }
                else
                {
                    badge.setVisibility(View.GONE);
                }
            }
        });

    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    public void initComponents() {
        badge = (TextView) findViewById(R.id.bdage);
        badge.setVisibility(View.GONE);
        home = (RadioButton) findViewById(R.id.rb_home);
        daiban = (RadioButton) findViewById(R.id.rb_daiban);
        my = (RadioButton) findViewById(R.id.rb_my);

        home.setOnCheckedChangeListener(this);
        daiban.setOnCheckedChangeListener(this);
        my.setOnCheckedChangeListener(this);

        transaction = getSupportFragmentManager().beginTransaction();

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            transaction = getSupportFragmentManager().beginTransaction();
            switch (buttonView.getId()) {
                case R.id.rb_home:

                    if (HomeFragment == null) {
                        HomeFragment = new HomeFragment();
                        transaction.add(R.id.all_content, HomeFragment);
                    }
                    if (MallFragment != null) {
                        transaction.hide(MallFragment);
                    }
                    if (MemberFragment != null) {
                        transaction.hide(MemberFragment);
                    }
                    transaction.show(HomeFragment);
                    break;
                case R.id.rb_daiban:
                    if (MallFragment == null) {
                        MallFragment = new FoundFragment();
                        transaction.add(R.id.all_content, MallFragment);
                    }
                    if (HomeFragment != null) {
                        transaction.hide(HomeFragment);
                    }
                    if (MemberFragment != null) {
                        transaction.hide(MemberFragment);
                    }

                    transaction.show(MallFragment);
                    break;
                case R.id.rb_my:

                    XNotificationCenter.getInstance().postNotice("MinePageShow",null);

                    if (MemberFragment == null) {
                        MemberFragment = new MineFragment();
                        transaction.add(R.id.all_content, MemberFragment);
                    }
                    if (HomeFragment != null) {
                        transaction.hide(HomeFragment);
                    }

                    if (MallFragment != null) {
                        transaction.hide(MallFragment);
                    }

                    transaction.show(MemberFragment);
                    break;
            }
            transaction.commitAllowingStateLoss();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitBy2Click(); // 调用双击退出函数
        }
        return false;
    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
        LocationApplication.isInited = true;

    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
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

    @Subscribe
    public void getEventmsg(MyEventBus myEventBus) {

        if(myEventBus.getMsg().equals("UserHeadImageEdit")) {

            uploadUserHeadImage();
        }

        if(myEventBus.getMsg().equals("NewDaiban")) {
            HomeFragment.web.reload();

            if(MallFragment != null && MallFragment.web != null)
            {
                MallFragment.web.reload();
            }


        }

    }

    public void getJsom() {


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




    int position = 0;
    public void uploadUserHeadImage()
    {

        AlertView alertView = new AlertView("选择图片", null, "取消", null,
                new String[]{"拍照", "从相册中选择"},
                this, AlertView.Style.ActionSheet, new OnItemClickListener(){
            public void onItemClick(Object o,int p){

                position = p;

            }
        });

        File f = new File(getExternalFilesDir(""), "temp.jpg");
        final Uri uri = Uri.fromFile(f);

        alertView.setOnDismissListener(new com.bigkoo.alertview.OnDismissListener() {
            @Override
            public void onDismiss(Object o) {
                if(position == 0)
                {
                    TakePhoto takePhoto=getTakePhoto();
                    takePhoto.onEnableCompress(CompressConfig.ofDefaultConfig(),true);
                    CropOptions cropOptions=new CropOptions.Builder().setAspectX(1).setAspectY(1).setWithOwnCrop(true).create();
                    takePhoto.onPickFromCaptureWithCrop(uri, cropOptions);
                }
                else if(position == 1)
                {
                    TakePhoto takePhoto=getTakePhoto();
                    takePhoto.onEnableCompress(CompressConfig.ofDefaultConfig(),true);
                    CropOptions cropOptions=new CropOptions.Builder().setAspectX(1).setAspectY(1).setWithOwnCrop(true).create();
                    takePhoto.onPickFromGalleryWithCrop(uri, cropOptions);
                }
            }
        });

        alertView.show();
    }


    private void uploadHeadImg(Bitmap bitmap)
    {
        if(bitmap == null)
        {
            return;
        }

        XActivityindicator.create(this).show();

        Map<String , RequestBody> params = new HashMap<>();
        params.put("id", XAPPUtil.createBody(APPDataCache.User.getId()));
        params.put("mobile", XAPPUtil.createBody(APPDataCache.User.getAccount()));
        params.put("file\"; filename=\"xtest.jpg",XAPPUtil.createBody(bitmap));

        XNetUtil.HandleReturnAll(APPService.userHeadEdit(params), new XNetUtil.OnHttpResult<HttpResult<Object>>() {
            @Override
            public void onError(Throwable e) {
                XActivityindicator.hide();
            }

            @Override
            public void onSuccess(HttpResult<Object> res) {

                XActivityindicator.hide();

                if(res.getData().getCode() == 0)
                {
                    APPDataCache.User.setAvatar(res.getData().getMsg());
                    APPDataCache.User.save();

                    String u = JSON.toJSONString(APPDataCache.User);
                    String js = "javascript:usergetinfo("+u+")";
                    MemberFragment.web.loadUrl(js);

                    return;
                }

                String msg = res.getData().getMsg();
                msg = msg == null ? "头像上传失败" : msg;
                XActivityindicator.showToast(msg);
            }
        });
    }


    @Override
    public void takeSuccess(TResult result) {
        super.takeSuccess(result);

        String path = result.getImages().get(0).getOriginalPath();
        Bitmap bitmap= BitmapFactory.decodeFile(path);
        uploadHeadImg(bitmap);

    }

    @Override
    public void takeFail(TResult result, String msg) {
        super.takeFail(result, msg);
        XNetUtil.APPPrintln(msg);
    }

    @Override
    public void takeCancel() {
        super.takeCancel();

    }




    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);//反注册EventBus
    }
}
