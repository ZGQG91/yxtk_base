package com.yidu.handler;

import com.yidu.IDbCompareApiHandler;
import com.yidu.enums.VersionEnum;
import com.yidu.result.DbApiResult;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/10/19.
 */
@Service
public class DbCompareApiHandler implements IDbCompareApiHandler {

    public DbApiResult execute(String srvname, VersionEnum version) {
        return null;
    }
}
