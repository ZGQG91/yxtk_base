package com.yidu.valid.inter;

import com.yidu.result.DbApiResult;

import java.util.Map;

/**
 * Created by Administrator on 2017/6/1.
 */
public interface IValidService<T> {
    boolean validLogin(Map<String, Object> map);
    DbApiResult<?> login(Map<String, Object> map);
    T register(Map<String, Object> map);
    T getUserByToken(Map<String, Object> map);
    T logout(Map<String, Object> map);
    T getUserInfo(Map<String, Object> map);
    T getUserInfoByMobile(Map<String, Object> map);
}
