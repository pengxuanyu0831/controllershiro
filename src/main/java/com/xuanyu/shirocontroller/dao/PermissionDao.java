package com.xuanyu.shirocontroller.dao;

import com.alibaba.fastjson.JSONObject;

import java.util.Set;

public interface PermissionDao {

    // 根据usernam检索权限
    JSONObject getUserPermission(String username);

    Set<String> getAllmenu();

    Set<String> getAllPermission();



}
