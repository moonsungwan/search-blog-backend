package com.search.blog.domain.bookmark.service;


import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.search.blog.domain.bookmark.controller.request.BookmarkBlogInsertRequest;
import com.search.blog.domain.bookmark.controller.response.BookmarkBlogResponse;
import com.search.blog.domain.bookmark.entity.BookmarkBlog;
import com.search.blog.domain.bookmark.repository.BookmarkBlogRepository;
import com.search.blog.global.entity.ApiResponseEntity;
import com.search.blog.global.exception.CustomException;
import com.search.blog.global.message.MessageCode;
import com.search.blog.global.utils.SecurityUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookmarkBlogService {

	private final BookmarkBlogRepository bookmarkBlogRepository;

	private final ModelMapper modelMapper;

	@Transactional(readOnly = true)
	public ApiResponseEntity<List<BookmarkBlogResponse>> find() {
		List<BookmarkBlogResponse> bookmarkBlogs = bookmarkBlogRepository.findByLoginId(SecurityUtil.getCurrentLoginId())
				  														 .stream()
				  														 .map(bookmark -> modelMapper.map(bookmark, BookmarkBlogResponse.class))
				  														 .collect(Collectors.toList());

		return new ApiResponseEntity<List<BookmarkBlogResponse>>(bookmarkBlogs, MessageCode.SUCCEED);
	}

	@Transactional
	public ApiResponseEntity<Boolean> save(BookmarkBlogInsertRequest blogInsertRequest) {
		BookmarkBlog bookmarkBlog = BookmarkBlog.Insert()
												.loginId(SecurityUtil.getCurrentLoginId())
												.bookmarkTitle(blogInsertRequest.getBookmarkTitle())
												.bookmarkUrl(blogInsertRequest.getBookmarkUrl())
												.build();

		if (bookmarkBlogRepository.existsByBookmarkUrl(bookmarkBlog.getBookmarkUrl())) {
			throw new CustomException(MessageCode.EXISTING_BOOKMARK);
		}

		bookmarkBlogRepository.save(bookmarkBlog);

		return new ApiResponseEntity<Boolean>(true, MessageCode.REGISTERED);
	}

	@Transactional
	public ApiResponseEntity<Boolean> delete(Long id) {
		BookmarkBlog findBookmarkBlog =  bookmarkBlogRepository.findById(id)
															   .orElseThrow(() -> new CustomException(MessageCode.NO_CONTENT));

		bookmarkBlogRepository.delete(findBookmarkBlog);

		return new ApiResponseEntity<Boolean>(true, MessageCode.DELETED);
	}

}