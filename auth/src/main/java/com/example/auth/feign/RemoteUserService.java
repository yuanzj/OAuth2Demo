package com.example.auth.feign;

import com.example.auth.feign.factory.RemoteUserServiceFallbackFactory;
import com.example.auth.model.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(contextId = "remoteUserService", value = "user", fallbackFactory = RemoteUserServiceFallbackFactory.class)
public interface RemoteUserService {

    /**
     * 通过用户名查询用户
     *
     * @param username 用户名
     * @return UserDto
     */
    @GetMapping("/users/info/{username}")
    UserDto findByUsername(@RequestHeader("from") String from, @PathVariable("username") String username);
}
