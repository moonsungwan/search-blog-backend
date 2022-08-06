package com.task.bank.domain.searchhistory.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.task.bank.domain.searchhistory.entity.SearchHistory;

public interface SearchHistoryRepository extends JpaRepository<SearchHistory, Long> {
	
	List<SearchHistory> findTop10ByOrderBySearchCountDesc();
	
//	@Lock(LockModeType.PESSIMISTIC_READ)
	SearchHistory findBySearchWord(String searchWord);
	
}