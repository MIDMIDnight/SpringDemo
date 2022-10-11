package com.ccc.Demo;

import com.ccc.Spring.ComponentScan;

//通过注解模拟Spring，Config类模拟Spring的xml文件
@ComponentScan("com.ccc.Demo.service")
public class AppConfig {
}
