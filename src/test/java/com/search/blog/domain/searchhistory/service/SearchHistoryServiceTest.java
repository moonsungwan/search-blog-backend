package com.search.blog.domain.searchhistory.service;

import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.test.context.junit4.SpringRunner;

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

	@Test
	@DisplayName("인기검색어 등록")
	void 인기검색어_등록() throws InterruptedException {
		// given
		String searchWord = "검색어 저장";

		// when
		SearchHistory searchHistory = searchHistoryRepository.save(SearchHistory.Insert().searchWord(searchWord).build());

		// then
		assertTrue(searchWord.equals(searchHistory.getSearchWord()));
	}

	@Test
	@DisplayName("인기검색어 등록 (검색된 횟수 동시성)")
	void 인기검색어_등록_동시성() throws InterruptedException {
		// given
		String searchWord = "동시성 테스트";

		// when
		AtomicInteger successCount = new AtomicInteger();
		int numberOfExcute = 100;
		ExecutorService service = Executors.newFixedThreadPool(10);
		CountDownLatch latch = new CountDownLatch(numberOfExcute);

		for (int i = 0; i < numberOfExcute; i++) {
			service.execute(() -> {
				try {
					successCount.getAndIncrement();

					SearchHistory searchHistory = searchHistoryRepository.findById(searchWord).get();
					searchHistory.addSearchCount(successCount.get());

					searchHistoryRepository.save(searchHistory);
				} catch (ObjectOptimisticLockingFailureException oe) {
					System.out.println("충돌감지");
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				latch.countDown();
			});
		}
		latch.await();

		// then
		int resultCount = searchHistoryRepository.findById(searchWord).get().getSearchCount();

		assertTrue(successCount.get() == resultCount);
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
