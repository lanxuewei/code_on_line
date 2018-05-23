package com.lanxuewei.code_on_line.controller;

import com.lanxuewei.code_on_line.authorization.annotation.NoNeedLogin;
import com.lanxuewei.code_on_line.authorization.config.Constants;
import com.lanxuewei.code_on_line.constant.ReturnCodeAndMsgEnum;
import com.lanxuewei.code_on_line.dao.mapper.ProblemMapper;
import com.lanxuewei.code_on_line.model.ReturnValue;
import com.lanxuewei.code_on_line.model.UserViewModel;
import com.lanxuewei.code_on_line.service.ProblemService;
import com.lanxuewei.code_on_line.service.UserService;
import com.wordnik.swagger.annotations.ApiOperation;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @Autowired
    private ProblemService problemService;

    /**
     * 用户注册
     * @param vUser TODO 如果存在该用户名则不插入，否则报唯一键冲突,会导致注册相同账号出现界面跳转错误
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    @NoNeedLogin
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

    /**
     * 禁用或启用用户账号 todo 待开发 映射方法 判断用户是否为管理员方法
     * @return
     */
    /*@RequestMapping(value = "/ID/{id}")
    @ApiOperation("disableUser")
    public ReturnValue disableOrEnableUser(@PathVariable(value = "id") Long id,
                                   @RequestParam(value = "status") Byte status,
                                   HttpServletRequest request) {
        Long userId = (Long) request.getAttribute(Constants.CURRENT_USER_ID);  // 获取用户id
        boolean isManager = problemService.isManager(userId);
        if (isManager) {
            userService.changeUserStatus(id, status);
            return new ReturnValue(ReturnCodeAndMsgEnum.Success);
        } else {
            return new ReturnValue(ReturnCodeAndMsgEnum.Permission_Denied);
        }
    }*/

    /**
     * 重置用户密码 todo 待开发 映射方法 判断用户是否为管理员方法
     * @return
     */
    /*@RequestMapping(value = "/ID/{id}", method = RequestMethod.PUT)
    @ApiOperation("reset user password")
    public ReturnValue resetUserPassword(HttpServletRequest request) {
        Long userId = (Long) request.getAttribute(Constants.CURRENT_USER_ID);  // 获取用户id
        boolean isManager = problemService.isManager(userId);
        if (isManager) {
            userService.resetUserPassword(id);
            return new ReturnValue(ReturnCodeAndMsgEnum.Success);
        } else {
            return new ReturnValue(ReturnCodeAndMsgEnum.Permission_Denied);
        }
    }*/



}
