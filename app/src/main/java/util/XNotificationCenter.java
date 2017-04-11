package util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by X on 2016/10/6.
 */
public class XNotificationCenter implements Serializable {

    public interface OnNoticeListener
    {
        void OnNotice(Object obj);
    }

    private Map<String,OnNoticeListener> arr = new HashMap<>();

    private static class SingletonHolder {
        /**
         * 单例对象实例
         */
        static final XNotificationCenter INSTANCE = new XNotificationCenter();
    }

    public static XNotificationCenter getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * private的构造函数用于避免外界直接使用new来实例化对象
     */
    private XNotificationCenter() {
    }

    /**
     * readResolve方法应对单例对象被序列化时候
     */
    private Object readResolve() {
        return getInstance();
    }

    public void addObserver(String key,OnNoticeListener listener)
    {
        arr.remove(key);
        arr.put(key,listener);

        XNetUtil.APPPrintln("OnNoticeListener: "+listener);
        XNetUtil.APPPrintln("map value: "+arr.get(key));
    }

    public void postNotice(String key,Object obj)
    {

        XNetUtil.APPPrintln("postNotice map value: "+arr.get(key));

        OnNoticeListener listener = arr.get(key);
        if(listener != null)
        {
            listener.OnNotice(obj);
        }

    }

    public void removeObserver(String key)
    {
        arr.remove(key);
    }


}
