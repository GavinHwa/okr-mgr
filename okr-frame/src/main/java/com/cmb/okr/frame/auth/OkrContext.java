package com.cmb.okr.frame.auth;

import java.util.concurrent.ConcurrentHashMap;

public class OkrContext {
	// 用户session信息
	public static ConcurrentHashMap<String, SessionBean> sessions = new ConcurrentHashMap<>();

}
