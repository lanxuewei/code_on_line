package com.lanxuewei.code_on_line.utils;

import java.util.Random;

/**
 * create by lanxuewei in 2018/3/31
 * String util
 */
public class StringUtil {

    /**
     * 生成随机长度字符串
     * @param length string length
     */
    public static String createStringByRandom(int length) {
        Random random = new Random();
        String string = "zxcvbnmlkjhgfdsaqwertyuiopQWERTYUIOPASDFGHJKLZXCVBNM1234567890";  //定义一个字符串（A-Z，a-z，0-9）即62位；
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(61);  //由Random生成随机数(0-61)
            stringBuffer.append(string.charAt(number));
        }
        return stringBuffer.toString();
    }

}
