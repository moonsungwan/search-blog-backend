package com.search.blog.domain.bookmark.controller.request;

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
public class BookmarkBlogInsertRequest {

	/* 로그인 ID */
	@ApiModelProperty(hidden = true)
	private String loginId;

	/* 북마크 제목 */
	@ApiParam(value = "bookmarkTitle", required = true)
	@Size(max = 500, message = "{validation.size.too_long} (50)")
	@NotBlank(message="{validation.notblank} bookmarkTitle")
	private String bookmarkTitle;

	/* 북마크 URL */
	@ApiParam(value = "bookmarkUrl", required = true)
	@Size(max = 500, message = "{validation.size.too_long} (50)")
	@NotBlank(message="{validation.notblank} bookmarkUrl")
	private String bookmarkUrl;

}
