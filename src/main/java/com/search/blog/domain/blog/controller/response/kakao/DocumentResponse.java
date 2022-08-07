package com.search.blog.domain.blog.controller.response.kakao;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DocumentResponse {
 
	private String title;
	
	private String contents;
	
	private String url;
	
	private String blogname;
	
	private String thumbnail;
	
	private String datetime;
	
}
