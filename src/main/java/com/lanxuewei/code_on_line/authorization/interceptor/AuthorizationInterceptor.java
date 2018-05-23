package com.lanxuewei.code_on_line.authorization.interceptor;

import com.lanxuewei.code_on_line.authorization.annotation.NoNeedLogin;
import com.lanxuewei.code_on_line.authorization.config.Constants;
import com.lanxuewei.code_on_line.authorization.manager.TokenManager;
import com.lanxuewei.code_on_line.authorization.model.TokenModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * create by lanxuewei in 2018/4/18 00:07
 * description: 自定义拦截器，判断此次请求是否有权限
 *
 * @see NoNeedLogin
 */
@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(AuthorizationInterceptor.class);

    @Autowired
    private TokenManager tokenManager;

    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) {
        response.setHeader("Access-Control-Allow-Origin", "*");  //允许跨域访问
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
        response.setHeader("Access-Control-Allow-Methods", "DELETE, PUT");
        if (!(handler instanceof HandlerMethod)) {                      //如果不是映射方法直接通过
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        //如果验证token失败，并且方法注明了Authorization，返回401错误
        if (method.getAnnotation(NoNeedLogin.class) != null) {
            logger.info("no need login!");
            return true;
        }
        String authorization = request.getHeader(Constants.AUTHORIZATION);  //从请求头中得到token
        TokenModel model = tokenManager.getToken(authorization);            //验证token
        if (tokenManager.checkToken(model)) {  //token验证成功
            //如果token验证成功，将token对应的用户的id存入request，便于之后的注入
            request.setAttribute(Constants.CURRENT_USER_ID, model.getUserId());
            logger.info("token correct");
            return true;
        }
        logger.info("token error!");
        return false;
    }
}
