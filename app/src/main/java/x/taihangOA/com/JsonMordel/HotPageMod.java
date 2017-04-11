package x.taihangOA.com.JsonMordel;

import java.util.List;

/**
 * Created by admins on 2016/8/18.
 */
public class HotPageMod {

    /**
     * ret : 200
     * data : {"code":0,"msg":"","info":[{"id":"7388","content":"老九门","create_time":"1471495349","nickname":"丸子","url":"http://7xotdy.com2.z0.glb.qiniucdn.com/2016-08-18_57b53cad7fec0.jpg","width":"1440","height":"1080","headimage":"http://qzapp.qlogo.cn/qzapp/1105030226/DDBF584B66281D511F4B1437DF1FDC99/100","uid":"151"},{"id":"7387","content":"比我原来的手机好多啦","create_time":"1471486220","nickname":"淹死的鱼","url":"http://7xotdy.com2.z0.glb.qiniucdn.com/2016-08-18_57b51907748e3.jpg","width":"747","height":"1328","headimage":"http://7xotdy.com2.z0.glb.qiniucdn.com/2016-01-04_568a4d79e96d0.jpg","uid":"154"},{"id":"7386","content":"这就尴尬了","create_time":"1471483089","nickname":"blue skies","url":"http://7xotdy.com2.z0.glb.qiniucdn.com/2016-08-18_57b50cd1b0a5d.jpg","width":"798","height":"315","headimage":"http://7xotdy.com2.z0.glb.qiniucdn.com/2016-08-13_57af0b43650d5.jpg","uid":"677"},{"id":"7385","content":"早上好！","create_time":"1471477541","nickname":"怀府人","url":"http://7xotdy.com2.z0.glb.qiniucdn.com/2016-08-18_57b4f725bc3ea.jpg","width":"612","height":"816","headimage":"http://7xotdy.com2.z0.glb.qiniucdn.com/2015-12-30_5683dbed9b8d8.jpg","uid":"155"},{"id":"7389","content":"要赶快学会了，怀府网打响第一枪","create_time":"1471502895","nickname":"。陌生","url":"http://7xotdy.com2.z0.glb.qiniucdn.com/2016-08-18_57b55a2fd5074.jpg","width":"640","height":"1136","headimage":"http://7xotdy.com2.z0.glb.qiniucdn.com/2016-01-20_569f2c30dce53.jpg","uid":"216"},{"id":"7380","content":"睡醒了出来吓吓你们！啦啦啦","create_time":"1471410427","nickname":"阿拉蕾","url":"http://7xotdy.com2.z0.glb.qiniucdn.com/2016-08-17_57b3f0fada069.jpg","width":"612","height":"816","headimage":"http://qzapp.qlogo.cn/qzapp/1105030226/584FDE37FCF6E84F198500FB4666124D/100","uid":"141"},{"id":"7384","content":"休息一天真舒服","create_time":"1471443172","nickname":"。陌生","url":"http://7xotdy.com2.z0.glb.qiniucdn.com/2016-08-17_57b470e3dac8c.jpg","width":"440","height":"781","headimage":"http://7xotdy.com2.z0.glb.qiniucdn.com/2016-01-20_569f2c30dce53.jpg","uid":"216"},{"id":"7383","content":"野生的菇茑\u2026\u2026","create_time":"1471442323","nickname":"老鼠","url":"http://7xotdy.com2.z0.glb.qiniucdn.com/2016-08-17_57b46d9152783.jpg","width":"2104","height":"1184","headimage":"http://qzapp.qlogo.cn/qzapp/1105030226/1E1F49B8C3348C7BDB9C4DA449A8933D/100","uid":"136"},{"id":"7382","content":"闲着看场电影吧！","create_time":"1471428969","nickname":"阿拉蕾","url":"http://7xotdy.com2.z0.glb.qiniucdn.com/2016-08-17_57b439690902f.jpg","width":"780","height":"1040","headimage":"http://qzapp.qlogo.cn/qzapp/1105030226/584FDE37FCF6E84F198500FB4666124D/100","uid":"141"},{"id":"7379","content":"这职业好啊","create_time":"1471404560","nickname":"落魄的自由","url":"http://7xotdy.com2.z0.glb.qiniucdn.com/2016-08-17_57b3da102e1e1.jpg","width":"1456","height":"1080","headimage":"http://7xotdy.com2.z0.glb.qiniucdn.com/2015-12-31_5684f39a6d9ba.jpg","uid":"160"}]}
     * msg :
     */

