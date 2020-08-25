package com.kuukokawaii.basic.function.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author 喵粮都输光了
 * @Date 2020/8/23 20:18
 * @Description 基础功能服务消费
 */
@SpringBootApplication
@EnableFeignClients(basePackages = {"com.kuukokawaii"})
@EnableEurekaClient
@EnableHystrixDashboard
@ComponentScan("com.kuukokawaii")
public class BasicFunctionConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BasicFunctionConsumerApplication.class, args);
    }

}
