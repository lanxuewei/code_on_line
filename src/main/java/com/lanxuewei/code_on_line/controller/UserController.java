package com.lanxuewei.code_on_line.controller;

import com.lanxuewei.code_on_line.authorization.annotation.NoNeedLogin;
import com.lanxuewei.code_on_line.authorization.config.Constants;
import com.lanxuewei.code_on_line.constant.ReturnCodeAndMsgEnum;
import com.lanxuewei.code_on_line.constant.ServiceConstant;
import com.lanxuewei.code_on_line.dao.mapper.ProblemMapper;
import com.lanxuewei.code_on_line.model.ReturnValue;
import com.lanxuewei.code_on_line.model.UserViewModel;
import com.lanxuewei.code_on_line.service.ProblemService;
import com.lanxuewei.code_on_line.service.UserService;
import com.wordnik.swagger.annotations.Api;
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
     * 查找用户列表
     * @param pageNum
     * @param pageSize
     * @param status
     * @param request
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation("get user list")
    public ReturnValue getUserList(@RequestParam(value = "pageNum", required = false, defaultValue = ServiceConstant.Default_PageNum) Integer pageNum,
                                   @RequestParam(value = "pageSize", required = false, defaultValue = ServiceConstant.Default_PageSize) Integer pageSize,
                                   @RequestParam(value = "status", required = false) Byte status,
                                   @RequestParam(value = "keyword", required = false) String keyword,
                                   HttpServletRequest request) {
        logger.info("---> getUserList");
        Long userId = (Long) request.getAttribute(Constants.CURRENT_USER_ID);  // 获取用户id
        boolean isManager = userService.isManager(userId);                     // 判断是否为管理员
        if (isManager) {  // 管理员
            return new ReturnValue(ReturnCodeAndMsgEnum.Success,
                    userService.getUserListByPage(pageNum, pageSize,status, keyword));
        } else {          // 非法权限
            return new ReturnValue(ReturnCodeAndMsgEnum.Permission_Denied);
        }
    }

    /**
     * 禁用或启用用户账号
     * @param id 用户id
     * @param status 更改为该状态
     * @param request
     * @return
     */
    @RequestMapping(value = "/updateStatus/ID/{id}", method = RequestMethod.GET)
    @ApiOperation("disableUser")
    public ReturnValue disableOrEnableUser(@PathVariable(value = "id", required = true) Long id,
                                           @RequestParam(value = "status", required = true) Byte status,
                                           HttpServletRequest request) {
        Long userId = (Long) request.getAttribute(Constants.CURRENT_USER_ID);  // 获取用户id
        boolean isManager = userService.isManager(userId);
        if (isManager) {
            boolean isSuccess = userService.changeUserStatus(id, status);
            if (isSuccess) {
                return new ReturnValue(ReturnCodeAndMsgEnum.Success);
            }
            return new ReturnValue(ReturnCodeAndMsgEnum.System_Error);
        } else {
            return new ReturnValue(ReturnCodeAndMsgEnum.Permission_Denied);
        }
    }

    /**
     * 重置用户密码(将密码重置为123456)
     * @return
     */
    @RequestMapping(value = "/resetPassword/ID/{id}", method = RequestMethod.GET)
    @ApiOperation("reset user password")
    public ReturnValue resetUserPassword(@PathVariable(value = "id", required = true) Long id,
                                         HttpServletRequest request) {
        Long userId = (Long) request.getAttribute(Constants.CURRENT_USER_ID);  // 获取用户id
        boolean isManager = userService.isManager(userId);
        if (isManager) {
            boolean isSuccess = userService.resetUserPassword(id);      // 重置用户密码
            if (isSuccess) {
                return new ReturnValue(ReturnCodeAndMsgEnum.Success);   // 成功
            }
            return new ReturnValue(ReturnCodeAndMsgEnum.System_Error);  // 内部错误
        } else {
            return new ReturnValue(ReturnCodeAndMsgEnum.Permission_Denied);
        }
    }

    /**
     * 根据status统计用户数量
     * @param status
     * @param request
     * @return
     */
    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @ApiOperation("count all users by status")
    public ReturnValue countAllUsersByStatus(@RequestParam("status") Byte status,
                                             HttpServletRequest request) {
        Long userId = (Long) request.getAttribute(Constants.CURRENT_USER_ID);   // 获取用户id
        boolean isManager = userService.isManager(userId);
        if (isManager) {
            return new ReturnValue(ReturnCodeAndMsgEnum.Success,
                    userService.countAllUsersByStatus(status));                 // 成功
        } else {
            return new ReturnValue(ReturnCodeAndMsgEnum.Permission_Denied);     // 无权限
        }
    }

}
