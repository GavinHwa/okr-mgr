package com.cmb.okr.dao.domain;

import com.cmb.okr.frame.db.BaseEntity;

/**
 * 该类是映射到表的实体类，负责与表进行一一对应。
 * @author: huang 
 * @date: 2018-01-12 09:22:49
 * @since 1.0.0
 */
public class SubjectAttachment extends BaseEntity {
private static final long serialVersionUID = 1L;
	

	/**
	 * 课题id
	 */
	private String subjectId;

	/**
	 * 附件类型
	 */
	private String attachType;

	/**
	 * 附件名称
	 */
	private String attachName;


	/**
	 * 课题id
	 */
	public void setSubjectId(String subjectId){
		this.subjectId = subjectId;
	}
	
	/**
	 * 课题id
	 */
	public String getSubjectId(){
		return  subjectId;
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
}