package com.search.blog.domain.blog.service;


import org.springframework.stereotype.Service;

import com.search.blog.domain.blog.controller.response.kakao.BlogResponse;
import com.search.blog.global.entity.ApiResponseEntity;
import com.search.blog.global.feign.KAKAOClient;
import com.search.blog.global.message.MessageCode;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BlogService {

	private final KAKAOClient kakaoClient;
	
	public ApiResponseEntity<BlogResponse> find(String query, String sort, Integer page, Integer size) {
		BlogResponse blogResponse = kakaoClient.searchBlog(query, sort, page, size);
		
		return new ApiResponseEntity<BlogResponse>(blogResponse, MessageCode.SUCCEED);
	}
	
}