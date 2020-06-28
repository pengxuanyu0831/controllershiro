package com.xuanyu.shirocontroller.shiro;

import com.alibaba.fastjson.JSONObject;
import com.xuanyu.shirocontroller.util.ErrorEnum;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

//重写,对登录失败或者未登陆的信息进行拦截
public class AjaxPermissionsAuthorizationFilter extends FormAuthenticationFilter {
    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", ErrorEnum.E_20011.getErrorCode());
        jsonObject.put("message", ErrorEnum.E_20011.getErrorMsg());
        PrintWriter out = null;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        try {
            res.setCharacterEncoding("UTF-8");
            res.setContentType("application/json");
            out = servletResponse.getWriter();
            out.println(jsonObject);
        } catch (Exception e) {
//            e.printStackTrace();}
        } finally {
            if (null != out) {
                out.flush();
                out.close();
            }
        }
        return false;
    }

    @Bean
    public FilterRegistrationBean registrationBean(AjaxPermissionsAuthorizationFilter ajaxPermissionsAuthorizationFilter){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(ajaxPermissionsAuthorizationFilter);
        filterRegistrationBean.setEnabled(false);
        return filterRegistrationBean;
    }


}
