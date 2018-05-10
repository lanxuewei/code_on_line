package com.lanxuewei.code_on_line.authorization.config;

import com.lanxuewei.code_on_line.authorization.interceptor.AuthorizationInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * create by lanxuewei in 2018/5/9 21:55
 * description:
 */
@Configuration
public class EnvConfiguration extends WebMvcConfigurationSupport{

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getApplicationContext().getBean(AuthorizationInterceptor.class));
    }
}
