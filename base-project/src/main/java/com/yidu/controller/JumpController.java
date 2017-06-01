package com.yidu.controller;
import com.yidu.result.DbApiResult;
import com.yidu.service.IServiceInterface;
import com.yidu.utils.PageData;
import com.yidu.utils.PageDataEnum;
import com.yidu.utils.PageDataInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2016/10/20.
 */
@Controller
public class JumpController {
    @Autowired(required = false)
    IServiceInterface userColService;
    @RequestMapping(value = "/index", method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView index(HttpServletRequest request){
        PageDataInter pageDataAll=new PageData(request, PageDataEnum.DEFAULT_ENUM_PARAM);
        String token= (String) pageDataAll.get("token");
//        String appName= (String) pageDataAll.get("appName");
        ModelAndView mv=new ModelAndView("index");
        if(StringUtils.isEmpty(token)){
            mv=new ModelAndView("login");
        }else{
            pageDataAll.put("appname","znxy");
            DbApiResult dbApiResult= (DbApiResult) userColService.execute("vaildLogin",pageDataAll,null);
            boolean flag= (boolean) dbApiResult.getData();
            if(!flag){
                mv=new ModelAndView("login");
            }
        }
        return mv;
    }

    @RequestMapping(value = "/login", method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView login(HttpServletRequest request){
        ModelAndView mv=new ModelAndView("login");
        return mv;
    }

    @RequestMapping(value = "/register", method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView register(HttpServletRequest request){
        ModelAndView mv=new ModelAndView("register");
        return mv;
    }
}
