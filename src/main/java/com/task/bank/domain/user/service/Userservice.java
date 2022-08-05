package com.task.bank.domain.user.service;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.task.bank.domain.user.controller.request.UserInsertRequest;
import com.task.bank.domain.user.controller.request.UserLoginRequest;
import com.task.bank.domain.user.controller.response.UserLoginResponse;
import com.task.bank.domain.user.entity.User;
import com.task.bank.domain.user.repository.UserRepository;
import com.task.bank.global.entity.ApiResponseEntity;
import com.task.bank.global.exception.CustomException;
import com.task.bank.global.message.MessageCode;
import com.task.bank.global.security.JwtTokenProvider;
import com.task.bank.global.security.UserAuthentication;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class Userservice {

	private final PasswordEncoder passwordEncoder;
	
	private final UserRepository userRepository;
	
	@Transactional
	public ApiResponseEntity<UserLoginResponse> signUp(UserInsertRequest userInsertRequest) {
		checkDuplication(userInsertRequest);

		User user = User.InsertUser()
						.loginId(userInsertRequest.getLoginId())
						.password(passwordEncoder.encode(userInsertRequest.getPassword()))
						.nickName(userInsertRequest.getNickName())
						.build();
		
		userRepository.save(user);

		return new ApiResponseEntity<UserLoginResponse>(createUserLoginResponse(user.getLoginId()));
	}

	@Transactional(readOnly = true)
	public ApiResponseEntity<UserLoginResponse> login(UserLoginRequest userLoginRequest) {
		User selectMember = getUser(userRepository.findByLoginId(userLoginRequest.getLoginId()));
		
        if (!passwordEncoder.matches(userLoginRequest.getPassword(), selectMember.getPassword())) {
        	throw new CustomException(MessageCode.INVALID_PASSWORD);
        }

        return new ApiResponseEntity<UserLoginResponse>(createUserLoginResponse(userLoginRequest.getLoginId()));
	}
	
	public ApiResponseEntity<Boolean> logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if (authentication != null) {
			new SecurityContextLogoutHandler().logout(request, response, authentication);
		}
		
		return new ApiResponseEntity<Boolean>(true, MessageCode.LOGOUT);
	}
	
	private UserLoginResponse createUserLoginResponse(String loginId) {
        Authentication authentication = new UserAuthentication(loginId, null);
        
		return UserLoginResponse.builder()
							 	.loginId(loginId)
							 	.accessToken(JwtTokenProvider.generateToken(authentication))
							 	.build();
	}
	
	private void checkDuplication(UserInsertRequest userRequest) {
		if (!userRepository.findByLoginId(userRequest.getLoginId()).isEmpty()) {
			throw new CustomException(MessageCode.MEMBER_EXISTING);
		}
	}
	
	private User getUser(Optional<User> user) throws CustomException {
		return user.orElseThrow(() -> new CustomException(MessageCode.NO_CONTENT));
	}

}