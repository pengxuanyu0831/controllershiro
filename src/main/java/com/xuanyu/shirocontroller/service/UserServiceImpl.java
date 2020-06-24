package com.xuanyu.shirocontroller.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xuanyu.shirocontroller.dao.UserDao;
import com.xuanyu.shirocontroller.util.CommonUtil;
import com.xuanyu.shirocontroller.util.ErrorEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.security.auth.login.CredentialNotFoundException;
import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;


    // 获取用户列表
    @Override
    public JSONObject listUsers(JSONObject jsonObject){
        CommonUtil.fillPageParam(jsonObject);
        int count = userDao.countUser(jsonObject);
        List<JSONObject> list  = userDao.listUsers(jsonObject);
        return CommonUtil.successPage(jsonObject,list,count);
    }

    // 添加用户
    @Override
    public JSONObject addUser(JSONObject jsonObject){
        int exist = userDao.existUser(jsonObject);
        if (exist > 0){
            return CommonUtil.errorJson(ErrorEnum.E_10009);
        }
        userDao.addUser(jsonObject);
        return CommonUtil.successJson();
    }
    // 获取用户关联的角色
    @Override
    public JSONObject getAllRoles(){
        List<JSONObject> roles = userDao.getAllRoles();
        return CommonUtil.successJson(roles);
    }

    @Override
    public JSONObject updateUser(JSONObject jsonObject){
        userDao.updateUser(jsonObject);
        return CommonUtil.successJson();
    }

    @Override
    public JSONObject listRoles(){
        List<JSONObject> roles = userDao.listRoles();
        return CommonUtil.successJson(roles);
    }

    @Override
    public JSONObject listAllPermission(){
        List<JSONObject> permissions = userDao.listAllPermission();
        return CommonUtil.successJson(permissions);
    }

    // 添加角色
    @Override
    // Transactional表明该类中的方法全部进行事务处理
    @Transactional(rollbackFor = Exception.class)
    // 告知编译器，忽略括号中指定的warnings
    @SuppressWarnings("unchecked")
    public JSONObject addRole(JSONObject jsonObject){
        userDao.insertRole(jsonObject);
        userDao.insertRolePermission(jsonObject.getString("roleId"),(List<Integer>)jsonObject.get("permission"));
        return CommonUtil.successJson();
    }

    // 修改角色权限
    @Override
    @Transactional(rollbackFor = Exception.class)
    @SuppressWarnings("unchecked")
    public JSONObject updateRole(JSONObject jsonObject){
        // 获取要修改的角色id 和 权限
        String roleId = jsonObject.getString("roleId");
        List<Integer> newPerms = (List<Integer>)jsonObject.get("permission");
        JSONObject roleInfo = userDao.getRoleAllInfo(jsonObject);
        Set<Integer> olePerms = (Set<Integer>) roleInfo.get("permission");
        // 修改角色名称
        updateRoleName(jsonObject,roleInfo);
        // 添加新的权限
        addNewPermission()

    }

    private void updateRoleName(JSONObject parmJSON,JSONObject roleInfo){
        String roleName = parmJSON.getString("roleName");
        if (!roleName.equals(roleInfo.getString("roleName"))){
           userDao.updateRoleName(parmJSON);
        }
    }
    private addNewPermission(String roleId,)


}
