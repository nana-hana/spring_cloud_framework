package com.kuukokawaii.security.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author 喵粮都输光了
 * @Date 2020/8/19 18:38
 * @Description 安全服务提供
 */
@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
@ComponentScan("com.kuukokawaii")
public class SecurityProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityProviderApplication.class, args);
    }

}
