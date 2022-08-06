package com.task.bank.domain.blog.controller;


import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

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
	public ApiResponseEntity<BlogResponse> find(@RequestParam("query") @NotBlank String query
											  , @RequestParam(value = "sort") String sort
											  , @RequestParam(value = "page", defaultValue = "1")  @Max(50) Integer page
											  , @RequestParam(value = "size", defaultValue = "10") @Max(50) Integer size)   {
		return blogService.find(query, sort, page, size);
	}
	
}
