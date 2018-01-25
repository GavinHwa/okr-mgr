package com.cmb.okr.attachment;

import java.io.InputStream;

public class AttachmentResponse {

	private InputStream is;

	private String fileName;

	public InputStream getIs() {
		return is;
	}

	public void setIs(InputStream is) {
		this.is = is;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
