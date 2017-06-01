package com.yidu.template;

import com.yidu.callback.ServiceCallback;
import com.yidu.result.DbApiResult;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * Created by Administrator on 2016/11/9.
 */
@Service
public class OperationTemplate {
    public void opera(DbApiResult<?> dbApiResult, Map<String,Object> map, ServiceCallback serviceCallback){
        boolean isValidFla=this.isValid(map);
        //配置的参数为true 执行用户登录验证(默认不开启用户认证)
        boolean flag=isValidFla?serviceCallback.valid():true;
        //验证使用是否已经登录 状态为true的话执行
        if(flag){
            this.invokeService(serviceCallback);
        }
    }

    /**
     * 执行服务
     * @param serviceCallback
     */
    public void invokeService(ServiceCallback serviceCallback){
        String version=serviceCallback.versionControl();
        serviceCallback.doOperation();
        if(!StringUtils.isEmpty(version)){
            serviceCallback.check(version);
        }else{
            serviceCallback.check();
        }
    }

    /**
     * 配置的参数
     * @param map
     * @return
     */
    public boolean isValid(Map<String,Object> map){
        Object isValid=map.get("isValid");
        boolean flag=(isValid==null)?false:Boolean.parseBoolean(String.valueOf(isValid));
        return flag;
    }

    /**
     * 多参数
     * @param dbApiResult
     * @param serviceCallback
     */
    public void opera(DbApiResult<?> dbApiResult, ServiceCallback serviceCallback){
        this.opera(dbApiResult,null,serviceCallback);
    }
}
