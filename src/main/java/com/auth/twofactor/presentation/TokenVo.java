package com.auth.twofactor.presentation;

public class TokenVo {

	private String token;

	public TokenVo() {
	}

	public TokenVo(String token) {
		this.token = token;
	}

	public String getToken() {
		return token;
	}
}
