package com.search.blog.global.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.search.blog.domain.blog.controller.response.kakao.BlogResponse;

@FeignClient(name = "KakaoClient", url = "${open-api.url.kakao}", configuration = KAkAOClientConfig.class, primary = false)
public interface KAKAOClient {

    @GetMapping(value = "/v2/search/blog")
    BlogResponse searchBlog(@RequestParam("query") String query
    					  , @RequestParam("sort") String sort
    					  , @RequestParam("page") int page
    					  , @RequestParam("size") int size);
	
}
