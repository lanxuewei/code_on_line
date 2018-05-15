package com.lanxuewei.code_on_line.dto;

import com.lanxuewei.code_on_line.dao.entity.Problem;
import com.lanxuewei.code_on_line.model.Page;

import java.util.List;

/**
 * create by lanxuewei in 2018/5/16 00:32
 * description: Problem list dto
 */
public class ProblemListDto {

    /**
     * 已做题目id
     */
    private List<Long> allResolvedProblemIds;

    /**
     * 题目集
     */
    private Page<Problem> problemDtoPage;

    public ProblemListDto(List<Long> allResolvedProblemIds, Page<Problem> problemDtoPage) {
        this.allResolvedProblemIds = allResolvedProblemIds;
        this.problemDtoPage = problemDtoPage;
    }

    public List<Long> getAllResolvedProblemIds() {
        return allResolvedProblemIds;
    }

    public void setAllResolvedProblemIds(List<Long> allResolvedProblemIds) {
        this.allResolvedProblemIds = allResolvedProblemIds;
    }

    public Page<Problem> getProblemDtoPage() {
        return problemDtoPage;
    }

    public void setProblemDtoPage(Page<Problem> problemDtoPage) {
        this.problemDtoPage = problemDtoPage;
    }
}
