package com.search.blog.domain.account.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import com.search.blog.domain.account.entity.Account;
import com.search.blog.domain.account.repository.AccountRepository;
import com.search.blog.global.exception.CustomException;
import com.search.blog.global.message.MessageCode;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@DisplayName("1. 계정 인증")
class AccountserviceTest {

	@Autowired
	private AccountRepository accountRepository;

	private PasswordEncoder passwordEncoder;

	@BeforeEach
	void setUp() {
		this.passwordEncoder = new BCryptPasswordEncoder();
	}

	@Test
    @DisplayName("회원가입_성공")
	public void 회원가입_성공() {
		// given
		String loginId = "accountA";
		String password = "1234";

		Account account = Account.Insert()
								 .loginId(loginId)
								 .password(passwordEncoder.encode(password))
								 .nickName("문성완")
								 .build();

		// when
		Account saveAccount = accountRepository.save(account);

		// then
		assertNotNull(saveAccount);
	}

	@Test
    @DisplayName("로그인_성공")
	public void 계정_조회_성공() {
		// given
		String loginId = "accountA";
		String password = "1234";

		Account account = Account.Insert()
								 .loginId(loginId)
								 .password(passwordEncoder.encode(password))
								 .nickName("문성완")
								 .build();

		// when
		Account saveAccount = accountRepository.save(account);
		Account loginAccount = accountRepository.findByLoginId(loginId)
												.orElseThrow(() -> new CustomException(MessageCode.INVALID_USER));;

		// then
		assertNotNull(loginAccount);
		assertEquals(loginAccount.getLoginId(), saveAccount.getLoginId());
		assertTrue(passwordEncoder.matches(password, saveAccount.getPassword()));
	}

}
