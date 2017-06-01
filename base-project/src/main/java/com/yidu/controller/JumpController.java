package com.yidu.controller;
import com.yidu.service.IServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
