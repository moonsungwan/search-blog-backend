package com.task.bank.domain.bookmark.controller.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BookmarkBlogInsertRequest {

	/* 북마크 제목 */
	@ApiParam(value = "bookmarkTitle", required = true)
	@Size(max = 50, message = "{validation.size.too_long} (50)")
	@NotBlank(message="{validation.notblank}")
	private String bookmarkTitle;
	
	/* 북마크 URL */
	@ApiParam(value = "bookmarkUrl", required = true)
	@Size(max = 50, message = "{validation.size.too_long} (50)")
	@NotBlank(message="{validation.notblank}")
	private String bookmarkUrl;
	
}
