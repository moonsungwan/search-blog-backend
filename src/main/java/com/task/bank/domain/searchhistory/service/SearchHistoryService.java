package com.task.bank.domain.searchhistory.service;


import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.task.bank.domain.searchhistory.controller.request.SearchHistoryInsertRequest;
import com.task.bank.domain.searchhistory.controller.response.SearchHistoryResponse;
import com.task.bank.domain.searchhistory.entity.SearchHistory;
import com.task.bank.domain.searchhistory.repository.SearchHistoryRepository;
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
		List<SearchHistoryResponse> response = searchHistoryRepository.findTop10ByOrderBySearchCountDesc()
				  													  .stream()
				  													  .map(history -> modelMapper.map(history, SearchHistoryResponse.class))
				  													  .collect(Collectors.toList());
		
		return new ApiResponseEntity<List<SearchHistoryResponse>>(response, MessageCode.SUCCEED);
	}
	
	@Transactional
	public ApiResponseEntity<Boolean> save(SearchHistoryInsertRequest searchHistoryInsertRequest) {
		SearchHistory searchHistory = searchHistoryRepository.findBySearchWord(searchHistoryInsertRequest.getSearchWord());
		
		if (ObjectUtils.isEmpty(searchHistory)) {
			searchHistory = SearchHistory.Insert()
										 .searchWord(searchHistoryInsertRequest.getSearchWord())
										 .build();
		} else {
			searchHistory = SearchHistory.Update()
										 .id(searchHistory.getId())
					 					 .searchWord(searchHistoryInsertRequest.getSearchWord())
					 					 .searchCount(searchHistory.getSearchCount())
					 					 .build();
		}
		
		searchHistoryRepository.save(searchHistory);	
		
		return new ApiResponseEntity<Boolean>(true, MessageCode.REGISTERED);
	}

}