package com.cmb.okr.dao.domain.okr.enums;

public enum ObjectiveType {
	team("团队"), personal("个人");

	private String value;

	private ObjectiveType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
