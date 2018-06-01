package com.lanxuewei.code_on_line.service.imp;

import com.github.pagehelper.PageHelper;
import com.lanxuewei.code_on_line.constant.ReturnCodeAndMsgEnum;
import com.lanxuewei.code_on_line.constant.ServiceConstant;
import com.lanxuewei.code_on_line.dao.entity.*;
import com.lanxuewei.code_on_line.dao.mapper.*;
import com.lanxuewei.code_on_line.dto.CaseDto;
import com.lanxuewei.code_on_line.dto.ProblemCountDto;
import com.lanxuewei.code_on_line.dto.ProblemDetailDto;
import com.lanxuewei.code_on_line.dto.ProblemDto;
import com.lanxuewei.code_on_line.judger.CppSolution;
import com.lanxuewei.code_on_line.judger.JudgeStatus;
import com.lanxuewei.code_on_line.model.CaseViewModel;
import com.lanxuewei.code_on_line.model.Page;
import com.lanxuewei.code_on_line.model.ProblemViewModel;
import com.lanxuewei.code_on_line.model.ReturnValue;
import com.lanxuewei.code_on_line.service.ProblemService;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.text.DecimalFormat;
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
    @Autowired
    private UserRecordMapper userRecordMapper;

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

    /**
     * 删除问题,修改状态码status为-1,并不是真正意义上删除
     * @param id
     * @return
     */
    public boolean deleteById(Long id) {
        return problemMapper.deleteById(id) != 0;
    }

    @Override
    public Problem findProblemById(Long id, Byte status) {
        return problemMapper.selectByPrimaryKey(id, status);
    }

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
    public ProblemDetailDto findProblemForDetail(Long problemId, Long userId, Byte status) {
        ProblemDetailDto problemDetailDto = new ProblemDetailDto();     // 返回结果集
        problemDetailDto.setProblem(problemMapper.selectByPrimaryKey(problemId, status));                      // 1
        problemDetailDto.setDoneCount(userProblemMapper.selectDoneCountByProblemId(problemId, status));        // 2
        problemDetailDto.setSuccessCount(userProblemMapper.selectSuccessCountByProblemId(problemId, status));  // 3
        problemDetailDto.setTags(tagMapper.selectTagsByProblemId(problemId, status));                          // 4
        return problemDetailDto;
    }


    /**
     * 根据题目难易度查询对应的题目数
     * @return
     */
    @Override
    public Map<Integer, Integer> countByDifficulty(Byte status) {
        List<Map<String, Integer>> list = problemMapper.selectCountByDifficulty(status);
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
        Map<Integer, Integer> difficultyMap = countByDifficulty(ServiceConstant.Problem.Normal);    // 查询难易度对应的题目数
        ProblemCountDto problemCountDto = difficultyMapToProblemCountDto(difficultyMap);            // 获取难易度对应题目数属性
        Integer totalCount = selectCount(ServiceConstant.Problem.Normal);                           // 查询题目总数
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
        problemCountDto.setEasyCount(difficultyMap.get(Easy_Int) == null ? 0 : difficultyMap.get(Easy_Int));        // 简单题
        problemCountDto.setMediumCount(difficultyMap.get(Medium_Int) == null ? 0 : difficultyMap.get(Medium_Int));  // 中等题
        problemCountDto.setDifficultyCount(difficultyMap.get(Difficulty_Int) == null ? 0 : difficultyMap.get(Difficulty_Int));  // 难题
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
        }
        setProblemThroughRateByCounts(problemDtos);         // 设置通过率
        return new Page<>(problemDtos);
    }


    /**
     * 计算题目通过率 按照次数算
     * @param problemDtos
     * @return
     */
    private List<ProblemDto> setProblemThroughRateByCounts(List<ProblemDto> problemDtos) {
        if (problemDtos != null) {
            for (ProblemDto problemDto : problemDtos) {  // 遍历问题集
                double throughRate = 0;
                if (problemDto.getSubmit() != 0) {       // 防止除数为0
                    throughRate = 1.0 * problemDto.getSuccess()/problemDto.getSubmit() * 100;   // 成功次数除以总提交次数
                }
                throughRate = (double) Math.round(throughRate * 100) / 100;                     // 保留两位小数
                problemDto.setThroughRate(throughRate);
            }
        }
        return problemDtos;  // 直接返回
    }

    /**
     * 计算题目对应通过率 按照用户算
     * @param problemDtos
     * @return
     */
    private List<ProblemDto> setProblemThroughRateByPeople(List<ProblemDto> problemDtos) {
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
    public boolean isManager(Long userId) {
        Byte status = userMapper.selectStatusByUserId(userId);  // 通过 userId 查找对应的 status
        if (status == ServiceConstant.User.manager) {  // 表示管理员
            return true;
        } else {
            return false;                              // 学生身份
        }
    }

    /**
     * 程序运行后更新数据库数据
     *  1.根据问题id查询测试用例集
     *  2.遍历测试用例,运行code
     *  3.根据返回值更新用户做题情况
     * @param userId
     * @param problemId
     * @param code
     * @return
     */
    @Override
    @Transactional
    public ReturnValue runCodeAfter(Long userId, Long problemId, String code) {
        List<Case> cases = caseMapper.selectAllByProblemId(problemId, ServiceConstant.Problem.Normal);  // 1 查询可用测试用例
        ReturnValue runCodeReturnVal = runCode(code, cases);  // 2 运行程序
        Byte isSuccess = null;
        if (runCodeReturnVal.getCode().equals(ReturnCodeAndMsgEnum.Accepted.getCode())) {  // 3 成功
            updateUserProblemRecord(userId, problemId, code, true);   // 答案正确
            isSuccess = 0;
            UserRecord userRecord = new UserRecord(userId, problemId, isSuccess, ReturnCodeAndMsgEnum.Accepted.getInfo());
            userRecordMapper.insertSelective(userRecord);
        } else {
            updateUserProblemRecord(userId, problemId, code, false);  // 失败
            isSuccess = -1;
            UserRecord userRecord = new UserRecord(userId, problemId, isSuccess);
            if (runCodeReturnVal.getCode().equals(ReturnCodeAndMsgEnum.Compile_Failed.getCode())) {     // 编译失败
                userRecord.setResultBody(ReturnCodeAndMsgEnum.Compile_Failed.getInfo());
            }
            if (runCodeReturnVal.getCode().equals(ReturnCodeAndMsgEnum.Time_Out.getCode())) {           // 超时
                userRecord.setResultBody(ReturnCodeAndMsgEnum.Time_Out.getInfo());
            }
            if (runCodeReturnVal.getCode().equals(ReturnCodeAndMsgEnum.Wrong_Answer.getCode())) {       // 答案错误
                userRecord.setResultBody(ReturnCodeAndMsgEnum.Wrong_Answer.getInfo());
            }
            userRecordMapper.insertSelective(userRecord);  // 更新记录
        }
        return runCodeReturnVal;
    }

    /**
     * 更新用户-问题记录 如果不存在则插入新纪录
     * @param userId
     * @param problemId
     * @param code
     * @param isSuccess
     */
    public void updateUserProblemRecord(Long userId, Long problemId, String code, boolean isSuccess) {
        Byte successOrFailed = null;
        UserProblem userProblem = new UserProblem();
        userProblem.setUserId(userId);
        userProblem.setProblemId(problemId);
        userProblem.setSubmit(1);         // 提交1次
        userProblem.setLastSubmit(code);  // 最后一次提交的代码
        if (isSuccess) {  // 成功
            successOrFailed = 0;
            userProblem.setSuccess(1);    // 成功1次
            userProblem.setFail(0);       // 失败0次
        } else {          // 失败
            successOrFailed = -1;
            userProblem.setFail(1);       // 失败1次
            userProblem.setSuccess(0);    // 成功0次
        }
        int updateCount = userProblemMapper.updateSubmitsByIds(userId, problemId, code,successOrFailed);  // 更新记录
        if (updateCount < 1) {            // 不存在该用户与该题记录则插入记录 1次提交 1次成功或失败 以及最后一次提交代码
            userProblemMapper.insertSelective(userProblem);  // 插入
        }
        problemMapper.updateSubmitById(problemId, successOrFailed);  // 更新题目中提交次数以及成功失败次数
    }

    /**
     * 运行代码
     * @param code
     * @param cases 需要通过的测试用例集
     * @return
     */
    @Override
    public ReturnValue runCode(String code, List<Case> cases) {
        int timeLimit = 3;        // 限制时间
        long memoryLimit = 1024;  // 限制内存
        if (cases != null && cases.size() != 0) {
            for (Case item : cases) {  // 遍历测试用例
                CppSolution solution = new CppSolution(code, timeLimit, memoryLimit,
                        item.getInput(), item.getOutput());
                JudgeStatus judgeStatus = solution.judge();                     // 编译运行
                if (judgeStatus.equals(JudgeStatus.COMPILE_ERROR)) {            // 编译错误
                    return new ReturnValue(ReturnCodeAndMsgEnum.Compile_Failed,
                            solution.getCompileLog());                          // 获取编译失败原因
                }
                if (judgeStatus.equals(JudgeStatus.TIME_LIMIT_EXCEEDED)) {      // 超时
                    return new ReturnValue(ReturnCodeAndMsgEnum.Time_Out);
                }
                if (judgeStatus.equals(JudgeStatus.WRONG_ANSWER)) {             // 答案错误
                    return new ReturnValue(ReturnCodeAndMsgEnum.Wrong_Answer,
                            new CaseDto(item));                                 // 存入未通过测试用例
                }
                solution.removeFiles();                                         // 删除临时文件
            }
            return new ReturnValue(ReturnCodeAndMsgEnum.Accepted);              // 成功
        }
        return new ReturnValue(ReturnCodeAndMsgEnum.No_Cases);  // 无测试用例
    }

    @Override
    public boolean modifyProblemById(Problem problem) {
        return problemMapper.updateByPrimaryKey(problem) != 0;
    }

    /**
     * 更新任务状态
     * @param status
     * @param id
     * @return
     */
    @Override
    public boolean changeStatusById(Byte status, Long id) {
        return problemMapper.updateStatusById(status, id) != 0;
    }
}
