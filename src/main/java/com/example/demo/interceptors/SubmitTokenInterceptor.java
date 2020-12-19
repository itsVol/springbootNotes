package com.example.demo.interceptors;

import com.example.demo.cache.CacheUtil;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SubmitTokenInterceptor implements HandlerInterceptor {
    @lombok.SneakyThrows
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)){
            return true;
        }
        HandlerMethod handlerMethod =(HandlerMethod) handler;
        //判断类或者方法上是否有SubmitToken注解
        if (handlerMethod.getBeanType().isAnnotationPresent(SubmitToken.class)||handlerMethod.getMethod().isAnnotationPresent(SubmitToken.class)) {
            final String submitToken = request.getParameter("submitToken");
            if (StringUtils.isEmpty(submitToken)) {
                throw new CommonException("submitToken不能为空！");
            }
            if (!CacheUtil.containKey(submitToken)) {
                throw new CommonException("submitToken失效，请重新获取！");
            }
            Object value = CacheUtil.getValue(submitToken);
            if (!"false".equals(value)) {
                throw new CommonException("数据正在处理，请不要重复提交");
            }
            //验证通过之后，将submitToken对应的值设置为正在处理
            CacheUtil.setValue(submitToken, "true");
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //业务处理完毕之后，将submitToken从缓存中移除
        final String submitToken = request.getParameter("submitToken");
        if(!StringUtils.isEmpty(submitToken)){
            CacheUtil.removeKey(submitToken);
        }
    }

    /**
     * 将SubmitToken用于增、删、改的方法或者类上
     */
    //@SubmitToken
   // @RequestMapping(value = "/register")
   // public boolean register(@RequestBody UserDto userDto) throws Exception {
        //......
      //  return true;
    //}
}
