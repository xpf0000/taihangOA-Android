package model;

import java.io.Serializable;

import util.DateUtils;

/**
 * Created by X on 2016/11/8.
 */

public class RenzhengModel implements Serializable {

    private String id = "";
    private String vip_time = "";
    private String content = "";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVip_time() {
        if(!vip_time.contains("年"))
        {
            vip_time = DateUtils.unixToStr(vip_time,"yyyy年MM月dd日");
        }
        return vip_time;
    }

    public void setVip_time(String vip_time) {
        this.vip_time = vip_time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
