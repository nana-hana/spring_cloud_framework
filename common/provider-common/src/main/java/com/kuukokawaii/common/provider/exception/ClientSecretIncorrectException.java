package com.kuukokawaii.common.provider.exception;

import com.kuukokawaii.common.exception.CustomizeException;
import com.kuukokawaii.common.lang.ErrorCodeEnum;

/**
 * @Author 喵粮都输光了
 * @Date 2020/8/22 0:53
 * @Description 客户端安全码不正确异常
 */
public class ClientSecretIncorrectException extends CustomizeException {

    public ClientSecretIncorrectException(String msg) {
        super(ErrorCodeEnum.CLIENT_SECRET_INCORRECT, msg);
    }

}
