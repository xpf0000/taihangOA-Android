package model;

import java.io.Serializable;

import util.ModelUtil;
import util.XAPPUtil;

/**
 * Created by X on 2016/12/29.
 */

public class OAModel implements Serializable {

    String oauid="";
    String oatruename="";
    Integer oasex = 0;
    String oausername="";
    String dwid="";
    String bmid="";
    String dw="";
    String bm="";
    String oatel="";
    String oamobile="";
    String address="";
    String qq="";
    String email="";
    String jgid="";

    public String getOauid() {
        return oauid;
    }

    public void setOauid(String oauid) {
        this.oauid = oauid;
    }

    public String getOatruename() {
        return oatruename;
    }

    public void setOatruename(String oatruename) {
        this.oatruename = oatruename;
    }

    public Integer getOasex() {
        return oasex;
    }

    public void setOasex(Integer oasex) {
        this.oasex = oasex;
    }

    public String getOausername() {
        return oausername;
    }

    public void setOausername(String oausername) {
        this.oausername = oausername;
    }

    public String getDwid() {
        return dwid;
    }

    public void setDwid(String dwid) {
        this.dwid = dwid;
    }

    public String getBmid() {
        return bmid;
    }

    public void setBmid(String bmid) {
        this.bmid = bmid;
    }

    public String getDw() {
        return dw;
    }

    public void setDw(String dw) {
        this.dw = dw;
    }

    public String getBm() {
        return bm;
    }

    public void setBm(String bm) {
        this.bm = bm;
    }

    public String getOatel() {
        return oatel;
    }

    public void setOatel(String oatel) {
        this.oatel = oatel;
    }

    public String getOamobile() {
        return oamobile;
    }

    public void setOamobile(String oamobile) {
        this.oamobile = oamobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJgid() {
        return jgid;
    }

    public void setJgid(String jgid) {
        this.jgid = jgid;
    }

    public void reSet()
    {
        ModelUtil.reSet(this);
        save();
    }

    public void save()
    {
        XAPPUtil.SaveAPPCache("OAUserModel",this);
    }

}
