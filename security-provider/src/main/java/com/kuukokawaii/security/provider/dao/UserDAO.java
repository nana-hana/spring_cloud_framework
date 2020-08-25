package com.kuukokawaii.security.provider.dao;

import com.kuukokawaii.common.provider.entity.UserDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author 喵粮都输光了
 * @Date 2020/8/19 23:08
 * @Description 用户 dao 接口
 */
@Mapper
public interface UserDAO {

    /**
     * 根据账号获取用户信息
     *
     * @param username 账号
     * @return UserDO
     */
    UserDO getUserByUsername(String username);

    /**
     * 登录更新登陆时间，字段更新时间和更新用户(此处为自己）
     *
     * @param userDO UserDO
     */
    void updateLoginInfo(UserDO userDO);
}