package com;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class TestDirectMemory {
    private static final int _1M = 1024*1024;
    public  static  void  main(String args[]){
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        try {
            Unsafe unsafe = (Unsafe)unsafeField.get(null);
            int index = 0;
            while (index <10000)
            {
                unsafe.allocateMemory(_1M);
                Thread.sleep(1000);
                index++;
            }
        } catch (IllegalAccessException
                  e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
