package com.lanxuewei.code_on_line.service.imp;

import com.lanxuewei.code_on_line.dao.entity.Problem;
import com.lanxuewei.code_on_line.dao.mapper.ProblemMapper;
import com.lanxuewei.code_on_line.service.ProblemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * create by lanxuewei in 2018/4/21 18:39
 * description: Problem 相关业务实现
 */
@Service
public class ProblemServiceImp implements ProblemService{

    private static final Logger logger = LoggerFactory.getLogger(ProblemServiceImp.class);

    @Autowired
    private ProblemMapper problemMapper;

    @Override
    public boolean addProblem(Problem problem) {
        return problemMapper.insert(problem) != 0;
    }

    @Override
    public boolean deleteProblemById(Long id) {
        return problemMapper.deleteByPrimaryKey(id) != 0;
    }

    @Override
    public Problem findProblemById(Long id) {
        return problemMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean modifyProblemById(Problem problem) {
        return problemMapper.updateByPrimaryKey(problem) != 0;
    }
}
