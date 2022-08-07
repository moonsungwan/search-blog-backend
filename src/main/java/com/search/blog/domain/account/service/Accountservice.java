package com.search.blog.domain.account.service;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.search.blog.domain.account.controller.request.AccountInsertRequest;
import com.search.blog.domain.account.controller.request.AccountLoginRequest;
import com.search.blog.domain.account.controller.response.AccountLoginResponse;
import com.search.blog.domain.account.entity.Account;
import com.search.blog.domain.account.repository.AccountRepository;
import com.search.blog.global.entity.ApiResponseEntity;
import com.search.blog.global.exception.CustomException;
import com.search.blog.global.message.MessageCode;
import com.search.blog.global.security.JwtTokenProvider;
import com.search.blog.global.security.UserAuthentication;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class Accountservice {

	private final PasswordEncoder passwordEncoder;
	
	private final AccountRepository accountRepository;
	
	@Transactional
	public ApiResponseEntity<AccountLoginResponse> signUp(AccountInsertRequest accountInsertRequest) {
		checkDuplication(accountInsertRequest);

		Account account = Account.Insert()
						.loginId(accountInsertRequest.getLoginId())
						.password(passwordEncoder.encode(accountInsertRequest.getPassword()))
						.nickName(accountInsertRequest.getNickName())
						.build();
		
		accountRepository.save(account);

		return new ApiResponseEntity<AccountLoginResponse>(createAccountLoginResponse(account.getLoginId()));
	}

	@Transactional(readOnly = true)
	public ApiResponseEntity<AccountLoginResponse> login(AccountLoginRequest accountLoginRequest) {
		Account findAccount = getAccount(accountRepository.findByLoginId(accountLoginRequest.getLoginId()));
		
        if (!passwordEncoder.matches(accountLoginRequest.getPassword(), findAccount.getPassword())) {
        	throw new CustomException(MessageCode.INVALID_PASSWORD);
        }

        return new ApiResponseEntity<AccountLoginResponse>(createAccountLoginResponse(accountLoginRequest.getLoginId()));
	}
	
	public ApiResponseEntity<Boolean> logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if (!ObjectUtils.isEmpty(authentication)) {
			new SecurityContextLogoutHandler().logout(request, response, authentication);
		}
		
		return new ApiResponseEntity<Boolean>(true, MessageCode.LOGOUT);
	}
	
	private AccountLoginResponse createAccountLoginResponse(String loginId) {
        Authentication authentication = new UserAuthentication(loginId, null);
        
		return AccountLoginResponse.builder()
							 	   .loginId(loginId)
							 	   .accessToken(JwtTokenProvider.generateToken(authentication))
							 	   .build();
	}
	
	private void checkDuplication(AccountInsertRequest accountInsertRequest) {
		if (!ObjectUtils.isEmpty(accountRepository.findByLoginId(accountInsertRequest.getLoginId()))) {
			throw new CustomException(MessageCode.MEMBER_EXISTING);
		}
	}
	
	private Account getAccount(Optional<Account> account) {
		return account.orElseThrow(() -> new CustomException(MessageCode.NO_CONTENT));
	}

}