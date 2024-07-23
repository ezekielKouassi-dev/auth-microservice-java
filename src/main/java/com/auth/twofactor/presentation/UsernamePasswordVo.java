package com.auth.twofactor.presentation;

public class UsernamePasswordVo {

	private String username;
	private String password;

	public UsernamePasswordVo() {
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
