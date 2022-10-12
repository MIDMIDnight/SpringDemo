package com.ccc.Demo.service;

import com.ccc.Spring.Autowired;
import com.ccc.Spring.Component;

@Component("OrderService")
public class OrderService {

    @Autowired
    private UserService UserService;


    public void showField(){
        System.out.println(UserService);
    }
}
