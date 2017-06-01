package com.yidu.template;

import com.yidu.callback.ServiceCallback;
import com.yidu.result.DbApiResult;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Created by Administrator on 2016/11/9.
 */
@Service
public class OperationTemplate {
    public void opera(DbApiResult<?> dbApiResult,ServiceCallback serviceCallback){
        String version=serviceCallback.versionControl();
        serviceCallback.doOperation();
        if(!StringUtils.isEmpty(version)){
            serviceCallback.check(version);
        }else{
            serviceCallback.check();
        }
    }
}
