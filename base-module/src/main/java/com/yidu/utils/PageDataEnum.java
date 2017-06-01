package com.yidu.utils;

/**
 * Created by Administrator on 2017/5/25.
 */
public enum PageDataEnum {
    DEFAULT_ENUM(0,0),
    DEFAULT_ENUM_PARAM(0,0,0,"param");
    //是否获取头参数
    private int isHead;
    //传输方式
    private int transmissionMethod;
    //加密方式
    private int secMethod;
    //加密之后的参数名称
    private String paramName;

    public int getIsHead() {
        return isHead;
    }

    public void setIsHead(int isHead) {
        this.isHead = isHead;
    }

    public int getTransmissionMethod() {
        return transmissionMethod;
    }

    public void setTransmissionMethod(int transmissionMethod) {
        this.transmissionMethod = transmissionMethod;
    }

    public int getSecMethod() {
        return secMethod;
    }

    public void setSecMethod(int secMethod) {
        this.secMethod = secMethod;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    PageDataEnum(int isHead, int secMethod,int transmissionMethod, String paramName){
        this.isHead=isHead;
        this.secMethod=secMethod;
        this.paramName=paramName;
        this.transmissionMethod=transmissionMethod;
    }
    PageDataEnum(int isHead, int secMethod){
        this.isHead=isHead;
        this.secMethod=secMethod;
    }
}
