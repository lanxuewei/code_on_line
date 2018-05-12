package com.lanxuewei.code_on_line.dao.entity;

import java.util.Date;

public class ProblemTag extends ProblemTagKey {

    // problemId tagId 为联合主键

    // 创建时间
    private Date createTime;

    // 更新时间
    private Date updateTime;

    // 状态码
    private Byte status;

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

    @Override
    public String toString() {
        return "ProblemTag{" +
                "createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", status=" + status +
                '}';
    }
}