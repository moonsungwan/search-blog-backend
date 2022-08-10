package com.search.blog.domain.searchhistory.controller.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SearchHistoryInsertRequest {

	/* 검색어 */
	@ApiParam(value = "searchWord", required = true)
	@Size(max = 50, message = "{validation.size.too_long} (50)")
	@Pattern(regexp = "^[ㄱ-ㅎㅏ-ㅣ가-힣0-9a-zA-Z-_:,.' ']{1,50}$", message = "{validation.field.searchWord}")
	@NotBlank(message="{validation.notblank} searchWord")
	private String searchWord;

}
