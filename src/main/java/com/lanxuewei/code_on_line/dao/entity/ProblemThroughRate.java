package com.lanxuewei.code_on_line.dao.entity;

/**
 * create by lanxuewei in 2018/5/16 14:57
 * description: 题目通过率
 */
public class ProblemThroughRate {

    /**
     * problem id
     */
    private Long problemId;

    /**
     * 成功次数(每个用户作为一个单位)
     */
    private Integer successCount;

    /**
     * 总提交次数
     */
    private Integer doneCount;

    /**
     * 通过率
     */
    private double through;

    public Long getProblemId() {
        return problemId;
    }

    public void setProblemId(Long problemId) {
        this.problemId = problemId;
    }

    public Integer getSuccessCount() {
        return successCount;
    }

    public void setSuccessCount(Integer successCount) {
        this.successCount = successCount;
    }

    public Integer getDoneCount() {
        return doneCount;
    }

    public void setDoneCount(Integer doneCount) {
        this.doneCount = doneCount;
    }

    public double getThrough() {
        return through;
    }

    public void setThrough(double through) {
        this.through = through;
    }

    @Override
    public String toString() {
        return "ProblemThroughRate{" +
                "problemId=" + problemId +
                ", successCount=" + successCount +
                ", doneCount=" + doneCount +
                ", through=" + through +
                '}';
    }
}
