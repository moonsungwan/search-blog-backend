package com.task.bank.domain.bookmark.controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.task.bank.domain.bookmark.controller.request.BookmarkBlogInsertRequest;
import com.task.bank.domain.bookmark.controller.response.BookmarkBlogResponse;
import com.task.bank.domain.bookmark.service.BookmarkBlogService;
import com.task.bank.global.entity.ApiResponseEntity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@Api(value = "BookmarkController", tags = "4. 북마크 즐겨찾기 - 북마크 즐겨찾기 API")
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class BookmarkBlogController {

	private final BookmarkBlogService bookmarkBlogService;
	
	@ApiOperation(value = "북마크 즐겨찾기 목록", notes="북마크 즐겨찾기 목록")
	@GetMapping("/search-history")
	public ApiResponseEntity<List<BookmarkBlogResponse>> find()   {
		return bookmarkBlogService.find();
	}
	
	@ApiOperation(value = "북마크 즐겨찾기 등록", notes="북마크 즐겨찾기 등록")
	@PostMapping("/search-history")
	public ApiResponseEntity<Boolean> save(@Valid @RequestBody BookmarkBlogInsertRequest bookmarkBlogInsertRequest)   {
		return bookmarkBlogService.save(bookmarkBlogInsertRequest);
	}
	
	@ApiOperation(value = "북마크 즐겨찾기 삭제", notes="북마크 즐겨찾기 삭제")
	@DeleteMapping("/search-history/{id}")
	public ApiResponseEntity<Boolean> delete(@PathVariable Long id)   {
		return bookmarkBlogService.delete(id);
	}

}
