package com.kuukokawaii.basic.function.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author 喵粮都输光了
 * @Date 2020/8/23 20:18
 * @Description 基础功能服务提供
 */
@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
@ComponentScan("com.kuukokawaii")
public class BasicFunctionProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(BasicFunctionProviderApplication.class, args);
    }

}
