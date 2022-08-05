package com.task.bank.domain.searchistory.service;


import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

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
		List<SearchHistoryResponse> response = searchHistoryRepository.findTop10ByOrderBySearchCountDesc()
				  													  .stream()
				  													  .map(history -> modelMapper.map(history, SearchHistoryResponse.class))
				  													  .collect(Collectors.toList());
		
		List<SearchHistoryResponse> response2 = searchHistoryRepository.findTop10ByOrderBySearchCountDesc()
				  	.stream()
				  .map(history -> modelMapper.map(history, SearchHistoryResponse.class))
				  .collect(Collectors.toList());

		searchHistoryRepository.findTop10ByOrderBySearchCountDesc().forEach(name -> {
		    System.out.println("11 : "+name.getSearchWord());
		});
		
		
		for (SearchHistoryResponse searchHistoryResponse : response) {
			System.out.println("gg" + searchHistoryResponse.getSearchWord());
		}
		
		return new ApiResponseEntity<List<SearchHistoryResponse>>(response, MessageCode.SUCCEED);
	}
	
	@Transactional
	public ApiResponseEntity<Boolean> save(SearchHistoryInsertRequest searchHistoryInsertRequest) {
		SearchHistory searchHistory = searchHistoryRepository.findBySearchWord(searchHistoryInsertRequest.getSearchWord());
		
		if (ObjectUtils.isEmpty(searchHistory)) {
			searchHistory = SearchHistory.InsertSearchHistory()
										 .searchWord(searchHistoryInsertRequest.getSearchWord())
										 .build();
		} else {
			searchHistory = SearchHistory.UpdateSearchHistory()
										 .id(searchHistory.getId())
					 					 .searchWord(searchHistoryInsertRequest.getSearchWord())
					 					 .searchCount(searchHistory.getSearchCount())
					 					 .build();
		}
		
		searchHistoryRepository.save(searchHistory);	
		
		return new ApiResponseEntity<Boolean>(true, MessageCode.REGISTERED);
	}

}