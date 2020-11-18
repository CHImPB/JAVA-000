package com.zhanghongyu.springboot.starter;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
@EnableConfigurationProperties(HongyuProperties.class)
public class HongyuAutoConfigration {
    @Resource
    private HongyuProperties properties;

    public void printProperties()
    {
        System.out.println("TeacherName="+properties.getTeacherName()
                +" Week05Date="+properties.getWeekDate()
                +" StudentName="+properties.getStudentName());
    }
}
