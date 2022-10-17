package com.ccc.Spring;

public interface BeanPostProcessor {
    Object postProcessBeforeIinitialization(Object bean,String beanName);

    Object postProcessAfterIinitialization(Object bean,String beanName);
}