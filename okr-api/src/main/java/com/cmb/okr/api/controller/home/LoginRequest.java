package com.cmb.okr.api.controller.home;

/**
 * 登录请求参数
 * 
 * @author hf
 *
 */
public class LoginRequest {

	/**
	 * 用户名
	 */
	private String loginName;

	/**
	 * 密码
	 */
	private String password;

	/**
	 * 验证码
	 */
	private String captcha;

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}
}
