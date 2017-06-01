package com.yidu.compone.impl;

import com.yidu.Permission.PermissionModule;
import com.yidu.cache.CacheKey;
import com.yidu.cache.Cached;
import com.yidu.compone.IUserCompone;
import com.yidu.dao.PermissionMapper;
import com.yidu.enums.OperationEnum;
import com.yidu.enums.PrefixEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2016/12/1.
 */
@Component
public class UserCompone implements IUserCompone {
    @Autowired(required = false)
    PermissionMapper permissionMapper;
    @Cached(prefix= PrefixEnum.USER_PERMISSION,operateType= OperationEnum.READ,expire = 100)
    public PermissionModule queryUserCompone(@CacheKey String userId,String srvName) {
        PermissionModule permissionList=permissionMapper.queryUserPermission(userId,srvName);
        return permissionList;
    }
}