    private int ret;
    /**
     * code : 0
     * msg :
     * info : [{"id":"7388","content":"老九门","create_time":"1471495349","nickname":"丸子","url":"http://7xotdy.com2.z0.glb.qiniucdn.com/2016-08-18_57b53cad7fec0.jpg","width":"1440","height":"1080","headimage":"http://qzapp.qlogo.cn/qzapp/1105030226/DDBF584B66281D511F4B1437DF1FDC99/100","uid":"151"},{"id":"7387","content":"比我原来的手机好多啦","create_time":"1471486220","nickname":"淹死的鱼","url":"http://7xotdy.com2.z0.glb.qiniucdn.com/2016-08-18_57b51907748e3.jpg","width":"747","height":"1328","headimage":"http://7xotdy.com2.z0.glb.qiniucdn.com/2016-01-04_568a4d79e96d0.jpg","uid":"154"},{"id":"7386","content":"这就尴尬了","create_time":"1471483089","nickname":"blue skies","url":"http://7xotdy.com2.z0.glb.qiniucdn.com/2016-08-18_57b50cd1b0a5d.jpg","width":"798","height":"315","headimage":"http://7xotdy.com2.z0.glb.qiniucdn.com/2016-08-13_57af0b43650d5.jpg","uid":"677"},{"id":"7385","content":"早上好！","create_time":"1471477541","nickname":"怀府人","url":"http://7xotdy.com2.z0.glb.qiniucdn.com/2016-08-18_57b4f725bc3ea.jpg","width":"612","height":"816","headimage":"http://7xotdy.com2.z0.glb.qiniucdn.com/2015-12-30_5683dbed9b8d8.jpg","uid":"155"},{"id":"7389","content":"要赶快学会了，怀府网打响第一枪","create_time":"1471502895","nickname":"。陌生","url":"http://7xotdy.com2.z0.glb.qiniucdn.com/2016-08-18_57b55a2fd5074.jpg","width":"640","height":"1136","headimage":"http://7xotdy.com2.z0.glb.qiniucdn.com/2016-01-20_569f2c30dce53.jpg","uid":"216"},{"id":"7380","content":"睡醒了出来吓吓你们！啦啦啦","create_time":"1471410427","nickname":"阿拉蕾","url":"http://7xotdy.com2.z0.glb.qiniucdn.com/2016-08-17_57b3f0fada069.jpg","width":"612","height":"816","headimage":"http://qzapp.qlogo.cn/qzapp/1105030226/584FDE37FCF6E84F198500FB4666124D/100","uid":"141"},{"id":"7384","content":"休息一天真舒服","create_time":"1471443172","nickname":"。陌生","url":"http://7xotdy.com2.z0.glb.qiniucdn.com/2016-08-17_57b470e3dac8c.jpg","width":"440","height":"781","headimage":"http://7xotdy.com2.z0.glb.qiniucdn.com/2016-01-20_569f2c30dce53.jpg","uid":"216"},{"id":"7383","content":"野生的菇茑\u2026\u2026","create_time":"1471442323","nickname":"老鼠","url":"http://7xotdy.com2.z0.glb.qiniucdn.com/2016-08-17_57b46d9152783.jpg","width":"2104","height":"1184","headimage":"http://qzapp.qlogo.cn/qzapp/1105030226/1E1F49B8C3348C7BDB9C4DA449A8933D/100","uid":"136"},{"id":"7382","content":"闲着看场电影吧！","create_time":"1471428969","nickname":"阿拉蕾","url":"http://7xotdy.com2.z0.glb.qiniucdn.com/2016-08-17_57b439690902f.jpg","width":"780","height":"1040","headimage":"http://qzapp.qlogo.cn/qzapp/1105030226/584FDE37FCF6E84F198500FB4666124D/100","uid":"141"},{"id":"7379","content":"这职业好啊","create_time":"1471404560","nickname":"落魄的自由","url":"http://7xotdy.com2.z0.glb.qiniucdn.com/2016-08-17_57b3da102e1e1.jpg","width":"1456","height":"1080","headimage":"http://7xotdy.com2.z0.glb.qiniucdn.com/2015-12-31_5684f39a6d9ba.jpg","uid":"160"}]
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
         * id : 7388
         * content : 老九门
         * create_time : 1471495349
         * nickname : 丸子
         * url : http://7xotdy.com2.z0.glb.qiniucdn.com/2016-08-18_57b53cad7fec0.jpg
         * width : 1440
         * height : 1080
         * headimage : http://qzapp.qlogo.cn/qzapp/1105030226/DDBF584B66281D511F4B1437DF1FDC99/100
         * uid : 151
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
            private String content;
            private String create_time;
            private String nickname;
            private String url;
            private String width;
            private String height;
            private String headimage;
            private String uid;

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

            public String getCreate_time() {
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

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getWidth() {
                return width;
            }

            public void setWidth(String width) {
                this.width = width;
            }

            public String getHeight() {
                return height;
            }

            public void setHeight(String height) {
                this.height = height;
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
        }
    }
}
