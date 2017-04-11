package model;

import java.io.Serializable;

/**
 * Created by X on 2016/12/12.
 */

public class HouseModel implements Serializable {


    /**
     * id : 426
     * fanghaoid : 1969
     * type : 1
     * fanghao : 702
     * louceng : 7楼
     * danyuan : 1单元
     * louhao : 4号楼
     * xiaoqu : 上林苑
     * houseid : 1632
     * quyu : 怀府西路
     */

    private String id;
    private String fanghaoid;
    private String type;
    private String fanghao;
    private String louceng;
    private String danyuan;
    private String louhao;
    private String xiaoqu;
    private String houseid;
    private String quyu;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFanghaoid() {
        return fanghaoid;
    }

    public void setFanghaoid(String fanghaoid) {
        this.fanghaoid = fanghaoid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFanghao() {
        return fanghao;
    }

    public void setFanghao(String fanghao) {
        this.fanghao = fanghao;
    }

    public String getLouceng() {
        return louceng;
    }

    public void setLouceng(String louceng) {
        this.louceng = louceng;
    }

    public String getDanyuan() {
        return danyuan;
    }

    public void setDanyuan(String danyuan) {
        this.danyuan = danyuan;
    }

    public String getLouhao() {
        return louhao;
    }

    public void setLouhao(String louhao) {
        this.louhao = louhao;
    }

    public String getXiaoqu() {
        return xiaoqu;
    }

    public void setXiaoqu(String xiaoqu) {
        this.xiaoqu = xiaoqu;
    }

    public String getHouseid() {
        return houseid;
    }

    public void setHouseid(String houseid) {
        this.houseid = houseid;
    }

    public String getQuyu() {
        return quyu;
    }

    public void setQuyu(String quyu) {
        this.quyu = quyu;
    }
}
