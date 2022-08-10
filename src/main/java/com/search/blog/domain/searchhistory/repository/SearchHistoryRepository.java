package com.search.blog.domain.searchhistory.repository;

import java.util.List;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.search.blog.domain.searchhistory.entity.SearchHistory;

public interface SearchHistoryRepository extends JpaRepository<SearchHistory, String> {

	List<SearchHistory> findTop10ByOrderBySearchCountDesc();

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select s from SearchHistory s where s.searchWord = :searchWord")
	SearchHistory findBySearchWord(@Param("searchWord") String searchWord);

}