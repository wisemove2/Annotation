package com.wisemove.reflection;

public class TestReflect05 {
    static{
        System.out.println("Main类被加载！");
    }

    public static void main(String[] args) throws ClassNotFoundException {
        // 主动
        // 1. new
        // Son son = new Son();

        // 2. 反射主动加载
        // Class.forName("com.wisemove.reflection.Son");

        // 被动
        // 1. 访问静态变量。子类去访问父类的静态变量，不回去加载子类
        // System.out.println(Son.b);

        // 2. 通过数组
        // Son[] sons = new Son[5];

        // 3. 调用子类的常量池
        System.out.println(Son.M);
    }
}


class Father{
    static int b = 2;

    static {
        System.out.println("Father类被加载");
    }
}

class Son extends Father{
    static int m = 100;
    static final int M = 1;

    static{
        System.out.println("Son类被加载！");
        m = 300;
    }

}