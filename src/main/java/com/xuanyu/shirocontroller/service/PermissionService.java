package com.xuanyu.shirocontroller.service;

import com.alibaba.fastjson.JSONObject;

public interface PermissionService {
    JSONObject getUserPermission(String username);
}
