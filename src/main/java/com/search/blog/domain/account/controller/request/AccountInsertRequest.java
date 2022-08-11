package com.search.blog.domain.account.controller.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiParam;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AccountInsertRequest {

	/* 로그인 ID */
	@ApiParam(value = "loginId", required = true)
	@Pattern(regexp = "[a-z1-9]{6,12}", message = "{validation.field.loginId} (loginId)")
	@NotBlank(message = "{validation.notblank} loginId")
	private String loginId;

	/* 비밀번호 */
	@ApiParam(value = "password", required = true)
	@Size(min = 4, max = 15, message = "{validation.field.password} (password)")
	@NotBlank(message="{validation.notblank} password")
	private String password;

	/* 닉네임 */
	@ApiParam(value = "nickName", required = true)
	@Size(max = 15, message = "{validation.size.too_long} (15)")
	@NotBlank(message = "{validation.notblank} nickName")
	private String nickName;

	@Builder
	public AccountInsertRequest(String loginId, String password, String nickName) {
		this.loginId = loginId;
		this.password = password;
		this.nickName = nickName;
	}

}
