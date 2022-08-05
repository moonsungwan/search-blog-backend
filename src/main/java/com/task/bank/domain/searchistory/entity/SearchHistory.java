package com.task.bank.domain.searchistory.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.task.bank.global.entity.BaseEntity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@IdClass(SearchHistoryPK.class)
@Table(name = "SEARCH_HISTORY")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SearchHistory extends BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
    /* 검색어 */
	@Id
    @Column(name = "SEARCH_WORD")
    private String searchWord;

    /* 검색 횟수 */
    @Column(name = "SEARCH_COUNT")
    private Integer searchCount = 1;

    @Builder(builderClassName = "Insert", builderMethodName = "Insert")
    public SearchHistory(String searchWord) {
        this.searchWord = searchWord;
    }
    
    @Builder(builderClassName = "Update", builderMethodName = "Update")
    public SearchHistory(Long id, String searchWord, int searchCount) {
    	this.id = id;
    	this.searchWord = searchWord;
    	this.searchCount = searchCount + 1;
    }
    
}
