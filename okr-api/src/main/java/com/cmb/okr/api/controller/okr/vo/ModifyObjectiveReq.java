package com.cmb.okr.api.controller.okr.vo;

import java.util.Date;

public class ModifyObjectiveReq {
	private String id;

	/**
	 * 目标名称
	 */
	private String objectiveContent;

	/**
	 * 目标开始时间
	 */
	private Date startDate;

	/**
	 * 目标结束时间
	 */
	private Date endDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getObjectiveContent() {
		return objectiveContent;
	}

	public void setObjectiveContent(String objectiveContent) {
		this.objectiveContent = objectiveContent;
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
