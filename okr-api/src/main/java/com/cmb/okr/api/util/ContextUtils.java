package com.cmb.okr.api.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.cmb.okr.api.constants.ApiConstants;
import com.cmb.okr.dao.domain.auth.User;
import com.cmb.okr.frame.auth.OkrContext;
import com.cmb.okr.frame.auth.SessionBean;

public class ContextUtils {

	/**
	 * 获取token
	 * 
	 * @param request
	 * @return
	 */
	public static String getToken(HttpServletRequest request) {
		return StringUtils.defaultString(request.getHeader(ApiConstants.TOKEN_HEADER));
	}

	/**
	 * 获取当前登录用户
	 * 
	 * @param request
	 * @return
	 */
	public static User getCurrentUser(HttpServletRequest request) {
		String token = getToken(request);
		return getCurrentUser(token);
	}

	/**
	 * 获取当前登录用户
	 * 
	 * @param token
	 * @return
	 */
	public static User getCurrentUser(String token) {
		if (StringUtils.isEmpty(token)) {
			return null;
		}
		SessionBean session = OkrContext.sessions.get(token);
		if (session == null) {
			return null;
		}
		return (User) session.getUser();
	}

	/**
	 * 注销
	 * 
	 * @param request
	 */
	public static void logOut(HttpServletRequest request) {
		String token = getToken(request);
		if (StringUtils.isEmpty(token)) {
			return;
		}
		OkrContext.sessions.remove(token);
	}

}
