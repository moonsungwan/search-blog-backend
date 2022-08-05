package com.task.bank.domain.bookmark.service;


import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.task.bank.domain.bookmark.controller.request.BookmarkBlogInsertRequest;
import com.task.bank.domain.bookmark.controller.response.BookmarkBlogResponse;
import com.task.bank.domain.bookmark.entity.BookmarkBlog;
import com.task.bank.domain.bookmark.repository.BookmarkBlogRepository;
import com.task.bank.global.entity.ApiResponseEntity;
import com.task.bank.global.message.MessageCode;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookmarkBlogService {

	private final BookmarkBlogRepository bookmarkBlogRepository;
	
	private final ModelMapper modelMapper;
		
	@Transactional(readOnly = true)
	public ApiResponseEntity<List<BookmarkBlogResponse>> find() {
		return new ApiResponseEntity<List<BookmarkBlogResponse>>(null, MessageCode.SUCCEED);
	}
	
	@Transactional
	public ApiResponseEntity<Boolean> save(BookmarkBlogInsertRequest blogInsertRequest) {
		BookmarkBlog bookmarkBlog = BookmarkBlog.Insert()
												.bookmarkBlogInsertRequest(blogInsertRequest)
												.build();
		
		bookmarkBlogRepository.save(bookmarkBlog);
		
		return new ApiResponseEntity<Boolean>(true, MessageCode.REGISTERED);
	}
	
	@Transactional
	public ApiResponseEntity<Boolean> delete(Long id) {
		bookmarkBlogRepository.deleteById(id);
		
		return new ApiResponseEntity<Boolean>(true, MessageCode.DELETED);
	}

}