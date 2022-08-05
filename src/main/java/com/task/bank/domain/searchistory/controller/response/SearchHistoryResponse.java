package com.task.bank.domain.searchistory.controller.response;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SearchHistoryResponse {
 
    /* 검색어 */
    private String searchWord;

    /* 검색 횟수 */
    private Integer searchCount;
	
}
