package com.cmb.okr.api.controller.subject.vo;

import java.util.Date;

import com.cmb.okr.dao.subject.enums.SubjectStatus;
import com.cmb.okr.dao.subject.enums.SubjectType;

public class SubjectResponse {

	private String id;

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

	private String iconUrl;

	/**
	 * 课题负责人
	 */
	private String leaderId;

	/**
	 * 负责人姓名
	 */
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
	private Date startupDate;

	/**
	 * 课题创建人
	 */
	private String createId;

	/**
	 * 课题创建时间
	 */
	private Date createTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getIconUrl() {
		return iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	public String getLeaderId() {
		return leaderId;
	}

	public void setLeaderId(String leaderId) {
		this.leaderId = leaderId;
	}

	public String getLeaderName() {
		return leaderName;
	}

	public void setLeaderName(String leaderName) {
		this.leaderName = leaderName;
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

	public Date getStartupDate() {
		return startupDate;
	}

	public void setStartupDate(Date startupDate) {
		this.startupDate = startupDate;
	}

	public String getCreateId() {
		return createId;
	}

	public void setCreateId(String createId) {
		this.createId = createId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
