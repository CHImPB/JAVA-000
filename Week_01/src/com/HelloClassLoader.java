package com;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class HelloClassLoader extends ClassLoader{
    public static  void main(String args[]) throws InstantiationException {
        try {
            Class c = new HelloClassLoader().findClass("Hello");
            Object obj = c.newInstance();
            Method method = c.getDeclaredMethod("hello",null);
            method.invoke(obj,null);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        //加在Hello.class
        byte[] data = null;
        File file = new File("D:\\workspace_java\\JAVA-000\\Week_01\\Hello.xlass");
        try (FileInputStream fis = new FileInputStream(file)) {
            int len;
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            while ((len = fis.read()) != -1){
                bos.write((byte)(255 - len));
            }
            data = bos.toByteArray();
            bos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(data == null){
            return  null;
        }
        return defineClass(name,data,0,data.length);
    }
}
