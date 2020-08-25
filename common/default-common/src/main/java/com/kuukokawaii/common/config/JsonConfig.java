package com.kuukokawaii.common.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author 喵粮都输光了
 * @Date 2020/8/19 16:29
 * @Description 用 fastjson 替换项目自带的 jackson
 */
@Configuration
public class JsonConfig implements WebMvcConfigurer {

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        // 先删除 jackson 转换器，否则会优先匹配 jackson 转换器
        converters.removeIf(item -> item instanceof MappingJackson2HttpMessageConverter);

        // 添加 fastjson 转换器
        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();

        // 配置 fastjson
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(
            // 输出值为null的字段
            SerializerFeature.WriteMapNullValue,
            // 将Collection类型字段的字段空值输出为[]
            SerializerFeature.WriteNullListAsEmpty,
            // 将字符串类型字段的空值输出为空字符串
            SerializerFeature.WriteNullStringAsEmpty,
            // 将数值类型字段的空值输出为0
            SerializerFeature.WriteNullNumberAsZero,
            // 禁用循环引用
            SerializerFeature.DisableCircularReferenceDetect
        );
        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);

        // 添加支持的 MediaType 为 application/json
        List<MediaType> fastMediaTypes = new ArrayList<>();
        fastMediaTypes.add(MediaType.APPLICATION_JSON);
        fastJsonHttpMessageConverter.setSupportedMediaTypes(fastMediaTypes);

        converters.add(fastJsonHttpMessageConverter);
    }

}
