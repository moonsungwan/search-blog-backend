package com.task.bank.domain.searchistory.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.task.bank.domain.searchistory.entity.SearchHistory;

public interface SearchHistoryRepository extends JpaRepository<SearchHistory, String> {
	
	List<SearchHistory> findTop10ByOrderBySearchCountDesc();
	
}