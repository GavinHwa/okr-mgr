package com.cmb.okr.frame.auth;

/**
 * 用户session信息
 * 
 * @author hf
 *
 */
public class SessionBean {

	// 用户信息
	private Object user;

	// 权限码
	private String authCodes;

	// 失效时间
	private long expire;

	public Object getUser() {
		return user;
	}

	public void setUser(Object user) {
		this.user = user;
	}

	public long getExpire() {
		return expire;
	}

	public void setExpire(long expire) {
		this.expire = expire + System.currentTimeMillis();
	}

	public String getAuthCodes() {
		return authCodes;
	}

	public void setAuthCodes(String authCodes) {
		this.authCodes = authCodes;
	}
}
