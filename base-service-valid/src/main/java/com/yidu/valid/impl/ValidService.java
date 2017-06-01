package com.yidu.valid.impl;
import com.yidu.compone.IUserColCompone;
import com.yidu.result.DbApiResult;
import com.yidu.valid.inter.IValidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/1.
 */
@Service
public class ValidService<T> implements IValidService<T> {
    @Autowired(required = false)
    IUserColCompone userColCompone;

    public boolean validLogin(Map<String, Object> map) {
        boolean flag=true;
        return flag;
    }

    public DbApiResult login(Map<String, Object> map) {
        DbApiResult<Object> dbApiResult=new DbApiResult<Object>();
        Object obj=userColCompone.login(map);
        dbApiResult.setData(obj);
        return dbApiResult;
    }


    public T register(Map<String, Object> map) {
        return null;
    }

    public T getUserByToken(Map<String, Object> map) {
        return null;
    }

    public T logout(Map<String, Object> map) {
        return null;
    }

    public T getUserInfo(Map<String, Object> map) {
        return null;
    }

    public T getUserInfoByMobile(Map<String, Object> map) {
        return null;
    }
}
