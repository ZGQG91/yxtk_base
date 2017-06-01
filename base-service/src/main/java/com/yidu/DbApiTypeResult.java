package com.yidu;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class DbApiTypeResult<T>{
	private static final long serialVersionUID = -2254621671109894906L;
	/* API返回码*/
    private int errorCode=200;
    /*API版本号*/
    private String version;
    private boolean success;
    private String errorMessage;
    private T data;
    private T totalCount;
    public DbApiTypeResult(){

    }
    public DbApiTypeResult(T data, String errorMessage, boolean success, String version){
        this.data=data;
        this.errorMessage=errorMessage;
        this.success=success;
        this.version=version;
    }
    public DbApiTypeResult(T data, String errorMessage, boolean success){
        this.data=data;
        this.errorMessage=errorMessage;
        this.success=success;
    }
    public DbApiTypeResult(T data, String errorMessage, boolean success, T totalCount){
        this.data=data;
        this.errorMessage=errorMessage;
        this.success=success;
        this.totalCount=totalCount;
    }
    public DbApiTypeResult(T data, String errorMessage, boolean success, T totalCount, int errorCode){
        this.data=data;
        this.errorMessage=errorMessage;
        this.success=success;
        this.totalCount=totalCount;
        this.errorCode=errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
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

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
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
