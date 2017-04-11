package util;

import android.support.v4.app.Fragment;

/**
 * Created by X on 16/9/3.
 */
public abstract class XHorizontalBaseFragment extends Fragment {
    /** Fragment当前状态是否可见 */
    protected boolean isVisible;
    protected boolean isResumed = false;
    protected boolean isLoaded = false;

    //setUserVisibleHint  adapter中的每个fragment切换的时候都会被调用，如果是切换到当前页，那么isVisibleToUser==true，否则为false
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        isResumed = true;
        if(!isLoaded && isVisible)
        {
            lazyLoad();
            isLoaded = true;
        }

    }

    /**
     * 可见
     */
    protected void onVisible() {

        XNetUtil.APPPrintln("isLoaded: "+isLoaded);

        if(!isResumed || isLoaded)
        {
            return;
        }

        lazyLoad();
        isLoaded = true;
    }


    /**
     * 不可见
     */
    protected void onInvisible() {


    }

    /**
     * 延迟加载
     * 子类必须重写此方法
     */
    protected abstract void lazyLoad();

    @Override
    public void onDestroy() {
        super.onDestroy();

        System.out.println("Fragment--->onDestroy");

    }

    @Override
    public void onDetach() {
        super.onDetach();

        System.out.println("Fragment--->onDetach");
    }
}
