package com.lanxuewei.code_on_line.dao.entity;

import java.util.Date;

public class UserRecord {

    // 主键
    private Long id;

    // 用户id
    private Long userId;

    // 问题id
    private Long problemId;

    // 是否成功
    private Byte isSuccess;

    // 创建时间
    private Date createTime;

    // 更新时间
    private Date updateTime;

    // 状态码
    private Byte status;

    public UserRecord(Long userId, Long problemId, Byte isSuccess) {
        this.userId = userId;
        this.problemId = problemId;
        this.isSuccess = isSuccess;
    }

    public UserRecord(Long userId, Long problemId, Byte isSuccess, Byte status) {
        this.userId = userId;
        this.problemId = problemId;
        this.isSuccess = isSuccess;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getProblemId() {
        return problemId;
    }

    public void setProblemId(Long problemId) {
        this.problemId = problemId;
    }

    public Byte getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(Byte isSuccess) {
        this.isSuccess = isSuccess;
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
}