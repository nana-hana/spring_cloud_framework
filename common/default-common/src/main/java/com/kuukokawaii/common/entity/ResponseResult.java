package com.kuukokawaii.common.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author 喵粮都输光了
 * @Date 2020/8/14 9:23
 * @Description 统一结果封装
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@ApiModel(description = "统一结果")
public class ResponseResult<T> implements Serializable {

    /**
     * HTTP 状态码或自定义错误代码
     */
    @ApiModelProperty(value = "HTTP 状态码或自定义错误代码", required = true)
    private Integer status;
    /**
     * 提示信息
     */
    @ApiModelProperty(value = "提示信息", required = true)
    private String msg;
    /**
     * 数据
     */
    @ApiModelProperty(value = "返回的数据", required = true)
    private T data;

}
