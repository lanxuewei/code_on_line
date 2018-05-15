package com.lanxuewei.code_on_line.controller;

import com.alibaba.fastjson.JSON;
import com.lanxuewei.code_on_line.authorization.annotation.NoNeedLogin;
import com.lanxuewei.code_on_line.authorization.config.Constants;
import com.lanxuewei.code_on_line.constant.ReturnCodeAndMsgEnum;
import com.lanxuewei.code_on_line.constant.ServiceConstant;
import com.lanxuewei.code_on_line.dao.entity.Problem;
import com.lanxuewei.code_on_line.model.Page;
import com.lanxuewei.code_on_line.model.ProblemViewModel;
import com.lanxuewei.code_on_line.model.ReturnValue;
import com.lanxuewei.code_on_line.service.ProblemService;
import com.wordnik.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
    public ReturnValue<Page> findProblemByPage(@RequestParam(value = "pageNum", required = false, defaultValue = ServiceConstant.Case.Default_PageNum) Integer pageNum,
                                               @RequestParam(value = "pageSize", required = false, defaultValue = ServiceConstant.Case.Default_PageSize) Integer pageSize,
                                               @RequestParam(value = "status", required = false) Byte status,
                                               @RequestParam(value = "resolve", required = false) Byte resolve,
                                               @RequestParam(value = "keyword", required = false) String keyword,
                                               @RequestParam(value = "difficulty", required = false) Byte difficulty,
                                               HttpServletRequest request) {
        logger.info("---> find problem by page");
        Long userId = (Long) request.getAttribute(Constants.CURRENT_USER_ID);  // 获取用户id
        return new ReturnValue<>(ReturnCodeAndMsgEnum.Success,
                problemService.selectByPage(pageNum, pageSize, status, keyword, difficulty,userId, resolve));
    }

    /**
     * 通过id查找问题
     * @param id
     * @return problem
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiOperation("find problem by id")
    public ReturnValue<Problem> findProblemById(@PathVariable Long id) {
        logger.info("---> findProblemById");
        Assert.notNull(id, "id can not be empty");
        return new ReturnValue<Problem>(ReturnCodeAndMsgEnum.Success, problemService.findProblemById(id));
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

}
