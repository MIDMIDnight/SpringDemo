package com.ccc.Spring;


import java.io.File;
import java.lang.annotation.Annotation;
import java.net.URL;

//模拟Spring容器
public class CApplicationContext {
    private Class Config;

    public CApplicationContext(Class config) {
        Config = config;

        //拿到配置类然后进行解析，同时通过@ComponentScan注解拿到包的路径
        ComponentScan ComponentScanAnnotation = (ComponentScan) config.getDeclaredAnnotation(ComponentScan.class);
        //拿到包扫描的路径
        String path = ComponentScanAnnotation.value();
        System.out.println(path);
        //拿到包扫描路径之后通过类加载器拿到资源URL再通过File创建资源的集合
        /*
            三种类加载器:
            Bootstrap
            Ext
            classpath
         */
        ClassLoader classLoader = config.getClassLoader();

        URL resource = classLoader.getResource("com/ccc/Demo/service");
        //File file = new File();
        System.out.println(resource);


    }

    public Object getBean(String BeanName){

        return null;
    }


}
