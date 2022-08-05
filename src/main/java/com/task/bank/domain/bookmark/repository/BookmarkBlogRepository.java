package com.task.bank.domain.bookmark.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.task.bank.domain.bookmark.entity.BookmarkBlog;

public interface BookmarkBlogRepository extends JpaRepository<BookmarkBlog, Long> {
	
	BookmarkBlog findByLongId(String loginId);
	
}