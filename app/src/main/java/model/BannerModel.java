package model;

/**
 * Created by X on 2016/10/1.
 */

public class BannerModel {


    /**
     * picurl : http://7xotdz.com2.z0.glb.qiniucdn.com/2016-07-26_5796d6bda81bf.jpg
     * url : http://wap.huaifuwang.com/city/news_info.php?id=5717&type=1
     * title : 推荐
     */

    private String picurl;
    private String url;
    private String title;



    public String getPicurl() {
        return picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "BannerModel{" +
                "picurl='" + picurl + '\'' +
                ", url='" + url + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
