package com.lanxuewei.code_on_line.model;

/**
 * create by lanxuewei in 2018/5/6 17:26
 * description: user相关界面模型,用于接收解析前端user模型
 */
public class UserViewModel {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 真实姓名
     */
    private String realname;

    /**
     * 性别
     */
    private Byte sex;

    /**
     * 头像  TODO 目前前端暂时不传参
     */
    private String img;

    /**
     * 个人描述
     */
    private String des;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public Byte getSex() {
        return sex;
    }

    public void setSex(Byte sex) {
        this.sex = sex;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    @Override
    public String toString() {
        return "UserViewModel{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", realname='" + realname + '\'' +
                ", sex=" + sex +
                ", img='" + img + '\'' +
                ", des='" + des + '\'' +
                '}';
    }
}
