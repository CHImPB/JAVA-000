package com.zhanghongyu;

import com.zhanghongyu.autowire.AttributeA;
import com.zhanghongyu.autowire.KlassC;
import com.zhanghongyu.xml.KlassA;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AutowireBeanTest {
    @Test
    public void test(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        KlassC klassC1 = (KlassC) context.getBean("klassC1");

        AttributeA attributeA = (AttributeA) context.getBean("attributeA");
        System.out.println("验证是否已装配到容器attributeA.hello()="+attributeA.hello());

        System.out.println("klassC1.hello()="+klassC1.hello());
        if(klassC1.attributeA != null){
            System.out.println("klassC1.attributeA.hello()="+klassC1.attributeA.hello());
        }

        if(klassC1.attributeB1 != null){
            System.out.println("==klassC1.attributeB1.hello()="+klassC1.attributeB1.hello());
        }
    }
}
