package com.kuukokawaii.basic.function.provider.controller;

import com.kuukokawaii.common.entity.ResponseResult;
import com.kuukokawaii.common.lang.ErrorCodeEnum;
import com.kuukokawaii.common.util.ResponseResultUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @Author 喵粮都输光了
 * @Date 2020/8/24 1:10
 * @Description 用户信息控制层
 */
@RestController
public class UserInfoController {

    /**
     * 获取所有用户信息
     *
     * @return ResponseResult<String>
     */
    @HystrixCommand(fallbackMethod = "getUsernameHystrix")
    @PreAuthorize("hasAnyAuthority('select_user')")
    @GetMapping("/user-info")
    public ResponseResult<String> getUsername(Principal principal) {
        return ResponseResultUtil.success(principal.getName());
    }

    /**
     * getUsername 熔断方法，返回空信息
     *
     * @return ResponseResult<String>
     */
    public ResponseResult<String> getUsernameHystrix(Principal principal) {
        return ResponseResultUtil.fail(ErrorCodeEnum.CIRCUIT_BREAKER);
    }

}
