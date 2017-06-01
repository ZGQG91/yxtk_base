package com.yidu.compone;

import com.yidu.Permission.PermissionModule;
import com.yidu.cache.CacheKey;

/**
 * Created by Administrator on 2016/12/1.
 */
public interface IUserCompone {
    PermissionModule queryUserCompone(@CacheKey String userId, String srvName);
}
