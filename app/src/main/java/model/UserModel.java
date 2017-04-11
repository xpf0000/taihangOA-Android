package model;

import com.alibaba.sdk.android.push.CommonCallback;
import com.alibaba.sdk.android.push.noonesdk.PushServiceFactory;

import org.greenrobot.eventbus.EventBus;

import java.io.Serializable;

import util.HttpResult;
import util.ModelUtil;
import util.XAPPUtil;
import util.XNetUtil;
import util.XNotificationCenter;
import x.taihangOA.com.Utils.MyEventBus;

import static x.taihangOA.com.MyAppService.LocationApplication.APPService;

/**
 * Created by X on 2016/11/4.
 */

public class UserModel implements Serializable {


    /**
     * id : 18
     * sex : null
     * mobile : 555555
     * departmentid : 20
     * name : no auth
     * avatar : http://images.ihlpp.cn/2017-03-28_58da0a1490fe7.jpg
     * user_status : 2
     * dname : 部门A
     */

    private String id;
    private Integer sex;
    private String mobile;
    private String departmentid;
    private String name;
    private String avatar;
    private String user_status;
    private String dname;
    /**
     * account : 17719226070
     * sex : null
     */

    private String account;

    private String token;

    public String getToken() {
        token = token == null ? "" : token;
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getId() {
        id = id == null ? "" : id;
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getSex() {
        sex = sex == null ? 0 : sex;
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDepartmentid() {
        return departmentid;
    }

    public void setDepartmentid(String departmentid) {
        this.departmentid = departmentid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUser_status() {
        return user_status;
    }

    public void setUser_status(String user_status) {
        this.user_status = user_status;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "id='" + id + '\'' +
                ", sex=" + sex +
                ", mobile='" + mobile + '\'' +
                ", departmentid='" + departmentid + '\'' +
                ", name='" + name + '\'' +
                ", avatar='" + avatar + '\'' +
                ", user_status='" + user_status + '\'' +
                ", dname='" + dname + '\'' +
                '}';
    }


    public boolean checkIsLogin()
    {
        return getId() != null && !getId().equals("");
    }

    public void reSet()
    {
        ModelUtil.reSet(this);
        save();
    }

    public void save()
    {
        XNetUtil.APPPrintln(this.toString());
        boolean b = XAPPUtil.SaveAPPCache("UserModel",this);
        XNetUtil.APPPrintln("保存Model: "+b);
    }

    public String getAccount() {
        account = account == null ? "" : account;
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }





    public void unRegistNotice()
    {
        if(getId() == null)
        {
            return;
        }

        PushServiceFactory.getCloudPushService().removeAlias(getId(),new CommonCallback() {
            @Override
            public void onSuccess(String s) {
                XNetUtil.APPPrintln("removeAlias success!!!!!!");
            }

            @Override
            public void onFailed(String s, String s1) {
                XNetUtil.APPPrintln("removeAlias fail!!!!!! "+s+" | "+s1);
            }
        });
    }

    public void registNotice()
    {
        if(getId() == null)
        {
            return;
        }

        PushServiceFactory.getCloudPushService().addAlias(getId(), new CommonCallback() {
            @Override
            public void onSuccess(String s) {
                XNetUtil.APPPrintln("addAlias success!!!!!!");
            }

            @Override
            public void onFailed(String s, String s1) {
                XNetUtil.APPPrintln("addAlias fail!!!!!! "+s+" | "+s1);
            }
        });

    }



    public void checkToken()
    {
        if(getToken().equals("") || getAccount().equals("") || getId().equals(""))
        {
            return;
        }

        XNetUtil.HandleReturnAll(APPService.UserChecktoken(getId(),getAccount(),getToken()), new XNetUtil.OnHttpResult<HttpResult<Object>>() {

            @Override
            public void onError(Throwable e) {

                XNetUtil.APPPrintln("checkToken error !!!!!!!!!!");
                XNetUtil.APPPrintln(e);
            }

            @Override
            public void onSuccess(HttpResult<Object> res) {

                if(res.getData().getCode() == 1)
                {
                    EventBus.getDefault().post(new MyEventBus("AccountLogout"));
                }
            }
        });


    }


}
