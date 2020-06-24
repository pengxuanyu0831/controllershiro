package com.xuanyu.shirocontroller.dao;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;

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

    // 添加角色
    int insertRole(JSONObject jsonObject);
    // 为角色分配权限
    int insertRolePermission(@Param("roleId") String roleId, @Param("permission") List<Integer>permission);
    // 删除旧的角色权限
    int deleteOldRolePermission(@Param("roleId") String roleId,@Param("permission")List<Integer>permission) ;
    // 返回角色信息，json类型的
    JSONObject getRoleAllInfo(JSONObject jsonObject);

    int updateRoleName(JSONObject jsonObject);

}

