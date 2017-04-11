package model;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/4/10 0010.
 */

public class LanchModel implements Serializable {


    /**
     * id : 11
     * url : http://ubmcmm.baidustatic.com/media/v1/0f0000NKE5IWt0R5kOvNj0.jpg
     * width : null
     * height : null
     * type : 0
     * enable : 1
     */

    private String id;
    private String url;
    private Object width;
    private Object height;
    private String type;
    private String enable;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Object getWidth() {
        return width;
    }

    public void setWidth(Object width) {
        this.width = width;
    }

    public Object getHeight() {
        return height;
    }

    public void setHeight(Object height) {
        this.height = height;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }
}
