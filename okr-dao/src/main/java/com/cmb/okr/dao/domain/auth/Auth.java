package com.cmb.okr.dao.domain.auth;

import com.cmb.okr.frame.db.BaseEntity;
import java.util.Date;


/**
 * 该类是映射到表的实体类，负责与表进行一一对应。
 * @author: huang
 * @date: 2018-02-21 03:20:31
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
	 *
	 */
	private String createId;

	/**
	 *
	 */
	private Date createTime;

	/**
	 *
	 */
	private String lastModifiedId;

	/**
	 *
	 */
	private Date lastModifiedTime;


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

	/**
	 *
	 */
	public void setCreateId(String createId){
		this.createId = createId;
	}

	/**
	 *
	 */
	public String getCreateId(){
		return  createId;
	}

	/**
	 *
	 */
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}

	/**
	 *
	 */
	public Date getCreateTime(){
		return  createTime;
	}

	/**
	 *
	 */
	public void setLastModifiedId(String lastModifiedId){
		this.lastModifiedId = lastModifiedId;
	}

	/**
	 *
	 */
	public String getLastModifiedId(){
		return  lastModifiedId;
	}

	/**
	 *
	 */
	public void setLastModifiedTime(Date lastModifiedTime){
		this.lastModifiedTime = lastModifiedTime;
	}

	/**
	 *
	 */
	public Date getLastModifiedTime(){
		return  lastModifiedTime;
	}
}