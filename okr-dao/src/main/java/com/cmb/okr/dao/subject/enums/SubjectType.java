package com.cmb.okr.dao.subject.enums;

/**
 * 课题类型
 * 
 * @author hf
 *
 */
public enum SubjectType {
	personal("个人"), geek("极客"), fresh("新员工");

	private String value;

	private SubjectType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
