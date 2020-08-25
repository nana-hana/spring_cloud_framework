package com.kuukokawaii.common.provider.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @Author 喵粮都输光了
 * @Date 2020/8/19 22:29
 * @Description 用户实体
 */
@Data
@ApiModel(description = "用户DO")
@Accessors(chain = true)
@NoArgsConstructor
public class UserDO implements Serializable {

    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id")
    private Long id;
    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名", required = true)
    private String nickname;
    /**
     * 登陆用账号
     */
    @ApiModelProperty(value = "登陆用账号", required = true)
    private String username;
    /**
     * 密码
     */
    @ApiModelProperty(value = "密码", required = true)
    private String password;
    /**
     * 最后一次登录时间
     */
    @ApiModelProperty(value = "最后一次登录时间")
    private Timestamp lastLoginTime;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Timestamp createTime;
    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    private Timestamp updateTime;
    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人", required = true)
    private Long createUser;
    /**
     * 最后一次修改的人
     */
    @ApiModelProperty(value = "最后一次修改的人", required = true)
    private Long updateUser;
    /**
     * 账号是否可用：1可用，0不可用
     */
    @ApiModelProperty(value = "账号是否可用")
    private Boolean enabled;
    /**
     * 账号是否没过期
     */
    @ApiModelProperty(value = "账号是否没过期")
    private Boolean nonExpired;
    /**
     * 账号是否没被锁定
     */
    @ApiModelProperty(value = "账号是否没被锁定")
    private Boolean accountNonLocked;
    /**
     * 证书（密码）是否没过期
     */
    @ApiModelProperty(value = "证书（密码）是否没过期")
    private Boolean credentialsNonExpired;

    public UserDO(String nickname, String username, String password, Long createUser, Long updateUser) {
        this.nickname = nickname;
        this.username = username;
        this.password = password;
        this.createUser = createUser;
        this.updateUser = updateUser;
    }
}
