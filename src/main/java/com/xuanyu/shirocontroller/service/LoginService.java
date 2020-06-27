package com.xuanyu.shirocontroller.service;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.JsonNode;

public interface LoginService {
    JSONObject authLogin(JSONObject jsonObject);

    // 获取user的用户名密码
    JSONObject getUser(String username,String password);

    JSONObject getInfo();

    JSONObject logout();
}
