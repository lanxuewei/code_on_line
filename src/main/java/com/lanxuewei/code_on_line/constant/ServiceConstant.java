/**
 * Copyright (c) 2017-2018 DeepWise All Rights Reserved.
 * http://www.deepwise.com
 */
package com.lanxuewei.code_on_line.constant;

/**
 * @author lanxuewei Create in 2018/5/2 19:56
 * Description: 业务相关常量
 */
public interface ServiceConstant {

    String Default_Status = "0";    // 默认status=0
    String Default_PageNum = "1";   // 默认为第1页
    String Default_PageSize = "5";  // 默认每页大小为5

    /**
     * case 相关业务常量
     */
    public interface Case {
//        String Default_PageNum = "1";   // 默认为第1页
//        String Default_PageSize = "5";  // 默认每页大小为5
//        Byte Default_Status = 0;        // 默认status=0
    }

    /**
     * Problem 相关业务
     */
    public interface Problem {
        Byte Normal = 0;    // 正常 可使用
        Byte Deleted = -1;  // 已删除 不可使用
    }

    /**
     * User 相关业务
     */
    public interface User {
        Byte manager = 0;  // 管理员
        Byte student = 1;  // 学生
    }
}
