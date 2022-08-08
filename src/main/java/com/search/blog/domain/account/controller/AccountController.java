package com.search.blog.domain.account.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.search.blog.domain.account.controller.request.AccountInsertRequest;
import com.search.blog.domain.account.controller.request.AccountLoginRequest;
import com.search.blog.domain.account.controller.response.AccountLoginResponse;
import com.search.blog.domain.account.service.Accountservice;
import com.search.blog.global.entity.ApiResponseEntity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@Api(value = "AccountController", tags = "1. 계정 - 인증 API")
@RequiredArgsConstructor
@RequestMapping("/api/v1/account")
public class AccountController {

	private final Accountservice userservice;
	
	@ApiOperation(value = "회원가입", notes="회원가입")
	@PostMapping("/sign-up")
	public ApiResponseEntity<AccountLoginResponse> signUp(@Valid @RequestBody AccountInsertRequest userRequest)   {
		return userservice.signUp(userRequest);
	}

	@ApiOperation(value = "로그인", notes="로그인")
	@PostMapping("/login")
	public ApiResponseEntity<AccountLoginResponse> login(@Valid @RequestBody AccountLoginRequest userLoginRequest)   {
		return userservice.login(userLoginRequest);
	}
	
	@ApiOperation(value = "로그아웃", notes="로그아웃")
	@PostMapping("/logout")
	public ApiResponseEntity<Boolean> logout(HttpServletRequest request, HttpServletResponse response) {
		return userservice.logout(request, response);
	}
	
}
