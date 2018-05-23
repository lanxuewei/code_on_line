package com.lanxuewei.code_on_line.service;

import com.lanxuewei.code_on_line.dao.entity.Case;
import com.lanxuewei.code_on_line.dao.entity.Problem;
import com.lanxuewei.code_on_line.dto.ProblemCountDto;
import com.lanxuewei.code_on_line.dto.ProblemDetailDto;
import com.lanxuewei.code_on_line.dto.ProblemDto;
import com.lanxuewei.code_on_line.judger.JudgeStatus;
import com.lanxuewei.code_on_line.model.Page;
import com.lanxuewei.code_on_line.model.ProblemViewModel;
import com.lanxuewei.code_on_line.model.ReturnValue;
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

    /**
     * 运行代码
     * @param userId
     * @param problemId
     * @param code
     */
    ReturnValue runCodeAfter(Long userId, Long problemId, String code);

    /**
     * 运行代码,返回运行结果
     * @param code
     * @param cases 需要通过的测试用例集
     * @return 通过或者失败
     */
    ReturnValue runCode(String code, List<Case> cases);

    //delete
    /**
     * delete problem by id
     * @param id
     * @return true or false
     */
    boolean deleteProblemById(Long id);

    /**
     * 删除问题(修改状态码status为-1,不是真正意义删除)
     * @param id
     * @return
     */
    boolean deleteById(Long id);

    //find
    /**
     * find problem
     * @param id 题目id
     * @param status
     * @return true or false
     */
    Problem findProblemById(Long id, Byte status);

    /**
     * 查询所有问题
     * @param status 状态码(状态划分 0:正常 -1:已删除 null:所有)
     * @param keyword 关键字
     * @param difficulty 难易度
     * @param resolve 状态码(针对于学生身份 状态划分 null:所有 0:已做 -1:未做)
     * @param resolvedProblemIds 已做题目 problemId 集
     * @return
     */
    List<ProblemDto> selectAll(Byte status,
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
     * @return 分页后数据集以及分页信息
     */
    Page<ProblemDto> selectByPage(Integer pageNum,
                               Integer pageSize,
                               Byte status,
                               String keyword,
                               Byte difficulty,
                               Long userId,
                               Byte resolve);
    /**
     * 查找用户已做题目id集
     * @param userId
     * @return
     */
    //List<Long> getAllResolvedProblems(Long userId);

    /**
     * 查询各难易度对应的题目数
     * @return
     */
    Map<Integer, Integer> countByDifficulty(Byte status);

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

    /**
     * find problem(其中包含题目详情,提交次数,成功次数,相关标签集,用户提交记录)
     *   1.根据 problemId 查找Problem
     *   2.根据 problemId 查找提交次数
     *   3.根据 problemId 查找成功次数
     *   4.根据 problemId 查询对应标签集合
     * @param problemId 题目id
     * @param userId 用户id
     * @param status 状态码
     * @return ProblemDetailDto
     */
    ProblemDetailDto findProblemForDetail(Long problemId, Long userId, Byte status);

    /**
     * 判断该用户是否为管理员
     * @param userId
     * @return
     */
    boolean isManager(Long userId);

    /**
     * 更新任务状态
     * @param status
     * @param id
     * @return
     */
    boolean changeStatusById(Byte status, Long id);

    //modify
    /**
     * modify problem
     * @param problem
     * @return true or false
     */
    boolean modifyProblemById(Problem problem);
}
