package com.lanxuewei.code_on_line.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.lanxuewei.code_on_line.authorization.annotation.NoNeedLogin;
import com.lanxuewei.code_on_line.constant.ReturnCodeAndMsgEnum;
import com.lanxuewei.code_on_line.constant.ServiceConstant;
import com.lanxuewei.code_on_line.dao.entity.Problem;
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
        return null;
    }

    /**
     * 分页查找problem
     * @param pageNum 页码
     * @param pageSize 每页大小
     * @return 分页后数据集以及包含分页信息
     */
    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation("find problem by page")
    public ReturnValue<Page> findProblemByPage(@RequestParam(defaultValue = ServiceConstant.Case.Default_PageNum) Integer pageNum,
                                               @RequestParam(defaultValue = ServiceConstant.Case.Default_PageSize) Integer pageSize) {
        logger.info("---> find problem by page");
        return new ReturnValue<>(ReturnCodeAndMsgEnum.Success, problemService.selectByPage(pageNum, pageSize));
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

}
