package com.coder.auto_rental.security;

import com.coder.auto_rental.entity.Permission;
import com.coder.auto_rental.entity.User;
import com.coder.auto_rental.service.IPermissionService;
import com.coder.auto_rental.service.IUserService;
import jakarta.annotation.Resource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class CustomUserDetailsService implements UserDetailsService {
    @Resource
    private IUserService userService;
    @Resource
    private IPermissionService permissionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.selectByUsername(username);
        if (user == null) {
            throw new RuntimeException("用户不存在");
        }
        List<Permission> permissions = permissionService.selectPermissionListByUserId(user.getId());
        user.setPermissionList(permissions);
//        通过stream流处理，将权限的对象转换为权限字符串列表
        List<String> list = permissions.stream()
                .filter(Objects::nonNull)
                .map(Permission::getPermissionCode)
                .filter(Objects::nonNull)
                .toList();
        List<GrantedAuthority>authorities = AuthorityUtils.createAuthorityList(list.toArray(new String[list.size()]));
        user.setAuthorities(authorities);
        System.out.println(user);
        return  user;
    }
}
