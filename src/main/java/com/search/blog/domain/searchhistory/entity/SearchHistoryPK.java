package com.search.blog.domain.searchhistory.entity;

import java.io.Serializable;

import javax.persistence.Column;

import lombok.Getter;

@Getter
public class SearchHistoryPK implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Column(name = "ID")
	private Long id;
	
    @Column(name = "SEARCH_WORD")
    private String searchWord;

}
