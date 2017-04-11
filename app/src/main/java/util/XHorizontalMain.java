package util;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

import java.util.List;

/**
 * Created by X on 16/9/2.
 */
public class XHorizontalMain extends ViewPager {

    private Context context;
    private XHorizontalPageAdapter mAdapter;
    private XHorizontalMenu menu;

    public void refresh()
    {
        if(mAdapter != null)
        {
            mAdapter.notifyDataSetChanged();
        }
    }

    public XHorizontalMenu getMenu() {
        return menu;
    }

    public void setMenu(XHorizontalMenu menu) {

        this.menu = menu;
        if(this.menu.getMain() != this)
        {
            this.menu.setMain(this);
        }

        if(mAdapter != null)
        {
            mAdapter.notifyDataSetChanged();
        }
    }

    private void init(Context context)
    {
        this.context = context;
//3.0及其以上版本，只需继承Activity，通过getFragmentManager获取事物
        FragmentManager fm = ((FragmentActivity)context).getSupportFragmentManager();
        //初始化自定义适配器
        mAdapter =  new XHorizontalPageAdapter(fm);
        //绑定自定义适配器
        setAdapter(mAdapter);

        addOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                if(menu != null)
                {
                    menu.setSelected(position);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    public XHorizontalMain(Context context) {
        super(context);
        init(context);
    }

    public XHorizontalMain(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }


    private class XHorizontalPageAdapter extends FragmentPagerAdapter {

        public XHorizontalPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            if(menu != null)
            {
                List<XHorizontalMenu.XHorizontalModel> arr = menu.getData();
                if(arr != null)
                {
                    return arr.size();
                }
            }
            return 0;
        }

        @Override
        public Fragment getItem(int position) {

            Fragment view = menu.getData().get(position).getView();

            if(view == null)
            {
                view = new Fragment();
            }

            return view;
        }

    }

}
