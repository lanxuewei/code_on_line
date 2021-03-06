package com.lanxuewei.code_on_line.controller;

import com.lanxuewei.code_on_line.authorization.annotation.NoNeedLogin;
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
@RequestMapping("/token")
public class TokenController {

    private static Logger logger = LoggerFactory.getLogger(TokenController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private TokenManager tokenManager;

    /**
     * 登陆
     * @param userName 用户名
     * @param password 密码
     * @param status 身份码
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    @NoNeedLogin
    @ApiOperation("login")
    public ReturnValue<TokenModel> login(@RequestParam("username") String userName,
                                         @RequestParam("password") String password,
                                         @RequestParam("status") Byte status) {
        logger.info("---> login");
        Assert.notNull(userName, "username can not be empty");
        Assert.notNull(password, "password can not be empty");
        Assert.notNull(status, "status can not be empty");

        User user = userService.findByUserName(userName, status);
        if (user == null ||                                         //未注册
                !user.getPassword().equals(password)) {             //密码错误
            return new ReturnValue<>(ReturnCodeAndMsgEnum.Username_Or_Password_Error);
        }
        TokenModel model = tokenManager.createToken(user.getId(), user.getUserName(), status);  //生成一个token，保持用户登陆状态

        return new ReturnValue<>(ReturnCodeAndMsgEnum.Success, model);
    }

    /**
     * 登出
     * @param user
     * @return
     */
    @RequestMapping(method = RequestMethod.DELETE)
    //@NoNeedLogin
    @ApiOperation(value = "logout")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "authorization", value = "authorization", required = true, dataType = "string", paramType = "header")
    )
    public ReturnValue logout(@CurrentUser User user) {
        logger.info("---> logout");
        tokenManager.deleteToken(user.getId());
        return new ReturnValue(ReturnCodeAndMsgEnum.Success);
    }

}
