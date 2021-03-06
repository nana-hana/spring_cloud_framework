package com.kuukokawaii.security.provider.common.config;

import org.springframework.boot.autoconfigure.security.oauth2.authserver.AuthorizationServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * @Author 喵粮都输光了
 * @Date 2020/8/19 21:05
 * @Description 令牌配置
 */
@Configuration()
public class TokenConfig {

    /**
     * 令牌存储策略：Jwt 令牌存储方案
     *
     * @return InMemoryTokenStore
     */
    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    /**
     * 获取 security 认证服务配置文件信息
     *
     * @return AuthorizationServerProperties
     */
    @Bean
    public AuthorizationServerProperties authorizationServerProperties() {
        return new AuthorizationServerProperties();
    }

    @Bean("customizeJwtAccessTokenConverter")
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        // 设置对称密钥，资源服务器使用该密钥来验证
        jwtAccessTokenConverter.setSigningKey(authorizationServerProperties().getJwt().getKeyValue());
        return jwtAccessTokenConverter;
    }

}
