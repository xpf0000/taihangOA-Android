package x.taihangOA.com.JsonMordel;

import java.util.List;

/**
 * Created by admins on 2016/6/16.
 */
public class ShopinfoMo {

    /**
     * ret : 200
     * data : {"code":0,"msg":"","info":[{"logo":"http://7xoljw.com1.z0.glb.clouddn.com/2015-11-25_56556403b25ac.jpg","shopname":"怀府网","tel":"123456","address":"aaaa","info":null,"id":"1"}]}
     * msg :
     */

    private int ret;
    /**
     * code : 0
     * msg :
     * info : [{"logo":"http://7xoljw.com1.z0.glb.clouddn.com/2015-11-25_56556403b25ac.jpg","shopname":"怀府网","tel":"123456","address":"aaaa","info":null,"id":"1"}]
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
         * logo : http://7xoljw.com1.z0.glb.clouddn.com/2015-11-25_56556403b25ac.jpg
         * shopname : 怀府网
         * tel : 123456
         * address : aaaa
         * info : null
         * id : 1
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
            private String logo;
            private String shopname;
            private String tel;
            private String address;
            private String info;
            private String id;
            private String orvip;
            private String  viplevel = "";

            public String getViplevel() {
                return viplevel;
            }

            public void setViplevel(String viplevel) {
                this.viplevel = viplevel;
            }

            public String getOrvip() {
                return orvip;
            }

            public void setOrvip(String orvip) {
                this.orvip = orvip;
            }

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }

            public String getShopname() {
                return shopname;
            }

            public void setShopname(String shopname) {
                this.shopname = shopname;
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

            public String getInfo() {
                return info;
            }

            public void setInfo(String info) {
                this.info = info;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }
        }
    }
}
