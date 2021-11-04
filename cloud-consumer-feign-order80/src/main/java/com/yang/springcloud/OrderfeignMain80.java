package com.yang.springcloud;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class OrderfeignMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderfeignMain80.class,args);
    }
}
