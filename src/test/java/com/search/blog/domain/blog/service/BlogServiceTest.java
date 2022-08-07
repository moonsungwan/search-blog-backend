package com.search.blog.domain.blog.service;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import com.search.blog.domain.blog.controller.response.kakao.BlogResponse;
import com.search.blog.global.feign.KAKAOClient;
import com.search.blog.global.feign.KAkAOClientConfig;

@RunWith(SpringRunner.class)
@DataJpaTest
@RestClientTest(KAKAOClient.class)
@Import({KAkAOClientConfig.class})
@DisplayName("2. 블로그 검색")
class BlogServiceTest {

	@Autowired
	private KAKAOClient kakaoClient;
	
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
