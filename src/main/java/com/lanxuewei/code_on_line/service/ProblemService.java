package com.lanxuewei.code_on_line.service;

import com.lanxuewei.code_on_line.dao.entity.Case;
import com.lanxuewei.code_on_line.dao.entity.Problem;
import com.lanxuewei.code_on_line.dto.ProblemCountDto;
import com.lanxuewei.code_on_line.dto.ProblemDto;
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
     * 查询所有问题
     * @param status 状态码(状态划分 0:正常 -1:已删除 null:所有)
     * @param keyword 关键字
     * @param difficulty 难易度
     * @param resolve 状态码(针对于学生身份 状态划分 null:所有 0:已做 -1:未做)
     * @param resolvedProblemIds 已做题目 problemId 集
     * @return
     */
    List<Problem> selectAll(Byte status,
                            String keyword,
                            Byte difficulty,
                            Byte resolve,
                            List<Long> resolvedProblemIds);

    /**
     * 查找总记录数
     * @param status 状态码(状态划分 0:正常 -1:已删除 null:所有)
     * @return count all problems
     */
    Integer selectCount(Byte status);

    /**
     * 分页查找问题(状态划分 0:正常 -1:已删除 null:所有) todo......
     * @param pageNum 页码
     * @param pageSize 每页大小
     * @param status 状态码
     * @param keyword 关键字(存在则对题目名进行模糊查找)
     * @param difficulty 难易度
     * @param userId 用户判断用户身份
     * @param resolve 状态码(针对于学生身份 状态划分 null:所有 0:已做 -1:未做)
     * @param allResolvedProblemIds 已做题目集
     * @return 分页后数据集以及分页信息
     */
    Page<Problem> selectByPage(Integer pageNum,
                               Integer pageSize,
                               Byte status,
                               String keyword,
                               Byte difficulty,
                               Long userId,
                               Byte resolve,
                               List<Long> allResolvedProblemIds);
    /**
     * 查找用户已做题目id集
     * @param userId
     * @return
     */
    List<Long> getAllResolvedProblems(Long userId);

    /**
     * 查询各难易度对应的题目数
     * @return
     */
    Map<Integer, Integer> countByDifficulty();

    /**
     * 统计该用户所通过的题数
     * @return
     */
    Integer countAllResolved(Long userId);

    /**
     * 获取题目相关统计信息以及用户完成题目数
     * @param userId
     * @return
     */
    ProblemCountDto countProblemAndResolved(Long userId);

    //modify
    /**
     * modify problem
     * @param problem
     * @return true or false
     */
    boolean modifyProblemById(Problem problem);
}
