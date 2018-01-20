package com.cmb.okr.dao.domain;

import com.cmb.okr.frame.db.BaseEntity;

/**
 * 该类是映射到表的实体类，负责与表进行一一对应。
 * @author: huang 
 * @date: 2018-01-19 08:22:31
 * @since 1.0.0
 */
public class Auth extends BaseEntity {
private static final long serialVersionUID = 1L;
	

	/**
	 * 权限码
	 */
	private String authCode;

	/**
	 * 权限描述
	 */
	private String authDescp;


	/**
	 * 权限码
	 */
	public void setAuthCode(String authCode){
		this.authCode = authCode;
	}
	
	/**
	 * 权限码
	 */
	public String getAuthCode(){
		return  authCode;
	}

	/**
	 * 权限描述
	 */
	public void setAuthDescp(String authDescp){
		this.authDescp = authDescp;
	}
	
	/**
	 * 权限描述
	 */
	public String getAuthDescp(){
		return  authDescp;
	}
}