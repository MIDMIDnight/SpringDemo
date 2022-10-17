package com.ccc.Demo;

import com.ccc.Demo.service.OrderService;
import com.ccc.Demo.service.UserService;
import com.ccc.Spring.CApplicationContext;

public class Test {
    public static void main(String[] args) {
        CApplicationContext cApplicationContext = new CApplicationContext(AppConfig.class);
        UserService orderservice = (UserService)cApplicationContext.getBean("UserService");
        String beanName = orderservice.getBeanName();
        System.out.println(beanName);
        OrderService orderService = (OrderService)cApplicationContext.getBean("OrderService");

    }
}
