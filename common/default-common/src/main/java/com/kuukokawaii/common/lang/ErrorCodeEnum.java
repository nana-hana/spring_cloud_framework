package com.kuukokawaii.common.lang;

/**
 * @Author 喵粮都输光了
 * @Date 2020/8/16 15:56
 * @Description 错误状态码
 */
public enum ErrorCodeEnum {

    /**
     * 参数错误：1000～1999
     */
    PARAM_NOT_VALID(1000, "参数无效"),
    PARAM_IS_BLANK(1001, "参数为空"),
    PARAM_TYPE_ERROR(1002, "参数类型错误"),
    PARAM_NOT_COMPLETE(1003, "参数缺失"),

    /**
     * 用户错误：2000~2999
     */
    USER_NOT_LOGIN(2000, "用户未登录"),
    USER_ACCOUNT_EXPIRED(2001, "账号已过期"),
    USER_CREDENTIALS_ERROR(2002, "密码错误"),
    USER_CREDENTIALS_EXPIRED(2003, "密码过期"),
    USER_ACCOUNT_DISABLE(2004, "账号不可用"),
    USER_ACCOUNT_LOCKED(2005, "账号被锁定"),
    USER_ACCOUNT_NOT_EXIST(2006, "账号不存在"),
    USER_ACCOUNT_ALREADY_EXIST(2007, "账号已存在"),
    USER_ACCOUNT_USE_BY_OTHERS(2008, "账号下线"),
    CLIENT_UNAPPROVED(2009, "未经允许的客户端身份验证信息"),

    /**
     * 业务错误：3000~3999
     */
    CIRCUIT_BREAKER(3000, "请求服务出错，服务熔断"),
    SERVICE_DEGRADATION(3001, "该服务暂时关闭，服务降级"),
    NO_PERMISSION(3002, "没有权限"),

    /**
     * 系统错误：4000~4001
     */
    INTERNAL_AUTHENTICATION_ERROR(4000, "内部认证服务异常"),

    CLIENT_SECRET_INCORRECT(4001, "客户端安全码错误"),

    CLIENT_AUTHENTICATION_INCORRECT(4002, "客户端身份验证错误");

    /**
     * 错误代码
     * 1000~1999：参数错误
     * 2000~2999：用户错误
     * 3000~3999：业务错误
     * 4000~4001：系统错误
     */
    private Integer code;
    /**
     * 错误信息
     */
    private String msg;

    ErrorCodeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
