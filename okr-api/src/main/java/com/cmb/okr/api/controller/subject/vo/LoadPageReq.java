package com.cmb.okr.api.controller.subject.vo;

import java.util.Date;

import com.cmb.okr.dao.subject.enums.SubjectStatus;
import com.cmb.okr.dao.subject.enums.SubjectType;

public class LoadPageReq {

	/**
	 * 课题名称
	 */
	private String name;

	/**
	 * 课题简介
	 */
	private String briefContent;

	/**
	 * 课题负责人
	 */
	private String leaderId;
	
	private String leaderName;

	/**
	 * 课题状态 processing：进行中 finished：已结束 suspend：暂停 cancel：取消
	 */
	private SubjectStatus status;

	/**
	 * 课题类型 personal：个人 geek：极客 fresh：新员工
	 */
	private SubjectType type;

	/**
	 * 课题发起时间
	 */
	private Date startupDateBegin;

	private Date startupDateEnd;

	/**
	 * 课题创建人
	 */
	private String createId;

	/**
	 * 课题创建时间
	 */
	private Date createTimeBegin;

	private Date createTimeEnd;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBriefContent() {
		return briefContent;
	}

	public void setBriefContent(String briefContent) {
		this.briefContent = briefContent;
	}

	public String getLeaderId() {
		return leaderId;
	}

	public void setLeaderId(String leaderId) {
		this.leaderId = leaderId;
	}

	public SubjectStatus getStatus() {
		return status;
	}

	public void setStatus(SubjectStatus status) {
		this.status = status;
	}

	public SubjectType getType() {
		return type;
	}

	public void setType(SubjectType type) {
		this.type = type;
	}

	public Date getStartupDateBegin() {
		return startupDateBegin;
	}

	public void setStartupDateBegin(Date startupDateBegin) {
		this.startupDateBegin = startupDateBegin;
	}

	public Date getStartupDateEnd() {
		return startupDateEnd;
	}

	public void setStartupDateEnd(Date startupDateEnd) {
		this.startupDateEnd = startupDateEnd;
	}

	public String getCreateId() {
		return createId;
	}

	public void setCreateId(String createId) {
		this.createId = createId;
	}

	public Date getCreateTimeBegin() {
		return createTimeBegin;
	}

	public void setCreateTimeBegin(Date createTimeBegin) {
		this.createTimeBegin = createTimeBegin;
	}

	public Date getCreateTimeEnd() {
		return createTimeEnd;
	}

	public void setCreateTimeEnd(Date createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}

	public String getLeaderName() {
		return leaderName;
	}

	public void setLeaderName(String leaderName) {
		this.leaderName = leaderName;
	}
}
