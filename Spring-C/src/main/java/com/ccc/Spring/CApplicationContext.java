package com.ccc.Spring;


import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

//模拟Spring容器
public class CApplicationContext {
    private Class Config;
    private ConcurrentHashMap<String,Object> singletonObjectPool=new ConcurrentHashMap<>();
    private ConcurrentHashMap<String,BeanDefinition> beanDefinitionConcurrentHashMap=new ConcurrentHashMap<>();

    public CApplicationContext(Class config) {
        Config = config;
        /*
            解析配置类
            ComponentScan注解-->扫描路径-->扫描-->拿到标有@Component注解的类-->BeanDefinition-->beanDefinitionConcurrentHashMap
         */
        scan(config);
        for (Map.Entry<String,BeanDefinition> entry: beanDefinitionConcurrentHashMap.entrySet()
             ) {
            String beanName = entry.getKey();
            BeanDefinition beanDefinition = entry.getValue();
            if (beanDefinition.getScope().equals("singleton")){
                Object bean=createBean(beanDefinition);
                singletonObjectPool.put(beanName,bean);
            }
        }
    }

    private Object createBean(BeanDefinition beanDefinition) {
        Class aClass = beanDefinition.getaClass();
        try {
            Object bean = aClass.newInstance();
            //实现依赖注入
            for (Field declaredField:aClass.getDeclaredFields()
                 ) {
                if (declaredField.isAnnotationPresent(Autowired.class)){
                    Object field = getBean(declaredField.getName());
                    declaredField.setAccessible(true);
                    declaredField.set(bean,field);
                }
                
            }
            return bean;
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private void scan(Class config) {
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
                try {
                    Class<?> clazz = classLoader.loadClass(className);
                    //创建一个BeanDefinition对象用来存放当前bean类的各种属性
                    BeanDefinition beanDefinition = new BeanDefinition();
                    beanDefinition.setaClass(clazz);
                    //首先判断当前类是否有Component注解,然后判断当前Bean是否有Scope注解，判断一下作用域的范围，是否单例
                    if (clazz.isAnnotationPresent(Component.class)) {

                        Component componentAnnotation = clazz.getDeclaredAnnotation(Component.class);
                        String beanName = componentAnnotation.value();
                        if (clazz.isAnnotationPresent(Scope.class)) {
                            Scope scopeAnnotation = clazz.getDeclaredAnnotation(Scope.class);
                            beanDefinition.setScope(scopeAnnotation.value());

                        }else {
                            beanDefinition.setScope("singleton");
                        }
                        beanDefinitionConcurrentHashMap.put(beanName,beanDefinition);
                    }
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }

            }
        }
    }

    public Object getBean(String BeanName){
        //判断是否存在 BeanName 的 Bean？
        if (beanDefinitionConcurrentHashMap.containsKey(BeanName)){
            //存在当前Bean，拿出来并且拿到scope
            BeanDefinition beanDefinition = beanDefinitionConcurrentHashMap.get(BeanName);
            //如果scope为singleton，则直接放进单例对象池里面
            if (beanDefinition.getScope().equals("singleton")){
                beanDefinition.getaClass();
                Object Bean = singletonObjectPool.get(BeanName);
                return Bean;
            }
            else {
                Object bean = createBean(beanDefinition);
                return bean;
            }
        }else {
            throw new RuntimeException("没有找到名字为"+BeanName+"的类");
        }
    }


}
