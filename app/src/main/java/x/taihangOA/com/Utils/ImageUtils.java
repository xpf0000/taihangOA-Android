package x.taihangOA.com.Utils;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.io.File;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import x.taihangOA.com.MyViews.CircleBitmapDisplayer;
import x.taihangOA.com.R;

/**
 * Created by admins on 2015/9/19.
 * 图片下载方法的return
 * author  zpp
 */
public class ImageUtils {
    public DisplayImageOptions setOptions(){
        DisplayImageOptions DisplayImageOptions = null;
        DisplayImageOptions = new DisplayImageOptions.Builder()
                .showStubImage(R.mipmap.nopic) // 设置图片下载期间显示的图片
                .showImageForEmptyUri(R.mipmap.nopic) // 设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.mipmap.nopic) // 设置图片加载或解码过程中发生错误显示的图片
                .cacheInMemory(true) // 设置下载的图片是否缓存在内存中
                .cacheOnDisc(true) // 设置下载的图片是否缓存在SD卡中
                .displayer(new RoundedBitmapDisplayer(20)) // 设置成圆角图片
                .build(); // 创建配置过得DisplayImageOption对象


        return DisplayImageOptions;

    }
    public DisplayImageOptions setcenterOptions(){
        DisplayImageOptions DisplayImageOptions = null;
        DisplayImageOptions = new  DisplayImageOptions.Builder()//
                .showStubImage(R.mipmap.nopic) // 设置图片下载期间显示的图片.cacheInMemory(true)//
                .cacheOnDisk(true)//
                .bitmapConfig(Bitmap.Config.RGB_565)//
                .build();
        return DisplayImageOptions;

    }
    public DisplayImageOptions setnoOptions(){
        DisplayImageOptions DisplayImageOptions = null;
        DisplayImageOptions = new DisplayImageOptions.Builder()
                .showStubImage(R.mipmap.nopic) // 设置图片下载期间显示的图片
                .showImageForEmptyUri(R.mipmap.nopic) // 设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.mipmap.nopic) // 设置图片加载或解码过程中发生错误显示的图片
                .cacheInMemory(true).imageScaleType(ImageScaleType.EXACTLY_STRETCHED) // 设置下载的图片是否缓存在内存中
                .cacheOnDisc(true) .displayer(new RoundedBitmapDisplayer(0))// 设置下载的图片是否缓存在SD卡中
                .build(); // 创建配置过得DisplayImageOption对象


        return DisplayImageOptions;

    }
    public DisplayImageOptions setCirclelmageOptions(){
        DisplayImageOptions DisplayImageOptions = null;
        DisplayImageOptions = new DisplayImageOptions.Builder()
                .showStubImage(R.mipmap.my_face_icon) // 设置图片下载期间显示的图片
                .showImageForEmptyUri(R.mipmap.my_face_icon) // 设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.mipmap.my_face_icon) // 设置图片加载或解码过程中发生错误显示的图片
                .cacheInMemory(true) // 设置下载的图片是否缓存在内存中
                .cacheOnDisc(true) .bitmapConfig(Bitmap.Config.RGB_565)// // 设置下载的图片是否缓存在SD卡中
                .displayer(new CircleBitmapDisplayer()) // 设置成圆角图片
                .build(); // 创建配置过得DisplayImageOption对象
        return DisplayImageOptions;
    }
    public DisplayImageOptions setqidongOptions(){
        DisplayImageOptions DisplayImageOptions = null;
        DisplayImageOptions = new DisplayImageOptions.Builder()
//                .showStubImage(R.mipmap.app) // 设置图片下载期间显示的图片
//                .showImageForEmptyUri(R.mipmap.app) // 设置图片Uri为空或是错误的时候显示的图片
//                .showImageOnFail(R.mipmap.app) // 设置图片加载或解码过程中发生错误显示的图片
                .cacheInMemory(true) // 设置下载的图片是否缓存在内存中
                .cacheOnDisc(true) // 设置下载的图片是否缓存在SD卡中
                .displayer(new RoundedBitmapDisplayer(0)) // 设置成圆角图片
                .build(); // 创建配置过得DisplayImageOption对象


        return DisplayImageOptions;

    }
    public static class AnimateFirstDisplayListener extends
            SimpleImageLoadingListener {

        static final List<String> displayedImages = Collections
                .synchronizedList(new LinkedList<String>());

        @Override
        public void onLoadingComplete(String imageUri, View view,
                                      Bitmap loadedImage) {
            if (loadedImage != null) {
                ImageView imageView = (ImageView) view;
                // 是否第一次显示
                boolean firstDisplay = !displayedImages.contains(imageUri);
                if (firstDisplay) {
                    // 图片淡入效果
                    FadeInBitmapDisplayer.animate(imageView, 500);
                    displayedImages.add(imageUri);
                }
            }
        }
    }
    public static class AnimateFirstDisplayListeners extends
            SimpleImageLoadingListener {

        static final List<String> displayedImages = Collections
                .synchronizedList(new LinkedList<String>());

        @Override
        public void onLoadingComplete(String imageUri, View view,
                                      Bitmap loadedImage) {
            if (loadedImage != null) {
                ImageView imageView = (ImageView) view;
                // 是否第一次显示
                boolean firstDisplay = !displayedImages.contains(imageUri);
                if (firstDisplay) {
                    // 图片淡入效果
//                    FadeInBitmapDisplayer.animate(imageView, 10);
                    displayedImages.add(imageUri);
                }
            }
        }
    }
    public boolean fileIsExists(String name){
        try{
            File f=new File(name);
            if(!f.exists()){
                return false;
            }

        }catch (Exception e) {
            // TODO: handle exception
            return false;
        }
        return true;
    }
}
