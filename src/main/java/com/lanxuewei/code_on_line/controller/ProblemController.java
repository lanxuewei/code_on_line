package com.lanxuewei.code_on_line.controller;

import com.alibaba.fastjson.JSON;
import com.lanxuewei.code_on_line.authorization.annotation.NoNeedLogin;
import com.lanxuewei.code_on_line.authorization.config.Constants;
import com.lanxuewei.code_on_line.constant.ReturnCodeAndMsgEnum;
import com.lanxuewei.code_on_line.constant.ServiceConstant;
import com.lanxuewei.code_on_line.dao.entity.Problem;
import com.lanxuewei.code_on_line.dto.ProblemDetailDto;
import com.lanxuewei.code_on_line.dto.ProblemDto;
import com.lanxuewei.code_on_line.dto.ProblemListDto;
import com.lanxuewei.code_on_line.judger.CppSolution;
import com.lanxuewei.code_on_line.judger.JudgeStatus;
import com.lanxuewei.code_on_line.model.Page;
import com.lanxuewei.code_on_line.model.ProblemViewModel;
import com.lanxuewei.code_on_line.model.ReturnValue;
import com.lanxuewei.code_on_line.service.ProblemService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * create by lanxuewei in 2018/4/21 17:27
 * description: problem controller
 */
@RestController
@RequestMapping("/problem")
public class ProblemController {

    private static final Logger logger = LoggerFactory.getLogger(ProblemController.class);

    @Autowired
    private ProblemService problemService;

    /**
     * 接口测试
     * @param jsonStr
     * @return
     */
    @RequestMapping(value = "/test")
    @NoNeedLogin
    @ApiOperation("interface test")
    public ReturnValue interfaceTest(@RequestParam String jsonStr) {
        logger.info("jsonStr = {}", jsonStr);
        ProblemViewModel problemViewModel = JSON.parseObject(jsonStr, ProblemViewModel.class);
        logger.info("problemViewModel = {}", problemViewModel);
        return new ReturnValue(ReturnCodeAndMsgEnum.Success);
    }

    /**
     * 新增问题
     * @param problemViewModel
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    @NoNeedLogin
    @ApiOperation("add problem")
    public ReturnValue addProblem(@RequestBody ProblemViewModel problemViewModel) {
        logger.info("---> add problem");
        logger.info("problemViewModel = {}", problemViewModel);
        problemService.addProblem(problemViewModel);
        return new ReturnValue(ReturnCodeAndMsgEnum.Success);
    }

    /**
     * 分页查找problem
     * @param pageNum 页码
     * @param pageSize 每页大小
     * @param status 状态码(状态划分 0:正常 -1:已删除 null:所有)
     * @param resolve 状态码(状态划分 null:所有 0:已做 -1:未做 )
     * @param keyword 关键字查找
     * @param difficulty 难易度
     * @return 分页后数据集以及包含分页信息
     */
    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation("find problem by page")
    public ReturnValue<Page> findProblemByPage(@RequestParam(value = "pageNum", required = false, defaultValue = ServiceConstant.Default_PageNum) Integer pageNum,
                                               @RequestParam(value = "pageSize", required = false, defaultValue = ServiceConstant.Default_PageSize) Integer pageSize,
                                               @RequestParam(value = "status", required = false) Byte status,
                                               @RequestParam(value = "resolve", required = false) Byte resolve,
                                               @RequestParam(value = "keyword", required = false) String keyword,
                                               @RequestParam(value = "difficulty", required = false) Byte difficulty,
                                               HttpServletRequest request) {
        logger.info("---> find problem by page");
        Long userId = (Long) request.getAttribute(Constants.CURRENT_USER_ID);             // 获取用户id
        Page<ProblemDto> problemDtoPage = problemService.selectByPage(pageNum, pageSize,
                status, keyword, difficulty,userId, resolve);                             // 查询所有记录
        return new ReturnValue<>(ReturnCodeAndMsgEnum.Success, problemDtoPage);           // 数据返回
    }

