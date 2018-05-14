package com.lanxuewei.code_on_line.dto;

/**
 * create by lanxuewei in 2018/5/14 12:11
 * description: problem 相关统计dto
 */
public class ProblemCountDto {

    // 简单题数
    private Integer easyCount;

    // 中等题数
    private Integer mediumCount;

    // 难题数
    private Integer difficultyCount;

    // 总题数
    private Integer totalCount;

    // 已完成题数
    private Integer resolved;

    public Integer getEasyCount() {
        return easyCount;
    }

    public void setEasyCount(Integer easyCount) {
        this.easyCount = easyCount;
    }

    public Integer getMediumCount() {
        return mediumCount;
    }

    public void setMediumCount(Integer mediumCount) {
        this.mediumCount = mediumCount;
    }

    public Integer getDifficultyCount() {
        return difficultyCount;
    }

    public void setDifficultyCount(Integer difficultyCount) {
        this.difficultyCount = difficultyCount;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getResolved() {
        return resolved;
    }

    public void setResolved(Integer resolved) {
        this.resolved = resolved;
    }

    @Override
    public String toString() {
        return "ProblemCountDto{" +
                "easyCount=" + easyCount +
                ", mediumCount=" + mediumCount +
                ", difficultyCount=" + difficultyCount +
                ", totalCount=" + totalCount +
                ", resolved=" + resolved +
                '}';
    }
}
