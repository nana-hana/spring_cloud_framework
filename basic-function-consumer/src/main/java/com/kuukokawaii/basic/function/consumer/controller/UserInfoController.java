package com.kuukokawaii.basic.function.consumer.controller;

import com.kuukokawaii.common.consumer.service.UserInfoBasicClientService;
import com.kuukokawaii.common.entity.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 喵粮都输光了
 * @Date 2020/8/24 1:10
 * @Description 用户信息控制层
 */
@RestController
public class UserInfoController {

    private final UserInfoBasicClientService userInfoService;

    @Autowired
    public UserInfoController(UserInfoBasicClientService userInfoService) {
        this.userInfoService = userInfoService;
    }

    /**
     * 获取所有用户信息
     *
     * @return ResponseResult<String>
     */
    @GetMapping("/user-info")
    public ResponseResult<String> getUsername() {
        return userInfoService.getUsername();
    }

}