    /**
     * 通过id查找问题 todo
     * @param id
     * @return problem
     */
    @RequestMapping(value = "/ID/{id}", method = RequestMethod.GET)
    @NoNeedLogin
    @ApiOperation("find problem by id")
    public ReturnValue<Problem> findProblemById(@PathVariable Long id) {
        logger.info("---> findProblemById");
        Assert.notNull(id, "id can not be empty");
        //return new ReturnValue<Problem>(ReturnCodeAndMsgEnum.Success, problemService.findProblemById(id));
        return null;
    }

    /**
     * 根据问题id删除该问题
     * @return
     */
    @RequestMapping(value = "/ID/{id}", method = RequestMethod.DELETE)
    @ApiOperation("delete problem by id")
    public ReturnValue deleteProblemById(@PathVariable(value = "id") Long id,
                                         HttpServletRequest request) {
        logger.info("---> deleteProblemById");
        Long userId = (Long) request.getAttribute(Constants.CURRENT_USER_ID);  // 获取用户id(用于判断是否为管理员,是否有该权限操作)
        boolean isManager = problemService.isManager(userId);  // 判断该用户是否为管理员
        if (isManager) {    // 管理员身份
            boolean isDelete = problemService.deleteById(id);
            if (isDelete) {
                return new ReturnValue(ReturnCodeAndMsgEnum.Success);       // 删除成功
            } else {
                return new ReturnValue(ReturnCodeAndMsgEnum.System_Error);  // 内部错误
            }
        } else {            // 无权限操作
            return new ReturnValue(ReturnCodeAndMsgEnum.Permission_Denied); // 无权限
        }
    }

    /**
     * 通过问题id查找对应问题详情,包含(其中包含题目详情,提交次数,成功次数,相关标签集,用户提交记录)
     * @param status 状态码
     * @return
     */
    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    @ApiOperation("find problem detail by id")
    public ReturnValue<ProblemDetailDto> findProblemDetailById(@PathVariable("id") Long problemId,
                                                               @RequestParam(value = "status", required = false) Byte status,
                                                               HttpServletRequest request) {
        logger.info("---> findProblemDetailById");
        Long userId = (Long) request.getAttribute(Constants.CURRENT_USER_ID);  // 获取用户id
        return new ReturnValue<>(ReturnCodeAndMsgEnum.Success
                ,problemService.findProblemForDetail(problemId, userId, status));
    }

    /**
     * 获取problem统计相关信息
     * @return
     */
    @RequestMapping(value = "/count", method = RequestMethod.GET)
    //@NoNeedLogin
    public ReturnValue countAllByDifficultyAndResolved(HttpServletRequest request) {
        logger.info("---> countAllByDifficultyAndResolved");
        Long userId = (Long) request.getAttribute(Constants.CURRENT_USER_ID);  // 获取用户id
        logger.info("userId = {}", userId);
        return new ReturnValue(ReturnCodeAndMsgEnum.Success, problemService.countProblemAndResolved(userId));
    }

    @RequestMapping(value = "/answer", method = RequestMethod.POST)
    @ApiOperation("run code")
    public ReturnValue runCode(@RequestParam(value = "problemId", required = true) Long problemId,
                               @RequestParam(value = "code", required = true) String code,
                               HttpServletRequest request) {
        logger.info("---> run code");
        Long userId = (Long) request.getAttribute(Constants.CURRENT_USER_ID);  // 获取用户id
        return problemService.runCodeAfter(userId, problemId, code);
    }

    /**
     * 更改题目状态
     * @param status
     * @return
     */
    @RequestMapping(value = "/updateStatus/ID/{id}", method = RequestMethod.GET)
    @ApiOperation("change problem status by id")
    public ReturnValue changeProblemStatusById(@PathVariable("id") Long id,
                                               @RequestParam("status") Byte status,
                                               HttpServletRequest request) {
        logger.info("---> changeProblemStatusById");
        Long userId = (Long) request.getAttribute(Constants.CURRENT_USER_ID);  // 获取用户id
        boolean isManager = problemService.isManager(userId);                  // 判断是否为管理员
        if (isManager) {  // 管理员
            problemService.changeStatusById(status, id);                       // 更新状态
            return new ReturnValue(ReturnCodeAndMsgEnum.Success);
        } else {          // 非法权限
            return new ReturnValue(ReturnCodeAndMsgEnum.Permission_Denied);
        }
    }

    // todo ....
    @RequestMapping(value = "")
    public ReturnValue getLastSubmit() {
        return null;
    }
}
