package com.lanxuewei.code_on_line.controller;

import com.lanxuewei.code_on_line.constant.ReturnCodeAndMsgEnum;
import com.lanxuewei.code_on_line.model.ReturnValue;
import com.lanxuewei.code_on_line.model.UserViewModel;
import com.lanxuewei.code_on_line.service.UserService;
import com.wordnik.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * create by lanxuewei in 2018/5/6 17:18
 * description: user controller
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    /**
     * 用户注册
     * @param vUser TODO 如果存在该用户名则不插入，否则报唯一键冲突,会导致注册相同账号出现界面跳转错误
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation("register")
    public ReturnValue register(UserViewModel vUser) {
        logger.info("vUser = {}", vUser);
        boolean isSuccess = userService.addUser(vUser);  //注册
        if (isSuccess) {
            return new ReturnValue(ReturnCodeAndMsgEnum.Success);          //注册成功
        } else {
            return new ReturnValue(ReturnCodeAndMsgEnum.Register_Failed);  //注册失败
        }
    }

}
