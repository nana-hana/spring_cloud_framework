package com.kuukokawaii.security.provider.common.config.service;

import com.kuukokawaii.common.provider.entity.UserDO;
import com.kuukokawaii.security.provider.dao.PermissionDAO;
import com.kuukokawaii.security.provider.dao.UserDAO;
import com.kuukokawaii.security.provider.entity.PermissionDO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author 喵粮都输光了
 * @Date 2020/8/19 22:38
 * @Description 从数据库加载用户信息
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserDAO userDAO;
    private final PermissionDAO permissionDAO;

    public UserDetailsServiceImpl(UserDAO userDAO, PermissionDAO permissionDAO) {
        this.userDAO = userDAO;
        this.permissionDAO = permissionDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 此处传递过来的 username 实际为 account
        UserDO userDO = userDAO.getUserByUsername(username);
        if (userDO == null) {
            throw new UsernameNotFoundException(username + "账号不存在");
        }
        // 获取用户权限信息
        List<PermissionDO> permissionList = permissionDAO.listUserPermissionByUserId(userDO.getId());
        // 提取 userPermissionList 中的权限名
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        permissionList.forEach(permission -> {
            String permissionDescription = permission.getPermissionCode();
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(permissionDescription);
            grantedAuthorities.add(grantedAuthority);
        });
        // 返回 UserDetails 信息
        return new User(
            userDO.getUsername(),
            userDO.getPassword(),
            userDO.getEnabled(),
            userDO.getNonExpired(),
            userDO.getCredentialsNonExpired(),
            userDO.getAccountNonLocked(),
            grantedAuthorities
        );
    }

}
