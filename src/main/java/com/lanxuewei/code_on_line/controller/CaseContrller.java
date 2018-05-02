package com.lanxuewei.code_on_line.controller;

import com.github.pagehelper.PageHelper;
import com.lanxuewei.code_on_line.constant.ReturnCodeAndMsgEnum;
import com.lanxuewei.code_on_line.dao.entity.Case;
import com.lanxuewei.code_on_line.model.ReturnValue;
import com.lanxuewei.code_on_line.service.CaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * create by lanxuewei in 2018/4/22 08:40
 * description: case controller
 */
@RestController
@RequestMapping(value = "/case")
public class CaseContrller {

    private static final Logger logger = LoggerFactory.getLogger(CaseContrller.class);

    @Autowired
    private CaseService caseService;

    @RequestMapping(value = "/page")
    public ReturnValue pageHelpTest() {
        return new ReturnValue<>(ReturnCodeAndMsgEnum.Success, caseService.selectAll());
    }


}
