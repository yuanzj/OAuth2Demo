package com.example.auth.config;

import com.example.auth.feign.RemoteUserService;
import com.example.auth.model.UserDto;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@AllArgsConstructor
@Component
public class MyUserDetailsService implements UserDetailsService {

    private final RemoteUserService remoteUserService;

    /**
     * 用户密码登录
     *
     * @param username 用户名
     * @return
     */
    @Override
    @SneakyThrows
    public UserDetails loadUserByUsername(String username) {
        UserDto result = remoteUserService.findByUsername("inner", username);
        return getUserDetails(result);
    }

    /**
     * 构建userdetails
     *
     * @param user 用户信息
     * @return UserDetails
     */
    private UserDetails getUserDetails(UserDto user) {
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }

        // 用户角色也应在数据库中获取
        String role = "ROLE_ADMIN";
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role));

        // 构造security用户
        return new MyUser(user.getId(), user.getUsername(), "{bcrypt}" + user.getPassword(), authorities);
    }
}