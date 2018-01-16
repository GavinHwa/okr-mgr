package com.cmb.okr.dao.domain;

import com.cmb.okr.frame.db.BaseEntity;
import java.util.Date;

/**
 * 该类是映射到表的实体类，负责与表进行一一对应。
 * @author: huang 
 * @date: 2018-01-12 09:22:36
 * @since 1.0.0
 */
public class SubjectMember extends BaseEntity {
private static final long serialVersionUID = 1L;
	

	/**
	 * 课题ID
	 */
	private String subjectId;

	/**
	 * 成员id
	 */
	private String memId;

	/**
	 * 加入时间
	 */
	private Date joinDate;

	/**
	 * 身份类型
	 */
	private String memType;


	/**
	 * 课题ID
	 */
	public void setSubjectId(String subjectId){
		this.subjectId = subjectId;
	}
	
	/**
	 * 课题ID
	 */
	public String getSubjectId(){
		return  subjectId;
	}

	/**
	 * 成员id
	 */
	public void setMemId(String memId){
		this.memId = memId;
	}
	
	/**
	 * 成员id
	 */
	public String getMemId(){
		return  memId;
	}

	/**
	 * 加入时间
	 */
	public void setJoinDate(Date joinDate){
		this.joinDate = joinDate;
	}
	
	/**
	 * 加入时间
	 */
	public Date getJoinDate(){
		return  joinDate;
	}

	/**
	 * 身份类型
	 */
	public void setMemType(String memType){
		this.memType = memType;
	}
	
	/**
	 * 身份类型
	 */
	public String getMemType(){
		return  memType;
	}
}