package model;

import java.io.Serializable;

/**
 * Created by X on 2016/12/19.
 */

public class ActityModel implements Serializable {


    /**
     * id : 6897
     * title : 凭会员卡可享受8折优惠
     * tel : 5281100
     * address : 合庆楼
     * content : <p>
     领取会员卡用户可享受8折优惠
     </p>
     * s_time : 1481558400
     * e_time : 1483027200
     * url : http://7xotdz.com2.z0.glb.qiniucdn.com/2016-12-13_584f7034793ae.jpg
     * view : 3
     */

    private String id;
    private String title;
    private String tel;
    private String address;
    private String content;
    private String s_time;
    private String e_time;
    private String url;
    private String view;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getS_time() {
        return s_time;
    }

    public void setS_time(String s_time) {
        this.s_time = s_time;
    }

    public String getE_time() {
        return e_time;
    }

    public void setE_time(String e_time) {
        this.e_time = e_time;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }
}
