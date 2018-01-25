package com.cmb.okr.dao.domain.headline.enums;

public enum HeadlineType {
	subject("课题"), salon("技术沙龙'");
	private String value;

	private HeadlineType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
