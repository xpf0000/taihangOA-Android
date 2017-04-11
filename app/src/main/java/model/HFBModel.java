package model;

import java.io.Serializable;

import util.DateUtils;

/**
 * Created by X on 2016/11/6.
 */

public class HFBModel implements Serializable {

    private String id="";
    private String hfb="";
    private String hfbsy="";
    private String name="";
    private String create_time="";

    private String nickname = "";
    private String headimage="";
    private String pm = "";
    private String nick = "";
    private String qdday = "";

    private String type = "";
    /**
     * uid : 708
     * username : 1472789008139
     */

    private String uid;
    private String username;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHfb() {
        return hfb;
    }

    public void setHfb(String hfb) {
        this.hfb = hfb;
    }

    public String getHfbsy() {
        return hfbsy;
    }

    public void setHfbsy(String hfbsy) {
        this.hfbsy = hfbsy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreate_time() {

        if(!create_time.contains("-"))
        {
            create_time = DateUtils.unixToStr(create_time,"yyyy-MM-dd");
        }

        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHeadimage() {
        return headimage;
    }

    public void setHeadimage(String headimage) {
        this.headimage = headimage;
    }

    public String getPm() {
        return pm;
    }

    public void setPm(String pm) {
        this.pm = pm;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getQdday() {
        return qdday;
    }

    public void setQdday(String qdday) {
        this.qdday = qdday;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
