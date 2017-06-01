package com.yidu.exception;

import org.apache.log4j.Level;

/**
 * Created by Administrator on 2016/10/19.
 */
public class DbException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    private int code = 1;
    private Level level;
    private Object data;

    public DbException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public DbException(String message) {
        super(message);
    }

    public DbException(Level level, String message) {
        super(message);
        this.level = level;
    }

    public DbException(Throwable cause) {
        super(cause);
    }

    public DbException(int code, String message,Object data) {
        super(message);
        this.data=data;
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public final Level getLevel() {
        return level;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
