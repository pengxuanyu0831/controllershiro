package com.xuanyu.shirocontroller.service;

import com.alibaba.fastjson.JSONObject;
import com.xuanyu.shirocontroller.dao.PermissionDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

public class PermissionServicImpl implements PermissionService {
    @Autowired
    private PermissionDao permissionDao;

    @Override
    public JSONObject getUserPermission(String username){
        JSONObject userPermission = getUserPermissionFromDb(username);
        return userPermission;
    }
    // 这里逻辑看起来不大对，整个项目run起来了看能不能改一下这里的逻辑
    public JSONObject getUserPermissionFromDb(String username){
        JSONObject userPermission = permissionDao.getUserPermission(username);
        int adminRoleId = 1;
        String roleIdKey = "roleId";
        if (adminRoleId == userPermission.getIntValue(roleIdKey)){
            Set<String> menuList = permissionDao.getAllmenu();
            Set<String> allPermisonList = permissionDao.getAllPermission();
            userPermission.put("menuList",menuList);
            userPermission.put("allPermissionList",allPermisonList);
        }
        return userPermission;
    }


}
