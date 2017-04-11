package util;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import x.taihangOA.com.MyAppService.LocationApplication;
import x.taihangOA.com.R;

/**
 * Created by moon.zhong on 2015/6/12.
 * time : 10:45
 */

public class ToolBarHelper {

    // 上下文，创建view的时候需要用到
    private Context mContext;

    /*base view*/
    private FrameLayout mContentView;

    /*用户定义的view*/
    private View mUserView;

    /*apptoolbar*/
    private Toolbar mToolBar;

    private Toolbar stateBar;

    /*视图构造器*/
    private LayoutInflater mInflater;

    /*
    * 两个属性
    * 1、toolbar是否悬浮在窗口之上
    * 2、toolbar的高度获取
    * */
    private static int[] ATTRS = {
            R.attr.windowActionBarOverlay,
            R.attr.actionBarSize
    };

    public ToolBarHelper(Context context, int layoutId) {
        this.mContext = context;
        mInflater = LayoutInflater.from(mContext);

        /*初始化整个内容*/
        initContentView();
        /*初始化用户定义的布局*/
        initUserView(layoutId);
        /*初始化toolbar*/
        initToolBar();
    }

    private void initContentView() {
        /*直接创建一个帧布局，作为视图容器的父容器*/
        mContentView = new FrameLayout(mContext);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        mContentView.setLayoutParams(params);

    }

    private void initToolBar() {
        /*通过inflater获取toolbar的布局文件*/
        View toolbar = mInflater.inflate(R.layout.apptoolbar, mContentView);
        mToolBar = (Toolbar) toolbar.findViewById(R.id.id_tool_bar);
        //stateBar =  (Toolbar) apptoolbar.findViewById(R.id.statebar);

//        ViewGroup.LayoutParams layoutParams = stateBar.getLayoutParams();
//        layoutParams.height = ApplicationClass.stateBarHeight;
//        stateBar.setLayoutParams(layoutParams);

        ViewGroup.LayoutParams layoutParams1 = mToolBar.getLayoutParams();
        layoutParams1.height = LocationApplication.navBarHeight+LocationApplication.stateBarHeight;

        mToolBar.setLayoutParams(layoutParams1);
        mToolBar.setPadding(0,LocationApplication.stateBarHeight,0,0);
    }

    private void initUserView(int id) {
        mUserView = mInflater.inflate(id, null);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        TypedArray typedArray = mContext.getTheme().obtainStyledAttributes(ATTRS);
        /*获取主题中定义的悬浮标志*/
        boolean overly = typedArray.getBoolean(0, false);
        /*获取主题中定义的toolbar的高度*/

        if(LocationApplication.stateBarHeight * LocationApplication.navBarHeight == 0)
        {
            LocationApplication.stateBarHeight = getStatusBarHeight(mContext);
            LocationApplication.navBarHeight = getDaoHangHeight(mContext);
        }

        typedArray.recycle();
        /*如果是悬浮状态，则不需要设置间距*/
        params.topMargin = overly ? 0 : LocationApplication.navBarHeight+LocationApplication.stateBarHeight;

        System.out.println("params.topMargin: "+params.topMargin);

        mContentView.addView(mUserView, params);

    }

    public FrameLayout getContentView() {
        return mContentView;
    }

    public Toolbar getToolBar() {
        return mToolBar;
    }



    /**
     //透明状态栏
     getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
     //透明导航栏
     getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

     * 获取状态栏高度
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen",
                "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }

        System.out.println("状态栏高度为: "+result);

        return result;
    }

    /**
     * 获取导航栏高度
     * @param context
     * @return
     */
    public static int getDaoHangHeight(Context context) {
        int result = 0;
        int resourceId=0;
        int rid = context.getResources().getIdentifier("config_showNavigationBar", "bool", "android");
        if (rid!=0){
            resourceId = context.getResources().getIdentifier("navigation_bar_height", "dimen", "android");
            return context.getResources().getDimensionPixelSize(resourceId);
        }else
            return 0;
    }

}
