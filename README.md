##### 什么是注解？

Annotation是从JDK5.0开始引入的新技术。



Annotation的作用：

1. 不是程序本身，可以对程序作出解释。（这一点和注释（comment）没什么区别）
2. 可以被其他程序（比如：编译器）读取



Annotation的格式：

注解式以 "@注解名"在代码中存在的，还可以添加一些参数值，例如：@SuppressWarnings(value="unchecked")



Annotation在哪里使用？

可以附加在package，class，method，field等上面。相当于给他们添加了额外的辅助信息，我们可以通过**反射机制编程**实现对这些元数据的访问。



##### 内置注解

@Override：定义在java.lang.Override中，此注解只适用于修辞方法，表示一个方法声明打算重写超类中的另一个方法的声明；

@Deprecated：定义在java.lang.Deprecated中，此注解可以用于修饰方法，属性，类，表示不鼓励程序员使用的这样的元素，通常是因为它很危险或者存在更好地选择。

@SuppressWarnings：定义在java.lang.SuppressWarnings中，用来抑制编译时的警告信息。

​	这个与前两个注释有所不同，你需要添加一个参数才能正确使用，这些参数都是已经定义好了的，我们选择性的使用就好了。

1. @SupressWarning("all")
2. @SupressWarning("unchecked")
3. @SupressWarnings(value={"unchecked", "deprecation"})
4. 等等



##### 元注解

元注解的作用就是注解其他注解，java定义了四个标准的meta-annotation类型，他们被用来提供对其他annotation类型作说明。

这些类型和他们所支持的类在java.lang.annotation包中可以找到。

@Target：用于描述注解的使用范围（即：被描述的注解可以用在什么地方）

@Retention：表示需要在什么级别保存该注解信息，用户描述注解的声明周期（SOURCE < CLASS < RUNTIME）

@Document：说明该注解将被包含在javadoc中

@Inherited：说明注解可以继承父类中的该注解

```java
package java.lang;

import java.lang.annotation.*;

/**
 * Indicates that a method declaration is intended to override a
 * method declaration in a supertype. If a method is annotated with
 * this annotation type compilers are required to generate an error
 * message unless at least one of the following conditions hold:
 *
 * <ul><li>
 * The method does override or implement a method declared in a
 * supertype.
 * </li><li>
 * The method has a signature that is override-equivalent to that of
 * any public method declared in {@linkplain Object}.
 * </li></ul>
 *
 * @author  Peter von der Ah&eacute;
 * @author  Joshua Bloch
 * @jls 8.4.8 Inheritance, Overriding, and Hiding
 * @jls 9.4.1 Inheritance and Overriding
 * @jls 9.6.4.4 @Override
 * @since 1.5
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.SOURCE)
public @interface Override {
}
```



可以看到上Override代码块中，**定义代码块的格式**和**元注解所在的位置**

定义代码块格式：

```java
@interface name{}
```

元注解所在位置：注解的上面；



##### TestAnnotation02.java

自己定义了一个注解，并且使用了元注解；



@Target的源码：

```java
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
public @interface Target {
    /**
     * Returns an array of the kinds of elements an annotation type
     * can be applied to.
     * @return an array of the kinds of elements an annotation type
     * can be applied to
     */
    ElementType[] value();
}
```

可以看到Target接受一个value的参数，alt + 点击ElementType：

