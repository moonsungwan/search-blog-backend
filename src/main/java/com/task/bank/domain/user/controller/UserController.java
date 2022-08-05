package com.task.bank.domain.user.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.task.bank.domain.user.controller.request.UserCreateRequest;
import com.task.bank.domain.user.controller.request.UserLoginRequest;
import com.task.bank.domain.user.controller.response.UserLoginResponse;
import com.task.bank.domain.user.service.Userservice;
import com.task.bank.global.entity.ApiResponseEntity;
import com.task.bank.global.message.MessageCode;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@Api(value = "MemberController", tags = "1.인증 - 인증 API")
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

	private final Userservice userservice;
	
	@ApiOperation(value = "회원가입", notes="회원가입")
	@PostMapping("/sign-up")
	public ApiResponseEntity<UserLoginResponse> signUp(@Valid @RequestBody UserCreateRequest userRequest)   {
		return userservice.signUp(userRequest);
	}

	@ApiOperation(value = "로그인", notes="로그인")
	@PostMapping("/login")
	public ApiResponseEntity<UserLoginResponse> login(@Valid @RequestBody UserLoginRequest userLoginRequest)   {
		return userservice.login(userLoginRequest);
	}
	
	@ApiOperation(value = "로그아웃", notes="로그아웃")
	@PostMapping("/logout")
	public ApiResponseEntity<Boolean> logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null) {
			new SecurityContextLogoutHandler().logout(request, response, authentication);
		}

		return new ApiResponseEntity<Boolean>(true, MessageCode.LOGOUT);
	}
	
}
