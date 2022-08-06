package com.task.bank.domain.account.service;

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

import com.task.bank.domain.account.controller.request.AccountInsertRequest;
import com.task.bank.domain.account.controller.request.AccountLoginRequest;
import com.task.bank.domain.account.controller.response.AccountLoginResponse;
import com.task.bank.domain.account.entity.Account;
import com.task.bank.domain.account.repository.AccountRepository;
import com.task.bank.global.entity.ApiResponseEntity;
import com.task.bank.global.exception.CustomException;
import com.task.bank.global.message.MessageCode;
import com.task.bank.global.security.JwtTokenProvider;
import com.task.bank.global.security.UserAuthentication;

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
		Account selectAccount = getAccount(accountRepository.findByLoginId(accountLoginRequest.getLoginId()));
		
        if (!passwordEncoder.matches(accountLoginRequest.getPassword(), selectAccount.getPassword())) {
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