package com.cmb.okr.dao.subject.enums;

/**
 * 
 * 课题状态
 * 
 * @author hf
 *
 */
public enum SubjectStatus {
	processing("进行中"), finished("已结束"), suspend("暂停"), cancel("取消");

	private String value;

	private SubjectStatus(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
