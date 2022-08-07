package com.search.blog.domain.searchhistory.service;

import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.test.context.junit4.SpringRunner;

import com.search.blog.domain.searchhistory.controller.request.SearchHistoryInsertRequest;
import com.search.blog.domain.searchhistory.entity.SearchHistory;
import com.search.blog.domain.searchhistory.repository.SearchHistoryRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@DisplayName("3. 인기 검색어 목록")
class SearchHistoryServiceTest {

	@Autowired
	private SearchHistoryRepository searchHistoryRepository;

	@MockBean
	private SearchHistoryService searchHistoryService;

	@Before
	void setUp() {
		String searchWord = "조회수테스트";
		searchHistoryRepository.save(SearchHistory.Insert().searchWord(searchWord).build());
	}

	@Test
	@DisplayName("인기검색어 등록")
	void 인기검색어_등록() throws InterruptedException {
		// given
		String searchWord = "검색어 저장";
		
		// when
		searchHistoryRepository.save(SearchHistory.Insert().searchWord(searchWord).build());

		SearchHistory searchHistory = searchHistoryRepository.findBySearchWord(searchWord);
		
		// then
		assertTrue(searchWord.equals(searchHistory.getSearchWord()));
	}

	@Test
	@DisplayName("인기검색어 등록 (검색된 횟수 동시성)")
	void 인기검색어_등록_동시성() throws InterruptedException {
		// given
		AtomicInteger successCount = new AtomicInteger();
		int numberOfExcute = 100;
		ExecutorService service = Executors.newFixedThreadPool(10);
		CountDownLatch latch = new CountDownLatch(numberOfExcute);

		for (int i = 0; i < numberOfExcute; i++) {
			service.execute(() -> {
				try {
					SearchHistoryInsertRequest searchHistoryInsertRequest = new SearchHistoryInsertRequest();
					searchHistoryInsertRequest.setId(123123L);
					searchHistoryInsertRequest.setSearchWord("검색어");

					searchHistoryService.save(searchHistoryInsertRequest);
					successCount.getAndIncrement();
					System.out.println("성공");
				} catch (ObjectOptimisticLockingFailureException oe) {
					System.out.println("충돌감지");
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				latch.countDown();
			});
		}
		latch.await();

		// when
		SearchHistory searchHistory = searchHistoryRepository.findBySearchWord("검색어");

		// then
		assertTrue(successCount.get() == numberOfExcute);
	}

	@Test
	@DisplayName("인기검색어 조회")
	void 인기검색어_조회_검색된횟수() {

		// given
		// when
		List<SearchHistory> list = searchHistoryRepository.findTop10ByOrderBySearchCountDesc();

		// then
		assertTrue(list.size() <= 10);
	}

}
