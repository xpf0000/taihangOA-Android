package x.taihangOA.com.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import x.taihangOA.com.R;
import x.taihangOA.com.Utils.ImageUtils;
import util.XAPPUtil;

/**
 * Created by Sai on 15/8/4.
 * 网络图片加载例子
 */
public class NetworkImageHolderView implements Holder<String> {
    private ImageView imageView;
    ImageLoader imageLoader;
    ImageUtils imageUtils;
    DisplayImageOptions options;
    ImageLoadingListener animateFirstListener;
    @Override
    public View createView(Context context) {
        //你可以通过layout文件来创建，也可以像我一样用代码创建，不一定是Image，任何控件都可以进行翻页
        imageView = new ImageView(context);
        imageUtils = new ImageUtils();
        imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(context));
        animateFirstListener = new ImageUtils.AnimateFirstDisplayListener();
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return imageView;
    }

    @Override
    public void UpdateUI(Context context,int position, String data) {

        Bitmap bmp = XAPPUtil.readBitMap(context,R.mipmap.loading);
        Drawable d = new BitmapDrawable(context.getResources(),bmp);
        imageView.setImageDrawable(d);

        options=imageUtils.setcenterOptions();
        imageLoader.displayImage(data,imageView,options);
//        ImageLoader.getInstance().displayImage(data,imageView);
    }
}
