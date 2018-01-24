package com.cmb.okr.api.controller.headline.vo;

import com.cmb.okr.dao.domain.headline.enums.HeadlineType;

public class AddHeadlineReq {
	/**
	 * 类别 sibject：课题 salon：技术沙龙
	 */
	private HeadlineType type;

	/**
	 * 关联id
	 */
	private String relatedId;

	/**
	 * 图片id
	 */
	private String imageId;

	/**
	 * 排序编号
	 */
	private int rank;

	public HeadlineType getType() {
		return type;
	}

	public void setType(HeadlineType type) {
		this.type = type;
	}

	public String getRelatedId() {
		return relatedId;
	}

	public void setRelatedId(String relatedId) {
		this.relatedId = relatedId;
	}

	public String getImageId() {
		return imageId;
	}

	public void setImageId(String imageId) {
		this.imageId = imageId;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}
}
