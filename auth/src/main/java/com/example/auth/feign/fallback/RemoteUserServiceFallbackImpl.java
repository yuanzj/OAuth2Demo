package com.example.auth.feign.fallback;

import com.example.auth.feign.RemoteUserService;
import com.example.auth.model.UserDto;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author yuanzhijian
 */
@Slf4j
@Component
public class RemoteUserServiceFallbackImpl implements RemoteUserService {

    @Setter
    private Throwable cause;

    @Override
    public UserDto findByUsername(String from, String username) {
        log.error("feign 查询用户信息失败:{}", username, cause);
        return null;
    }
}