package com.cmb.okr.api.controller.okr.vo;

import java.util.Date;
import java.util.List;

import com.cmb.okr.dao.domain.okr.enums.ObjectiveType;

public class AddObjectiveReq {

	/**
	 * 课题ID
	 */
	private String subjectId;

	/**
	 * 父目标 只有个人目标才能有父目标
	 */
	private String parentId;

	/**
	 * 目标名称
	 */
	private String objectiveContent;

	/**
	 * 目标类型 team：团队 personal：个人
	 */
	private ObjectiveType objectiveType;

	/**
	 * 目标开始时间
	 */
	private Date startDate;

	/**
	 * 目标结束时间
	 */
	private Date endDate;
	
	/**
	 * 关键结果列表
	 */
	List<AddKeyResultReq> keyResults;

	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getObjectiveContent() {
		return objectiveContent;
	}

	public void setObjectiveContent(String objectiveContent) {
		this.objectiveContent = objectiveContent;
	}

	public ObjectiveType getObjectiveType() {
		return objectiveType;
	}

	public void setObjectiveType(ObjectiveType objectiveType) {
		this.objectiveType = objectiveType;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public List<AddKeyResultReq> getKeyResults() {
		return keyResults;
	}

	public void setKeyResults(List<AddKeyResultReq> keyResults) {
		this.keyResults = keyResults;
	}

}
