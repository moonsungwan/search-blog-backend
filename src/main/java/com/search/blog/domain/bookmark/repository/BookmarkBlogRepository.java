package com.search.blog.domain.bookmark.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.search.blog.domain.bookmark.entity.BookmarkBlog;

public interface BookmarkBlogRepository extends JpaRepository<BookmarkBlog, Long> {

	List<BookmarkBlog> findByLoginId(String loginId);

	boolean existsByBookmarkTitle(String bookmarkTitle);

}
