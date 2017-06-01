package com.yidu.compone;

import java.util.Map;

/**
 * Created by Administrator on 2017/1/3.
 */
public interface IUserColCompone {
    boolean validLogin(Map<String,Object> map);
    Object login(Map<String,Object> map);
    Object register(Map<String,Object> map);
    Object getUserByToken(Map<String,Object> map);
    Object logout(Map<String,Object> map);
    Object getUserInfo(Map<String,Object> map);
    Object getUserInfoByMobile(Map<String,Object> map);
}
