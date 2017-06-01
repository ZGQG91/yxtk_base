package com.yidu.controller;

import com.yidu.result.DbApiResult;
import com.yidu.service.IDynCompreApiService;
import com.yidu.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Created by Administrator on 2016/10/18.
 */
@Controller
public class DbApiController {
    @Autowired
    IDynCompreApiService iDynCompreApiService;
    @ResponseBody
    @RequestMapping(value = { "/api/{srvname}/{method}" }, method = {RequestMethod.GET,RequestMethod.POST},produces = "application/json; charset=utf-8")
    public DbApiResult<Object> api(@PathVariable("srvname")String srvname,@PathVariable("method")String method,HttpServletRequest request,HttpServletResponse response){
        PageDataInter pageData=new PageData(request,PageDataEnum.DEFAULT_ENUM_PARAM);
        Tools.outParamInfo(srvname,method,pageData);
        DbApiResult<Object> dbApiResult=iDynCompreApiService.execute(srvname,method,pageData);
        return dbApiResult;
    }
    @ResponseBody
    @RequestMapping(value = { "/upload/{srvname}/{method}" }, method = {RequestMethod.GET,RequestMethod.POST},produces = "application/json; charset=utf-8")
    public DbApiResult<Object> upload(@PathVariable("srvname")String srvname,@PathVariable("method")String method,HttpServletRequest request,@RequestParam("file") MultipartFile file){
        DbApiResult<Object> dbApiResult=iDynCompreApiService.execute(srvname,method,new PageData(request,PageDataEnum.DEFAULT_ENUM_PARAM),new PageDataHeaderImpl(request),file);
        return dbApiResult;
    }
}
