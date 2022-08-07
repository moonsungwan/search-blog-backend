package com.search.blog.domain.bookmark.controller.response;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BookmarkBlogResponse {
 
	private Long id;

	private String bookmarkTitle;
	
	private String bookmarkUrl;
	
}
