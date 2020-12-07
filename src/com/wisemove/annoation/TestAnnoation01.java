package com.wisemove.annoation;

import java.util.ArrayList;
import java.util.List;

public class TestAnnoation01 extends Object{

    // @Override 重写注解
    @Override
    public String toString() {
        return super.toString();
    }

    @Deprecated
    public static void test01(){

    }

    @SuppressWarnings("all")
    public static void test02(){
        List list = new ArrayList();
    }

    public static void main(String[] args) {
        test01();
        test02();
    }
}
