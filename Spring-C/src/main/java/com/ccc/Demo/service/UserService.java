package com.ccc.Demo.service;


import com.ccc.Spring.BeanNameAware;
import com.ccc.Spring.Component;
import com.ccc.Spring.Scope;

@Component("UserService")
public class UserService implements BeanNameAware {
    private String beanName;
    @Override
    public void setBeanName(String name) {
        beanName=name;
    }

    public String getBeanName() {
        return beanName;
    }
}
