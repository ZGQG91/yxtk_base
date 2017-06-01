package com.yidu.compone.item;

import com.alibaba.fastjson.JSONObject;
import com.readyidu.pojo.UserRest;
import com.readyidu.service.UserRestService;
import com.yidu.compone.IUserColCompone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by Administrator on 2017/1/4.
 */
@Component
public class UserColCompone implements IUserColCompone {
    @Autowired(required = false)
    UserRestService userRestService;

    public Object validLogin(Map map) {
        UserRest userRest=new UserRest();
        String appName= (String) map.get("appName");
        String token= (String) map.get("token");
        userRest.setToken(token);
        userRest.setAppName(appName);
        String result=userRestService.validToken(userRest);
        JSONObject jsonObject=JSONObject.parseObject(result);
        JSONObject validobject= (JSONObject) jsonObject.get("data");
        boolean validFlag=false;
        if(validobject!=null){
            validFlag=validobject.getBoolean("valid");
        }
        return validFlag;
    }

    public Object login(Map map) {
        UserRest userRest=new UserRest();
        String mobile= (String) map.get("mobile");
        String password= (String) map.get("password");
        String appName= (String) map.get("appName");
        userRest.setMobile(mobile);
        userRest.setPassword(password);
        userRest.setAppName(appName);
        String result=userRestService.login(userRest);
        JSONObject jsonObject=JSONObject.parseObject(result);
        return jsonObject;
    }

    public Object register(Map map) {
        UserRest userRest=new UserRest();
        String mobile= (String) map.get("mobile");
        String password= (String) map.get("password");
        String userType= "user";
        userRest.setMobile(mobile);
        userRest.setPassword(password);
        userRest.setUserType(userType);
        String result=userRestService.register(userRest);
        JSONObject jsonObject=JSONObject.parseObject(result);
        return jsonObject;
    }

    public Object getUserByToken(Map map) {
        UserRest userRest=new UserRest();
        String appName= (String) map.get("appName");
        String token= (String) map.get("token");
        userRest.setAppName(appName);
        userRest.setToken(token);
        String result=userRestService.getUserByToken(userRest);
        JSONObject jsonObject=JSONObject.parseObject(result);
        return jsonObject;
    }

    public Object logout(Map map) {
        UserRest userRest=new UserRest();
        String appName= (String) map.get("appName");
        String token= (String) map.get("token");
        userRest.setAppName(appName);
        userRest.setToken(token);
        String result=userRestService.logout(userRest);
        JSONObject jsonObject=JSONObject.parseObject(result);
        return jsonObject;
    }

    public Object getUserInfo(Map map) {
        UserRest userRest=new UserRest();
        userRest.setId(Long.parseLong(String.valueOf(map.get("userId"))));
        String result=userRestService.getUserInfo(userRest);
        JSONObject jsonObject=JSONObject.parseObject(result);
        return jsonObject;
    }

    public Object getUserInfoByMobile(Map map) {
        UserRest userRest=new UserRest();
        userRest.setMobile(String.valueOf(map.get("mobile")));
        String result=userRestService.getUserInfoByMobile(userRest);
        JSONObject jsonObject=JSONObject.parseObject(result);
        return jsonObject;
    }
}