```java
public enum ElementType {
    /** Class, interface (including annotation type), enum, or record
     * declaration */
    TYPE,

    /** Field declaration (includes enum constants) */
    FIELD,

    /** Method declaration */
    METHOD,

    /** Formal parameter declaration */
    PARAMETER,

    /** Constructor declaration */
    CONSTRUCTOR,

    /** Local variable declaration */
    LOCAL_VARIABLE,

    /** Annotation type declaration */
    ANNOTATION_TYPE,

    /** Package declaration */
    PACKAGE,

    /**
     * Type parameter declaration
     *
     * @since 1.8
     */
    TYPE_PARAMETER,

    /**
     * Use of a type
     *
     * @since 1.8
     */
    TYPE_USE,

    /**
     * Module declaration.
     *
     * @since 9
     */
    MODULE,

    /**
     * {@preview Associated with records, a preview feature of the Java language.
     *
     *           This constant is associated with <i>records</i>, a preview
     *           feature of the Java language. Programs can only use this
     *           constant when preview features are enabled. Preview features
     *           may be removed in a future release, or upgraded to permanent
     *           features of the Java language.}
     *
     * Record component
     *
     * @jls 8.10.3 Record Members
     * @jls 9.7.4 Where Annotations May Appear
     *
     * @since 14
     */
    @jdk.internal.PreviewFeature(feature=jdk.internal.PreviewFeature.Feature.RECORDS,
                                 essentialAPI=true)
    RECORD_COMPONENT;
}
```

可以看到他的这些参数接受的不同的值：

|       参数       |                 解释                 |
| :--------------: | :----------------------------------: |
|       TYPE       | 类，接口（包括注释），枚举或记录声明 |
|      FIELD       |       字段声明（包括枚举常量）       |
|      METHOD      |               方法声明               |
|    PARAMETER     |             形式参数声明             |
|   CONSTRUCTOR    |             构造函数声明             |
|  LOCAL_VARIABLE  |             局部变量声明             |
| ANNOTATION_TYPE  |             注释类型声明             |
|     PACKAGE      |                包声明                |
|  TYPE_PARAMETER  |             类型参数声明             |
|     TYPE_USE     |               使用类型               |
|      MODULE      |               模块类型               |
| RECORD_COMPONENT |               记录成分               |



@Retention源码：

```java
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
public @interface Retention {
    /**
     * Returns the retention policy.
     * @return the retention policy
     */
    RetentionPolicy value();
}
```

可以看到，它接受一个value参数，然后ctrl + 点击 RetentionPolicy：

```java
public enum RetentionPolicy {
    /**
     * 批注将被编译器丢弃.
     */
    SOURCE,

    /**
     * Annotations are to be recorded in the class file by the compiler
     * but need not be retained by the VM at run time.  This is the default
     * behavior.
     */
    CLASS,

    /**
     * Annotations are to be recorded in the class file by the compiler and
     * retained by the VM at run time, so they may be read reflectively.
     *
     * @see java.lang.reflect.AnnotatedElement
     */
    RUNTIME
}
```

|  参数   |                             含义                             |
| :-----: | :----------------------------------------------------------: |
| SOURCE  |                     批注将被编译器丢弃；                     |
|  CLASS  | 注释将由编译器记录在类文件中，但虚拟机不必在运行时保留它们。 这是默认行为。 |
| RUNTIME | 注释将由编译器记录在类文件中，并在运行时由虚拟机保留，因此可以通过反射方式读取它们。 |

可以根据SuppressWarnings注解只在源码时有效，而Deprecated在运行时还有效。可以通过这两个例子理解一下RetentionPolicy的参数。

```java
@Target({TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE, MODULE})
@Retention(RetentionPolicy.SOURCE)
public @interface SuppressWarnings {
    /**
     * The set of warnings that are to be suppressed by the compiler in the
     * annotated element.  Duplicate names are permitted.  The second and
     * successive occurrences of a name are ignored.  The presence of
     * unrecognized warning names is <i>not</i> an error: Compilers must
     * ignore any warning names they do not recognize.  They are, however,
     * free to emit a warning if an annotation contains an unrecognized
     * warning name.
     *
     * <p> The string {@code "unchecked"} is used to suppress
     * unchecked warnings. Compiler vendors should document the
     * additional warning names they support in conjunction with this
     * annotation type. They are encouraged to cooperate to ensure
     * that the same names work across multiple compilers.
     * @return the set of warnings to be suppressed
     */
    String[] value();
}
```

