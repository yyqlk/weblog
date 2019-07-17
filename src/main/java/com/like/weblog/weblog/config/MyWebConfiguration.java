package com.like.weblog.weblog.config;

import com.like.weblog.weblog.utils.LoginHandleInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;



@Configuration
public class MyWebConfiguration implements WebMvcConfigurer{

    @Autowired
    LoginHandleInterceptor loginHandleInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginHandleInterceptor).addPathPatterns("/**").
                excludePathPatterns("/callback");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
    }
}
