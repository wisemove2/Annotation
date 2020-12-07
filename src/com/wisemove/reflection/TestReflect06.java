package com.wisemove.reflection;

public class TestReflect06 {
    public static void main(String[] args) throws ClassNotFoundException {
        // 获取系统类的加载器
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        System.out.println(classLoader);

        // 获取系统类加载器的父类加载器——扩展类加载器
        ClassLoader parent = classLoader.getParent();
        System.out.println(parent);

        // 获取扩展类加载器的父类加载器——根加载器(c/c++)
        ClassLoader parent1 = parent.getParent();
        System.out.println(parent1);

        // 测试当前类是谁加载的
        classLoader = Class.forName("com.wisemove.reflection.TestReflect06").getClassLoader();
        System.out.println(classLoader);

        // 测试Object是谁加载的
        classLoader = Class.forName("java.lang.Object").getClassLoader();
        System.out.println(classLoader);

        // 获取系统类加载器可以加载的路径
        System.out.println(System.getProperty("java.class.path"));
    }
}
