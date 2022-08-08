package com.search.blog.domain.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.search.blog.domain.account.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

	Account findByLoginId(String loginId);
	
}