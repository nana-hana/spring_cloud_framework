package com.kuukokawaii.zuul;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author 喵粮都输光了
 * @Date 2020/8/19 16:07
 * @Description Zuul 网关
 */
@SpringBootApplication
@EnableZuulProxy
@ComponentScan("com.kuukokawaii")
public class ZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuulApplication.class, args);
    }

}
