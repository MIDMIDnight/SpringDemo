package com.ccc.Demo.service;


import com.ccc.Spring.BeanNameAware;
import com.ccc.Spring.Component;
import com.ccc.Spring.Scope;

@Component("UserService")
public class UserService implements BeanNameAware {
    private String beanName;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setBeanName(String name) {
        beanName=name;
    }

    public String getBeanName() {
        return beanName;
    }
}
