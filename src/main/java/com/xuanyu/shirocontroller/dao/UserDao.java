package com.xuanyu.shirocontroller.dao;

import com.alibaba.fastjson.JSONObject;

import java.util.LinkedHashSet;
import java.util.List;

public interface UserDao {
    int countUser(JSONObject jsonObject);


    List<JSONObject> listUsers(JSONObject jsonObject);

    // 用户是否存在
    int existUser(JSONObject jsonObject);

    // 添加用户
    int addUser(JSONObject jsonObject);

    // 获取用户关联的角色
    List<JSONObject> getAllRoles();

    int updateUser(JSONObject jsonObject);

    List<JSONObject> listRoles();

    List<JSONObject> listAllPermission();


}
