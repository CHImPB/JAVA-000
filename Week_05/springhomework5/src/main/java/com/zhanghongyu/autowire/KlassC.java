package com.zhanghongyu.autowire;

import org.springframework.beans.factory.annotation.Autowired;

public class KlassC {
//    直接用Autowired也可以实现AttributeA自动装载
//    @Autowired
    public AttributeA attributeA;

    public AttributeA getAttributeA() {
        return attributeA;
    }

    public void setAttributeA(AttributeA attributeA) {
        this.attributeA = attributeA;
    }

    //根据constructor自总装载测试用
    public AttributeB attributeB1;

    public String hello()
    {
        return  "hello zhanghongyu this is autowire class";
    }

    public KlassC(){
        super();
    }
    public KlassC(AttributeB attributeB){
        super();
        this.attributeB1 = attributeB;
    }
}
