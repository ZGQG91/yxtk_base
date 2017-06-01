package com.yidu.service;
import com.yidu.result.DbApiResult;
import com.yidu.utils.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Administrator on 2016/10/19.
 */
public interface IDynCompreApiService {
    DbApiResult<Object> execute(String srvName, String method, PageDataInter request);

    DbApiResult<Object> execute(String srvName, String method, PageDataInter request, PageDataInter header);

    DbApiResult<Object> execute(String srvName, String method, PageDataInter request, PageDataInter header, MultipartFile file);
}
