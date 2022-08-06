package com.task.bank.domain.blog.controller.response.naver;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BlogResponse {
 
	private Long id;

	private String bookmarkTitle;
	
	private String bookmarkUrl;
	
}
