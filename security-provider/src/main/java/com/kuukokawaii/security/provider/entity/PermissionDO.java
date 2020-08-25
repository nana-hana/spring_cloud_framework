package com.kuukokawaii.security.provider.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author 喵粮都输光了
 * @Date 2020/8/19 22:19
 * @Description 权限实体
 */
@Data
@ApiModel(description = "权限DO")
@Accessors(chain = true)
@NoArgsConstructor
public class PermissionDO implements Serializable {

    /**
     * 权限id
     */
    @ApiModelProperty(value = "权限id")
    private Long id;
    /**
     * 权限标识符
     */
    @ApiModelProperty(value = "权限标识符", required = true)
    private String permissionCode;
    /**
     * 权限描述
     */
    @ApiModelProperty(value = "权限名", required = true)
    private String permissionName;
    /**
     * 资源路径
     */
    @ApiModelProperty(value = "资源路径", required = true)
    private String url;
    /**
     * 请求方法
     */
    @ApiModelProperty(value = "请求方法", required = true)
    private String action;

    public PermissionDO(String permissionCode, String permissionName, String url, String action) {
        this.permissionCode = permissionCode;
        this.permissionName = permissionName;
        this.url = url;
        this.action = action;
    }

}
