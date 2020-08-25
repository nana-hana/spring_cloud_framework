package com.kuukokawaii.basic.function.provider.common.config;

import com.kuukokawaii.common.constant.Constant;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * @Author 喵粮都输光了
 * @Date 2020/8/20 0:04
 * @Description web 安全配置
 */
@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceSecurityConfig extends ResourceServerConfigurerAdapter {

    private final TokenConfig tokenConfig;

    public ResourceSecurityConfig(TokenConfig tokenConfig) {
        this.tokenConfig = tokenConfig;
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(Constant.SECURITY_RESOURCE_SECURITY)
            .tokenStore(tokenConfig.tokenStore());
    }

}
