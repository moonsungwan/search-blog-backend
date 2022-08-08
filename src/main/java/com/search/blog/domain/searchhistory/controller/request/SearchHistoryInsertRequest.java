package com.search.blog.domain.searchhistory.controller.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SearchHistoryInsertRequest {

	/* ID */
	@ApiModelProperty(hidden = true)
	private Long id;
	
	/* 검색어 */
	@ApiParam(value = "searchWord", required = true)
	@Size(max = 50, message = "{validation.size.too_long} (50)")
	@NotBlank(message="{validation.notblank}")
	private String searchWord;
	
}
