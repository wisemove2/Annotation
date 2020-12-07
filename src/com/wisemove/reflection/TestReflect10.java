package com.wisemove.reflection;

import java.lang.annotation.*;
import java.lang.reflect.Field;

public class TestReflect10 {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException {
        Class cla = Class.forName("com.wisemove.reflection.Student2");

        // 通过反射回去获得注解
        Annotation[] annotations = cla.getAnnotations();
        for(Annotation annotation : annotations){
            System.out.println(annotation);
        }

        // 获得注解value的值
        TableClass tableClass = (TableClass)cla.getAnnotation(TableClass.class);
        System.out.println(tableClass.value());

        // 获得域上的注解
        Field fid = cla.getDeclaredField("id");

        TableField tableField = fid.getAnnotation(TableField.class);
        System.out.println(tableField.columnName());
        System.out.println(tableField.type());
        System.out.println(tableField.length());
    }


}

@TableClass("db_student")
class Student2{
    @TableField(columnName = "db_id", type = "int", length = 10)
    private int id;
    @TableField(columnName = "db_name", type = "varchar", length = 10)
    private String name;
    @TableField(columnName = "db_age", type = "int", length = 10)
    private int age;

    public Student2(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Student2() {
    }

    @Override
    public String toString() {
        return "Student2{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

@Target(ElementType.TYPE) // 类名注解
@Retention(RetentionPolicy.RUNTIME) // 运行时的注解
@interface TableClass{
    String value();
}

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface TableField{
    String columnName();
    String type();
    int length();
}