package x.taihangOA.com.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.robin.lazy.cache.CacheLoaderManager;
import com.umeng.analytics.MobclickAgent;

import java.util.List;

import model.DepartmentModel;
import model.LanchModel;
import util.XNetUtil;
import x.taihangOA.com.R;
import x.taihangOA.com.Utils.GlobalVariables;
import x.taihangOA.com.Utils.HttpRequest;
import x.taihangOA.com.Utils.ImageUtils;
import cn.iwgang.countdownview.CountdownView;
import util.DataCache;
import util.XAPPUtil;

import static x.taihangOA.com.MyAppService.LocationApplication.APPDataCache;
import static x.taihangOA.com.MyAppService.LocationApplication.APPService;

/**
 * Created by admins on 2015/11/24.
 */
public class Loading extends Activity {
    ImageLoader imageLoader;
    DisplayImageOptions options;
    x.taihangOA.com.Utils.ImageUtils ImageUtils;
    ImageLoadingListener animateFirstListener;
    ImageView welimg;
    CountdownView countdownView;
    TextView breaks;
    Thread thread;
    boolean a=true;
    ImageView logo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading);

        APPDataCache = new DataCache();

        logo=(ImageView)findViewById(R.id.logo) ;
        welimg = (ImageView) findViewById(R.id.welimg);

        getImg();

        //logo.setVisibility(View.GONE);
        welimg.setImageResource(R.mipmap.appad);
        welimg.setVisibility(View.VISIBLE);
        countdownView=(CountdownView)findViewById(R.id.cv_countdownViewTest1);
        breaks=(TextView)findViewById(R.id.breaks);

        breaks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a=false;
                Intent Intent = new Intent();
                if(APPDataCache.User.checkIsLogin())
                {
                    Intent.setClass(Loading.this, MainActivity.class);
                    Loading.this.startActivity(Intent);
                    finish();
                }
                else
                {
                    Intent.setClass(Loading.this, Login.class);
                    Loading.this.startActivity(Intent);
                    finish();
                }
            }
        });


        try {
            GoApp();
            countdownView.start(3000);
        } catch (Exception e) {
            Intent Intent = new Intent();

            if(APPDataCache.User.checkIsLogin())
            {
                Intent.setClass(Loading.this, MainActivity.class);
                Loading.this.startActivity(Intent);
                finish();
            }
            else
            {
                Intent.setClass(Loading.this, Login.class);
                Loading.this.startActivity(Intent);
                finish();
            }

        }

    }

    public void GoApp() {
        new Handler().postDelayed(thread=new Thread() {
            @Override
            public void run() {
                super.run();
                Intent Intent = new Intent();
                if (a){

                    if(APPDataCache.User.checkIsLogin())
                    {
                        Intent.setClass(Loading.this, MainActivity.class);
                        Loading.this.startActivity(Intent);
                        finish();
                    }
                    else
                    {
                        Intent.setClass(Loading.this, Login.class);
                        Loading.this.startActivity(Intent);
                        finish();
                    }

                }else {
                    thread.interrupt();
                }


            }
        }, 3000);
    }

    private void getImg() {


        XNetUtil.Handle(APPService.APPGetAPPLanuch(), new XNetUtil.OnHttpResult<LanchModel>() {
            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onSuccess(LanchModel lanchModel) {

                imageLoader = ImageLoader.getInstance();
                imageLoader.init(ImageLoaderConfiguration.createDefault(Loading.this));
                animateFirstListener = new ImageUtils.AnimateFirstDisplayListener();
                ImageUtils=new ImageUtils();

                imageLoader.displayImage(lanchModel.getUrl(),welimg,options,
                        animateFirstListener);

                welimg.setVisibility(View.VISIBLE);

            }
        });


    }


    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}
