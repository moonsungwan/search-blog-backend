package com.task.bank.domain.blog.service;


import org.springframework.stereotype.Service;

import com.task.bank.domain.blog.controller.response.kakao.BlogResponse;
import com.task.bank.global.entity.ApiResponseEntity;
import com.task.bank.global.feign.KAKAOClient;
import com.task.bank.global.message.MessageCode;

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