package com.search.blog.domain.blog.service;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.search.blog.domain.blog.controller.response.kakao.BlogResponse;
import com.search.blog.global.feign.KAKAOClient;

@RunWith(SpringRunner.class)
@DataJpaTest
@DisplayName("2. 블로그 검색")
class BlogServiceTest {

    @MockBean(name = "kakaoClient")
    KAKAOClient kakaoClient;
	
	@Test
    @DisplayName("블로그 검색 (카카오 API)")
	public void 블로그_검색_카카오API() {
		// given
		String query = "카카오";
		String sort = "accuracy";
		int page = 1;
		int size = 10;
		
		// when
		BlogResponse blogResponse = kakaoClient.searchBlog(query, sort, page, size);
		
		// then
		assertTrue(blogResponse.getDocuments().size() > 0);
	}

}
