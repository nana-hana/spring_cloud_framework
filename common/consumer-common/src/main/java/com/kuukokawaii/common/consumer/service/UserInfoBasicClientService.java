package com.kuukokawaii.common.consumer.service;

import com.kuukokawaii.common.consumer.filter.FeignRequestInterceptor;
import com.kuukokawaii.common.entity.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author 喵粮都输光了
 * @Date 2020/8/24 19:39
 * @Description 基础功能模块-用户信息服务
 */
@Component
@FeignClient(name = "BASIC-FUNCTION-PROVIDER",
    fallbackFactory = UserInfoBasicClientServiceFallBackFactory.class,
    configuration = FeignRequestInterceptor.class)
public interface UserInfoBasicClientService {

    /**
     * 获取所有用户信息
     *
     * @return ResponseResult<String>
     */
    @GetMapping("/user-info")
    ResponseResult<String> getUsername();

}
