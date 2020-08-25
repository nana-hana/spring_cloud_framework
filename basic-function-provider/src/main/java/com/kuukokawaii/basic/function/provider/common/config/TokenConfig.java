package com.kuukokawaii.basic.function.provider.common.config;

import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
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
@Configuration("resourceTokenConfig")
public class TokenConfig {

    /**
     * 获取 security 资源服务配置文件信息
     */
    private final ResourceServerProperties resourceServerProperties;

    public TokenConfig(ResourceServerProperties resourceServerProperties) {
        this.resourceServerProperties = resourceServerProperties;
    }

    /**
     * 令牌存储策略：Jwt 令牌存储方案
     *
     * @return InMemoryTokenStore
     */
    @Bean("resourceTokenStore")
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean("customizeJwtAccessTokenConverter")
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        // 设置对称密钥，资源服务器使用该密钥来验证
        jwtAccessTokenConverter.setSigningKey(resourceServerProperties.getJwt().getKeyValue());
        return jwtAccessTokenConverter;
    }

}
