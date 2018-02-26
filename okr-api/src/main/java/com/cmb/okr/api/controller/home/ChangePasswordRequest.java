package com.cmb.okr.api.controller.home;

import java.util.Date;

public class ChangePasswordRequest {

	private String oldPossword;

	private String newPassword;

	public String getModifyId() {
		return modifyId;
	}

	public void setModifyId(String modifyId) {
		this.modifyId = modifyId;
	}

	private String modifyId;

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	private Date modifyTime;

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
