package com.kuukokawaii.security.provider.dao;

import com.kuukokawaii.security.provider.entity.PermissionDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author 喵粮都输光了
 * @Date 2020/8/19 23:08
 * @Description 权限 dao 接口
 */
@Mapper
public interface PermissionDAO {

    /**
     * 根据用户 id 获取用户权限信息（包括权限对应的资源）
     *
     * @param userId 用户id
     * @return UserPermissionBO
     */
    List<PermissionDO> listUserPermissionByUserId(Long userId);

}