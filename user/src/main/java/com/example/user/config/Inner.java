package com.example.user.config;

import java.lang.annotation.*;

/**
 * 内部服务间调用注解
 * @author yuanzhijian
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Inner {

}