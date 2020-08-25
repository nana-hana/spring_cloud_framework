package com.kuukokawaii.security.provider.common.config;

import com.kuukokawaii.common.entity.ResponseResult;
import com.kuukokawaii.common.lang.ErrorCodeEnum;
import com.kuukokawaii.common.util.ResponseResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.stereotype.Component;

/**
 * @Author 喵粮都输光了
 * @Date 2020/8/23 16:18
 * @Description OAuth 异常处理
 */
@Component
@Slf4j
public class SecurityWebResponseExceptionTranslator implements WebResponseExceptionTranslator<OAuth2Exception> {

    @Override
    public ResponseEntity<OAuth2Exception> translate(Exception e) {
        ResponseResult<String> result;
        if (e instanceof AccountExpiredException) {
            log.error("账号过期");
            result = ResponseResultUtil.fail(ErrorCodeEnum.USER_ACCOUNT_EXPIRED);
        } else if (e instanceof BadCredentialsException) {
            log.error("密码错误");
            result = ResponseResultUtil.fail(ErrorCodeEnum.USER_CREDENTIALS_ERROR);
        } else if (e instanceof CredentialsExpiredException) {
            log.error("密码过期");
            result = ResponseResultUtil.fail(ErrorCodeEnum.USER_CREDENTIALS_EXPIRED);
        } else if (e instanceof DisabledException) {
            log.error("账号不可用");
            result = ResponseResultUtil.fail(ErrorCodeEnum.USER_ACCOUNT_DISABLE);
        } else if (e instanceof LockedException) {
            log.error("账号锁定");
            result = ResponseResultUtil.fail(ErrorCodeEnum.USER_ACCOUNT_LOCKED);
        } else if (e instanceof InternalAuthenticationServiceException) {
            log.error("用户不存在");
            result = ResponseResultUtil.fail(ErrorCodeEnum.USER_ACCOUNT_NOT_EXIST);
        } else {
            log.error("认证错误");
            result = ResponseResultUtil.fail(ErrorCodeEnum.INTERNAL_AUTHENTICATION_ERROR);
        }
        log.error(result.getMsg(), e);
        return new ResponseEntity<>(
            OAuth2Exception.create(String.valueOf(result.getStatus()), result.getMsg()),
            HttpStatus.valueOf(result.getStatus())
        );
    }

}
