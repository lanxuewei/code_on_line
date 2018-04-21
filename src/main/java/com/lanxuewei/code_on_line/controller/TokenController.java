package com.lanxuewei.code_on_line.controller;

import com.lanxuewei.code_on_line.authorization.annotation.Authorization;
import com.lanxuewei.code_on_line.authorization.annotation.CurrentUser;
import com.lanxuewei.code_on_line.authorization.manager.TokenManager;
import com.lanxuewei.code_on_line.authorization.model.TokenModel;
import com.lanxuewei.code_on_line.constant.ReturnCodeAndMsgEnum;
import com.lanxuewei.code_on_line.dao.entity.User;
import com.lanxuewei.code_on_line.model.ReturnValue;
import com.lanxuewei.code_on_line.service.UserService;
import com.wordnik.swagger.annotations.ApiImplicitParam;
import com.wordnik.swagger.annotations.ApiImplicitParams;
import com.wordnik.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * create by lanxuewei in 2018/4/15 08:01
 * description: 获取和删除 token 的请求地址，在 Restful 设计中其实就是对应着登陆和退出登陆的资源映射
 */
@RestController
@RequestMapping("/tokens")
public class TokenController {

    private static Logger logger = LoggerFactory.getLogger(TokenController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private TokenManager tokenManager;

    //test
    @RequestMapping(value = "/test")
    public void test() {
        logger.info("---> test");
    }

    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation("登陆")
    public ReturnValue login(@RequestParam String userName, @RequestParam String password) {
        logger.info("---> login");
        Assert.notNull(userName, "username can not be empty");
        Assert.notNull(password, "password can not be empty");

        User user = userService.findByUserName(userName);
        if (user == null ||                                         //未注册
                !user.getPassword().equals(password)) {             //密码错误
            return new ReturnValue(ReturnCodeAndMsgEnum.Username_Or_Password_Error);
        }
        TokenModel model = tokenManager.createToken(user.getId());  //生成一个token，保持用户登陆状态
        return new ReturnValue(ReturnCodeAndMsgEnum.Success, model);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @Authorization
    @ApiOperation(value = "退出登陆")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "authorization", value = "authorization", required = true, dataType = "string", paramType = "header")
    )
    public ReturnValue logout(@CurrentUser User user) {
        logger.info("---> logout");
        tokenManager.deleteToken(user.getId());
        return new ReturnValue(ReturnCodeAndMsgEnum.Success);
    }

}
