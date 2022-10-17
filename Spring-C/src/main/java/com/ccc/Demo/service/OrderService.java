package com.ccc.Demo.service;

import com.ccc.Spring.Autowired;
import com.ccc.Spring.Component;
import com.ccc.Spring.InitializiBean;

@Component("OrderService")
public class OrderService implements InitializiBean {


    @Autowired
    private UserService UserService;


    public void showField(){
        System.out.println(UserService);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("测试bean加载的初始化");
    }
}
