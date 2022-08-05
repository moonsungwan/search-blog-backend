package com.task.bank.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.task.bank.domain.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
