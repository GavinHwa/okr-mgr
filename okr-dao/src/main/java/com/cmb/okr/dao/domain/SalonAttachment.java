package com.cmb.okr.dao.domain;

import com.cmb.okr.frame.db.BaseEntity;

/**
 * 该类是映射到表的实体类，负责与表进行一一对应。
 * @author: huang 
 * @date: 2018-01-12 09:22:24
 * @since 1.0.0
 */
public class SalonAttachment extends BaseEntity {
private static final long serialVersionUID = 1L;
	

	/**
	 * 技术沙龙ID
	 */
	private String salonId;

	/**
	 * 附件类型
	 */
	private String attachType;

	/**
	 * 附件名称
	 */
	private String attachName;

	/**
	 * 附件链接
	 */
	private String attachUrl;


	/**
	 * 技术沙龙ID
	 */
	public void setSalonId(String salonId){
		this.salonId = salonId;
	}
	
	/**
	 * 技术沙龙ID
	 */
	public String getSalonId(){
		return  salonId;
	}

	/**
	 * 附件类型
	 */
	public void setAttachType(String attachType){
		this.attachType = attachType;
	}
	
	/**
	 * 附件类型
	 */
	public String getAttachType(){
		return  attachType;
	}

	/**
	 * 附件名称
	 */
	public void setAttachName(String attachName){
		this.attachName = attachName;
	}
	
	/**
	 * 附件名称
	 */
	public String getAttachName(){
		return  attachName;
	}

	/**
	 * 附件链接
	 */
	public void setAttachUrl(String attachUrl){
		this.attachUrl = attachUrl;
	}
	
	/**
	 * 附件链接
	 */
	public String getAttachUrl(){
		return  attachUrl;
	}
}