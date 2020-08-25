package com.kuukokawaii.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author 喵粮都输光了
 * @Date 2020/8/14 11:45
 * @Description swagger 配置
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * 配置 swagger 文档分组：喵粮都输光了
     *
     * @return Docket
     */
    @Bean
    public Docket groupYingJie() {
        return new Docket(DocumentationType.SWAGGER_2)
            .groupName("喵粮都输光了")
            .apiInfo(apiInfo())
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.kuukokawaii"))
            .build();
    }

    /**
     * 配置 swagger 的 docket 实例
     *
     * @return Docket
     */
    @Bean
    public Docket createRestApi(Environment envi) {
        // 设置 swagger 页面能显示的环境
        Profiles profiles = Profiles.of("dev");
        boolean flag = envi.acceptsProfiles(profiles);

        return new Docket(DocumentationType.SWAGGER_2)
            // swagger 信息
            .apiInfo(apiInfo())
            // 是否开启 swagger
            .enable(flag)
            .select()
            // 扫描的包位置
            .apis(RequestHandlerSelectors.basePackage("com.kuukokawaii"))
            // 扫描的 uri 路径
            .paths(PathSelectors.any())
            .build();
    }

    /**
     * 配置 swagger 信息
     *
     * @return ApiInfo
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("工程认证 API 文档")
            .version("1.0")
            .build();
    }

}
