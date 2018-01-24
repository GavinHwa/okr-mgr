package com.cmb.okr.api.controller.subject.vo;

import com.cmb.okr.dao.subject.enums.SubjectStatus;

public class ChangeStatusReq {

	private String subjectId;

	private SubjectStatus status;

	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

	public SubjectStatus getStatus() {
		return status;
	}

	public void setStatus(SubjectStatus status) {
		this.status = status;
	}
}
