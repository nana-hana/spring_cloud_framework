package com.kuukokawaii.common.consumer.service;

import com.kuukokawaii.common.lang.ErrorCodeEnum;
import com.kuukokawaii.common.util.ResponseResultUtil;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @Author 喵粮都输光了
 * @Date 2020/8/24 19:40
 * @Description 基础功能模块-用户信息服务降级
 */
@Component
public class UserInfoBasicClientServiceFallBackFactory implements FallbackFactory<UserInfoBasicClientService> {

    @Override
    public UserInfoBasicClientService create(Throwable cause) {
        return () -> ResponseResultUtil.fail(ErrorCodeEnum.SERVICE_DEGRADATION);
    }

}
