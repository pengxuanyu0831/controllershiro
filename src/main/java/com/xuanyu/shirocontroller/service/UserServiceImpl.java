package com.xuanyu.shirocontroller.service;

import com.alibaba.fastjson.JSONObject;
import com.xuanyu.shirocontroller.dao.UserDao;
import com.xuanyu.shirocontroller.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public JSONObject listUser(JSONObject jsonObject) {
        CommonUtil.fillPageParam(jsonObject);
        int count = userDao.countUser(jsonObject);
        List<JSONObject> list = userDao.listUser(jsonObject);
        return CommonUtil.successPage(jsonObject, list, count);
    }




}
