package com.zhanghongyu;

import com.zhanghongyu.school.School;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SchoolBeanTest {
    @Test
    public void test(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        School school = (School)context.getBean("school");
        school.ding();
    }
}
