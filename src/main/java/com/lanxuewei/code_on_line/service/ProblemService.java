package com.lanxuewei.code_on_line.service;

import com.lanxuewei.code_on_line.dao.entity.Problem;
import org.springframework.stereotype.Service;

/**
 * create by lanxuewei in 2018/4/21 17:30
 * description: Problem 类的CURD操作
 */
@Service
public interface ProblemService {

    //add
    /**
     * add problem
     * @param problem
     * @return true or false
     */
    boolean addProblem(Problem problem);

    //delete
    /**
     * delete problem by id
     * @param id
     * @return true or false
     */
    boolean deleteProblemById(Long id);

    //find
    /**
     * find problem
     * @param id
     * @return true or false
     */
    Problem findProblemById(Long id);

    //modify
    /**
     * modify problem
     * @param problem
     * @return true or false
     */
    boolean modifyProblemById(Problem problem);
}
