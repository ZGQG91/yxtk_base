package com.yidu.controller;

import com.yidu.result.DbApiResult;
import com.yidu.utils.PageData;
import com.yidu.utils.PageDataEnum;
import com.yidu.utils.PageDataInter;
import com.yidu.utils.Tools;
import com.yidu.valid.inter.IValidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2017/6/1.
 */
@Controller
public class ValidController {
    @Autowired(required = false)
    IValidService iValidService;
    @ResponseBody
    @RequestMapping(value = { "/apiUser/user" }, method = {RequestMethod.GET,RequestMethod.POST},produces = "application/json; charset=utf-8")
    public Object apiUser(HttpServletRequest request, HttpServletResponse response){
        PageDataInter pageData=new PageData(request, PageDataEnum.DEFAULT_ENUM_PARAM);
        Tools.outParamInfo(pageData);
        DbApiResult<Object> obj=iValidService.login(pageData);
        return obj;
    }
}
