package com.cmb.okr.dao.enums;

/**
 * 用户类型
 * @author hf
 *
 */
public enum UserType {
	admin("管理员"), normal("普通用户");
	private String value;

	private UserType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
