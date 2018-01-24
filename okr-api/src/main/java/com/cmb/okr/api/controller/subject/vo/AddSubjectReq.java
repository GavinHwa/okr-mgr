package com.cmb.okr.api.controller.subject.vo;

import com.cmb.okr.dao.subject.enums.SubjectType;

public class AddSubjectReq {

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
	 * 课题类型
            personal：个人
            geek：极客
            fresh：新员工
	 */
	private SubjectType type;

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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getLeaderId() {
		return leaderId;
	}

	public void setLeaderId(String leaderId) {
		this.leaderId = leaderId;
	}

	public SubjectType getType() {
		return type;
	}

	public void setType(SubjectType type) {
		this.type = type;
	}
}
