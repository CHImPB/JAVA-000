package com.zhanghongyu;

import com.zhanghongyu.annotation.KlassB;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.applet.AppletContext;

public class AnnotationBeanTest {

    @Test
    public void test(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        KlassB klassB1 = (KlassB) context.getBean("klassB1");
        System.out.println( klassB1.hello());
    }
}
