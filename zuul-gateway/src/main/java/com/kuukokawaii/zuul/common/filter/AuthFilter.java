package com.kuukokawaii.zuul.common.filter;

import com.alibaba.fastjson.JSON;
import com.kuukokawaii.common.constant.Constant;
import com.kuukokawaii.common.util.EncryptUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author 喵粮都输光了
 * @Date 2020/8/20 16:42
 * @Description 认证过滤器
 */
public class AuthFilter extends ZuulFilter {

    @Override
    public String filterType() {
        // 在路由之前拦截
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        // 优先级，越小越优先
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        // 过滤逻辑，此处直接放行
        return true;
    }

    @Override
    public Object run() {
        // 过滤通过后执行 token 转发
        RequestContext requestContext = RequestContext.getCurrentContext();
        // 获取认证信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof OAuth2Authentication)) {
            // 如果不是 OAuth2Authentication 则不进行解析
            return null;
        }
        OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) authentication;
        // 获取当前用户身份信息
        Authentication userAuthentication = oAuth2Authentication.getUserAuthentication();
        String principal = userAuthentication.getName();
        // 获取当前用户权限信息并存入 list 中
        List<String> authorities = new ArrayList<>();
        userAuthentication.getAuthorities().forEach(
            grantedAuthority -> authorities.add(grantedAuthority.getAuthority())
        );
        // 获取令牌中的其它信息
        OAuth2Request oAuth2Request = oAuth2Authentication.getOAuth2Request();
        Map<String, String> requestParameters = oAuth2Request.getRequestParameters();
        Map<String, Object> jsonToken = new HashMap<>(requestParameters);
        // 将身份信息和权限信息存入 json，加入 http 的 header 中
        jsonToken.put("principal", principal);
        jsonToken.put("authorities", authorities);
        // 转发给微服务：将 jsonToken 转成 json 格式并进行 Base64 编码
        String jsonTokenString = EncryptUtil.encodeUtf8StringBase64(JSON.toJSONString(jsonToken));
        requestContext.addZuulRequestHeader(Constant.REQUEST_TOKEN_HEADER, jsonTokenString);
        return null;
    }
}
