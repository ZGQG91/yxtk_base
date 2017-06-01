package com.yidu.service.impl;

import com.yidu.baseabstract.AbstractService;
import com.yidu.compone.IUserColCompone;
import com.yidu.exception.Code;
import com.yidu.result.DbApiResult;
import com.yidu.service.IServiceInterface;
import com.yidu.utils.PageDataInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by Administrator on 2017/1/3.
 */
@Service
public class UserColService extends AbstractService implements IServiceInterface {
    @Autowired(required = false)
    IUserColCompone userColCompone;
    @Override
    public Object execute(String method, PageDataInter param, PageDataInter header) {
        if(method.equals("login")){
            Object result=this.login(param);
            return new DbApiResult(result,"用户登录", Code.BIZSUCCESS);
        }
        if(method.equals("register")){
            Object result=this.register(param);
            return new DbApiResult(result,"注册新用户", Code.BIZSUCCESS);
        }
        if(method.equals("vaildLogin")){
            Object result=this.validLogin(param);
            return new DbApiResult(result,"验证用户登录", Code.BIZSUCCESS);
        }
        if(method.equals("getUserByToken")){
            Object result=this.getUserByToken(param);
            return new DbApiResult(result,"获取用户信息", Code.BIZSUCCESS);
        }
        if(method.equals("logout")){
            Object result=this.logout(param);
            return new DbApiResult(result,"登出", Code.BIZSUCCESS);
        }
        return new DbApiResult(null,"请求方法不存在", Code.BIZFAIL);
    }

    public Object validLogin(Map<String,Object> map){
        boolean flag=userColCompone.validLogin(map);
        return flag;
    }

    public Object login(Map<String,Object> map){
        Object object=userColCompone.login(map);
        return object;
    }

    public Object register(Map<String,Object> map){
        Object object=userColCompone.register(map);
        return object;
    }

    public Object getUserByToken(Map<String,Object> map){
        Object object=userColCompone.getUserByToken(map);
        return object;
    }

    public Object logout(Map<String,Object> map){
        Object object=userColCompone.logout(map);
        return object;
    }
}
