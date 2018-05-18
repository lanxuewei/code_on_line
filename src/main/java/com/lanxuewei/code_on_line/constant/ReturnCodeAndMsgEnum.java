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
    User_Not_Exist(-1001, "user does not exist"),  //用户不存在
    User_Not_Login(-1002, "user not login"),       //账号未登录
    Register_Failed(-1010, "register failure"),    //注册失败

    /**
     * Problem 相关业务
     */
    Problem_Not_Exist(-2001, "problem does not exist"),

    /**
     * Case 相关业务
     */
    Tag_Exist(-3001, "tag exist"),

    /**
     * 做题相关
     */
    No_Cases(-4000, "no cases"),              // 无测试用例
    Compile_Failed(-4001, "compile failed"),  // 编译失败
    Time_Out(-4002, "time out"),              // 超出时间
    Memory_Out(-4003, "memory out"),          // 超出内存
    Wrong_Answer(-4004, "wrong answer"),      // 答案错误
    Accepted(-4005, "accepted"),              // 答案正确

    /**
     * 内部服务器错误
     */
    System_Error(1000, "system error");

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
