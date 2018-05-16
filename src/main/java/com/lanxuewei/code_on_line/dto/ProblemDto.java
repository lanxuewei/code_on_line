package com.lanxuewei.code_on_line.dto;

import com.lanxuewei.code_on_line.dao.entity.Problem;

import java.util.Date;
import java.util.List;

/**
 * create by lanxuewei in 2018/5/16 13:13
 * description: problem dto
 */
public class ProblemDto {

    /**
     * 是否已做
     */
    private boolean isResolved;

    /**
     * 通过率
     */
    private double throughRate;

    /**
     * 其余属性与 Problem 相同
     */
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

    /**
     * 设置ProblemDto list的 isResolved 属性
     * @param allResolvedProblemIds
     * @param problemDtos
     * @return
     */
    public static List<ProblemDto> setProblemDtoIsResolved(List<Long> allResolvedProblemIds, List<ProblemDto> problemDtos) {
        if (problemDtos != null) {
            if (allResolvedProblemIds != null) {
                //boolean isResolved = false;
                for (ProblemDto problemDto : problemDtos) {
                    if (allResolvedProblemIds.contains(problemDto.getId())) {  // 该题已做
                        problemDto.setResolved(true);   // 已做
                    } else {
                        problemDto.setResolved(false);  // 未做
                    }
                }
            }
            return problemDtos;
        }
        return null;
    }

    public boolean isResolved() {
        return isResolved;
    }

    public void setResolved(boolean resolved) {
        isResolved = resolved;
    }

    public double getThroughRate() {
        return throughRate;
    }

    public void setThroughRate(double throughRate) {
        this.throughRate = throughRate;
    }

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
        this.name = name;
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
        this.des = des;
    }

    @Override
    public String toString() {
        return "ProblemDto{" +
                "isResolved=" + isResolved +
                ", throughRate=" + throughRate +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", difficulty=" + difficulty +
                ", submit=" + submit +
                ", fail=" + fail +
                ", success=" + success +
                ", author='" + author + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", status=" + status +
                ", des='" + des + '\'' +
                '}';
    }
}
