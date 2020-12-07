package com.wisemove.annoation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class TestAnnotation03 {
    @MyAnnotation2(name="", schools = "CCNU")
    public void test(){

    }

    @MyAnnotation3("")
    public void test01(){

    }
}

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotation2{
    // 注解的参数：参数名 + 类型
    // 上面注解中必须要给name赋值，除非加上default "" 就不用赋值了
    String name() default "";
    int id() default -1; // 如果默认值为-1，表示不存在

    String[] schools() default {"CCNU", "WHU"};
}

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotation3{
    String value(); // value可以省略，但是上面的name只有一个的时候不能省略
}