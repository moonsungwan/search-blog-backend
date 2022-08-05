package com.task.bank.domain.searchistory.service;


import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.task.bank.domain.searchistory.controller.request.SearchHistoryInsertRequest;
import com.task.bank.domain.searchistory.controller.response.SearchHistoryResponse;
import com.task.bank.domain.searchistory.entity.SearchHistory;
import com.task.bank.domain.searchistory.repository.SearchHistoryRepository;
import com.task.bank.global.entity.ApiResponseEntity;
import com.task.bank.global.message.MessageCode;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SearchHistoryService {

	private final SearchHistoryRepository searchHistoryRepository;
	
	private final ModelMapper modelMapper;
		
	@Transactional(readOnly = true)
	public ApiResponseEntity<List<SearchHistoryResponse>> find() {
		List<SearchHistory> list = searchHistoryRepository.findTop10ByOrderBySearchCountDesc();
		
		return new ApiResponseEntity<List<SearchHistoryResponse>>(createSerarchHistoryResponse(list), MessageCode.SUCCEED);
	}
	
	@Transactional
	public ApiResponseEntity<Boolean> save(SearchHistoryInsertRequest searchHistoryInsertRequest) {
		SearchHistory searchHistory = searchHistoryRepository.getById(searchHistoryInsertRequest.getSearchWord());
		searchHistory.addCount();
		
		searchHistoryRepository.save(searchHistory);
		
		return new ApiResponseEntity<Boolean>(true, MessageCode.REGISTERED);
	}

	private List<SearchHistoryResponse> createSerarchHistoryResponse(List<SearchHistory> list) {
		return searchHistoryRepository.findTop10ByOrderBySearchCountDesc()
	    							  .stream()
	    							  .map(history -> modelMapper.map(history, SearchHistoryResponse.class))
	    							  .collect(Collectors.toList());
	} 
	
}