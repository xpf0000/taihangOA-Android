package x.taihangOA.com.JsonMordel;

import java.util.List;

/**
 * Created by admins on 2016/6/15.
 */
public class VipInfo {

    /**
     * ret : 200
     * data : {"code":0,"msg":"","info":[{"id":"12","values":"9","cardnumber":"1466578118761","color":"#e62129","logo":"http://7xoljw.com1.z0.glb.clouddn.com/2015-11-25_56556403b25ac.jpg","shopname":"怀府网","type":"计次卡","tel":"123456","address":"aaaa","info":null,"shopid":"1"}]}
     * msg :
     */

    private int ret;
    /**
     * code : 0
     * msg :
     * info : [{"id":"12","values":"9","cardnumber":"1466578118761","color":"#e62129","logo":"http://7xoljw.com1.z0.glb.clouddn.com/2015-11-25_56556403b25ac.jpg","shopname":"怀府网","type":"计次卡","tel":"123456","address":"aaaa","info":null,"shopid":"1"}]
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
         * id : 12
         * values : 9
         * cardnumber : 1466578118761
         * color : #e62129
         * logo : http://7xoljw.com1.z0.glb.clouddn.com/2015-11-25_56556403b25ac.jpg
         * shopname : 怀府网
         * type : 计次卡
         * tel : 123456
         * address : aaaa
         * info : null
         * shopid : 1
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
            private String values;
            private String cardnumber;
            private String color;
            private String logo;
            private String shopname;
            private String type;
            private String tel;
            private String address;
            private String info;
            private String shopid;
            private String jifen;
            private String cardid;

            public String getCardid() {
                return cardid;
            }

            public void setCardid(String cardid) {
                this.cardid = cardid;
            }

            public String getJifen() {
                return jifen;
            }

            public void setJifen(String jifen) {
                this.jifen = jifen;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getValues() {
                return values;
            }

            public void setValues(String values) {
                this.values = values;
            }

            public String getCardnumber() {
                return cardnumber;
            }

            public void setCardnumber(String cardnumber) {
                this.cardnumber = cardnumber;
            }

            public String getColor() {
                return color;
            }

            public void setColor(String color) {
                this.color = color;
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

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
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

            public String getShopid() {
                return shopid;
            }

            public void setShopid(String shopid) {
                this.shopid = shopid;
            }
        }
    }
}
