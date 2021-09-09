package com.yaozhou.springboot_shiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by WXHang on HANG at 2021/9/9 15:46
 * Desc：
 */
@Controller
public class MyController {
    @RequestMapping({"HelloShiro","/","index"})
    public String helloShiro(Model model){
        model.addAttribute("msg","HelloShiro");
        return "index";
    }

    @RequestMapping("update")
    public String userUpdate(){

        return "user/update";
    }

    @RequestMapping("add")
    public String userAdd(){

        return "user/add";
    }

    @RequestMapping("toLogin")
    public String toLogin(){

        return "login";
    }

    @RequestMapping("login")
    public String login(String username,String password,Model model){
        //获取当前用户
        Subject subject = SecurityUtils.getSubject();
        //封装用户登录数据
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        //登录验证
        try {
            subject.login(token);
            return "index";
        } catch (UnknownAccountException e) {
            model.addAttribute("msg","用户名错误！");
            return "login";
        }catch (IncorrectCredentialsException e) {
            model.addAttribute("msg","密码错误！");
            return "login";
        }

    }
}
