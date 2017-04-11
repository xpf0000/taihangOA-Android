package model;

import java.io.Serializable;

/**
 * Created by X on 2016/11/1.
 */

public class GoodsModel implements Serializable {


    /**
     * id : 2
     * name : 水动乐饮料
     * hfb : 30
     * url : http://7xotdz.com2.z0.glb.qiniucdn.com/2016-07-07_577dc4b4355a7.png
     */

    private String id;
    private String name;
    private String hfb;
    private String url;
    private String number;
    private String content;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHfb() {
        return hfb;
    }

    public void setHfb(String hfb) {
        this.hfb = hfb;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
