package com.kuukokawaii.common.util;

import com.kuukokawaii.common.entity.ResponseResult;
import com.kuukokawaii.common.lang.ErrorCodeEnum;
import org.springframework.http.HttpStatus;

/**
 * @Author 喵粮都输光了
 * @Date 2020/8/19 17:31
 * @Description 统一分装工具类
 */
public class ResponseResultUtil {

    /**
     * 操作成功返回有数据统一结果
     *
     * @param data 数据
     * @return 统一结果信息 Result
     */
    public static <T> ResponseResult<T> success(T data) {
        return new ResponseResult<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), data);
    }

    /**
     * 操作成功返回无数据统一结果
     *
     * @return 统一结果信息 Result
     */
    public static ResponseResult<String> success() {
        return success("");
    }

    /**
     * 操作失败返回统一结果
     *
     * @param status HTTP 状态码
     * @param msg    提示信息
     * @return 统一结果信息 Result
     */
    public static ResponseResult<String> fail(Integer status, String msg) {
        return new ResponseResult<>(status, msg, "");
    }

    /**
     * 操作失败返回统一结果
     *
     * @param errorCodeEnum 自定义错误状态码
     * @return 统一结果信息 Result
     */
    public static ResponseResult<String> fail(ErrorCodeEnum errorCodeEnum) {
        return fail(errorCodeEnum.getCode(), errorCodeEnum.getMsg());
    }

    /**
     * 操作失败返回统一结果
     *
     * @param httpStatus HttpStatus 类默认状态码
     * @return 统一结果信息 Result
     */
    public static ResponseResult<String> fail(HttpStatus httpStatus) {
        return fail(httpStatus.value(), httpStatus.getReasonPhrase());
    }

}
