package com.lu.demo.interceptor.config;

import com.lu.demo.interceptor.MyInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * 控制权限
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Autowired
    private MyInterceptor interceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> path = new ArrayList<>();
        path.add("/js/*.js");
        path.add("/css/*.css");
        path.add("/img/*.png");
        path.add("/img/*.jpg");
        path.add("/layui/**");
        path.add("/dormitory/login");
        registry.addInterceptor(interceptor).addPathPatterns("/**")
                .excludePathPatterns(path);
    }
}
