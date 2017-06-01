package com.yidu.result;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class DbApiResult<T>{
	private static final long serialVersionUID = -2254621671109894906L;
	/* API返回码*/
    private int code=200;
    /*API版本号*/
    private String version;
    private boolean success;
    private String message;
    private T data;
    private T totalCount;
    public DbApiResult(){

    }
    public DbApiResult(T data, String message, boolean success, String version){
        this.data=data;
        this.message=message;
        this.success=success;
        this.version=version;
    }
    public DbApiResult(T data, String message, boolean success){
        this.data=data;
        this.message=message;
        this.success=success;
    }
    public DbApiResult(T data, String message, boolean success, T totalCount){
        this.data=data;
        this.message=message;
        this.success=success;
        this.totalCount=totalCount;
    }
    public DbApiResult(T data, String message, boolean success, T totalCount, int code){
        this.data=data;
        this.message=message;
        this.success=success;
        this.totalCount=totalCount;
        this.code=code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this,
                ToStringStyle.SIMPLE_STYLE);
    }

    public T getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(T totalCount) {
        this.totalCount = totalCount;
    }
}
