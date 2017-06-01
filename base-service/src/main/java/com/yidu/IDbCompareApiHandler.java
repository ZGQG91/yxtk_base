package com.yidu;

import com.yidu.enums.VersionEnum;
import com.yidu.result.DbApiResult;

/**
 * Created by Administrator on 2016/10/19.
 */
public interface IDbCompareApiHandler {
    public DbApiResult execute(String srvname,VersionEnum version);
}
