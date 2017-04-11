package util;
import com.robin.lazy.cache.CacheLoaderManager;

import model.OAModel;
import model.UserModel;

/**
 * Created by X on 2016/10/2.
 */

public class DataCache {

    public UserModel User = new UserModel();
    public OAModel OAUser = new OAModel();

    public int land = 0;
    public boolean msgshow = false;

    public DataCache() {

        UserModel model= CacheLoaderManager.getInstance().loadSerializable("UserModel");
        OAModel oaModel = CacheLoaderManager.getInstance().loadSerializable("OAUserModel");

        if(oaModel != null)
        {
            OAUser = oaModel;
        }

        if(model != null)
        {
            XNetUtil.APPPrintln("UserModel readed!!!!!!!!!!!!!");
            User = model;
//            User.getUinfo();
//            User.getUser();
//            User.getMsgCount();
//            if(!User.getUid().equals(""))
//            {
//                land = 1;
//            }

        }
        else
        {
            ModelUtil.reSet(User);
        }

        XNetUtil.APPPrintln("User: "+User.toString());

    }
}
