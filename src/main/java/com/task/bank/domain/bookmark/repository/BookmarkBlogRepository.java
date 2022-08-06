package com.task.bank.domain.bookmark.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.task.bank.domain.bookmark.entity.BookmarkBlog;

public interface BookmarkBlogRepository extends JpaRepository<BookmarkBlog, Long> {
	
	List<BookmarkBlog> findByLoginId(String loginId);

//	@Transactional
//	@Modifying
//	@Query("DELETE FROM BookmarkBlog WHERE id = :id")
//	Integer deleteByBookmarkBlogPKId(@Param("id") Long id);
	
}