package com.xuanyu.shirocontroller.dao;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

public interface UserDao {
    int countUser(JSONObject jsonObject);
    List<JSONObject> listUser(JSONObject jsonObject);

    // 用户是否存在
    int existUser(JSONObject jsonObject);

    // 添加用户
    int addUser(JSONObject jsonObject);
}
