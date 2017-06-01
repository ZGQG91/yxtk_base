package com.yidu.permission;

import com.yidu.Permission.PermissionModule;
import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * Created by Administrator on 2016/12/1.
 */
public class PermissionTools {
//    /**
//     * 根据类型解析(1.通配符的方式  2.请求方法的方式)
//     * @param permissionTypesRela 权限类型关系
//     */
//    static void permissionType(PermissionTypesRela permissionTypesRela){
//        switch(permissionTypesRela.getRela()){
//            case WILDCARD:
//            case METHOD:
//        }
//    }

    /**
     *
     * @param permission 用户当前服务的权限数组
     * @param rela  配置的权限
     * @return
     */
    public static boolean parsePermission(String [] permission,Permission rela){
        if(permission==null)return true;
        PermissionRela permissionRela=rela.getPermissionRela();
        switch(permissionRela){
            case AND:
                for(PermissionDesc permissionDesc:rela.getPermissionDesc()){
                    boolean flag = false;
                    for(String perRela:permission){
                        if (StringUtils.equalsIgnoreCase(perRela, permissionDesc.getName())) {
                            flag = true;
                            break;
                        }
                    }
                    if (!flag) {
                        return false;
                    }
                }
                return true;
            case OR:
                for(PermissionDesc permissionDesc:rela.getPermissionDesc()){
                    for(String perRela:permission){
                        if (StringUtils.equalsIgnoreCase(perRela, permissionDesc.getName())) {
                            return true;
                        }
                    }
                }
                return false;
            case NOT:
                for(PermissionDesc permissionDesc:rela.getPermissionDesc()){
                    for(String perRela:permission){
                        if (StringUtils.equalsIgnoreCase(perRela, permissionDesc.getName())) {
                            return false;
                        }
                    }
                }
                return true;
        }
        return true;
    }

    public static String[] ListToStrArray(List<Permission> list){
        String[] str=new String[list.size()];
        for(int i=0;i<list.size();i++){
            str[i]=list.get(i).getRole();
        }
        return str;
    }

    public static String[] splitToStr(PermissionModule permissionModule){
        if(permissionModule==null){
            return null;
            //throw new DbException("权限未配置");
        }else{
            String role=permissionModule.getMethod();
            String []roleStr=role.split("\\|");
            return roleStr;
        }

    }
}
