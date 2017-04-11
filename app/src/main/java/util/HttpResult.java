package util;
/**
 * Created by X on 2016/10/1.
 */

public class HttpResult<T> {

    /**
     * ret : 200
     * data : {"code":0,"msg":"","info":[{"picurl":"http://7xotdz.com2.z0.glb.qiniucdn.com/2016-07-26_5796d6bda81bf.jpg","url":"http://wap.huaifuwang.com/city/news_info.php?id=5717&type=1","title":"推荐"}]}
     * msg :
     */

    private int ret;
    /**
     * code : 0
     * msg :
     * info : [{"picurl":"http://7xotdz.com2.z0.glb.qiniucdn.com/2016-07-26_5796d6bda81bf.jpg","url":"http://wap.huaifuwang.com/city/news_info.php?id=5717&type=1","title":"推荐"}]
     */

    private DataBean<T> data;
    private String msg;

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public DataBean<T> getData() {
        return data;
    }

    public void setData(DataBean<T> data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public class DataBean<T> {
        private int code;
        private String msg="";

        private String sum="";

        public String getSum() {
            return sum;
        }

        public void setSum(String sum) {
            this.sum = sum;
        }

        /**
         * picurl : http://7xotdz.com2.z0.glb.qiniucdn.com/2016-07-26_5796d6bda81bf.jpg
         * url : http://wap.huaifuwang.com/city/news_info.php?id=5717&type=1
         * title : 推荐
         */



        private T info;

        private Uinfo uinfo;

        private Orderinfo orderinfo;

        public Orderinfo getOrderinfo() {
            return orderinfo;
        }

        public void setOrderinfo(Orderinfo orderinfo) {
            this.orderinfo = orderinfo;
        }

        public Uinfo getUinfo() {
            return uinfo;
        }

        public void setUinfo(Uinfo uinfo) {
            this.uinfo = uinfo;
        }

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

        public T getInfo() {
            return info;
        }

        public void setInfo(T info) {
            this.info = info;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "code=" + code +
                    ", msg='" + msg + '\'' +
                    ", info=" + info.toString() +
                    '}';
        }
    }

    public class Orderinfo
    {

        /**
         * ordernumber : 1480387951676
         * create_time : 1480387951
         * money : 0.01
         */

        private String ordernumber;
        private int create_time;
        private String money;

        public String getOrdernumber() {
            return ordernumber;
        }

        public void setOrdernumber(String ordernumber) {
            this.ordernumber = ordernumber;
        }

        public int getCreate_time() {
            return create_time;
        }

        public void setCreate_time(int create_time) {
            this.create_time = create_time;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }
    }

    public class Uinfo
    {
        /**
         * pm : 2
         * nick : xxxpppfff
         * headimage : http://7xotdy.com2.z0.glb.qiniucdn.com/2016-08-17_57b41738cfa85.jpg
         * qdday : 4
         */

        private String pm;
        private String nick;
        private String headimage;
        private String qdday;
        private String hfb;

        public String getHfb() {
            return hfb;
        }

        public void setHfb(String hfb) {
            this.hfb = hfb;
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

        public String getHeadimage() {
            return headimage;
        }

        public void setHeadimage(String headimage) {
            this.headimage = headimage;
        }

        public String getQdday() {
            return qdday;
        }

        public void setQdday(String qdday) {
            this.qdday = qdday;
        }
    }


    @Override
    public String toString() {
        return "HttpResult{" +
                "ret=" + ret +
                ", data=" + data.toString() +
                ", msg='" + msg + '\'' +
                '}';
    }





}
