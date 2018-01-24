package com.cmb.okr.dao.domain.auth;

import com.cmb.okr.frame.db.BaseEntity;

/**
 * 该类是映射到表的实体类，负责与表进行一一对应。
 * @author: huang 
 * @date: 2018-01-19 08:22:31
 * @since 1.0.0
 */
public class UserAuth extends BaseEntity {
private static final long serialVersionUID = 1L;
	

	/**
	 * 用户id
	 */
	private String userId;

	/**
	 * 权限id
	 */
	private String authId;


	/**
	 * 用户id
	 */
	public void setUserId(String userId){
		this.userId = userId;
	}
	
	/**
	 * 用户id
	 */
	public String getUserId(){
		return  userId;
	}

	/**
	 * 权限id
	 */
	public void setAuthId(String authId){
		this.authId = authId;
	}
	
	/**
	 * 权限id
	 */
	public String getAuthId(){
		return  authId;
	}
}