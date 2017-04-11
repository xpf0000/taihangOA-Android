package model;

import java.io.Serializable;

/**
 * Created by X on 2016/11/29.
 */

public class NewsModel implements Serializable {

    /**
     * id : 6892
     * title : 怀府币规则
     * source :
     * view : 0
     * comment : 0
     * update_time : 1480401727
     * content : <p>
     怀府币规则
     </p>
     <p>
     怀府币规则
     </p>
     <p>
     怀府币规则
     </p>
     <p>
     怀府币规则
     </p>
     <p>
     怀府币规则
     </p>
     * description :
     */

    private String id;
    private String title;
    private String source;
    private String view;
    private String comment;
    private String update_time;
    private String content;
    private String description;
    private String url;
    private String image;

    public String getImage() {
        image = image == null ? "" : image;
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUrl() {
        url = url == null ? "" : url;
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

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

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
