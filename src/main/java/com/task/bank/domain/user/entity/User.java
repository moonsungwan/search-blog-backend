package com.task.bank.domain.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "USER")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

	/* 로그인 ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LOG_ID")
    private Long loginId;

    /* 비밀번호 */
    @Column(name = "PASSWORD")
    private String password;

}
