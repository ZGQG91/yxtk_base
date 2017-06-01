package com.yidu.permission;

import com.yidu.exception.DbException;

/**
 * Created by Administrator on 2016/12/2.
 */
public class Permission {
    private String role;
    private String rela;
    private PermissionDesc []permissionDesc;

    private PermissionRela permissionRela=PermissionRela.AND;

    public PermissionDesc[] getPermissionDesc() {
        return permissionDesc;
    }

    public void setPermissionDesc(PermissionDesc[] permissionDesc) {
        this.permissionDesc = permissionDesc;
    }

    public PermissionRela getPermissionRela() {
        return permissionRela;
    }

    public void setPermissionRela(PermissionRela permissionRela) {
        this.permissionRela = permissionRela;
    }

    public Permission(String role){
        String []roleStr=role.split("\\|");
        this.rela=roleStr[roleStr.length-1];
        if(roleStr.length<2){
            throw new DbException("权限配置错误");
        }
        this.role=arrayToStr(roleStr,roleStr.length-2);
    }

    private String arrayToStr(String[] arrayStr,int changeLength){
        String arrayStrReturn="";
        permissionDesc=new PermissionDesc[arrayStr.length-1];
        for(int i=0;i<(arrayStr.length-1);i++){
            PermissionDesc permissionDesc=new PermissionDesc();
            permissionDesc.setName(arrayStr[i]);
            this.permissionDesc[i]=permissionDesc;
            //arrayStrReturn+=arrayStr[i];
        }
        return arrayStrReturn;
    }

    public String getRole() {
        return role;
    }

    public String getRela() {
        return rela;
    }
}
