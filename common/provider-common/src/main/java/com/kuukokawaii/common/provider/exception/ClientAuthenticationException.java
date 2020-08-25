package com.kuukokawaii.common.provider.exception;

import com.kuukokawaii.common.exception.CustomizeException;
import com.kuukokawaii.common.lang.ErrorCodeEnum;

/**
 * @Author 喵粮都输光了
 * @Date 2020/8/22 1:02
 * @Description 客户端信息验证错误异常
 */
public class ClientAuthenticationException extends CustomizeException {

    public ClientAuthenticationException(String msg) {
        super(ErrorCodeEnum.CLIENT_AUTHENTICATION_INCORRECT, msg);
    }

}