```java
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(value={CONSTRUCTOR, FIELD, LOCAL_VARIABLE, METHOD, PACKAGE, MODULE, PARAMETER, TYPE})
public @interface Deprecated {
    /**
     * Returns the version in which the annotated element became deprecated.
     * The version string is in the same format and namespace as the value of
     * the {@code @since} javadoc tag. The default value is the empty
     * string.
     *
     * @return the version string
     * @since 9
     */
    String since() default "";

    /**
     * Indicates whether the annotated element is subject to removal in a
     * future version. The default value is {@code false}.
     *
     * @return whether the element is subject to removal
     * @since 9
     */
    boolean forRemoval() default false;
}
```



@Document源码：

```java
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
public @interface Documented {
}
```

没有参数：直接的含义就是是否保存到javadoc中。



@Inherit源码：

```java
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
public @interface Inherited {
}
```

没有参数：表示子类可以继承父类的注解





##### 自定义注解：

使用@interface自定义注解时，自动继承了java.lang.annotation.Annotation接口

分析：

1. @interface用来声明一个注解，格式：~~public~~ @interface 注解名{定义内容}
2. 其中的每一个方法实际上是声明了一个配置参数
3. 方法的名称是参数的名称
4. 返回值类型就是参数的类型（返回值只能是基本类型，Class，String，enum）。
5. 可以通过default来声明参数的默认值
6. 如果只有一个参数成员，一般参数名为value
7. 注解元素必须要有值，我们定义注解元素时，经常使用空字符串，0作为默认值

TestAnnotation03.java





##### 反射 JAVA Reflection

1. Reflection（反射）是java被视为动态语言的关键，反射机制允许程序在执行期借助Reflection API取得任何类的内部信息，并能直接操作任意对象的内部属性及方法。Class c = Class.forName("java.lang.String")
2. 加载完类之后，在堆内存的方法去就产生了一个Class类型的对象（一个类只有一个Class对象），这个对象就包含了完整的类的结构信息（包括private的属性）。我们可以通过这个对象看到类的结构。这个对象就像一面镜子，透过这个镜子看到类的结构，所以，我们形象的称之为”反射“。

正常方式：引入需要的包名称——>通过new实例化——> 取得实例化对象

反射方式：实例化对象——>getClass()方法——>得到完整的“包类”的名称



##### java反射机制研究及应用

1. 在运行时判断任意一个对象所属的类
2. 在运行时构造任意一个类的对象
3. 在运行时判断任意一个所具有的成员变量和方法
4. 在运行时获取泛型信息
5. 在运行时调用任意一个对象的成员变量和方法
6. 在运行时处理注解
7. 生成动态代理



##### 反射的优点和缺点：

优点：可以实现动态创建对象和编译，体现出很大的灵活性

缺点：对性能有影响。使用反射基本上是一种解释操作，我们可以告诉java虚拟机，我们希望做什么并且满足我们的要求。这类操作总是慢于直接执行相同的操作。



反射相关API：

java.lang.Class：代表一个类

java.lang.reflect.Method：代表类的方法

java.lang.reflect.Field：代表类的成员变量

java.lang.reflect.Constructor：代表类的构造器



##### Class类

对象照镜子后可以得到的信息：某个类的属性、方法和构造器、某个类到底实现了哪些接口。对于每个类而言，JRE都为其保留一个不变的class类型的对象。一个Class对象包含了特定的某个结构的有关信息。

1. Class本身也是一个类
2. Class对象只能有系统建立对象
3. 一个加载的类在JVM中只会有一个Class实例
4. 一个Class对象对应的是一个加载到JVM中的一个class文件
5. 每个类的实例都会记得自己是由哪个Class实例所生成
6. 通过Class可以完整地得到一个类中的所有被加载的结构
7. Class类时Reflection的根源，针对任何你想动态加载、运行的类，唯有先获得相应的Class对象



