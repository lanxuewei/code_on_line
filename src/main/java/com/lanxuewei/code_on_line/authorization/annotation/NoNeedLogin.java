package com.lanxuewei.code_on_line.authorization.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * create by lanxuewei in 2018/4/18 00:02
 * description: 在Controller的方法中使用此注解，表示不需要认证
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NoNeedLogin {
}
