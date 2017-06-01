package com.yidu.baseabstract;

import com.yidu.compone.IUserCompone;
import com.yidu.template.OperationTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2016/11/9.
 */
public abstract class AbstractDynCompreApiService {
    @Autowired
    protected OperationTemplate operationTemplate;

    @Autowired
    IUserCompone userCompone;
}
