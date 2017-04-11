package model;

import java.io.Serializable;

/**
 * Created by X on 2016/11/13.
 */

public class GroupModel implements Serializable {

    private String  id = "";
    private String  name = "";
    private String  jdinfo = "";
    private String  url = "";
    private String  viplevel = "";

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

    public String getJdinfo() {
        return jdinfo;
    }

    public void setJdinfo(String jdinfo) {
        this.jdinfo = jdinfo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getViplevel() {
        return viplevel;
    }

    public void setViplevel(String viplevel) {
        this.viplevel = viplevel;
    }
}