Class类的常用方法

|                   方法名                   |                          功能说明                           |
| :----------------------------------------: | :---------------------------------------------------------: |
|      static ClassforName(String name)      |                 返回指定类名name的Class对象                 |
|            Object newInstance()            |          调用缺省构造函数，返回Class对象的一个实例          |
|                 getName()                  | 返回此Class对象所表示的实体（类，接口，数组类或void）的名称 |
|           Class getSuperClass()            |             返回当前Class对象的父类的Class对象              |
|          Class[] getinterfaces()           |                   获取当前Class对象的接口                   |
|        ClassLoader getClassLoader()        |                     返回该类的类加载器                      |
|      Constructor[] getConstructors()       |            返回一个包含某些Constructor对象的数组            |
| Method getMethod(String name, Class ... T) |       返回一个method对象，此对象的形参类型为paramType       |
|        Field[] getDeclaredFields()         |                   返回Field对象的一个数组                   |



##### 获取类的实例

1. 若已知具体的类，通过类的class属性获取，该方法最为安全可靠，程序性能最高。

```java
Class cla = Person.class;
```

2. 已知某个类的实例，调用该实例的getClass()方法获取Class对象

```java
Class cla = person.getClass();
```

3. 已知一个类的全名，且该类在类路径下，可通过Class类的静态方法forName()获取，可能跑出ClassNotFoundException异常

```java
Class cla = Class.forName("com.wisemove.reflection.User");
```

4. 内置基本数据类型可以直接用类名.Type
5. 还可以用ClassLoader

TestReflect02.java 演示了上面的几种方式



TestReflect03.java

哪些类型可以有Class对象？

1. class：外部类，成员（成员内部类，静态内部类），局部内部类，匿名内部类
2. interface：接口
3. [] ：数组
4. enum：枚举
5. annotation：注解@interface
6. primitive type：基本数据类型
7. void





##### 类的加载机制

jvm把class文件加载到内存，并对数据进行校验、解译和初始化，最终形成JVM可以直接使用的JAVA类型的过程包括如下步骤：

1. 加载
2. 链接（验证、准备、解析）
3. 初始化



加载：

1. 将class文件字节码内容加载到内存中，并将这些静态数据转换成方法区中的运行时数据结构，在堆中生成一个代表这个类的java.lang.Class对象，作为方法区类数据的访问入口。

2. class字节码——>类加载器——>内存——>外部可以通过Class对象操作类



链接：

将java类的二进制代码合并到JVM的运行状态之中的过程。

1. 验证：确保加载的类的信息符合JVM规范，没有安全方面的问题
2. 准备：正式为类变量（static变量）分配内存并设置变量初始值的阶段，这些内存都将在方法去中进行分配。
3. 解析：虚拟机常量池内的符合引用替换为直接引用的过程
4. 常量池（类名、字符串、方法名、代码）



初始化

1. 初始化阶段是执行类构造器<clinit>方法的过程。类构造器方法是由编译器自动收集类中所有类变量的赋值动作和静态语言（static）块中的语句合并产生的。
2. 当初始化一个类的时候，如果发现其父类还没有进行过初始化，则需要先执行父类初始化
3. 虚拟机会保证一个类的<clinit>方法在多线程中被正确加锁和同步
4. 当访问一个java类的静态区域时，只有真正声明这个域才会被初始化。



##### TestReflect04.java

可以看到首先是static int m = 10;执行，然后在是静态代码块执行，然后在是构造方法执行。

总结：

1. **静态变量**
2. **静态方法**
3. **常量池**
4. 类代码



什么时候会发生类的初始化？

##### 主动引用和被动引用

主动引用：（一定会发生了类的初始化）： TestReflect05.java

1. new一个类的对象
2. 调用类的静态成员（除了final常量）和静态方法
3. 使用java.lang.reflect包的方法对类进行反射调用
4. 当虚拟机启动，java hello，则一定会初始化hello类。就是先启动main方法所在的类
5. 当初始化一个类，如果其父类没有被初始化，则先会初始化父类



