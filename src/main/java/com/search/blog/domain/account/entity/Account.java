package com.search.blog.domain.account.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.search.blog.global.entity.BaseEntity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "ACCOUNT")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
    /* 로그인 ID */
    @Column(name = "LOGIN_ID", nullable = false)
    private String loginId;

    /* 비밀번호 */
    @Column(name = "PASSWORD", nullable = false)
    private String password;
    
    /* 닉네임 */
    @Column(name = "NICK_NAME", nullable = false)
    private String nickName;

    @Builder(builderClassName = "Insert", builderMethodName = "Insert")
    public Account(String loginId, String password, String nickName) {
        this.loginId = loginId;
        this.password = password;
        this.nickName = nickName;
    }
    
}
