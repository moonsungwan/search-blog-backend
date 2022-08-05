package com.task.bank.domain.user.controller.request;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserLoginRequest {

	/* 로그인 ID */
	@ApiParam(value = "loginId", required = true)
	@NotBlank(message="{validation.notblank}")
	private String loginId;

	/* 비밀번호 */
	@ApiParam(value = "password", required = true)
	@NotBlank(message="{validation.notblank}")
	private String password;

}
