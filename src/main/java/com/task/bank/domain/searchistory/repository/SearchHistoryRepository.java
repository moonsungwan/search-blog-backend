package com.task.bank.domain.searchistory.repository;

import java.util.List;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import com.task.bank.domain.searchistory.entity.SearchHistory;

public interface SearchHistoryRepository extends JpaRepository<SearchHistory, Long> {
	
	List<SearchHistory> findTop10ByOrderBySearchCountDesc();
	
	@Lock(LockModeType.PESSIMISTIC_READ)
	SearchHistory findBySearchWord(String searchWord);
	
}