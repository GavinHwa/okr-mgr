package com.cmb.okr.api.controller.home;

import com.cmb.okr.dao.domain.User;

public class LoginResponse {

	private String authCode;

	private String token;

	private User user;

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
