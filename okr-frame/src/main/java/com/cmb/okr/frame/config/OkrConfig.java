package com.cmb.okr.frame.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("okr.frame")
public class OkrConfig {
	/**
	 * session失效时间 秒默认30分钟
	 */
	private int sessionExpireSeconds = 60 * 30;

	/**
	 * 初始密码 默认123456
	 */
	private String initPassword = "123456";

	public long getSessionExpireMills() {
		return this.sessionExpireSeconds * 1000;
	}

	public int getSessionExpireSeconds() {
		return sessionExpireSeconds;
	}

	public void setSessionExpireSeconds(int sessionExpireSeconds) {
		this.sessionExpireSeconds = sessionExpireSeconds;
	}

	public String getInitPassword() {
		return initPassword;
	}

	public void setInitPassword(String initPassword) {
		this.initPassword = initPassword;
	}
}
