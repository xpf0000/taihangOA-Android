package x.taihangOA.com.MyViews;

import android.app.Activity;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import x.taihangOA.com.R;


/**
 * Created by admins on 2015/10/26.
 */
public class MyPopwindows {
    View popView;
    PopupWindow popupWindow;
    private MyPopwindowsListener mListViewListener;
    public void showpop(Activity activity,String txt){
        popView =activity.getLayoutInflater().inflate(
                R.layout.toastpop, null);
        WindowManager windowManager = activity.getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        popupWindow = new PopupWindow(popView, LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.FILL_PARENT);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.update();
        popupWindow.showAtLocation(popView,
                Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        TextView textView=(TextView)popView.findViewById(R.id.popedttxt);
        textView.setText(txt);
        Button back = (Button) popView.findViewById(R.id.back);
        Button update = (Button) popView.findViewById(R.id.update);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startLoadMore();
                popupWindow.dismiss();
            }
        });
    }
    public void setMyPopwindowswListener(MyPopwindowsListener l) {
        mListViewListener = l;
    }
    public interface MyPopwindowsListener {
        public void onRefresh();
    }
    private void startLoadMore() {

            mListViewListener.onRefresh();

    }
}
