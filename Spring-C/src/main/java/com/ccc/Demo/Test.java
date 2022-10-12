package com.ccc.Demo;

import com.ccc.Demo.service.OrderService;
import com.ccc.Spring.CApplicationContext;

public class Test {
    public static void main(String[] args) {
        CApplicationContext cApplicationContext = new CApplicationContext(AppConfig.class);
        OrderService orderservice = (OrderService)cApplicationContext.getBean("OrderService");
        orderservice.showField();
    }
}
