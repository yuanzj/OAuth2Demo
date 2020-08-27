package com.example.order.feign.fallback;

import com.example.order.feign.RemoteUserService;
import com.example.order.model.UserDto;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RemoteUserServiceFallbackImpl implements RemoteUserService {

    @Setter
    private Throwable cause;

    @Override
    public UserDto findById(Long id) {
        log.error("feign 查询用户信息失败:{}", id, cause);
        return null;
    }
}