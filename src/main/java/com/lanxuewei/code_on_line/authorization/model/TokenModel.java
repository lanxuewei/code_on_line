package com.lanxuewei.code_on_line.authorization.model;

/**
 * create by lanxuewei in 2018/4/14 15:03
 * description: TokenModel的Model类
 * TODO 可以增加字段提高安全性，例如时间戳、url签名
 */
public class TokenModel {

    private Long userId;  //用户名
    private String token; //随机生成的 uuid
    private Byte status;  //身份码

    public TokenModel(Long userId, String token) {
        this.userId = userId;
        this.token = token;
    }

    public TokenModel(Long userId, String token, Byte status) {
        this.userId = userId;
        this.token = token;
        this.status = status;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }
}
