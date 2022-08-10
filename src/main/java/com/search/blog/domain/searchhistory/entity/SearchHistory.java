package com.search.blog.domain.searchhistory.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.search.blog.global.entity.BaseEntity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "SEARCH_HISTORY")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SearchHistory extends BaseEntity {

    /* 검색어 */
	@Id
    @Column(name = "SEARCH_WORD")
    private String searchWord;

    /* 검색 횟수 */
    @Column(name = "SEARCH_COUNT")
    private Integer searchCount = 1;

    public void addSearchCount(int count) {
    	this.searchCount = count;
    }

    @Builder(builderClassName = "Insert", builderMethodName = "Insert")
    public SearchHistory(String searchWord) {
        this.searchWord = searchWord;
    }

    @Builder(builderClassName = "Update", builderMethodName = "Update")
    public SearchHistory(Long id, String searchWord, int searchCount) {
    	this.searchWord = searchWord;
    	this.searchCount = searchCount + 1;
    }

}
