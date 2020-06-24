package com.xuanyu.shirocontroller.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xuanyu.shirocontroller.dao.UserDao;
import com.xuanyu.shirocontroller.util.CommonUtil;
import com.xuanyu.shirocontroller.util.ErrorEnum;
import org.springframework.beans.factory.annotation.Autowired;

import javax.security.auth.login.CredentialNotFoundException;
import java.util.List;

public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;


    // 获取用户列表
    @Override
    public JSONObject listUser(JSONObject jsonObject){
        CommonUtil.fillPageParam(jsonObject);
        int count = userDao.countUser(jsonObject);
        List<JSONObject> list  = userDao.listUser(jsonObject);
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


}
