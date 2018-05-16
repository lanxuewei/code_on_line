package com.lanxuewei.code_on_line.service.imp;

import com.github.pagehelper.PageHelper;
import com.lanxuewei.code_on_line.constant.ServiceConstant;
import com.lanxuewei.code_on_line.dao.entity.*;
import com.lanxuewei.code_on_line.dao.mapper.*;
import com.lanxuewei.code_on_line.dto.ProblemCountDto;
import com.lanxuewei.code_on_line.dto.ProblemDto;
import com.lanxuewei.code_on_line.model.CaseViewModel;
import com.lanxuewei.code_on_line.model.Page;
import com.lanxuewei.code_on_line.model.ProblemViewModel;
import com.lanxuewei.code_on_line.service.ProblemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * create by lanxuewei in 2018/4/21 18:39
 * description: Problem 相关业务实现
 */
@Service
public class ProblemServiceImp implements ProblemService{

    private static final Logger logger = LoggerFactory.getLogger(ProblemServiceImp.class);

    @Autowired
    private ProblemMapper problemMapper;
    @Autowired
    private CaseMapper caseMapper;
    @Autowired
    private TagMapper tagMapper;
    @Autowired
    private ProblemTagMapper problemTagMapper;
    @Autowired
    private UserProblemMapper userProblemMapper;
    @Autowired
    private UserMapper userMapper;

    private static final String Difficulty_Str = "difficulty";
    private static final String Count_Str = "count";
    private static final Integer Easy_Int = 0;
    private static final Integer Medium_Int = 1;
    private static final Integer Difficulty_Int = 2;

    /**
     * 将 ProblemViewModel 解析为 Problem Case集合 以及生成 ProblemTag 并插入 todo 未进行name唯一检查
     * 1、插入 Problem 记录
     * 2、插入 Case集 记录
     * 3、插入 ProblemTag 关联记录
     * @param problemViewModel
     * @return
     */
    @Override
    @Transactional
    public boolean addProblem(ProblemViewModel problemViewModel) {
        if (problemViewModel != null) {
            Problem problem = problemViewModelToProblem(problemViewModel);                   // 获取 Problem 实体
            problemMapper.insertSelective(problem);                                          // 1、插入问题
            List<Case> cases = caseViewModelsToCases(problemViewModel, problem.getId());     // 获取 Case 实体集合
            for (int i = 0; i < cases.size(); i++) {
                caseMapper.insertSelective(cases.get(i));      // 2、插入测试用例
            }
            List<Tag> tags = problemViewModel.getTags();                                     // 获取 Tag 实体集合
            for (int i = 0; i < tags.size(); i++) {
                ProblemTag problemTag = new ProblemTag();      // 用于插入问题标签关联记录
                problemTag.setProblemId(problem.getId());
                problemTag.setTagId(tags.get(i).getId());
                problemTagMapper.insertSelective(problemTag);  // 3、插入 问题-标签关联记录
            }
            return true;
        }
        return false;

        //return problemMapper.insert(problem) != 0;
    }

    /**
     * 将 ProblemViewModel 转化为 Problem
     * @param problemViewModel
     * @return
     */
    private Problem problemViewModelToProblem(ProblemViewModel problemViewModel) {
        if (problemViewModel != null) {
            Problem problem = new Problem();
            problem.setName(problemViewModel.getName());              // 题目名
            problem.setAuthor(problemViewModel.getAuthor());          // 作者
            problem.setDifficulty(problemViewModel.getDifficulty());  // 难易度
            problem.setDes(problemViewModel.getDes());                // 描述
            problem.setDesHtml(problemViewModel.getDesHtml());        // 描述 html 格式
            return problem;
        }
        return null;
    }

