package com.wisemove.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class TestReflect07 {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException {
        Class c1 = Class.forName("com.wisemove.reflection.User");


        // 获得类的名字
        System.out.println(c1.getName()); // 包名 + 类名
        System.out.println(c1.getSimpleName()); // 类名

        // 获得类属性
        Field[] fields = c1.getFields(); // 只能找到public属性，所以这个数组为空
        for(Field field : fields){
            System.out.println(field);
        }

        fields = c1.getDeclaredFields(); // 找到全部属性，包括private属性
        for(Field field : fields){
            System.out.println(field);
        }


        Field name = null;
        try {
            name = c1.getField("name");
            System.out.println(name);
        } catch (NoSuchFieldException e) {
            System.out.println("请检查该属性是否为私有，或者是否拼写错误！");
        }


        Field name1 = null;
        try {
            name1 = c1.getDeclaredField("name");
            System.out.println(name1);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            System.out.println("请检查是否拼写错误！");
        }

        // 获得类的方法
        // 获得公共的，和继承的
        Method[] methods = c1.getMethods();
        for(Method method : methods){
            System.out.println(method);
        }

        // 获得本类的所有方法，但是没有继承的。
        methods = c1.getDeclaredMethods();
        for(Method method : methods){
            System.out.println(method);
        }

        // 获取指定方法
        Method getName = c1.getMethod("getName", null);
        Method setName = c1.getMethod("setName", String.class);
        System.out.println(getName);
        System.out.println(setName);

        // 获取指定的构造器
        Constructor[] constructors = c1.getConstructors();
        for(Constructor constructor : constructors){
            System.out.println(constructor);
        }

        constructors = c1.getDeclaredConstructors();
        for(Constructor constructor : constructors){
            System.out.println(constructor);
        }

        // 获得指定构造器
        Constructor constructor = c1.getDeclaredConstructor(String.class, int.class, int.class);
        System.out.println(constructor);
    }
}
