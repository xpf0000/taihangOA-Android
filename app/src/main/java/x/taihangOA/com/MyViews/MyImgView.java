package x.taihangOA.com.MyViews;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import x.taihangOA.com.Utils.BitmapCenter;

/**
 * Created by admins on 2015/12/6.
 */
public class MyImgView extends ImageView{
    BitmapCenter bitmapCenter;
    public MyImgView(Context context, AttributeSet attrs) {
        super(context, attrs);
        bitmapCenter=new BitmapCenter();
    }

    @Override
    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        BitmapDrawable bd = (BitmapDrawable) drawable;
        Bitmap bitmap = bd.getBitmap();
        bitmapCenter.BitmapCenter(bitmap,200);
    }
}
