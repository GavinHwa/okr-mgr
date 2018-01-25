package com.cmb.okr.dao.domain.headline;

import com.cmb.okr.dao.domain.headline.enums.HeadlineType;
import com.cmb.okr.frame.db.BaseEntity;
import java.util.Date;

/**
 * 该类是映射到表的实体类，负责与表进行一一对应。
 * 
 * @author: huang
 * @date: 2018-01-24 09:35:52
 * @since 1.0.0
 */
public class Headline extends BaseEntity {
	private static final long serialVersionUID = 1L;

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
	private Integer rank;

	/**
	 * 是否有效
	 */
	private Integer isValid;

	/**
	 * 添加时间
	 */
	private Date createTime;

	/**
	 * 添加人
	 */
	private String createId;

	/**
	 * 类别 sibject：课题 salon：技术沙龙
	 */
	public void setType(HeadlineType type) {
		this.type = type;
	}

	/**
	 * 类别 sibject：课题 salon：技术沙龙
	 */
	public HeadlineType getType() {
		return type;
	}

	/**
	 * 关联id
	 */
	public void setRelatedId(String relatedId) {
		this.relatedId = relatedId;
	}

	/**
	 * 关联id
	 */
	public String getRelatedId() {
		return relatedId;
	}

	/**
	 * 图片id
	 */
	public void setImageId(String imageId) {
		this.imageId = imageId;
	}

	/**
	 * 图片id
	 */
	public String getImageId() {
		return imageId;
	}

	/**
	 * 排序编号
	 */
	public void setRank(Integer rank) {
		this.rank = rank;
	}

	/**
	 * 排序编号
	 */
	public Integer getRank() {
		return rank;
	}

	/**
	 * 是否有效
	 */
	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}

	/**
	 * 是否有效
	 */
	public Integer getIsValid() {
		return isValid;
	}

	/**
	 * 添加时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 添加时间
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 添加人
	 */
	public void setCreateId(String createId) {
		this.createId = createId;
	}

	/**
	 * 添加人
	 */
	public String getCreateId() {
		return createId;
	}
}