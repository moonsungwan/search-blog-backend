package com.search.blog.domain.account.controller.response;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AccountLoginResponse {

	/* 로그인 ID */
	private String loginId;
	
	/* 이름 */
	private String nickName;
	
	/* 토큰 */
	private String accessToken;

}
