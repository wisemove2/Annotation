package com.wisemove.reflection;

public class TestReflect02 {

    public static void main(String[] args) throws ClassNotFoundException {
        Person person = new Student();
        System.out.println(person.name);

        // 方式1. 通过对象获得
        Class c1 = person.getClass();
        System.out.println(c1.hashCode());

        // 方式2. 通过包名
        Class c2 = Class.forName("com.wisemove.reflection.Student");
        System.out.println(c2.hashCode());

        // 方式3. 通过类名.class
        Class c3 = Student.class;
        System.out.println(c3.hashCode());

        // 方式4. 基本内置类型的包装类，都用一个Type属性
        Class c4 = Integer.TYPE;

        // 获得父类的类型
        Class c5 = c1.getSuperclass();
        System.out.println(c5);
    }

}

class Person{
    public String name;

    public Person(String name) {
        this.name = name;
    }

    public Person() {
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                '}';
    }
}

class Student extends Person{
    public Student(){
        this.name = "学生";
    }
}

class Teacher extends Person{
    public Teacher(){
        this.name = "老师";
    }
}