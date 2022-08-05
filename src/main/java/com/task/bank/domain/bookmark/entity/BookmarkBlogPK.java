package com.task.bank.domain.bookmark.entity;

import java.io.Serializable;

import javax.persistence.Column;

import lombok.Getter;

@Getter
public class BookmarkBlogPK implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Column(name = "ID")
	private Long id;
	
	@Column(name = "LOGIN_ID")
    private String loginId;
}
