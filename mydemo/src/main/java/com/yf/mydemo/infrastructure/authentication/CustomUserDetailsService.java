package com.yf.mydemo.infrastructure.authentication;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.yf.mydemo.domain.entity.User;
import com.yf.mydemo.domain.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Component
public class CustomUserDetailsService implements UserDetailsService {
    @Resource
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User sysUser = userRepository.getOne(Wrappers.<User>query().lambda().eq(User::getName,username));
        if(sysUser==null){
            throw new UsernameNotFoundException("用户不存在");
        }
        return getDetail(sysUser);
    }


    private UserDetails getDetail(User sysUser){
        Set<String> permissions =  new HashSet<String>();
        permissions.add("Admin");
        permissions.add("Role1");
        String[] roles = new String[0];
        if(permissions.stream().count()>0){
            roles = permissions.stream().map(role -> "ROLE_" + role).toArray(String[]::new);
        }
        Collection<? extends GrantedAuthority> authorities  = AuthorityUtils.createAuthorityList(roles);
        CustomUserDetailsUser customUserDetailsUser = new CustomUserDetailsUser(sysUser.getUserId(),sysUser.getName(),sysUser.getPwd(),authorities);
        return customUserDetailsUser;
    }
}
