package com.lanxuewei.code_on_line.authorization.annotation;

/**
 * create by lanxuewei in 2018/4/18 00:30
 * description: 在controller的方法参数中使用此注解，该方法在映射时会注入当前登陆的User对象
 * @see com.lanxuewei.code_on_line.authorization.resolvers.CurrentUserMethodArgumentResolver
 */
public @interface CurrentUser {
}
