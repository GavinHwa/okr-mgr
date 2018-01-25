package com.cmb.okr.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.cmb.okr.dao.domain.auth.User;
import com.cmb.okr.frame.db.BaseDao;
import com.cmb.okr.frame.security.SecurityUtils;
import com.cmb.okr.frame.util.AppValidator;

@Component
public class AuthService {

	private static final String SQL_MAP_BASE = "com.cmb.okr.service.AuthService.";

	private static final String SELECT_AUTH_CODE_BY_USER = "selectAuthCodeByUser";

	@Autowired
	private BaseDao baseDao;

	/**
	 * 通过用户id获取用户权限码
	 * 
	 * @param userId
	 * @return
	 */
	public String getAuthCodes(String userId) {
		List<String> codeList = this.baseDao.queryForAll(SQL_MAP_BASE + SELECT_AUTH_CODE_BY_USER, userId);
		if (CollectionUtils.isEmpty(codeList)) {
			return null;
		}
		return StringUtils.join(codeList, ",");
	}

	public void changePwd(User user, String newPwd) {
		String salt = StringUtils.isEmpty(user.getSalt()) ? SecurityUtils.getSalt(user.getYstCode()) : user.getSalt();

		String pwd = SecurityUtils.getPassphrase(salt, newPwd);
		User entity = new User();
		entity.setId(user.getId());
		user.setSalt(salt);
		user.setPassword(pwd);
		this.updateUser(user);
	}

	public void updateUser(User user) {
		this.baseDao.update(user);
	}

	/**
	 * 验证用户登录
	 * 
	 * @param loginName
	 * @param password
	 * @return
	 */
	public User checkPassword(String loginName, String password) {
		User user = this.loadUser(loginName);
		if (user == null) {
			return null;
		}
		if (!SecurityUtils.matchPassphrase(user.getPassword(), user.getSalt(), password)) {
			return null;
		}
		return user;
	}

	/**
	 * 通过登录信息获取用户
	 * 
	 * 支持手机号和登录名登录
	 * 
	 * @param ystCode
	 * @return
	 */
	public User loadUser(String loginName) {
		// 以移事通账号为登录名查询用户
		User userQuery = new User();
		if (AppValidator.isMobile(loginName)) {
			userQuery.setPhone(loginName);
		} else {
			userQuery.setLoginName(loginName);
		}
		User u = baseDao.load(userQuery);
		return u;
	}

	public User loadUserById(String id) {
		User uq = new User();
		uq.setId(id);
		return baseDao.load(uq);
	}
}
