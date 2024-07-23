package com.auth.twofactor.presentation;

public class GenerationCodeOTPVo {

	private String email;

	public GenerationCodeOTPVo() {}

	public GenerationCodeOTPVo(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
