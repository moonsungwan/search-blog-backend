package com.task.bank.global.security;

import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class UserAuthentication extends UsernamePasswordAuthenticationToken {

	private static final long serialVersionUID = -5545601893399772599L;

	public UserAuthentication(String principal, String credentials) {
        super(principal, credentials);
    }

    public UserAuthentication(String principal, String credentials, List<GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }
    
}