package com.task.bank.domain.user.controller.request;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRequest {

	/* 로그인 ID */
	@ApiParam(value = "id", required = true)
	@NotNull(message="{validation.notnull}")
	private final Long id;

	/* 비밀번호 */
	@ApiParam(value = "contractTerm", required = true)
	@NotNull(message="{validation.notnull}")
	private final String password;

}
