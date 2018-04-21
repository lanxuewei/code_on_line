package com.lanxuewei.code_on_line.dao.entity;

import java.util.Date;

/**
 * 问题(对应表 problem)
 */
public class Problem {
    //id
    private Long id;

    //问题名
    private String name;

    //问题难度 0:简单 1:中等 2:难 默认为0
    private Byte difficulty;

    //提交次数 默认为0
    private Integer submit;

    //失败次数 默认为 0
    private Integer fail;

    //成功次数 默认为 0
    private Integer success;

    //作者
    private String author;

    //创建时间
    private Date createTime;

    //更新时间
    private Date updateTime;

    //状态码
    private Byte status;

    //描述
    private String des;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Byte getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Byte difficulty) {
        this.difficulty = difficulty;
    }

    public Integer getSubmit() {
        return submit;
    }

    public void setSubmit(Integer submit) {
        this.submit = submit;
    }

    public Integer getFail() {
        return fail;
    }

    public void setFail(Integer fail) {
        this.fail = fail;
    }

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des == null ? null : des.trim();
    }
}