package com.search.blog.domain.searchhistory.controller;


import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.search.blog.domain.searchhistory.controller.request.SearchHistoryInsertRequest;
import com.search.blog.domain.searchhistory.controller.response.SearchHistoryResponse;
import com.search.blog.domain.searchhistory.service.SearchHistoryService;
import com.search.blog.global.entity.ApiResponseEntity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@Api(value = "SearchHistoryController", tags = "3. 인기 검색어 - 인기 검색어 API")
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class SearchHistoryController {

	private final SearchHistoryService searchHistoryService;
	
	@ApiOperation(value = "인기 검색어 목록", notes="인기 검색어 목록")
	@GetMapping("/search-history")
	public ApiResponseEntity<List<SearchHistoryResponse>> find()   {
		return searchHistoryService.find();
	}
	
	@ApiOperation(value = "인기 검색어 등록", notes="인기 검색어 등록")
	@PostMapping("/search-history")
	public ApiResponseEntity<Boolean> save(@Validated @RequestBody SearchHistoryInsertRequest searchHistoryInsertRequest)   {
		return searchHistoryService.save(searchHistoryInsertRequest);
	}

}
