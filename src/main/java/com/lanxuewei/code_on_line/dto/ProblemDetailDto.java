package com.lanxuewei.code_on_line.dto;

import com.lanxuewei.code_on_line.dao.entity.Problem;
import com.lanxuewei.code_on_line.dao.entity.Tag;

import java.util.List;

/**
 * create by lanxuewei in 2018/5/17 09:30
 * description: 问题详情dto
 */
public class ProblemDetailDto {

    /**
     * Problem 所有属性
     */
    private Problem problem;

    /**
     * 提交次数
     */
    private Integer doneCount;

    /**
     * 成功次数
     */
    private Integer successCount;

    /**
     * 相关联的标签集
     */
    private List<Tag> tags;

    /**
     * 提交记录 todo 是上一次代码，还是统计
     */
    private String submits;

    public Problem getProblem() {
        return problem;
    }

    public void setProblem(Problem problem) {
        this.problem = problem;
    }

    public Integer getDoneCount() {
        return doneCount;
    }

    public void setDoneCount(Integer doneCount) {
        this.doneCount = doneCount;
    }

    public Integer getSuccessCount() {
        return successCount;
    }

    public void setSuccessCount(Integer successCount) {
        this.successCount = successCount;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public String getSubmits() {
        return submits;
    }

    public void setSubmits(String submits) {
        this.submits = submits;
    }
}
