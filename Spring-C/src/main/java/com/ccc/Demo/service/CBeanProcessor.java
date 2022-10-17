package com.ccc.Demo.service;

import com.ccc.Spring.BeanPostProcessor;
import com.ccc.Spring.Component;

@Component
public class CBeanProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeIinitialization(Object bean, String beanName) {
        if (beanName.equals("UserService")){
             ((UserService) bean).setName("hahahahahah");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterIinitialization(Object bean, String beanName) {
        System.out.println("初始化后");
        return bean;
    }
}
