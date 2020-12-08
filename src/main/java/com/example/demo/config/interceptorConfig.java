package com.example.demo.config;

import com.example.demo.interceptors.myInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Component
public class interceptorConfig implements WebMvcConfigurer {
    //覆盖父类中添加拦截器的方法
    @Autowired
    private  myInterceptor myInterceptors;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myInterceptors)
                .addPathPatterns("/file/**")
                .excludePathPatterns("/file/upload");

    }
}
