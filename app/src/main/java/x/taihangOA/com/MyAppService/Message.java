package x.taihangOA.com.MyAppService;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.alibaba.sdk.android.push.MessageReceiver;
import com.alibaba.sdk.android.push.notification.CPushMessage;
import com.bigkoo.alertview.AlertView;

import org.greenrobot.eventbus.EventBus;

import util.XActivityindicator;
import util.XNetUtil;
import x.taihangOA.com.Activity.Login;
import x.taihangOA.com.Utils.MyEventBus;
import util.XNotificationCenter;

import static x.taihangOA.com.MyAppService.LocationApplication.APPDataCache;

/**
 * Created by admins on 2016/8/9.
 */
public class Message extends MessageReceiver {
    @Override
    protected void onMessage(Context context, CPushMessage cPushMessage) {
        super.onMessage(context, cPushMessage);

        if(LocationApplication.context == null || !(LocationApplication.context instanceof Activity))
        {
            return;
        }

        if(cPushMessage.getTitle().equals("账号在其它设备已登录"))
        {
            if(APPDataCache.User.getToken().length() > 0 && !APPDataCache.User.getToken().equals(cPushMessage.getContent()))
            {
                EventBus.getDefault().post(new MyEventBus("AccountLogout"));
            }

            return;

        }
        else
        {
            XNetUtil.APPPrintln("^^^^^^^^^^^^^^^^999999999");

            EventBus.getDefault().post(
                    new MyEventBus("NewDaiban"));

            XActivityindicator.hide();

            AlertView Alert = new AlertView("提醒", cPushMessage.getTitle(), null, null,
                    new String[]{"确定"},
                    LocationApplication.context, AlertView.Style.Alert, null);

            XActivityindicator.setAlert(Alert);

            Alert.show();

        }







    }
}
