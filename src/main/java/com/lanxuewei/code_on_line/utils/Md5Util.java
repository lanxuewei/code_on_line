package com.lanxuewei.code_on_line.utils;

import org.springframework.util.DigestUtils;

/**
 * 用于加密
 */
public class Md5Util {

    private static final String salt = "%*HE$24hdi55)&H";  //盐值

    /**
     * 使用 md5 加密
     * @param sourceStr
     * @return
     */
    public static String getMd5(String sourceStr){
        String base = sourceStr + "/" + salt;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }

}
