package com.example.order.feign;

import com.example.order.feign.factory.RemoteUserServiceFallbackFactory;
import com.example.order.model.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(contextId = "remoteUserService", value = "user", fallbackFactory = RemoteUserServiceFallbackFactory.class)
public interface RemoteUserService {

    @GetMapping("/users/{id}")
    UserDto findById(@PathVariable("id") Long id);

}
