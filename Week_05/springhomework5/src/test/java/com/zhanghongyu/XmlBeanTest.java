package com.zhanghongyu;

import com.zhanghongyu.xml.KlassA;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class XmlBeanTest {
    @Test
    public void test(){
        // 初始化Spring容器，加载配置文件，并对bean进行实例化
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        // 设值方式输出结果
        KlassA klassA1 = (KlassA) context.getBean("klassA1");
        System.out.println(klassA1.toString());
        // 构造方式输出结果
        KlassA klassA2 = (KlassA) context.getBean("klassA2");
        System.out.println(klassA2.toString());

    }
}
