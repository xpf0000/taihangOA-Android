package model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by X on 2016/12/8.
 */

public class QuanModel implements Serializable{


    /**
     * id : 9734
     * content : 看见了
     * comment : 0
     * zan : 2
     * create_time : 1479553649
     * category_id : 77
     * title : 购物秀
     * nickname : cheng
     * headimage : http://7xotdy.com2.z0.glb.qiniucdn.com/2016-06-27_5770e17be8054.jpg
     * uid : 5
     * sex : 1
     * picList : [{"url":"http://7xotdy.com2.z0.glb.qiniucdn.com/2016-11-19_58303271be224.jpg","width":"540","height":"960"}]
     * zanList : [{"nickname":"cheng"},{"nickname":"刘小明、"}]
     * commentList : []
     * orzan : 1
     */

    private String id;
    private String content;
    private String comment;
    private String zan;
    private String create_time;
    private String category_id;
    private String title;
    private String nickname;
    private String headimage;
    private String uid;
    private String sex;
    private int orzan;
    private List<PicListBean> picList;
    private List<ZanListBean> zanList;
    private List<?> commentList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getZan() {
        return zan;
    }

    public void setZan(String zan) {
        this.zan = zan;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getOrzan() {
        return orzan;
    }

    public void setOrzan(int orzan) {
        this.orzan = orzan;
    }

    public List<PicListBean> getPicList() {
        return picList;
    }

    public void setPicList(List<PicListBean> picList) {
        this.picList = picList;
    }

    public List<ZanListBean> getZanList() {
        return zanList;
    }

    public void setZanList(List<ZanListBean> zanList) {
        this.zanList = zanList;
    }

    public List<?> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<?> commentList) {
        this.commentList = commentList;
    }

    public static class PicListBean {
        /**
         * url : http://7xotdy.com2.z0.glb.qiniucdn.com/2016-11-19_58303271be224.jpg
         * width : 540
         * height : 960
         */

        private String url;
        private String width;
        private String height;

        public String getUrl() {
            url = url == null ? "" : url;
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getWidth() {
            width = width == null ? "" : width;
            return width;
        }

        public void setWidth(String width) {
            this.width = width;
        }

        public String getHeight() {
            height = height == null ? "" : height;
            return height;
        }

        public void setHeight(String height) {
            this.height = height;
        }
    }

    public static class ZanListBean {
        /**
         * nickname : cheng
         */

        private String nickname;

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }
    }
}
