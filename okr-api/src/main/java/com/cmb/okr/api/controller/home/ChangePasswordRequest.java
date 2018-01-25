package com.cmb.okr.api.controller.home;

public class ChangePasswordRequest {

	private String oldPossword;

	private String newPassword;

	public String getOldPossword() {
		return oldPossword;
	}

	public void setOldPossword(String oldPossword) {
		this.oldPossword = oldPossword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

}
