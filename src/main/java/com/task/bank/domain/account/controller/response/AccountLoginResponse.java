package com.task.bank.domain.account.controller.response;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AccountLoginResponse {

	/* 로그인 ID */
	private String loginId;
	
	/* 토큰 */
	private String accessToken;

}
