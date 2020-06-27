package com.xuanyu.shirocontroller.controller;

import com.alibaba.fastjson.JSONObject;
import com.xuanyu.shirocontroller.service.UserService;
import com.xuanyu.shirocontroller.util.CommonUtil;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

/**
 * @author: pengxy
 **/


@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    // 查询用户列表
    @RequiresPermissions("user:list")
    @GetMapping("/listUsers")
    public JSONObject listUsers(HttpServletRequest request){
        return userService.listUsers(CommonUtil.requestToJson(request));
    }

    @RequiresPermissions("user:add")
    @GetMapping("/addUser")
    public JSONObject addUser(@RequestBody JSONObject requestJson){
        CommonUtil.addValidation(requestJson,"username , password , nickname , roleId");
        return userService.addUser(requestJson);
    }

    @RequiresPermissions("user：update")
    @PostMapping("/updateUser")
    public JSONObject updateUser(@RequestBody JSONObject requestJson){
        CommonUtil.addValidation(requestJson,"nickname , roleId , deleteStaus ,UserId");
        return userService.updateUser(requestJson);
    }
// =============角色层===============

    // 获取权限返回前端
    @RequiresPermissions(value = {"user:add, user:update"},logical = Logical.OR)
    @GetMapping("/getAllRoles")
    public JSONObject getAllRoles(){
        return userService.getAllRoles();
    }
    // 列出角色
    @RequiresPermissions("role:list")
    @GetMapping("/listRole")
    public JSONObject listRole(){
        return userService.listRoles();
    }

    // 添加角色
    @RequiresPermissions("role:add")
    @GetMapping("/addRole")
    public JSONObject addRole(@RequestBody JSONObject requestJson){
        CommonUtil.addValidation(requestJson,"roleName,permission");
        return userService.addRole(requestJson);
    }

    // 修改角色
    @RequiresPermissions("role:update")
    @GetMapping("/updateRole")
    public JSONObject updateRole(@RequestBody JSONObject requestJson){
        CommonUtil.addValidation(requestJson,"roleId,roleName,permission");
        return userService.updateRole(requestJson);
    }








}
