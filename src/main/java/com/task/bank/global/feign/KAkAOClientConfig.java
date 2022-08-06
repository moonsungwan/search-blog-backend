package com.task.bank.global.feign;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.RequestInterceptor;

@Configuration
public class KAkAOClientConfig {

    @Bean
    public RequestInterceptor requestHeader(@Value("${open-api.client-key.kakao}") String token) {
        return new KAKAOAuthRequestInterceptor(token);
    }
    
}
