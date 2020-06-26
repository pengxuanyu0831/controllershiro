package com.xuanyu.shirocontroller.config;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xuanyu.shirocontroller.util.CommonUtil;
import com.xuanyu.shirocontroller.util.ErrorEnum;

// 错误类，统一返回json给前端
public class CommonJsonExecption extends RuntimeException {
    private JSONObject resultJson;
    // 可以在任意一处代码抛出错误
    public CommonJsonExecption(ErrorEnum errorEnum){
        this.resultJson = CommonUtil.errorJson(errorEnum);
    }
    public CommonJsonExecption(JSONObject resultJson){
        this.resultJson = resultJson;
    }
    public JSONObject getResultJson(){
        return resultJson;
    }
}
