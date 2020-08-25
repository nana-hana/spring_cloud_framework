package com.kuukokawaii.common.provider.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kuukokawaii.common.constant.Constant;
import com.kuukokawaii.common.util.EncryptUtil;
import io.micrometer.core.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author 喵粮都输光了
 * @Date 2020/8/24 16:19
 * @Description 解析令牌并授权
 */
@Component
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
        HttpServletRequest request,
        @NonNull HttpServletResponse response,
        @NonNull FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader(Constant.REQUEST_TOKEN_HEADER);
        if (token != null) {
            // header 解码并转 json 对象
            String jsonToken = EncryptUtil.decodeUtf8StringBase64(token);
            JSONObject jsonObject = JSON.parseObject(jsonToken);
            // 获取用户账号并存入 userDO
            String principal = jsonObject.getString("principal");
            // 获取用户权限并转成 string 数组的格式
            JSONArray authoritiesArray = jsonObject.getJSONArray("authorities");
            String[] authorities = new String[authoritiesArray.size()];
            for (int i = 0; i < authoritiesArray.size(); i++) {
                authorities[i] = authoritiesArray.getString(i);
            }
            // 创建 authenticationToken
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                principal, null, AuthorityUtils.createAuthorityList(authorities)
            );
            // 设置 authenticationToken 的 detail 信息
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            // 填充至 security 的上下文中
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        //放行过滤器
        filterChain.doFilter(request, response);
    }
}
