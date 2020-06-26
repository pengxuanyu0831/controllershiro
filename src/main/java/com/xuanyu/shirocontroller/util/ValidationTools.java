package com.xuanyu.shirocontroller.util;

// 校验规则
public class ValidationTools {
    public static boolean isNotEmpty(String str){
        return null == str || " ".equals(str) || "null".equals(str);
    }

    public static  boolean isNotEmpty(Object obj){
        return null == obj || " ".equals(obj);
    }
}
