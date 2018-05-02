package com.lanxuewei.code_on_line.constant;

/**
 * create by lanxuewei in 2018/4/16 14:40
 * description: 返回值以及消息常量
 */
public enum ReturnCodeAndMsgEnum {

    Success(0, "ok"),  //请求成功

    /**
     * User 相关业务
     */
    Username_Or_Password_Error(-1000, "username or password error"),  //账号或密码错误
    User_Not_Exist(-1001, "user not exist"),  //用户不存在
    User_Not_Login(-1002, "user not login"),  //账号未登录

    /**
     * Problem 相关业务
     */
    Problem_Not_Exist(-2001, "problem not exist");

    /**
     * code 状态码
     */
    private int code;

    /**
     * info 状态信息
     */
    private String info;

    ReturnCodeAndMsgEnum(int code, String info) {
        this.code = code;
        this.info = info;
    }

    public static ReturnCodeAndMsgEnum getByCode(int code) {
        for (ReturnCodeAndMsgEnum item : ReturnCodeAndMsgEnum.values()) {
            if (item.code == code) {
                return item;
            }
        }
        return null;
    }

    public int getCode() {
        return code;
    }

    public String getInfo() {
        return info;
    }
}
