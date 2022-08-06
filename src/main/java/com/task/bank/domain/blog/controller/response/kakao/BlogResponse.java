package com.task.bank.domain.blog.controller.response.kakao;


import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BlogResponse {
 
	private MetaResponse meta;
	
	private List<DocumentResponse> documents;
	
}
