package com.wisemove.reflection;

import java.lang.reflect.*;

public class TestReflect08 {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
        // 通过Class获得对象
        Class cla = Class.forName("com.wisemove.reflection.User");
        User user = (User)cla.newInstance();
        System.out.println(user);

        // 通过构造器创建对象
        Constructor constructor = cla.getDeclaredConstructor(String.class, int.class, int.class);
        User wisemove = (User) constructor.newInstance("wisemove", 1, 24);
        System.out.println(wisemove);

        // 通过反射调用普通方法
        User user1 = (User)cla.newInstance();
        Method setName = cla.getDeclaredMethod("setName", String.class);
        setName.invoke(user1, "wise");
        System.out.println(user1.getName());
        System.out.println(user1);

        // 通过反射操作属性
        User user2 = (User)cla.newInstance();
        Field namefield = cla.getDeclaredField("name");

        namefield.setAccessible(true); // 设置可以访问私有属性，否则会跑出异常
        namefield.set(user2, "move");
        System.out.println(user2);
    }
}
