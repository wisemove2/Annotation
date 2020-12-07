package com.wisemove.annoation;

import java.lang.annotation.*;

@MyAnnotation
public class TestAnnotation02 {

    @MyAnnotation
    public static void method(){

    }

    public static void main(String[] args) {
        method();
    }
}

// 注解定义的方式，定义一个注解。表示这个注解可以定义在方法和类上
@Target(value={ElementType.METHOD, ElementType.TYPE})
// 表示注解在什么地方有效
@Retention(value = RetentionPolicy.RUNTIME)
// 是否将注解生成到javadoc中
@Documented
// 表示子类可以继承父类的注解
@Inherited
@interface MyAnnotation{

}
