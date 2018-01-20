package com.cmb.okr.api.controller.home;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.druid.util.StringUtils;
import com.cmb.okr.api.BaseController;
import com.cmb.okr.api.util.ContextUtils;
import com.cmb.okr.dao.domain.User;
import com.cmb.okr.frame.auth.LoginRequired;
import com.cmb.okr.frame.auth.OkrContext;
import com.cmb.okr.frame.auth.SessionBean;
import com.cmb.okr.frame.base.JsonResponse;
import com.cmb.okr.frame.config.OkrConfig;
import com.cmb.okr.frame.util.ObjectConvert;
import com.cmb.okr.service.AuthService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@Api("基础接口")
@RequestMapping("/")
@RestController
public class HomeController extends BaseController {

	@Autowired
	private AuthService authService;

	@Autowired
	private OkrConfig OkrConfig;

	/**
	 * 用户登录
	 * 
	 * @param request
	 * @return
	 */
	@ApiOperation(value = "登录", notes = "")
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public JsonResponse login(@RequestBody LoginRequest request) {
		return doBusiness((res) -> {
			// 判断用户名密码
			User user = authService.checkPassword(request.getLoginName(), request.getPassword());
			if (user == null) {
				res.setCode("401");
				res.setMsg("用户名或密码错误");
				return;
			}
			SessionBean session = new SessionBean();
			session.setUser(user);
			String authCodes = authService.getAuthCodes(user.getId());
			session.setAuthCodes(authCodes);
			session.setExpire(OkrConfig.getSessionExpireMills());
			// 登录成功保存用户登录状态
			String token = UUID.randomUUID().toString().concat(user.getYstCode());
			OkrContext.sessions.put(token, session);
			// 生成token
			LoginResponse response = new LoginResponse();
			response.setAuthCode(authCodes);
			response.setToken(token);
			User u = ObjectConvert.convert(user, User.class);
			u.setPassword(null);
			u.setSalt(null);
			response.setUser(u);
			res.setResult(response);
		}, "登录异常");
	}

	/**
	 * 验证用户是否已经登录
	 * 
	 * @param request
	 * @return
	 */
	@ApiOperation(value = "验证用户是否登录", notes = "")
	@RequestMapping(value = "/checkLogin", method = RequestMethod.GET)
	@ApiImplicitParam(value = "okraccesstoken", paramType = "header", required = false, name = "okraccesstoken")
	public JsonResponse checkLogin(HttpServletRequest request) {
		return doBusiness((res) -> {
			String token = ContextUtils.getToken(request);
			if (OkrContext.sessions.get(token) != null) {
				res.setResult(true);
			} else {
				res.setResult(false);
			}
		}, "验证用户是否登录失败");
	}

	// 修改密码
	@ApiOperation(value = "修改密码", notes = "")
	@ApiImplicitParam(value = "okraccesstoken", paramType = "header", required = true, name = "okraccesstoken")
	@LoginRequired
	@RequestMapping(value = "/changepwd", method = RequestMethod.POST)
	public JsonResponse changePassword(HttpServletRequest request, @RequestBody ChangePasswordRequest prequest) {
		return doBusiness((res) -> {
			User user = ContextUtils.getCurrentUser(request);
			if (!StringUtils.isEmpty(user.getSalt())) {
				// 如果用户没有设置密码则表示用户是首次登录修改密码
				if (authService.checkPassword(user.getYstCode(), prequest.getOldPossword()) == null) {
					res.setCode("401");
					res.setMsg("原密码错误!");
					return;
				}
			}

			this.authService.changePwd(user, prequest.getNewPassword());

		}, "修改密码失败");
	}

	// @AuthRequired("a001")
	@ApiOperation(value = "重置密码", notes = "")
	@ApiImplicitParam(paramType = "path", required = true, name = "userId")
	@RequestMapping(value = "/resetpwd/{userId}", method = RequestMethod.POST)
	public JsonResponse resetPassword(HttpServletRequest request, @PathVariable("userId") String userId) {
		return doBusiness((res) -> {
			String defaultPwd = OkrConfig.getInitPassword();
			User user = authService.loadUserById(userId);
			if (user == null) {
				res.setCode("500");
				res.setMsg("用户不存在");
				return;
			}
			this.authService.changePwd(user, defaultPwd);

		}, "密码重置失败");
	}
	// 修改头像 TODO

}
