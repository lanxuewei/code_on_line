package com.lanxuewei.code_on_line.dao.entity;

public class UserProblemKey {

    // 主键
    private Long userId;

    // 主键
    private Long problemId;

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
}