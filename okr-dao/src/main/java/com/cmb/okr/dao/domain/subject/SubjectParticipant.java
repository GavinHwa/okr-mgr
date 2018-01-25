package com.cmb.okr.dao.domain.subject;

import com.cmb.okr.frame.db.BaseEntity;
import java.util.Date;
import java.util.Date;

/**
 * 该类是映射到表的实体类，负责与表进行一一对应。
 * @author: huang 
 * @date: 2018-01-25 09:36:24
 * @since 1.0.0
 */
public class SubjectParticipant extends BaseEntity {
private static final long serialVersionUID = 1L;
	

	/**
	 * 用户id
	 */
	private String userId;

	/**
	 * 用户名称
	 */
	private String userName;

	/**
	 * 课题id
	 */
	private String subjectId;

	/**
	 * 是否已退出
	 */
	private Integer isQuit;

	/**
	 * 参与时间
	 */
	private Date joinDate;

	/**
	 * 退出时间
	 */
	private Date quitDate;

	/**
	 * 
	 */
	private String quitRemark;


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
	 * 用户名称
	 */
	public void setUserName(String userName){
		this.userName = userName;
	}
	
	/**
	 * 用户名称
	 */
	public String getUserName(){
		return  userName;
	}

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
	 * 是否已退出
	 */
	public void setIsQuit(Integer isQuit){
		this.isQuit = isQuit;
	}
	
	/**
	 * 是否已退出
	 */
	public Integer getIsQuit(){
		return  isQuit;
	}

	/**
	 * 参与时间
	 */
	public void setJoinDate(Date joinDate){
		this.joinDate = joinDate;
	}
	
	/**
	 * 参与时间
	 */
	public Date getJoinDate(){
		return  joinDate;
	}

	/**
	 * 退出时间
	 */
	public void setQuitDate(Date quitDate){
		this.quitDate = quitDate;
	}
	
	/**
	 * 退出时间
	 */
	public Date getQuitDate(){
		return  quitDate;
	}

	/**
	 * 
	 */
	public void setQuitRemark(String quitRemark){
		this.quitRemark = quitRemark;
	}
	
	/**
	 * 
	 */
	public String getQuitRemark(){
		return  quitRemark;
	}
}