package com.cmb.okr.api.controller.headline.vo;

import com.cmb.okr.dao.domain.headline.enums.HeadlineType;

public class LoadHLPageReq {

	private HeadlineType type;

	public HeadlineType getType() {
		return type;
	}

	public void setType(HeadlineType type) {
		this.type = type;
	}
}
