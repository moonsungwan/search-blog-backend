package com.task.bank.domain.user.service;

import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.task.bank.domain.user.controller.request.UserCreateRequest;
import com.task.bank.domain.user.controller.request.UserLoginRequest;
import com.task.bank.domain.user.controller.response.UserResponse;
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

	private final UserRepository userRepository;
	
    private final PasswordEncoder passwordEncoder;

	@Transactional
	public ApiResponseEntity<UserResponse> signUp(UserCreateRequest userCreateRequest) {
		checkDuplication(userCreateRequest);

		User user = User.CreateUser()
						.loginId(userCreateRequest.getLoginId())
						.password(passwordEncoder.encode(userCreateRequest.getPassword()))
						.nickName(userCreateRequest.getNickName())
						.build();
		
		userRepository.save(user);

		return new ApiResponseEntity<UserResponse>(MessageCode.REGISTERED);
	}

	public ApiResponseEntity<String> login(UserLoginRequest userLoginRequest) {
		User selectMember = getUser(userRepository.findByLoginId(userLoginRequest.getLoginId()));
		
        if (!passwordEncoder.matches(userLoginRequest.getPassword(), selectMember.getPassword())) {
        	throw new CustomException(MessageCode.INVALID_PASSWORD);
        }

        Authentication authentication = new UserAuthentication(userLoginRequest.getLoginId(), null, null);
        return new ApiResponseEntity<String>(JwtTokenProvider.generateToken(authentication));
	}
	
	private void checkDuplication(UserCreateRequest userRequest) {
		if (!userRepository.findByLoginId(userRequest.getLoginId()).isEmpty()) {
			throw new CustomException(MessageCode.MEMBER_EXISTING);
		}
	}
	
	private User getUser(Optional<User> user) throws CustomException {
		return user.orElseThrow(() -> new CustomException(MessageCode.NO_CONTENT));
	}

}