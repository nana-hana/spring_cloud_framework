package com.kuukokawaii.common.consumer.exception;

import com.kuukokawaii.common.entity.ResponseResult;
import com.kuukokawaii.common.util.ResponseResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author 喵粮都输光了
 * @Date 2020/8/14 19:13
 * @Description Controller 层的全局异常处理
 */
@RestControllerAdvice
@Slf4j
public class ConsumerControllerExceptionHandler {

    /**
     * 运行时异常全局处理
     *
     * @param e 运行时异常
     * @return 统一结果处理
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RuntimeException.class)
    public ResponseResult<String> runtimeExceptionHandler(RuntimeException e) {
        log.error("运行时异常：", e);
        return ResponseResultUtil.fail(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

}
