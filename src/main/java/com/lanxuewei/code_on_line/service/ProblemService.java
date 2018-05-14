package com.lanxuewei.code_on_line.service;

import com.lanxuewei.code_on_line.dao.entity.Case;
import com.lanxuewei.code_on_line.dao.entity.Problem;
import com.lanxuewei.code_on_line.model.Page;
import com.lanxuewei.code_on_line.model.ProblemViewModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * create by lanxuewei in 2018/4/21 17:30
 * description: Problem 类的CURD操作
 */
@Service
public interface ProblemService {

    //add
    /**
     * add problem
     * @param problemViewModel
     * @return true or false
     */
    boolean addProblem(ProblemViewModel problemViewModel);

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
    /**
     * 查找所有问题
     * @return all problems
     */
    List<Problem> selectAll();
    /**
     * 查找总记录数
     * @return count all problems
     */
    int selectCount();
    /**
     * 分页查找problem
     * @param pageNum 页码
     * @param pageSize 每页大小
     * @return 分页后数据集以及分页信息
     */
    Page<Problem> selectByPage(Integer pageNum, Integer pageSize);
    /**
     * 查询各难易度对应的题目数
     * @return
     */
    Map<Integer, Integer> findCountByDifficulty();

    //modify
    /**
     * modify problem
     * @param problem
     * @return true or false
     */
    boolean modifyProblemById(Problem problem);
}
