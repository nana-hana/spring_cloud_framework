package com.kuukokawaii.zuul.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

/**
 * @Author 喵粮都输光了
 * @Date 2020/8/20 0:04
 * @Description web 安全配置
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 安全拦截机制
     *
     * @param http http 信息
     * @throws Exception 异常
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            // 跨域保护关闭并开启跨域共享
            .csrf().disable()
            // 此处请求全部放行，在统一资源服务配置中的具体资源处进行拦截验证
            .authorizeRequests()
            .antMatchers("/**").permitAll()
            // 关闭 session
            .and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

}
