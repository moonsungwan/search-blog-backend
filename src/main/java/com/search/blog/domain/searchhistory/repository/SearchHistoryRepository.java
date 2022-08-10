package com.search.blog.domain.searchhistory.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import com.search.blog.domain.searchhistory.entity.SearchHistory;

public interface SearchHistoryRepository extends JpaRepository<SearchHistory, String> {

	List<SearchHistory> findTop10ByOrderBySearchCountDesc();

	@Lock(LockModeType.PESSIMISTIC_WRITE)
	Optional<SearchHistory> findById(String searchWord);

}