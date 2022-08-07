package com.search.blog.global.feign.config;

import com.google.common.base.Preconditions;

import feign.RequestInterceptor;
import feign.RequestTemplate;

public class KAKAOAuthRequestInterceptor implements RequestInterceptor {
	
    private String token;

    public KAKAOAuthRequestInterceptor(String token) {
        Preconditions.checkNotNull(token, "Token should not be null");
        this.token = token;
    }

    @Override
    public void apply(RequestTemplate template) {
        template.header("Authorization", "KakaoAK " + token);
    }
    
}