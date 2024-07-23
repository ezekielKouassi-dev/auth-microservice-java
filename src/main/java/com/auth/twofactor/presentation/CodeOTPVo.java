package com.auth.twofactor.presentation;

public class CodeOTPVo {

	private String code;

	public CodeOTPVo() {}

	public CodeOTPVo(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
