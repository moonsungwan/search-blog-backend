package com.task.bank.domain.bookmark.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.task.bank.domain.bookmark.entity.BookmarkBlog;

public interface BookmarkBlogRepository extends JpaRepository<BookmarkBlog, Long> {
	
	Optional<BookmarkBlog> findByLoginId(String loginId);
	
}