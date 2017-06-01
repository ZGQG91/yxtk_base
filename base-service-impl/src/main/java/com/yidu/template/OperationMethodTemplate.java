package com.yidu.template;
import com.yidu.callback.MethodCallback;
import com.yidu.result.DbApiResult;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Created by Administrator on 2016/11/9.
 */
@Service
public class OperationMethodTemplate {
    /**
     * 比对方法版本
     * @param execResult  返回的对象
     * @param version  当前用户的版本
     * @param methodVersion 配置的版本
     * @param methodCallback  回调
     */
    public void opera(DbApiResult<Object> execResult, String version, String methodVersion, MethodCallback methodCallback){
        if(StringUtils.isEmpty(methodVersion)){
            methodCallback.invokeMethod();
        }
        if(version.compareTo(methodVersion)<=0){
            //当前用户版本小于等于配置的版本(version<=methodVersion) oldMethod
            methodCallback.oldMethod();
        }
        if(version.compareTo(methodVersion)>0){
            //当前用户版本大于配置的版本(version>methodVersion) method
            methodCallback.invokeMethod();
        }

    }
}
