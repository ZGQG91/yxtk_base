package com.yidu.dao;

import com.yidu.MybatisDao;
import com.yidu.Permission.PermissionModule;
import org.apache.ibatis.annotations.Param;

/**
 * Created by Administrator on 2016/10/18.
 */
@MybatisDao
public interface PermissionMapper {
    /**
     * 查找用户权限
     * @param  userId  用户ID
     * @return
     */
    PermissionModule queryUserPermission(@Param("userId") String userId, @Param("srvName") String srvName);
}
