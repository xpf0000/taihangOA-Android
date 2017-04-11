package x.taihangOA.com.MyViews;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import x.taihangOA.com.R;

/**
 * Created by admins on 2015/11/19.
 */
public class CallPhonePop {
    private View  popView;
    PopupWindow popupWindow;
    public void showpop(final Activity activity,final  String number) {
        popView = activity.getLayoutInflater().inflate(
                R.layout.setprogramme, null);
        WindowManager windowManager = activity.getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        popupWindow = new PopupWindow(popView, LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.FILL_PARENT);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.update();
        popupWindow.setAnimationStyle(R.style.PopupAnimation);
        popupWindow.showAtLocation(popView,
                Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        TextView textView=(TextView)popView.findViewById(R.id.number);
        textView.setText(number);
        TextView call=(TextView)popView.findViewById(R.id.call);
        TextView ext=(TextView)popView.findViewById(R.id.ext);
        ext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("tel:" + number);
                Intent intent = new Intent(Intent.ACTION_DIAL, uri);
                activity.startActivity(intent);
                popupWindow.dismiss();
            }
        });
    }
}
