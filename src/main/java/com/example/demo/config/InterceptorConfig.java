package com.example.demo.config;

import com.example.demo.interceptors.myInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author itsVol
 */
@Component
public class InterceptorConfig implements WebMvcConfigurer{
    //覆盖父类中添加拦截器的方法
    /**
     * 自己写的拦截器注册
     */
    @Autowired
    private  myInterceptor myInterceptors;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myInterceptors)
                .addPathPatterns("/file/**")
                .excludePathPatterns("/file/upload");

    }
}
