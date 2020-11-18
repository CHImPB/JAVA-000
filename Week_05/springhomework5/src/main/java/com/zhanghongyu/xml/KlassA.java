package com.zhanghongyu.xml;

/**
 * @author zh.hongyu
 */
public class KlassA {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public  KlassA(){
        super();
    }
    public  KlassA(String name,int age) {
        super();
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString()
    {
        return "KlassA[name=" + name + ",age=" + age + "]";
    }
}
