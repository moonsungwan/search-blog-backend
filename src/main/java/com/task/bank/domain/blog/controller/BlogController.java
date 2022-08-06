package com.task.bank.domain.blog.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.task.bank.domain.blog.controller.response.kakao.BlogResponse;
import com.task.bank.domain.blog.service.BlogService;
import com.task.bank.global.entity.ApiResponseEntity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@Api(value = "BlogController", tags = "2. 블로그 - 블로그 검색 API")
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class BlogController {

	private final BlogService blogService;
	
	@ApiOperation(value = "블로그 목록", notes="블로그 목록")
	@GetMapping("/blog")
	public ApiResponseEntity<BlogResponse> find(@RequestParam("query") String query
												, @RequestParam("sort") String sort
												, @RequestParam("page") Integer page)   {
		return blogService.find(query, sort, page);
	}
	
}
