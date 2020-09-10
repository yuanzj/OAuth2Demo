package com.example.auth;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * 扩展用户信息
 */
public class MyUser extends User {
    /**
     * 用户ID
     */
    @Getter
    private Long id;

    public MyUser(Long id, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.id = id;
    }

    @Override
    public String toString() {
        return "MyUser{" +
                "id=" + id +
                '}';
    }
}
