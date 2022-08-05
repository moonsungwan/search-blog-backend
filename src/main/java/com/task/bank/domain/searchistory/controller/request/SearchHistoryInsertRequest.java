package com.task.bank.domain.searchistory.controller.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SearchHistoryInsertRequest {

	/* 검색어 */
	@ApiParam(value = "searchWord", required = true)
	@Size(max = 10, message = "{validation.size.too_long} (10)")
	@NotBlank(message="{validation.notblank}")
	private String searchWord;
	
}
