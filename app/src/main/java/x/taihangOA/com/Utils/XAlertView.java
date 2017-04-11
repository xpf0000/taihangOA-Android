package x.taihangOA.com.Utils;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import java.lang.ref.WeakReference;

import x.taihangOA.com.R;
import util.XNetUtil;

/**
 * Created by X on 2016/12/8.
 */

public class XAlertView {

    private WeakReference<Context> contextWeak;
    private ViewGroup decorView;//activity的根View
    private ViewGroup rootView;//AlertView 的 根View
    private LinearLayout alertimg;//窗口headerView

    private boolean isShowing;

    private Animation outAnim;
    private Animation inAnim;
    private int gravity = Gravity.CENTER;

    public XAlertView(Context context) {
        this.contextWeak = new WeakReference<>(context);
        inAnim = getInAnimation();
        outAnim = getOutAnimation();
        initViews();
    }


    protected void initViews(){

        Context context = contextWeak.get();
        if(context == null) return;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        decorView = (ViewGroup) ((Activity)context).getWindow().getDecorView().findViewById(android.R.id.content);
        rootView = (ViewGroup) layoutInflater.inflate(R.layout.qd_success_alertview, decorView, false);
        rootView.setLayoutParams(new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
        ));

        XNetUtil.APPPrintln(rootView);

        alertimg = (LinearLayout) rootView.findViewById(R.id.alert_img);

        XNetUtil.APPPrintln(alertimg);

    }


    private Animation.AnimationListener outAnimListener = new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            dismissImmediately();
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    };


    public void show() {
        if (isShowing()) {
            return;
        }
        onAttached(rootView);
    }

    private void onAttached(View view) {
        isShowing = true;
        decorView.addView(view);
        alertimg.startAnimation(inAnim);

        handler.postDelayed(runnable, 1500);

    }

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            dismiss();
        }
    };


    public boolean isShowing() {
        return rootView.getParent() != null && isShowing;
    }

    public void dismiss() {
        //消失动画
        outAnim.setAnimationListener(outAnimListener);
        alertimg.startAnimation(outAnim);
    }

    public void dismissImmediately() {
        decorView.removeView(rootView);
        isShowing = false;
    }

    public Animation getInAnimation() {
        Context context = contextWeak.get();
        if(context == null) return null;

        int res = getAnimationResource(this.gravity, true);
        return AnimationUtils.loadAnimation(context, res);
    }

    public Animation getOutAnimation() {
        Context context = contextWeak.get();
        if(context == null) return null;

        int res = getAnimationResource(this.gravity, false);
        return AnimationUtils.loadAnimation(context, res);
    }


    private int getAnimationResource(int gravity, boolean isInAnimation) {
        switch (gravity) {
            case Gravity.BOTTOM:
                return isInAnimation ? com.bigkoo.alertview.R.anim.slide_in_bottom : com.bigkoo.alertview.R.anim.slide_out_bottom;
            case Gravity.CENTER:
                return isInAnimation ? com.bigkoo.alertview.R.anim.fade_in_center : com.bigkoo.alertview.R.anim.fade_out_center;
        }
        return -1;
    }

}
