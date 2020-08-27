package com.example.order.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Component;

/**
 * FeignClient拦截器
 * 为FeignClient HTTP调用增加Authorization头
 */
@Component
public class FeignClientInterceptor implements RequestInterceptor {

    private static final String AUTHORIZATION_HEADER = "Authorization";

    @Override
    public void apply(RequestTemplate requestTemplate) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            Object details = authentication.getDetails();
            if (details instanceof OAuth2AuthenticationDetails) {
                OAuth2AuthenticationDetails holder = (OAuth2AuthenticationDetails) details;
                String token = holder.getTokenValue();
                String tokenType = holder.getTokenType();

                requestTemplate.header(AUTHORIZATION_HEADER, String.format("%s %s", tokenType, token));
            }
        } else {

        }
    }
}