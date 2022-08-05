package com.task.bank.domain.user.controller.response;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponse {

	/* 로그인 ID */
	private final Long id;

	/* 비밀번호 */
	private final String password;

}
