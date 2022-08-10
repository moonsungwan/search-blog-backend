package com.search.blog.domain.searchhistory.service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.search.blog.domain.searchhistory.controller.request.SearchHistoryInsertRequest;
import com.search.blog.domain.searchhistory.controller.response.SearchHistoryResponse;
import com.search.blog.domain.searchhistory.entity.SearchHistory;
import com.search.blog.domain.searchhistory.repository.SearchHistoryRepository;
import com.search.blog.global.entity.ApiResponseEntity;
import com.search.blog.global.message.MessageCode;

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
		Optional<SearchHistory> findSearchHistory = searchHistoryRepository.findById(searchHistoryInsertRequest.getSearchWord());

		if (findSearchHistory.isPresent()) {
			SearchHistory searchHistory = findSearchHistory.get();

			searchHistoryRepository.save(SearchHistory.Update()
					 								  .searchWord(searchHistoryInsertRequest.getSearchWord())
					 								  .searchCount(searchHistory.getSearchCount())
					 								  .build());
		} else {
			searchHistoryRepository.save(SearchHistory.Insert()
					 								  .searchWord(searchHistoryInsertRequest.getSearchWord())
					 								  .build());
		}

		return new ApiResponseEntity<Boolean>(true, MessageCode.REGISTERED);
	}

}