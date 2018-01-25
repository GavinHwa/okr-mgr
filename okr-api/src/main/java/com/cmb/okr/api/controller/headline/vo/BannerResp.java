package com.cmb.okr.api.controller.headline.vo;

import java.util.Date;

public class BannerResp {

	private String id;

	/**
	 * 类别 sibject：课题 salon：技术沙龙
	 */
	private String type;

	/**
	 * 关联id
	 */
	private String relatedId;

	/**
	 * 图片id
	 */
	private String imageId;

	/**
	 * 图片url
	 */
	private String imageUrl;

	/**
	 * 排序编号
	 */
	private int rank;

	private Date createTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
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

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
