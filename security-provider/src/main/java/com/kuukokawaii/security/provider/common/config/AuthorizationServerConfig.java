package com.kuukokawaii.security.provider.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.sql.DataSource;
import java.util.Collections;

/**
 * @Author 喵粮都输光了
 * @Date 2020/8/19 20:49
 * @Description 认证服务配置
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    /**
     * Jwt 令牌值和 OAuth 身份验证信息转换器
     */
    private final JwtAccessTokenConverter jwtAccessTokenConverter;
    /**
     * 数据源
     */
    private final DataSource dataSource;
    /**
     * 令牌存储策略
     */
    private final TokenStore tokenStore;
    /**
     * 异常处理
     */
    private final SecurityWebResponseExceptionTranslator securityWebResponseExceptionTranslator;
    /**
     * 认证服务
     */
    private final UserDetailsService userDetailsService;
    /**
     * 密码模式
     */
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthorizationServerConfig(DataSource dataSource,
                                     TokenStore tokenStore,
                                     @Qualifier("customizeJwtAccessTokenConverter") JwtAccessTokenConverter jwtAccessTokenConverter,
                                     @Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService,
                                     SecurityWebResponseExceptionTranslator securityWebResponseExceptionTranslator,
                                     AuthenticationManager authenticationManager) {
        this.dataSource = dataSource;
        this.tokenStore = tokenStore;
        this.jwtAccessTokenConverter = jwtAccessTokenConverter;
        this.userDetailsService = userDetailsService;
        this.securityWebResponseExceptionTranslator = securityWebResponseExceptionTranslator;
        this.authenticationManager = authenticationManager;
    }

    /**
     * 存取客户端详情信息配置
     *
     * @param clients 客户端信息
     * @throws Exception 异常
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // 数据库存取客户端详情
        clients.withClientDetails(clientDetailsService());
    }

    /**
     * 设置令牌访问端点
     *
     * @param endpoints 端点信息
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints
            // 开启密码授权类型
            .authenticationManager(authenticationManager)
            // 设置令牌配置服务
            .tokenServices(authorizationServerTokenServices())
            // 使用 refresh_token 额外配置 userDetailsService
            .userDetailsService(userDetailsService)
            // 允许端点请求的方法
            .allowedTokenEndpointRequestMethods(HttpMethod.POST)
            // OAuth2 异常处理
            .exceptionTranslator(securityWebResponseExceptionTranslator);
    }

    /**
     * 配置令牌访问端点时的安全策略
     *
     * @param security 令牌安全
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security
            // 是否提供公有密钥端点：/oauth/token_key，用于 Jwt 验证签名
            .tokenKeyAccess("permitAll()")
            // 是否提供资源服务访问的令牌解析端点：/oauth/check_token isAuthenticated
            .checkTokenAccess("permitAll()")
            // 允许表单认证
            .allowFormAuthenticationForClients();
    }

    /**
     * 客户端详情信息存储值数据库
     *
     * @return ClientDetailsService
     */
    @Bean("customizeClientDetailsService")
    public ClientDetailsService clientDetailsService() {
        return new JdbcClientDetailsService(dataSource);
    }

    /**
     * 令牌配置服务
     *
     * @return AuthorizationServerTokenServices
     */
    @Bean
    public AuthorizationServerTokenServices authorizationServerTokenServices() {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        // 客户端详情信息服务
        defaultTokenServices.setClientDetailsService(clientDetailsService());
        // 是否支持刷新令牌
        defaultTokenServices.setSupportRefreshToken(true);
        // 令牌存储策略
        defaultTokenServices.setTokenStore(tokenStore);
        // 设置令牌有效期 2 小时(秒)
        defaultTokenServices.setAccessTokenValiditySeconds(7200);
        // 默认刷新令牌有效期 3 天(秒)
        defaultTokenServices.setRefreshTokenValiditySeconds(259200);
        // 令牌增强器，在原令牌基础上增加 Jwt 令牌功能
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Collections.singletonList(jwtAccessTokenConverter));
        defaultTokenServices.setTokenEnhancer(tokenEnhancerChain);
        return defaultTokenServices;
    }

}
