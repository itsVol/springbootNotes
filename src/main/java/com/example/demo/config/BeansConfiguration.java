package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Calendar;

/**
 * 一次性在工厂中批量创建多个对象，每个方法对应一个实例
 * @author itsVol
 */
@Configuration
public class BeansConfiguration {
    /**
     * //该注解相当于 spring.xml bean标签的作用   用来创建在这个工厂的一个实例。
     * @return
     */
    @Bean
    public Calendar getCalendar(){
        return Calendar.getInstance();
    }
}
