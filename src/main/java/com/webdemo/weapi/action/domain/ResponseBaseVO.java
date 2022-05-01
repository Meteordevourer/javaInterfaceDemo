package com.webdemo.weapi.action.domain;

public class ResponseBaseVO {

    private static final long serialVersionUID = 1L;

    private String resCode;
    private String resMsg;
    private String resSrt;
    private String version;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getResCode() {
        return resCode;
    }

    public void setResCode(String resCode) {
        this.resCode = resCode;
    }

    public String getResMsg() {
        return resMsg;
    }

    public void setResMsg(String resMsg) {
        this.resMsg = resMsg;
    }

    public String getResSrt() {
        return resSrt;
    }

    public void setResSrt(String resSrt) {
        this.resSrt = resSrt;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

}
