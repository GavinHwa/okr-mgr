package com.cmb.okr.api.controller.okr.vo;

import java.util.Date;

import com.cmb.okr.dao.domain.okr.enums.ObjectiveType;

public class ObjectiveResponse {

	private String id;
	
	private String subjectId;
	
	private String subjectName;
	
	private String parentId;
	
	private String parentName;
	
	private String objectiveContent;
	
	private ObjectiveType objectiveType;
	
	private Date startDate;
	
	private Date endDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
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
}
