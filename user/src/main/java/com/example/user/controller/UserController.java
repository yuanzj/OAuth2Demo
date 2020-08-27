package com.example.user.controller;

import com.example.user.config.Inner;
import com.example.user.entity.UserEntity;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@RequestMapping("/users")
@RestController
public class UserController {

    private static final Map<Long, UserEntity> DATA = new ConcurrentHashMap<>();

    static {
        DATA.put(1L, new UserEntity(1L, "admin", new BCryptPasswordEncoder().encode("123456"), "test@test", "13888888888"));
    }

    @GetMapping
    public Collection<UserEntity> findAll(@RequestParam(required = false) String username) {
        if (!StringUtils.isEmpty(username)) {
            return DATA.values().stream().filter(item -> item.getUsername().equals(username)).collect(Collectors.toList());
        }
        return DATA.values();
    }

    @GetMapping("/{id}")
    public UserEntity findById(@PathVariable("id") Long id) {
        return DATA.getOrDefault(id, null);
    }

    @Inner
    @GetMapping("/info/{username}")
    public UserEntity findByUsername(@PathVariable("username") String username) {
        Optional<UserEntity> userEntityOptional = DATA.values().stream().filter(item -> item.getUsername().equals(username)).findFirst();
        return userEntityOptional.orElse(null);
    }

}
