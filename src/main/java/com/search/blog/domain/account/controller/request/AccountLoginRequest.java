package com.search.blog.domain.account.controller.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AccountLoginRequest {

	/* 로그인 ID */
	@ApiParam(value = "loginId", required = true)
	@Pattern(regexp = "[a-zA-Z1-9]{6,12}", message = "{validation.field.loginId}")
	@NotBlank(message="{validation.notblank} loginId")
	private String loginId;

	/* 비밀번호 */
	@ApiParam(value = "password", required = true)
	@Size(min = 4, max = 15, message = "{validation.field.password}")
	@NotBlank(message="{validation.notblank} password")
	private String password;

}
