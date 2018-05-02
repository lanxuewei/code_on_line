package com.lanxuewei.code_on_line.controller;

import com.lanxuewei.code_on_line.constant.ReturnCodeAndMsgEnum;
import com.lanxuewei.code_on_line.constant.ServiceConstant;
import com.lanxuewei.code_on_line.model.Page;
import com.lanxuewei.code_on_line.model.ReturnValue;
import com.lanxuewei.code_on_line.service.CaseService;
import com.wordnik.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * create by lanxuewei in 2018/4/22 08:40
 * description: case controller
 */
@RestController
@RequestMapping(value = "/case")
public class CaseController {

    private static final Logger logger = LoggerFactory.getLogger(CaseController.class);

    @Autowired
    private CaseService caseService;

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation("select case by page")
    public ReturnValue<Page> pageHelpTest(@RequestParam(defaultValue = ServiceConstant.Case.Default_PageNum) Integer pageNum,
                                          @RequestParam(defaultValue = ServiceConstant.Case.Default_PageSize) Integer pageSize) {
        return new ReturnValue<>(ReturnCodeAndMsgEnum.Success, caseService.selectByPage(pageNum, pageSize));
    }


}
