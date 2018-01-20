package com.cmb.okr.api.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.cmb.okr.api.util.ContextUtils;
import com.cmb.okr.dao.domain.User;
import com.cmb.okr.dao.enums.UserType;
import com.cmb.okr.frame.auth.AuthRequired;
import com.cmb.okr.frame.auth.LoginRequired;
import com.cmb.okr.frame.auth.OkrContext;
import com.cmb.okr.frame.auth.SessionBean;
import com.cmb.okr.frame.config.OkrConfig;
import com.cmb.okr.frame.exception.AppException;
import com.cmb.okr.frame.util.SpringHelper;

public class AuthInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String token = ContextUtils.getToken(request);
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		boolean loginReuired = handlerMethod.getMethod().isAnnotationPresent(LoginRequired.class);
		boolean authRequired = handlerMethod.getMethod().isAnnotationPresent(AuthRequired.class);
		if (!loginReuired && !authRequired) {
			return true;
		}

		// 判断用户是否登录
		SessionBean session = OkrContext.sessions.get(token);
		if (session == null) {
			throw new AppException("用户未登录");
		}
		User user = (User) session.getUser();
		// 判断用户是否有权限
		if (authRequired && !UserType.admin.equals(user.getUserType())) {
			if (StringUtils.isEmpty(session.getAuthCodes())) {
				throw new AppException("无权限访问");
			}
			AuthRequired ar = handlerMethod.getBeanType().getAnnotation(AuthRequired.class);
			// 获取所需权限
			String[] auths = ar.value();
			for (String authCode : auths) {
				if (session.getAuthCodes().contains(authCode)) {
					return true;
				}
			}
			throw new AppException("无权限访问");
		}
		// 重新设置session的失效时间
		session.setExpire(SpringHelper.getBean(OkrConfig.class).getSessionExpireMills());
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
