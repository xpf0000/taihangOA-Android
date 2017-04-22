package x.taihangOA.com.MyAppService;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.multidex.MultiDex;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

import com.alibaba.sdk.android.push.CloudPushService;
import com.alibaba.sdk.android.push.CommonCallback;
import com.alibaba.sdk.android.push.noonesdk.PushServiceFactory;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;
import com.baidu.mapapi.SDKInitializer;

import com.bigkoo.alertview.AlertView;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import com.robin.lazy.cache.CacheLoaderConfiguration;
import com.robin.lazy.cache.CacheLoaderManager;
import com.robin.lazy.cache.disk.naming.HashCodeFileNameGenerator;
import com.robin.lazy.cache.memory.MemoryCache;
import com.robin.lazy.cache.util.MemoryCacheUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import util.XActivityindicator;
import x.taihangOA.com.Activity.Loading;
import x.taihangOA.com.Activity.Login;
import x.taihangOA.com.R;
import x.taihangOA.com.Utils.MyEventBus;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import util.DataCache;
import util.ServicesAPI;
import util.XAPPUtil;
import util.XNetUtil;
import util.XNotificationCenter;

public class LocationApplication extends Application {
    public LocationClient mLocationClient;
    public MyLocationListener mMyLocationListener;
    public double longitude, latitude, a, conlongitude, conlatitude;
    public Vibrator mVibrator;
    private LocationMode tempMode = LocationMode.Battery_Saving;
    private String tempcoor = "bd09ll";
    LocationClientOption locationClientOption;
    public String adress,getStreet;
    public String city,District;
    private static final String TAG = "Init";


    public static int stateBarHeight = 0;
    public static int navBarHeight = 0;

    public static int SW = 0;
    public static int SH = 0;

    public static Context context;

    public static Retrofit retrofit;

    public static ServicesAPI APPService;

    static public DataCache APPDataCache;

    private boolean isActive = false;

    public static long serverTimeInterval;

    public static boolean isInited = false;
    public static boolean needShowAccountLogout = false;
    /**
     * 创建全局变量 全局变量一般都比较倾向于创建一个单独的数据类文件，并使用static静态变量
     *
     * 这里使用了在Application中添加数据的方法实现全局变量
     * 注意在AndroidManifest.xml中的Application节点添加android:name=".MyApplication"属性
     *
     */
    private WindowManager.LayoutParams wmParams = new WindowManager.LayoutParams();

    public WindowManager.LayoutParams getMywmParams() {
        return wmParams;
    }

    private List<WeakReference<Activity>> vcArrs = new ArrayList<>();

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        EventBus.getDefault().register(this);

        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                XNetUtil.APPPrintln("onActivityCreated: "+activity);
                context = activity;
            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {
                context = activity;
                WeakReference<Activity> item = new WeakReference<Activity>(activity);
                vcArrs.add(item);

                if (!isActive)
                {
                    isActive = true;
                    APPDataCache.User.checkToken();
                    XNetUtil.APPPrintln("APP is BecomeActive!!!!!!");
                }

                try
                {
                    XAPPUtil.isNetWorkAvailable(context);
                }
                catch (Exception e)
                {

                }

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {
                if (!isAppOnForeground()) {
                    //app 进入后台
                    isActive = false;
                    //全局变量isActive = false 记录当前已经进入后台
                }
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });

        context = getApplicationContext();

