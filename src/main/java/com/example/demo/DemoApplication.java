package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        System.out.println("正在启动");
        SpringApplication.run(DemoApplication.class, args);
        System.out.println("启动成功");
    }

}
