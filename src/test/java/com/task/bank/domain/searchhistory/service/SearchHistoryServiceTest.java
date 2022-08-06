package com.task.bank.domain.searchhistory.service;

import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.task.bank.domain.searchhistory.entity.SearchHistory;
import com.task.bank.domain.searchhistory.repository.SearchHistoryRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@DisplayName("3. 인기 검색어 목록")
class SearchHistoryServiceTest {

    private static final ExecutorService service = Executors.newFixedThreadPool(100);
	
    @Autowired
    private SearchHistoryRepository searchHistoryRepository;

	private String searchWord = "조회수테스트";

	@Before
	void setUp() {
		searchHistoryRepository.save(SearchHistory.Insert()
				 								  .searchWord(searchWord)
				 								  .build());	
	}
	
	@Test
    @DisplayName("인기검색어 등록 (검색된 횟수 동시성)")
	void 인기검색어_등록() throws InterruptedException {
		// given
		String searchWord = "조회수테스트";
		
		// when
	    CountDownLatch countDownLatch = new CountDownLatch(100);
	    
        for (int i=0; i < 100; i++) {
        	service.execute(() -> {
        		SearchHistory searchHistory = searchHistoryRepository.findBySearchWord(searchWord);
        		
        		searchHistoryRepository.save(SearchHistory.Update()
							 							  .id(searchHistory.getId())
							 							  .searchWord(searchWord)
							 							  .build());
        		
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        
        SearchHistory searchHistory = searchHistoryRepository.findBySearchWord(searchWord);
		// then
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
