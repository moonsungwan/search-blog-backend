package com.task.bank.domain.bookmark.controller.response;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BookmarkBlogResponse {
 
	private Long id;

	private String loginId;

	private String bookmarkTitle;
	
	private String bookmarkUrl;
	
}
