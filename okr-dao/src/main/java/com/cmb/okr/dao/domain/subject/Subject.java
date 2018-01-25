package com.cmb.okr.dao.domain.subject;

import java.util.Date;

import com.cmb.okr.dao.subject.enums.SubjectStatus;
import com.cmb.okr.dao.subject.enums.SubjectType;
import com.cmb.okr.frame.db.BaseEntity;

/**
 * 该类是映射到表的实体类，负责与表进行一一对应。
 * @author: huang 
 * @date: 2018-01-24 01:02:22
 * @since 1.0.0
 */
public class Subject extends BaseEntity {
private static final long serialVersionUID = 1L;
	

	/**
	 * 课题名称
	 */
	private String name;

	/**
	 * 课题简介
	 */
	private String briefContent;

	/**
	 * 课题内容
	 */
	private String content;

	/**
	 * 课题图标
	 */
	private String icon;

	/**
	 * 课题负责人
	 */
	private String leaderId;

	/**
	 * 课题状态
            processing：进行中
            finished：已结束
            suspend：暂停
            cancel：取消
	 */
	private SubjectStatus status;

	/**
	 * 课题类型
            personal：个人
            geek：极客
            fresh：新员工
	 */
	private SubjectType type;

	/**
	 * 课题发起时间
	 */
	private Date startupDate;

	/**
	 * 课题创建人
	 */
	private String createId;

	/**
	 * 课题创建时间
	 */
	private Date createTime;


	/**
	 * 课题名称
	 */
	public void setName(String name){
		this.name = name;
	}
	
	/**
	 * 课题名称
	 */
	public String getName(){
		return  name;
	}

	/**
	 * 课题简介
	 */
	public void setBriefContent(String briefContent){
		this.briefContent = briefContent;
	}
	
	/**
	 * 课题简介
	 */
	public String getBriefContent(){
		return  briefContent;
	}

	/**
	 * 课题内容
	 */
	public void setContent(String content){
		this.content = content;
	}
	
	/**
	 * 课题内容
	 */
	public String getContent(){
		return  content;
	}

	/**
	 * 课题图标
	 */
	public void setIcon(String icon){
		this.icon = icon;
	}
	
	/**
	 * 课题图标
	 */
	public String getIcon(){
		return  icon;
	}

	/**
	 * 课题负责人
	 */
	public void setLeaderId(String leaderId){
		this.leaderId = leaderId;
	}
	
	/**
	 * 课题负责人
	 */
	public String getLeaderId(){
		return  leaderId;
	}

	/**
	 * 课题状态
            processing：进行中
            finished：已结束
            suspend：暂停
            cancel：取消
	 */
	public void setStatus(SubjectStatus status){
		this.status = status;
	}
	
	/**
	 * 课题状态
            processing：进行中
            finished：已结束
            suspend：暂停
            cancel：取消
	 */
	public SubjectStatus getStatus(){
		return  status;
	}

	/**
	 * 课题类型
            personal：个人
            geek：极客
            fresh：新员工
	 */
	public void setType(SubjectType type){
		this.type = type;
	}
	
	/**
	 * 课题类型
            personal：个人
            geek：极客
            fresh：新员工
	 */
	public SubjectType getType(){
		return  type;
	}

	/**
	 * 课题类型
	 */
	public void setStartupDate(Date startupDate){
		this.startupDate = startupDate;
	}
	
	/**
	 * 课题类型
	 */
	public Date getStartupDate(){
		return  startupDate;
	}

	/**
	 * 课题创建人
	 */
	public void setCreateId(String createId){
		this.createId = createId;
	}
	
	/**
	 * 课题创建人
	 */
	public String getCreateId(){
		return  createId;
	}

	/**
	 * 课题创建时间
	 */
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	
	/**
	 * 课题创建时间
	 */
	public Date getCreateTime(){
		return  createTime;
	}
}