被动引用（不会发生类的初始化）： TestReflect05.java

1. 当访问一个静态域时，只有真正声明这个域的类才会被初始化
2. 通过子类引用父类的静态变量，不会导致子类初始化
3. 通过数组定义类引用，不会触发此类的初始化
4. 引用常量不会触发此类的初始化（**常量在编译阶段就存入调用类的常量池中了**）

**可以看到这些被动引用符合上面的总结，我访问静态变量、静态方法、常量池三种的时候，并没有用到具体的类，所以不需要类初始化。**





##### 类的加载器的作用

类加载的作用：将class文件的字节码内容加载到内存中，并将这些静态数据转换成方法区的运行时数据结构，然后在堆中生成一个代表这个类的java.lang.Class对象，作为方法区中类数据的访问入口。

类缓存：标准的javase类加载器，可以按要求查找类，但一旦某个类被加载到类加载器中，它将维持加载（缓存）一段时间。不过jvm垃圾回收机制可以回收这些Class对象。



引导类加载器(Bootstrap Classloader)：用C++编写的，是JVM自带的类加载器。负责java平台核心库，用来装载核心类库(bin/rt.jar)。该加载器无法直接获取。

扩展类加载器(Extension Classloader)：负责jre/lib/ext目录下的jar包指定目录下jar包装入工作库

系统类加载器(System Classloader)：负责java -classpath 或path下所指目录下的类与jar包装入工作，是最常用的加载器。



从下往上检查自己是否被装载；从上往下尝试加载；

TestReflect06.java







##### 获取运行时类的完整结构

通过反射获取运行时类的完整结构：

Field、Method、Constructor、Supreclass、Interface、Annotation

TestReflect07.java





##### 有了Class对象，能做什么？

创建类的对象：调用Class对象的newInstance()方法

1. 类必须有一个无参数的构造器
2. 类的构造器的访问权限需要足够

注意：这边也可以有有参数的构造器，只要你清楚构造器中参数类型和顺序即可进行实例化操作。

步骤：

1. 通过Class类的getDeclaredConstructor(Class ... parameterTypes)取得本类的指定形参类型的构造器
2. 向构造器的形参中传递一个对象数组进去，里面包含了构造器中所需的各个参数
3. 通过Constructor实例化对象



TestReflect08.java

调用指定的方法：

通过反射，调用类中的方法，通过Method类完成。

1. 通过Class类的getMethod(String name, Class ... parameterTypes) 方法取得一个Method对象，并设置此方法操作时所需要的参数类型
2. 之后使用Object invoke(Object obj, Object[] args)进行调用，并向方法中传递要设置的obj对象的参数信息。



setAccessible：

1. Method和Field、Constructor对象都有setAccessible()方法
2. setAccessible作用是启动和禁用访问安全检查的开关
3. 参数值为true则指示反射的对象在使用时应该允许通过java的访问检查
   1. 提高反射的效率。如果代码中必须用反射，而该语句需要频繁的被调用，那么设置为true
   2. 是的原本无法访问的私有成员也可以被访问
4. 参数值设置为fakse表示反射的对象应该实施java语言检查





TestReflect09.java

普通方式，反射方式，反射方式关闭检测之间的性能对比。





反射操作注解：

getAnnotations

getAnnotation

Object RelationShip Mapping ——> 对象关系映射

```java
class Student{
	int id;
	String name;
	int age;
}
```

| id   | name      | age  |
| ---- | --------- | ---- |
| 001  | wisemove  | 24   |
| 002  | slijfdlsj | 12   |

1. 类和表结构对应
2. 属性和字段对应
3. 对象和记录对应

利用注解和反射完成对应关系：

TestReflect10.java



#### TODO LIST:

1. 深入理解java虚拟机
2. 学习的时候一直说Spring中用的很多