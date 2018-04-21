package com.lanxuewei.code_on_line.dao.entity;

/**
 * 用户(对应表 user) TODO 考虑是否需要 createTime 和 updateTime
 */
public class User {
    //用户id
    private Long id;

    //用户名
    private String userName;

    //密码
    private String password;

    //状态码 权限默认学生 0表示老师以及管理员 1表示学生
    private Byte status;

    //真实姓名
    private String realName;

    //性别
    private Byte sex;

    //头像
    private String img;

    //个人描述
    private String des;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
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
        this.img = img == null ? null : img.trim();
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des == null ? null : des.trim();
    }
}