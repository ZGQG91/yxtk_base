package com.yidu.service;

import com.yidu.utils.PageDataInter;

/**
 * Created by Administrator on 2016/10/18.
 */
public interface IServiceInterface {
    Object execute(String method, PageDataInter param, PageDataInter header);

    Object execute(String method, PageDataInter param, PageDataInter header,String methodVersion);
}
