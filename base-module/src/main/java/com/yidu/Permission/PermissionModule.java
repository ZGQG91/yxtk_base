package com.yidu.Permission;

import com.yidu.module.BaseModule;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/12/1.
 */
public class PermissionModule extends BaseModule{
    private String serviceName;
    private String method;
    private String filterType;

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getFilterType() {
        return filterType;
    }

    public void setFilterType(String filterType) {
        this.filterType = filterType;
    }
}
