package com.yikuan.util;

public class StringUtil {

    public static boolean isEmpty(String str){
        // 判断字符串是否为空；去掉前后的空格
        return str == null || "".equals(str.trim());
    }


    public static boolean isNotEmpty(String str){
        // 判断字符串是否不是空，去掉前后空格
        return str != null && "".equals(str.trim());
    }
}
