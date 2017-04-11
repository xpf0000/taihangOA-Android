package util;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;

import org.greenrobot.eventbus.EventBus;

import x.taihangOA.com.Activity.Login;
import x.taihangOA.com.Activity.MainActivity;
import x.taihangOA.com.Activity.MapVC;
import x.taihangOA.com.R;
import x.taihangOA.com.Utils.MyEventBus;
import model.UserModel;

import static x.taihangOA.com.MyAppService.LocationApplication.APPDataCache;

/**
 * Created by Administrator on 2017/3/31 0031.
 */

public class HandleJSMsg {

    public static void handle(JSONObject obj, Activity vc)
    {
        Integer type=obj.getInteger("type");
        String msg=obj.getString("msg");

        if(type == 0)  //url 跳转
        {
            if(!CountTime.isBeyoundTime("启动界面", 300)){
                return;
            }

            String url = obj.getString("url");

            Bundle bundle = new Bundle();
            bundle.putString("url","file:///android_asset/html/"+url);

            if(vc instanceof XHtmlVC)
            {
                ((XHtmlVC) vc).pushVC(XHtmlVC.class,bundle);
            }
            else
            {
                pushVC(vc,bundle);
            }

        }
        else if(type == 1)  //返回
        {
            if(obj.getString("back") != null)
            {
                EventBus.getDefault().post(new MyEventBus("logout"));
            }
            else
            {
                vc.finish();
            }

        }
        else if(type == 2)  //登录成功
        {
            if(msg.equals("登录成功"))
            {

                JSONObject info=obj.getJSONObject("info");
                UserModel user = JSON.toJavaObject(info,UserModel.class);
                APPDataCache.User = user;
                APPDataCache.User.save();

                APPDataCache.User.registNotice();

                XNetUtil.APPPrintln(user.toString());

                Intent Intent = new Intent();
                Intent.setClass(vc, MainActivity.class);
                vc.startActivity(Intent);
                vc.finish();

            }

            if(msg.equals("退出登录"))
            {
                EventBus.getDefault().post(new MyEventBus("logout"));
            }

        }
        else if(type == 3)  //时间日期选择
        {
            EventBus.getDefault().post(new MyEventBus("showTimePicker"));
        }
        else if(type == 4)  //地图选择
        {
            Intent Intent = new Intent();
            Intent.setClass(vc, MapVC.class);
            vc.startActivity(Intent);

            ((XHtmlVC)vc).setMapFlag(obj.getString("flag"));

        }
        else if(type == 5)  //地图选择
        {
            if(msg.equals("车辆申请添加成功"))
            {
                vc.finish();
            }

            EventBus.getDefault().post(new MyEventBus("AddCarTaskSuccess"));
        }
        else if(type == 6)  //物品选择完成
        {
            JSONArray arr = obj.getJSONArray("info");
            XNotificationCenter.getInstance().postNotice("ResChoose",arr);
            vc.finish();
        }
        else if(type == 7)  //物品申请添加成功
        {
            if(msg.equals("物品申请添加成功"))
            {
                vc.finish();
            }
            EventBus.getDefault().post(new MyEventBus("AddResTaskSuccess"));
        }
        else if(type == 8)  //待办事项操作成功
        {
            EventBus.getDefault().post(new MyEventBus("DaibanActionSuccess"));
        }
        else if(type == 9)  //待办人选择完成
        {
            JSONObject arr = obj.getJSONObject("info");
            XNotificationCenter.getInstance().postNotice("DaibanChoose",arr);
            vc.finish();
        }
        else if(type == 10)  //物品申请添加成功
        {
            if(msg.equals("督查督办添加成功"))
            {
                vc.finish();
            }
            EventBus.getDefault().post(new MyEventBus("AddOverseerTaskSuccess"));
        }

        else if(type == 11)  //用户头像上传
        {
            EventBus.getDefault().post(new MyEventBus("UserHeadImageEdit"));
        }

        else if(type == 12)  //待办事项总数
        {
            int count = obj.getInteger("info");
            XNotificationCenter.getInstance().postNotice("DaibanCount",count);
        }

        else if(type == 13)  //用户手机号更新成功
        {
            EventBus.getDefault().post(new MyEventBus("UserUpdateMobile"));
        }

        else if(type == 14)  //版本升级
        {
            EventBus.getDefault().post(new MyEventBus("CheckVersion"));
        }

    }

    public static void pushVC(Activity vc,Bundle bundle)
    {
        Intent intentActive = new Intent(vc, XHtmlVC.class);
        //用Bundle携带数据
        bundle.putBoolean("isPush", true);
        intentActive.putExtras(bundle);
        vc.startActivity(intentActive);
        vc.overridePendingTransition(R.anim.push_left_in,R.anim.push_left_out);
    }

    public static void presentModelVC(Activity vc,Bundle bundle)
    {
        Intent intentActive = new Intent(vc, XHtmlVC.class);
        //用Bundle携带数据
        bundle.putBoolean("isPush", false);
        intentActive.putExtras(bundle);
        vc.startActivity(intentActive);
        vc.overridePendingTransition(R.anim.push_up_in,R.anim.push_up_out);
    }


}
