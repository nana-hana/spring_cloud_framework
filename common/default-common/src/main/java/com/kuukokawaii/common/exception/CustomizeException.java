package com.kuukokawaii.common.exception;

import com.kuukokawaii.common.lang.ErrorCodeEnum;

/**
 * @Author 喵粮都输光了
 * @Date 2020/8/22 1:04
 * @Description 自定义总异常
 */
public class CustomizeException extends RuntimeException {

    /**
     * 错误代码
     */
    private final Integer errorCode;

    public CustomizeException(ErrorCodeEnum errorCodeEnum, String msg) {
        super(errorCodeEnum.getMsg() + msg);
        errorCode = errorCodeEnum.getCode();
    }

    public Integer getErrorCode() {
        return errorCode;
    }

}
