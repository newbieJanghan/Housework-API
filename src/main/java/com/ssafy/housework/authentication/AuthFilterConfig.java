package com.ssafy.housework.authentication;

import com.ssafy.housework.authentication.jwt.JwtAuthFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthFilterConfig {

    @Bean
    public FilterRegistrationBean<JwtAuthFilter> jwtAuthFilter() {
        FilterRegistrationBean<JwtAuthFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new JwtAuthFilter());
        // Apply filter to /api/v1/* URIs
        registrationBean.addUrlPatterns("/api/v1/*");
        return registrationBean;
    }
}