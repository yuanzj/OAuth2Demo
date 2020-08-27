package com.example.order.controller;

import com.example.auth.MyUser;
import com.example.order.entity.OrderEntity;
import com.example.order.feign.RemoteUserService;
import com.example.order.model.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@RequestMapping("/orders")
@RestController
public class OrderController {

    private static final Map<Long, OrderEntity> DATA = new ConcurrentHashMap<>();

    static {
        DATA.put(1L, new OrderEntity(1L, LocalDateTime.now()));
        DATA.put(2L, new OrderEntity(2L, LocalDateTime.now()));
    }

    @Autowired
    private RemoteUserService remoteUserService;

    @GetMapping
    public Collection<OrderEntity> list() {
        // 测试服务间调用
        MyUser user = (MyUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDto userDto = remoteUserService.findById(user.getId());
        log.info(userDto.toString());
        return DATA.values();
    }


}
