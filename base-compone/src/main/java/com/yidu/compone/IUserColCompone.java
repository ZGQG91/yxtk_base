package com.yidu.compone;

import java.util.Map;

/**
 * Created by Administrator on 2017/1/3.
 */
public interface IUserColCompone<T> {
    T validLogin(Map<String,Object> map);
    <T>T login(Map<String,Object> map);
    <T>T register(Map<String,Object> map);
    <T>T getUserByToken(Map<String,Object> map);
    <T>T logout(Map<String,Object> map);
    <T>T getUserInfo(Map<String,Object> map);
    <T>T getUserInfoByMobile(Map<String,Object> map);
}
