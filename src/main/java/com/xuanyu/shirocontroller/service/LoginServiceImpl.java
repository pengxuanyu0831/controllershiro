package com.xuanyu.shirocontroller.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xuanyu.shirocontroller.dao.LoginDao;
import com.xuanyu.shirocontroller.util.CommonUtil;
import com.xuanyu.shirocontroller.util.Constants;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.function.Supplier;

public class LoginServiceImpl implements LoginService {
    @Autowired
    private LoginDao loginDao;
    @Autowired
    private PermissionService permissionService;


    @Override
    public JSONObject authLogin(JSONObject jsonObject){
        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");
        JSONObject info = new JSONObject();
        // SecurityUtils 是 shiro 的一个抽象类，没有任何子类
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try{
            currentUser.login(token);
            info.put("result","success");
        }catch (AuthenticationException e){
            info.put("result","fail");
        }
        return CommonUtil.successJson(info);
    }

    @Override
    public JSONObject logout(){
        try{
            Subject currentUser = SecurityUtils.getSubject();
            currentUser.logout();
        }catch (Exception e){
        }
        return CommonUtil.successJson();
    }

    @Override
    public JSONObject getUser(String username,String password){
        return loginDao.getUser(username, password);
    }

    @Override
    public JSONObject getInfo(){
        // getSubject 是 SecurityUtils 的核心方法
        Session session = SecurityUtils.getSubject().getSession();
        JSONObject userInfo = (JSONObject) session.getAttribute(Constants.SESSION_USER_INFO);
        String username = userInfo.getString("username");
        JSONObject info = new JSONObject();
        JSONObject userPermission = permissionService.getUserPermission(username);
        session.setAttribute(Constants.SESSION_USER_PERMISSION,userPermission);
        info.put("userPermission",userPermission);
        return CommonUtil.successJson(info);
    }
}
