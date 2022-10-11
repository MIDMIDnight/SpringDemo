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
        path=path.replace(".","/");
        //拿到包扫描路径之后通过类加载器拿到资源URL再通过File创建资源的集合
        /*
            三种类加载器:
            Bootstrap:加载jre/lib下的核心jar包
            Ext：加载扩展jar包
            classpath：加载类路径下的所有的类
         */
        ClassLoader classLoader = config.getClassLoader();
        //通过classloader获取到类路径下的资源文件获得URL
        URL resource = classLoader.getResource(path);
        //通过URL获取到文件夹
        File file = new File(resource.getFile());
        //通过File获取到目录里面的所有对象，
        if (file.isDirectory()){
            File[] files = file.listFiles();
            for (File f:files
                 ) {
                String fileName = f.getAbsolutePath();
                String className=fileName.substring(fileName.indexOf("com"),fileName.indexOf(".class"));
                className=className.replace("\\",".");
                System.out.println(className);
                try {
                    Class<?> clazz = classLoader.loadClass(className);
                    if (clazz.isAnnotationPresent(Component.class)) {

                    }
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }

            }
        }


    }

    public Object getBean(String BeanName){

        return null;
    }


}
