package com.ccc.Demo;

import com.ccc.Spring.CApplicationContext;

public class Test {
    public static void main(String[] args) {
        CApplicationContext cApplicationContext = new CApplicationContext(AppConfig.class);
        Object userservice1 = cApplicationContext.getBean("userservice");
        Object userservice2 = cApplicationContext.getBean("userservice");
        Object userservice3 = cApplicationContext.getBean("userservice");
        System.out.println(userservice1);
        System.out.println(userservice2);;
        System.out.println(userservice3);

    }
}
