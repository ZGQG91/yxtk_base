package com.yidu.baseabstract;

import com.yidu.service.IServiceInterface;
import com.yidu.template.OperationMethodTemplate;
import com.yidu.utils.PageDataInter;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2016/10/20.
 */
public abstract class AbstractService implements IServiceInterface {
    @Autowired
    protected OperationMethodTemplate operationMethodTemplate;
    public Object execute(String method, PageDataInter param, PageDataInter header) {
        return null;
    }
    public Object execute(String method, PageDataInter param, PageDataInter headerd,String methodVersion) {
        return null;
    }
}
