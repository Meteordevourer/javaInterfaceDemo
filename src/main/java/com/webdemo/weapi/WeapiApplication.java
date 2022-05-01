package com.webdemo.weapi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement(proxyTargetClass = true)
@MapperScan("com.webdemo.weapi.action.dao")
public class WeapiApplication {
    public static void main(String[] args) {
        SpringApplication.run(WeapiApplication.class, args);
        System.out.println("开发平台-->启动完成");
    }
}
