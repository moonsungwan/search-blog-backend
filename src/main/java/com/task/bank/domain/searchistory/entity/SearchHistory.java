package com.task.bank.domain.searchistory.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.task.bank.global.entity.BaseEntity;

import lombok.AccessLevel;
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
    private Integer searchCount;
    
    public void addCount() {
    	this.searchCount = this.searchCount + 1;
    }
    
}
