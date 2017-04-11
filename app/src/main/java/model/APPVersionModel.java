package model;

import java.io.Serializable;

/**
 * Created by X on 2016/12/21.
 */

public class APPVersionModel implements Serializable {


    /**
     * id : 2
     * versionname : 1.0.1
     * versioncode : 0
     * url : sdfsaf
     * description : asdfasf
     */

    private String id;
    private String versionname;
    private String versioncode;
    private String url;
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVersionname() {
        return versionname;
    }

    public void setVersionname(String versionname) {
        this.versionname = versionname;
    }

    public String getVersioncode() {
        return versioncode;
    }

    public void setVersioncode(String versioncode) {
        this.versioncode = versioncode;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
