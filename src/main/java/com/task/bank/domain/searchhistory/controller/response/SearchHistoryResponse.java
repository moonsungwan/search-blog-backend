package com.task.bank.domain.searchhistory.controller.response;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SearchHistoryResponse {
 
    /* 검색어 */
    private String searchWord;

    /* 검색 횟수 */
    private Integer searchCount;
	
}
