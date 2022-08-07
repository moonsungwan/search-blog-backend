package com.search.blog.domain.blog.controller.response.kakao;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MetaResponse {
 
	private String total_count;
	
	private String pageable_count;
	
	private String is_end;
	
}