        MemoryCache memoryCache= MemoryCacheUtils.createLruMemoryCache(1024*1024*256);
        CacheLoaderConfiguration config = new CacheLoaderConfiguration(this, new HashCodeFileNameGenerator(), 1024 * 1024 * 1024*64, 200000, memoryCache,60*24*30*365*20);
        CacheLoaderManager.getInstance().init(config);

        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        SW = displayMetrics.widthPixels;
        SH = displayMetrics.heightPixels;

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {

                Request request = chain.request().newBuilder()
                        .addHeader("User-Agent","Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36")
                        .addHeader("Content-Type","text/plain; charset=utf-8")
                        .addHeader("Accept","*/*")
                        .build();

                XNetUtil.APPPrintln("URL: "+request.url().toString());
                if(request.body() != null)
                {
                    XNetUtil.APPPrintln("Body: "+request.body().toString());
                }

                Response response = chain.proceed(request);
                String date = response.header("Date");
                SimpleDateFormat format = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss 'GMT'", Locale.US);
                format.setTimeZone(TimeZone.getTimeZone("GMT"));

                try {

                    Date serverdate = format.parse(date);
                    Date localdate = new Date();

                    serverTimeInterval = serverdate.getTime() - localdate.getTime();

                } catch (ParseException e) {
                    e.printStackTrace();
                }

                return response;
            }
        }).build();


        retrofit = new Retrofit.Builder()
                .baseUrl(ServicesAPI.APPUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .callFactory(client)
                .build();

        APPService = retrofit.create(ServicesAPI.class);

        initCloudChannel(this);
        // System.out.println("getApplicationContext()"+getApplicationContext());
        SDKInitializer.initialize(getApplicationContext());
        mLocationClient = new LocationClient(this.getApplicationContext());

        //初始化imgload
        initImageLoader();

        mMyLocationListener = new MyLocationListener();
        mLocationClient.registerLocationListener(mMyLocationListener);
        locationClientOption = new LocationClientOption();
        locationClientOption.setLocationMode(tempMode);//设置定位模式
        locationClientOption.setCoorType(tempcoor);//返回的定位结果是百度经纬度，默认值gcj02
        locationClientOption.setIsNeedAddress(true);
        mLocationClient.setLocOption(locationClientOption);
        mVibrator = (Vibrator) getApplicationContext().getSystemService(
                Service.VIBRATOR_SERVICE);
        mLocationClient.start();


        XNotificationCenter.getInstance().addObserver("APPFinish", new XNotificationCenter.OnNoticeListener() {
            @Override
            public void OnNotice(Object obj) {

                for(WeakReference<Activity> item : vcArrs)
                {
                    if(item.get() != null)
                    {
                        item.get().finish();
                        item.clear();
                    }
                }

                vcArrs.clear();
                vcArrs = null;

                System.exit(0);
                //android.os.Process.killProcess(android.os.Process.myPid());

            }
        });



        System.out.println("================init============");
    }

    //初始化网络图片缓存库
    private void initImageLoader() {
        //网络图片例子,结合常用的图片缓存库UIL,你可以根据自己需求自己换其他网络图片库
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder().
                showImageForEmptyUri(R.drawable.app_default)
                .cacheInMemory(true).cacheOnDisk(true).build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                getApplicationContext()).defaultDisplayImageOptions(defaultOptions)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO).build();
        ImageLoader.getInstance().init(config);

    }

    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            longitude = location.getLongitude();
            latitude = location.getLatitude();
            adress = location.getAddrStr();
            city = location.getCity();
            District=location.getDistrict();
            getStreet=location.getStreet();
            System.out.println("longitude:" + longitude + "latitude:"
                    + latitude);
            System.out.println("adressa:" + adress);
            if (adress!=null){
                mLocationClient.stop();
            }
        }

        @Override
        public void onConnectHotSpotMessage(String s, int i) {

        }

    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        System.out.println("+++++++++++");
    }
    private void initCloudChannel(Context applicationContext) {

        try
        {
            PushServiceFactory.init(applicationContext);
            CloudPushService pushService = PushServiceFactory.getCloudPushService();
            pushService.register(applicationContext, new CommonCallback() {
                @Override
                public void onSuccess(String response) {
                    XNetUtil.APPPrintln("init cloudchannel success");

                    if(APPDataCache != null)
                    {
                        if(APPDataCache.User.getId().equals(""))
                        {
                            APPDataCache.User.unRegistNotice();
                        }
                    }


                }
                @Override
                public void onFailed(String errorCode, String errorMessage) {
                    XNetUtil.APPPrintln("init cloudchannel failed -- errorcode:" + errorCode + " -- errorMessage:" + errorMessage);
                }
            });
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


    }


    /**
     * 程序是否在前台运行
     *
     * @return
     */
    public boolean isAppOnForeground() {
        // Returns a list of application processes that are running on the
        // device

        ActivityManager activityManager = (ActivityManager) getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
        String packageName = getApplicationContext().getPackageName();

        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager
                .getRunningAppProcesses();
        if (appProcesses == null)
            return false;

        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            // The name of the process that this object is associated with.
            if (appProcess.processName.equals(packageName)
                    && appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                return true;
            }
        }

        return false;
    }



    @Subscribe
    public void getEventmsg(MyEventBus myEventBus) {


        XNetUtil.APPPrintln("context ： %%%%%%%%%%%%%： " + context);


        if (myEventBus.getMsg().equals("logout")) {

            APPDataCache.User.reSet();
            APPDataCache.User.unRegistNotice();

            doLogout();
        }

        if (myEventBus.getMsg().equals("AccountLogout")) {

            APPDataCache.User.reSet();
            APPDataCache.User.unRegistNotice();

            if(context instanceof Loading)
            {
                needShowAccountLogout = true;
            }
            else
            {
                XActivityindicator.hide();
                AlertView Alert = new AlertView("提醒", "您的账户已在其他设备登录", null, null,
                        new String[]{"确定"},
                        context, AlertView.Style.Alert, new com.bigkoo.alertview.OnItemClickListener() {
                    @Override
                    public void onItemClick(Object o, int position) {
                        if (position == 0) {

                            XNetUtil.APPPrintln("###################### ^^^^^^^^^^^^^^^^^^");

                            doLogout();

                        }
                    }
                });
                XActivityindicator.setAlert(Alert);
                Alert.show();
            }

        }


    }

    private void doLogout()
    {
        if(!(context instanceof Login))
        {
            Intent Intent = new Intent();
            Intent.setClass(context, Login.class);
            context.startActivity(Intent);
        }

        for(WeakReference<Activity> item : vcArrs)
        {
            if(item.get() != null)
            {
                if(item.get() instanceof Login)
                {
                    continue;
                }
                item.get().finish();
            }
        }
    }

}
