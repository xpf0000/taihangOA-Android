package x.taihangOA.com.Adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import x.taihangOA.com.R;
import x.taihangOA.com.Utils.ImageUtils;

public class NaPageadapter extends PagerAdapter {
    private List<View> mList;
    private Context context;
    ImageView image;
    ArrayList<HashMap<String, String>> imgarray;
    com.nostra13.universalimageloader.core.ImageLoader ImageLoader;
    DisplayImageOptions options;
    x.taihangOA.com.Utils.ImageUtils ImageUtils;
    ImageLoadingListener animateFirstListener;
    public NaPageadapter(List<View> list, Context context,
                         ArrayList<HashMap<String, String>> imgarray) {
        this.mList = list;
        this.context = context;
        this.imgarray = imgarray;
        ImageUtils = new ImageUtils();
        ImageLoader = ImageLoader.getInstance();
        ImageLoader.init(ImageLoaderConfiguration.createDefault(context));
        animateFirstListener = new ImageUtils.AnimateFirstDisplayListeners();

    }

    /**
     * Return the number of views available.
     */
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return mList.size();
    }

    /**
     * Remove a page for the given position. 滑动过后就销毁 ，销毁当前页的前一个的前一个的页！
     * instantiateItem(View container, int position) This method was deprecated
     * in API level . Use instantiateItem(ViewGroup, int)
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // TODO Auto-generated method stub
        container.removeView(mList.get(position));

    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        // TODO Auto-generated method stub
        return arg0 == arg1;
    }

    /**
     * Create the page for the given position.
     */
    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {
        options=ImageUtils.setnoOptions();
        final View view = mList.get(position);
        image = (ImageView) view.findViewById(R.id.viewimage);
        final String url= imgarray.get(position).get("PicUrl");
        options=ImageUtils.setqidongOptions();
        ImageLoader.displayImage(url, image, options,
                animateFirstListener);

        container.removeView(mList.get(position));
        container.addView(mList.get(position));
        return mList.get(position);
    }

}
