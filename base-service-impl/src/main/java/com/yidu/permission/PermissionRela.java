package com.yidu.permission;

/**
 * Created by Administrator on 2016/12/2.
 */
public enum PermissionRela {
    AND("AND","与"),
    OR("OR","或"),
    NOT("NOT","非");
    PermissionRela(String code,String desc){
        this.code=code;
        this.desc=desc;
    }
    private String code;
    private String desc;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
