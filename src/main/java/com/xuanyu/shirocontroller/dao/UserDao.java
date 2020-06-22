package com.xuanyu.shirocontroller.dao;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

public interface UserDao {
    int countUser(JSONObject jsonObject);
    List<JSONObject> listUser(JSONObject jsonObject);
}
