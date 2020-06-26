package com.xuanyu.shirocontroller.controller;

import com.alibaba.fastjson.JSONObject;
import com.xuanyu.shirocontroller.service.UserService;
import com.xuanyu.shirocontroller.util.CommonUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

/**
 * @author: pengxy
 **/


@RestController
@RequestMapping(".user")
public class UserController {
    @Autowired
    private UserService userService;

    // 查询用户列表
    @RequiresPermissions("user:list")
    @GetMapping("/list")
    public JSONObject listUsers(HttpServletRequest request){
        return userService.listUsers(CommonUtil.requestToJson(request));
    }


}
