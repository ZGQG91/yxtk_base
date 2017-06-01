package com.yidu.baseabstract;

import com.yidu.compone.IUserColCompone;
import com.yidu.compone.IUserCompone;
import com.yidu.permission.Permission;
import com.yidu.service.IServiceInterface;
import com.yidu.template.OperationTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * Created by Administrator on 2016/11/9.
 */
public abstract class AbstractDynCompreApiService {
    /**
     * 服务
     */
    protected Map<String, IServiceInterface> compareHandlers;
    /**
     * 权限
     */
    protected Map<String,Permission> permission;
    /**
     * 方法版本控制
     */
    protected Map<String,String> versionHanlers;

    /**
     * 配置参数(是否开启用户认证)
     */
    protected Map<String,Object> config;

    public void setPermission(Map<String, Permission> permission) {
        this.permission = permission;
    }

    public void setCompareHandlers(Map<String, IServiceInterface> compareHandlers) {
        this.compareHandlers = compareHandlers;
    }
    public void setVersionHanlers(Map<String, String> versionHanlers) {
        this.versionHanlers = versionHanlers;
    }

    public void setConfig(Map<String, Object> config) {
        this.config = config;
    }

    @Autowired
    protected OperationTemplate operationTemplate;

    @Autowired
    protected IUserCompone userCompone;

    @Autowired
    protected IUserColCompone userColCompone;
}
