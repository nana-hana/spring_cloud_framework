package com.kuukokawaii.zuul.common.config;

import com.kuukokawaii.common.constant.Constant;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * @Author 喵粮都输光了
 * @Date 2020/8/20 13:34
 * @Description 统一资源服务配置，在网关进行校验，然后转发明文 token（网关不会自动转发 token）
 */
@Configuration
public class ResourceServerConfig {

    /**
     * Jwt 令牌存储方案
     */
    private final TokenStore tokenStore;

    public ResourceServerConfig(TokenStore tokenStore) {
        this.tokenStore = tokenStore;
    }

    /**
     * @Author 喵粮都输光了
     * @Date 2020/8/20 16:08
     * @Description Security 资源服务
     */
    @Configuration
    @EnableResourceServer
    public class SecurityResourceServerConfig extends ResourceServerConfigurerAdapter {

        /**
         * 资源管理配置
         *
         * @param resources 资源信息
         */
        @Override
        public void configure(ResourceServerSecurityConfigurer resources) {
            resources
                // 资源 id
                .resourceId(Constant.SECURITY_RESOURCE_SECURITY)
                // 令牌解析服务：使用 Jwt 令牌存储方案的校验方法
                .tokenStore(tokenStore)
                // 开启无状态
                .stateless(true);
        }

        /**
         * 安全访问配置
         *
         * @param http http 信息
         * @throws Exception 异常
         */
        @Override
        public void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
                .antMatchers("/security/**").permitAll();
        }

    }

    /**
     * @Author 喵粮都输光了
     * @Date 2020/8/24 2:03
     * @Description 基础功能资源服务
     */
    @Configuration
    @EnableResourceServer
    public class BasicFunctionResourceServerConfig extends ResourceServerConfigurerAdapter {

        /**
         * 资源管理配置
         *
         * @param resources 资源信息
         */
        @Override
        public void configure(ResourceServerSecurityConfigurer resources) {
            resources
                // 资源 id
                .resourceId(Constant.SECURITY_RESOURCE_SECURITY)
                // 令牌解析服务：使用 Jwt 令牌存储方案的校验方法
                .tokenStore(tokenStore);
        }

        /**
         * 安全访问配置
         *
         * @param http http 信息
         * @throws Exception 异常
         */
        @Override
        public void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
                .antMatchers("/basic/**").authenticated();
        }

    }

}