    /**
     * 将 problemViewModel 中 caseViewModels 集转化为 Case 集
     * @param problemViewModel
     * @param problemId
     * @return
     */
    private List<Case> caseViewModelsToCases(ProblemViewModel problemViewModel, Long problemId) {
        if (problemViewModel.getAuthor() != null) {
            List<Case> cases = new ArrayList<>();
            for (CaseViewModel item : problemViewModel.getCaseViewModels()) {
                Case testCase = new Case();
                testCase.setProblemId(problemId);    // 问题id
                testCase.setInput(item.getInput());  // 转化为Case
                testCase.setOutput(item.getOutput());
                cases.add(testCase);                 // 加入集合
            }
            return cases;
        }
        return null;
    }

    @Override
    public boolean deleteProblemById(Long id) {
        return problemMapper.deleteByPrimaryKey(id) != 0;
    }

    @Override
    public Problem findProblemById(Long id) {
        return problemMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据题目难易度查询对应的题目数
     * @return
     */
    @Override
    public Map<Integer, Integer> countByDifficulty() {
        List<Map<String, Integer>> list = problemMapper.selectCountByDifficulty();
        Map<Integer, Integer> result = transferCountMap(list);
        return result;
    }

    /**
     * 将根据难度分组统计数据的list转化为map
     *   结果 map 为 {0->2, 1->3, 2->6} key为难度系数,即 0 1 2, value为问题数
     * @param list
     * @return
     */
    private Map<Integer, Integer> transferCountMap(List<Map<String, Integer>> list) {
        if (list != null) {
            Map<Integer, Integer> resultMap = new HashMap<>();
            for (Map<String, Integer> map : list) {
                logger.info("map = {}", map);
                //Integer count = map.get("difficulty");
                resultMap.put(map.get(Difficulty_Str), map.get(Count_Str));
            }
            return resultMap;
        }
        return null;
    }

    /**
     * 获取题目相关统计信息以及用户完成题目数
     * @param userId
     * @return
     */
    @Override
    @Transactional
    public ProblemCountDto countProblemAndResolved(Long userId) {
        Map<Integer, Integer> difficultyMap = countByDifficulty();                        // 查询难易度对应的题目数
        ProblemCountDto problemCountDto = difficultyMapToProblemCountDto(difficultyMap);  // 获取难易度对应题目数属性
        Integer totalCount = selectCount(ServiceConstant.Problem.Normal);                 // 查询题目总数
        problemCountDto.setTotalCount(totalCount);
        Integer resolved = userProblemMapper.countAllResolved(userId);  // 已完成题目数
        problemCountDto.setResolved(resolved);
        return problemCountDto;
    }

    /**
     * 将难易度统计 map 转化为 ProblemCountDto 中属性
     *   map {0->1, 1->4, 2->9} key为难易度标示，value为对应题目数量
     * @param difficultyMap
     * @return
     */
    private ProblemCountDto difficultyMapToProblemCountDto(Map<Integer, Integer> difficultyMap) {
        ProblemCountDto problemCountDto = new ProblemCountDto();                // 用于封装难易度对应题目数
        difficultyMap.get(Easy_Int);
        problemCountDto.setEasyCount(difficultyMap.get(Easy_Int));              // 简单题
        problemCountDto.setMediumCount(difficultyMap.get(Medium_Int));          // 中等题
        problemCountDto.setDifficultyCount(difficultyMap.get(Difficulty_Int));  // 难题
        return problemCountDto;
    }

    /**
     * 查询所有问题
     * @param status 状态码(状态划分 0:正常 -1:已删除 null:所有)
     * @param keyword 关键字
     * @param difficulty 难易度
     * @param resolve 状态码(针对于学生身份 状态划分 null:所有 0:已做 -1:未做)
     * @param resolvedProblemIds 已做题目 problemId 集
     * @return
     */
    @Override
    public List<ProblemDto> selectAll(Byte status, String keyword, Byte difficulty, Byte resolve, List<Long> resolvedProblemIds) {
        return problemMapper.selectAll(status, keyword, difficulty, resolve , resolvedProblemIds);
    }

    /**
     * 查询问题总记录数(状态划分 0:正常 -1:已删除 null:所有)
     * @param status 状态码
     * @return
     */
    @Override
    public Integer selectCount(Byte status) {
        return problemMapper.selectCount(status);
    }

    /**
     * 根据用户id查找完成的题目数
     * @param userId
     * @return
     */
    @Override
    public Integer countAllResolved(Long userId) {
        return userProblemMapper.countAllResolved(userId);
    }

    /**
     * 分页查找问题(状态划分 0:正常 -1:已删除 null:所有)
     * @param pageNum 页码
     * @param pageSize 每页大小
     * @param status 状态码
     * @param keyword 关键字(存在则对题目名进行模糊查找)
     * @param difficulty 难易度
     * @param userId 用户判断用户身份
     * @param resolve 状态码(针对于学生身份 状态划分 null:所有 0:已做 -1:未做)
     * @return
     */
    @Override
    @Transactional
    public Page<ProblemDto> selectByPage(Integer pageNum,
                                      Integer pageSize,
                                      Byte status,
                                      String keyword,
                                      Byte difficulty,
                                      Long userId,
                                      Byte resolve) {
        boolean isManager = isManager(userId);          // 判断是否为管理员
        List<ProblemDto> problemDtos = null;            // 返回结果集
        if (isManager) {  // 管理员身份
            PageHelper.startPage(pageNum, pageSize);
            problemDtos = problemMapper.selectAll(status, keyword, difficulty, null, null);
        } else {          // 学生身份
            List<Long> allResolvedProblemIds = userProblemMapper.
                    selectProblemIdByResolved(userId);  // 通过 userId 查找该用户已做题目集
            PageHelper.startPage(pageNum, pageSize);
            problemDtos = problemMapper.selectAll(status, keyword, difficulty, resolve, allResolvedProblemIds);
            problemDtos = ProblemDto.setProblemDtoIsResolved(allResolvedProblemIds, problemDtos);  // 设置题目已做或者未做标识
            setProblemThroughrate(problemDtos);         // 设置通过率
        }
        return new Page<>(problemDtos);
    }

    /**
     * 计算题目对应通过率
     * @param problemDtos
     * @return
     */
    private List<ProblemDto> setProblemThroughrate(List<ProblemDto> problemDtos) {
        if (problemDtos != null) {
            List<Long> problemIds = getProblemIds(problemDtos);  // 获取需要计算通过率的问题id集
            List<ProblemThroughRate> problemThroughRates = userProblemMapper.selectProblemThroughRate(problemIds);  // 计算问题通过率
            for (ProblemDto problemDto : problemDtos) {
                for (ProblemThroughRate problemThroughRate : problemThroughRates) {
                    if (problemDto.getId() == problemThroughRate.getProblemId()) {   // id 相同即本题通过率计算值
                        problemDto.setThroughRate(problemThroughRate.getThrough());  // 设置通过率
                    }
                }
            }
        }
        return problemDtos;  // 直接返回
    }


    /**
     * 获取ProblemDto集合中的所有id
     * @param problemDtos
     * @return
     */
    private List<Long> getProblemIds(List<ProblemDto> problemDtos) {
        List<Long> problemIds = new ArrayList<>();
        if (problemDtos != null) {
            for (ProblemDto problemDto : problemDtos) {
                problemIds.add(problemDto.getId());  // 获取所有ProblemDto的id值
            }
        }
        return problemIds;
    }

    /**
     * 判断该用户是否为管理员
     * @param userId
     * @return
     */
    private boolean isManager(Long userId) {
        Byte status = userMapper.selectStatusByUserId(userId);  // 通过 userId 查找对应的 status
        if (status == ServiceConstant.User.manager) {  // 表示管理员
            return true;
        } else {
            return false;                              // 学生身份
        }
    }

    @Override
    public boolean modifyProblemById(Problem problem) {
        return problemMapper.updateByPrimaryKey(problem) != 0;
    }
}
