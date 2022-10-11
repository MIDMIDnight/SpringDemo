package com.ccc.Spring;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
/*
注解的复习：
Rentention（保留），标识这个注解怎么保存，是只存在代码中还是编入class文件中，或者是在运行是能够通过注解和反射进行访问
     RetentionPolicy       SOURCE: Annotation信息只存在于编译器处理时期，编译完之后就被遗弃，   例如@Override注解只是在编译的时候存在，一旦编译结束就失去了作用；就是在java文件转换为class文件的时候就被遗弃
                            CLASS: 编译器将Annotation保存在.class文件中，只是存在但不起作用，在jvm加载类的时候被遗弃  就是在java文件转换为class文件的过程中保留但不起作用
                          RUNTIME: 编译器将Annotation存储于class文件中，jvm可以读取  就是在java文件转换为class文件的过程中保留并且能够被jvm读取

 */

public @interface ComponentScan {

    //扫描路径
    String value();
}
