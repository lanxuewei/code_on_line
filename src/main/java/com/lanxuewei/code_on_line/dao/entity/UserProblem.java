package com.lanxuewei.code_on_line.dao.entity;

import java.util.Date;

public class UserProblem extends UserProblemKey {

    // userId problemId 为联合主键

    // 提交次数
    private Integer submit;

    // 失败次数
    private Integer fail;

    // 成功次数
    private Integer success;

    // 创建时间
    private Date createTime;

    // 更新时间
    private Date updateTime;

    // 状态码
    private Byte status;

    // 最后一次提交记录
    private String lastSubmit;

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

    public String getLastSubmit() {
        return lastSubmit;
    }

    public void setLastSubmit(String lastSubmit) {
        this.lastSubmit = lastSubmit == null ? null : lastSubmit.trim();
    }
}