package com.task.bank.domain.user.controller.response;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserLoginResponse {

	/* 로그인 ID */
	private String loginId;
	
	/* 토큰 */
	private String accessToken;

}
