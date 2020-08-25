package com.kuukokawaii.common.provider.config;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @Author 喵粮都输光了
 * @Date 2020/8/23 20:51
 * @Description Hystrix 监控配置
 */
@Component
public class HystrixMetricsConfig {

    @Bean
    public ServletRegistrationBean<HystrixMetricsStreamServlet> hystrixMetricsStreamServlet() {
        ServletRegistrationBean<HystrixMetricsStreamServlet> servletRegistrationBean =
            new ServletRegistrationBean<>(new HystrixMetricsStreamServlet());
        servletRegistrationBean.addUrlMappings("/actuator/hystrix.stream");
        servletRegistrationBean.setName("SecurityProvider");
        return servletRegistrationBean;
    }

}
