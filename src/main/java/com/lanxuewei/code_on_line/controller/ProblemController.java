package com.lanxuewei.code_on_line.controller;

import com.lanxuewei.code_on_line.constant.ReturnCodeAndMsgEnum;
import com.lanxuewei.code_on_line.dao.entity.Problem;
import com.lanxuewei.code_on_line.model.ReturnValue;
import com.lanxuewei.code_on_line.service.ProblemService;
import com.wordnik.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
     * find problem by id
     * @param id
     * @return problem
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiOperation("find problem by id")
    public ReturnValue findProblemById(@PathVariable Long id) {
        logger.info("---> findProblemById");
        Assert.notNull(id, "id can not be empty");
        Problem data = problemService.findProblemById(id);
        if (data != null) {  //查询不为空
            return new ReturnValue(ReturnCodeAndMsgEnum.Success, data);
        }                    //查询结果为空
        return new ReturnValue(ReturnCodeAndMsgEnum.Problem_Not_Exist, data);
    }


}
