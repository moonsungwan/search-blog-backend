package com.task.bank.domain.bookmark.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.task.bank.domain.bookmark.controller.request.BookmarkBlogInsertRequest;
import com.task.bank.domain.bookmark.entity.BookmarkBlog;
import com.task.bank.domain.bookmark.repository.BookmarkBlogRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@DisplayName("4. 블로그 즐겨찾기")
class BookmarkBlogServiceTest {

	@Autowired
	private BookmarkBlogRepository bookmarkBlogRepository;
	
	@Test
    @DisplayName("블로그 즐겨찾기 추가")
	public void 블로그_즐겨찾기_추가() {
		// given
		BookmarkBlogInsertRequest blogInsertRequest = new BookmarkBlogInsertRequest();
		blogInsertRequest.setLoginId("accountA");;
		blogInsertRequest.setBookmarkTitle("손흥민");
		blogInsertRequest.setBookmarkUrl("https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=1&ie=utf8&query=%EC%86%90%ED%9D%A5%EB%AF%BC");
		
		// when
		BookmarkBlog bookmarkBlog = BookmarkBlog.Insert()
											    .loginId(blogInsertRequest.getLoginId())
											    .bookmarkTitle(blogInsertRequest.getBookmarkTitle())
											    .bookmarkUrl(blogInsertRequest.getBookmarkUrl())
												.build();
		
		bookmarkBlog = bookmarkBlogRepository.save(bookmarkBlog);
		
		// then
		assertNotNull(bookmarkBlog);
		assertEquals(blogInsertRequest.getBookmarkTitle(), bookmarkBlog.getBookmarkTitle());
	}
	
	@Test
	@DisplayName("블로그 즐겨찾기 삭제")
	public void 블로그_즐겨찾기_삭제() {
		// before
		BookmarkBlogInsertRequest blogInsertRequest = new BookmarkBlogInsertRequest();
		blogInsertRequest.setLoginId("accountB");;
		blogInsertRequest.setBookmarkTitle("황의조");
		blogInsertRequest.setBookmarkUrl("https://search.naver.com/search.naver?sm=tab_hty.top&where=nexearch&query=%ED%99%A9%EC%9D%98%EC%A1%B0&oquery=%EC%86%90%ED%9D%A5%EB%AF%BC&tqi=hXIlrsprvxZssuGuAnossssstxV-413194");
		
		// when
		BookmarkBlog bookmarkBlog = BookmarkBlog.Insert()
											    .loginId(blogInsertRequest.getLoginId())
											    .bookmarkTitle(blogInsertRequest.getBookmarkTitle())
											    .bookmarkUrl(blogInsertRequest.getBookmarkUrl())
												.build();
		
		bookmarkBlogRepository.save(bookmarkBlog);
		
		// given
		Long id = Long.parseLong(String.valueOf("1"));
		
		// when
		BookmarkBlog findBookmarkBlog =  bookmarkBlogRepository.findById(id).get();
		bookmarkBlogRepository.delete(findBookmarkBlog);
		
		BookmarkBlog deleteBookmarkBlog =  bookmarkBlogRepository.findById(id).get();
		
		// then
		assertNull(deleteBookmarkBlog);
	}
	
	@Test
	@DisplayName("블로그 즐겨찾기 목록")
	public void 블로그_즐겨찾기_목록() {
		BookmarkBlogInsertRequest blogInsertRequest = new BookmarkBlogInsertRequest();
		blogInsertRequest.setLoginId("accountB");;
		blogInsertRequest.setBookmarkTitle("황의조");
		blogInsertRequest.setBookmarkUrl("https://search.naver.com/search.naver?sm=tab_hty.top&where=nexearch&query=%ED%99%A9%EC%9D%98%EC%A1%B0&oquery=%EC%86%90%ED%9D%A5%EB%AF%BC&tqi=hXIlrsprvxZssuGuAnossssstxV-413194");
		
		// when
		BookmarkBlog bookmarkBlog = BookmarkBlog.Insert()
											    .loginId(blogInsertRequest.getLoginId())
											    .bookmarkTitle(blogInsertRequest.getBookmarkTitle())
											    .bookmarkUrl(blogInsertRequest.getBookmarkUrl())
												.build();
		
		bookmarkBlogRepository.save(bookmarkBlog);
		
		// given
		String loginId = "accountB";
		
		// when
		List<BookmarkBlog> bookmarkBlogs = bookmarkBlogRepository.findByLoginId(loginId);
		
		// then
		assertTrue(bookmarkBlogs.size() > 0);
	}

}
