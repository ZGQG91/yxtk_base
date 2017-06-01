package com.yidu.permission;

/**
 * Created by Administrator on 2016/12/1.
 */
public enum PermissionTypes {

    WILDCARD("wildcard","通配符"),
    METHOD("method","方法");
    private String code;
    private String desc;

    PermissionTypes(String code,String desc){
        this.code=code;
        this.desc=desc;
    }
}
