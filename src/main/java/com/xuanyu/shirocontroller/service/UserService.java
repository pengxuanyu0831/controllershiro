package com.xuanyu.shirocontroller.service;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;

public interface UserService {
    // 用户列表
    JSONObject listUser(JSONObject jsonObject);
    // 全部角色
    JSONObject getAllRoles();
    // 添加用户
    JSONObject addUser(JSONObject jsonObject);
    // 修改用户
    JSONObject updateUser(JSONObject jsonObject);
    // 角色列表
    JSONObject listRole();
    // 查询所有权限
    JSONObject listAllPermission();
    // 添加角色
    JSONObject addRole(JSONObject jsonObject);
    //  修改角色
    JSONObject updateRole(JSONObject jsonObject);
    // 删除
    JSONObject deleteRole(JSONObject jsonObject);
}
