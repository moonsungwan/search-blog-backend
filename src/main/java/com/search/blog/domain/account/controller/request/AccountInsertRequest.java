package com.search.blog.domain.account.controller.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AccountInsertRequest {

	/* 로그인 ID */
	@ApiParam(value = "loginId", required = true)
	@Size(max = 15, message = "{validation.size.too_long} (15)")
	@NotBlank(message="{validation.notblank}")
	private String loginId;

	/* 비밀번호 */
	@ApiParam(value = "password", required = true)
	@NotBlank(message="password {validation.notblank}")
	private String password;

	/* 닉네임 */
	@ApiParam(value = "nickName", required = true)
	@Size(max = 15, message = "{validation.size.too_long} (15)")
	@NotBlank(message="nickName {validation.notblank}")
	private String nickName;

}
