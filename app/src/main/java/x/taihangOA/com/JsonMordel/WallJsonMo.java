package x.taihangOA.com.JsonMordel;

import java.util.List;

/**
 * Created by admins on 2016/6/21.
 */
public class WallJsonMo {

    /**
     * ret : 200
     * data : {"code":0,"msg":"","info":[{"money":"10","value":"9","create_time":"1467169957","shopname":"怀府网","xftype":"2","cardtype":"3"},{"money":"10","value":"10","create_time":"1467169957","shopname":"怀府网","xftype":"1","cardtype":"2"},{"money":"100","value":"150","create_time":"1467169957","shopname":"怀府网","xftype":"2","cardtype":"2"},{"money":"0","value":"1","create_time":"1467169957","shopname":"怀府网","xftype":"1","cardtype":"1"},{"money":"100","value":"10","create_time":"1467169957","shopname":"怀府网","xftype":"2","cardtype":"1"}]}
     * msg :
     */

    private int ret;
    /**
     * code : 0
     * msg :
     * info : [{"money":"10","value":"9","create_time":"1467169957","shopname":"怀府网","xftype":"2","cardtype":"3"},{"money":"10","value":"10","create_time":"1467169957","shopname":"怀府网","xftype":"1","cardtype":"2"},{"money":"100","value":"150","create_time":"1467169957","shopname":"怀府网","xftype":"2","cardtype":"2"},{"money":"0","value":"1","create_time":"1467169957","shopname":"怀府网","xftype":"1","cardtype":"1"},{"money":"100","value":"10","create_time":"1467169957","shopname":"怀府网","xftype":"2","cardtype":"1"}]
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
         * money : 10
         * value : 9
         * create_time : 1467169957
         * shopname : 怀府网
         * xftype : 2
         * cardtype : 3
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
            private String money;
            private String value;
            private long create_time;
            private String shopname;
            private int xftype;
            private int cardtype;

            private String jf="";
            private String jfsy="";

            public String getJf() {
                return jf;
            }

            public void setJf(String jf) {
                this.jf = jf;
            }

            public String getJfsy() {
                return jfsy;
            }

            public void setJfsy(String jfsy) {
                this.jfsy = jfsy;
            }

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public long getCreate_time() {
                return create_time;
            }

            public void setCreate_time(long create_time) {
                this.create_time = create_time;
            }

            public String getShopname() {
                return shopname;
            }

            public void setShopname(String shopname) {
                this.shopname = shopname;
            }

            public int getXftype() {
                return xftype;
            }

            public void setXftype(int xftype) {
                this.xftype = xftype;
            }

            public int getCardtype() {
                return cardtype;
            }

            public void setCardtype(int cardtype) {
                this.cardtype = cardtype;
            }
        }
    }
}
