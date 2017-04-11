package x.taihangOA.com.JsonMordel;

import java.util.List;

/**
 * Created by admins on 2016/6/27.
 */
public class MessageList {

    /**
     * ret : 200
     * data : {"code":0,"msg":"","info":[{"id":"4","title":"ddd","content":"ddd","create_time":"1466824413","shopname":"怀府网"}]}
     * msg :
     */

    private int ret;
    /**
     * code : 0
     * msg :
     * info : [{"id":"4","title":"ddd","content":"ddd","create_time":"1466824413","shopname":"怀府网"}]
     */

    private DataBean data;
    private String msg;

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
        private int code;
        private String msg;
        /**
         * id : 4
         * title : ddd
         * content : ddd
         * create_time : 1466824413
         * shopname : 怀府网
         */

        private List<InfoBean> info;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public List<InfoBean> getInfo() {
            return info;
        }

        public void setInfo(List<InfoBean> info) {
            this.info = info;
        }

        public static class InfoBean {
            private String id;
            private String title;
            private String content;
            private String create_time;
            private String shopname;
            private String xqname;
            private String kan;
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

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getShopname() {
                return shopname;
            }

            public void setShopname(String shopname) {
                this.shopname = shopname;
            }

            public String getXqname() {
                return xqname;
            }

            public void setXqname(String xqname) {
                this.xqname = xqname;
            }

            public String getKan() {
                return kan;
            }

            public void setKan(String kan) {
                this.kan = kan;
            }
        }
    }
}
