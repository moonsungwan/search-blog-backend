package com.task.bank.domain.account.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.task.bank.domain.account.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

	Optional<Account> findByLoginId(String loginId);
	
}