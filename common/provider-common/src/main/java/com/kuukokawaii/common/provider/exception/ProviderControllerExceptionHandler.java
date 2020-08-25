package com.kuukokawaii.common.provider.exception;

import com.kuukokawaii.common.entity.ResponseResult;
import com.kuukokawaii.common.lang.ErrorCodeEnum;
import com.kuukokawaii.common.util.ResponseResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author 喵粮都输光了
 * @Date 2020/8/14 19:13
 * @Description provider 模块 Controller 层的全局异常处理
 */
@RestControllerAdvice
@Slf4j
public class ProviderControllerExceptionHandler {

    /**
     * 账号未找到异常处理
     *
     * @param e UsernameNotFoundException
     * @return 统一结果处理
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseResult<String> usernameNotFoundExceptionHandler(UsernameNotFoundException e) {
        log.error("账号不存在：", e);
        return ResponseResultUtil.fail(ErrorCodeEnum.USER_ACCOUNT_NOT_EXIST);
    }

    /**
     * 用户未登录异常处理
     *
     * @param e InsufficientAuthenticationException
     * @return 统一结果处理
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InsufficientAuthenticationException.class)
    public ResponseResult<String> insufficientAuthenticationExceptionHandler(InsufficientAuthenticationException e) {
        log.error("用户未登录：", e);
        return ResponseResultUtil.fail(ErrorCodeEnum.USER_NOT_LOGIN);
    }

    /**
     * 账号过期异常处理
     *
     * @param e AccountExpiredException
     * @return 统一结果处理
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(AccountExpiredException.class)
    public ResponseResult<String> accountExpiredExceptionHandler(AccountExpiredException e) {
        log.error("账号已过期：", e);
        return ResponseResultUtil.fail(ErrorCodeEnum.USER_ACCOUNT_EXPIRED);
    }

    /**
     * 密码错误异常处理
     *
     * @param e BadCredentialsException
     * @return 统一结果处理
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseResult<String> badCredentialsExceptionHandler(BadCredentialsException e) {
        log.error("密码错误：", e);
        return ResponseResultUtil.fail(ErrorCodeEnum.USER_CREDENTIALS_ERROR);
    }

    /**
     * 密码过期异常处理
     *
     * @param e CredentialsExpiredException
     * @return 统一结果处理
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CredentialsExpiredException.class)
    public ResponseResult<String> credentialsExpiredExceptionHandler(CredentialsExpiredException e) {
        log.error("密码过期：", e);
        return ResponseResultUtil.fail(ErrorCodeEnum.USER_CREDENTIALS_EXPIRED);
    }

    /**
     * 账号不可用异常处理
     *
     * @param e DisabledException
     * @return 统一结果处理
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DisabledException.class)
    public ResponseResult<String> disabledExceptionHandler(DisabledException e) {
        log.error("账号不可用：", e);
        return ResponseResultUtil.fail(ErrorCodeEnum.USER_ACCOUNT_DISABLE);
    }

    /**
     * 账号被锁定异常处理
     *
     * @param e LockedException
     * @return 统一结果处理
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(LockedException.class)
    public ResponseResult<String> lockedExceptionHandler(LockedException e) {
        log.error("账号被锁定：", e);
        return ResponseResultUtil.fail(ErrorCodeEnum.USER_ACCOUNT_LOCKED);
    }

    /**
     * 内部认证服务异常处理
     *
     * @param e InternalAuthenticationServiceException
     * @return 统一结果处理
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InternalAuthenticationServiceException.class)
    public ResponseResult<String> internalAuthenticationServiceExceptionHandler(InternalAuthenticationServiceException e) {
        log.error("内部认证服务异常：", e);
        return ResponseResultUtil.fail(ErrorCodeEnum.INTERNAL_AUTHENTICATION_ERROR);
    }


    /**
     * 未经批准的客户端身份验证异常处理
     *
     * @param e UnapprovedClientAuthenticationException
     * @return 统一结果处理
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UnapprovedClientAuthenticationException.class)
    public ResponseResult<String> unapprovedClientAuthenticationExceptionHandler(UnapprovedClientAuthenticationException e) {
        log.error("未经批准的客户端身份验证异常：", e);
        return ResponseResultUtil.fail(ErrorCodeEnum.CLIENT_UNAPPROVED);
    }

    /**
     * 客户端安全码错误异常
     *
     * @param e ClientSecretIncorrectException
     * @return 统一结果处理
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ClientSecretIncorrectException.class)
    public ResponseResult<String> clientSecretIncorrectExceptionHandler(ClientSecretIncorrectException e) {
        log.error("客户端安全码错误：", e);
        return ResponseResultUtil.fail(ErrorCodeEnum.CLIENT_SECRET_INCORRECT);
    }

    /**
     * 客户端身份验证错误异常
     *
     * @param e ClientAuthenticationException
     * @return 统一结果处理
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ClientAuthenticationException.class)
    public ResponseResult<String> clientAuthenticationExceptionHandler(ClientAuthenticationException e) {
        log.error("客户端身份验证错误：", e);
        return ResponseResultUtil.fail(ErrorCodeEnum.CLIENT_AUTHENTICATION_INCORRECT);
    }

}
