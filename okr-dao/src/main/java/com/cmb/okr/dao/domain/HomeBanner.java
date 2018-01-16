package com.cmb.okr.dao.domain;

import com.cmb.okr.frame.db.BaseEntity;
import java.util.Date;

/**
 * 该类是映射到表的实体类，负责与表进行一一对应。
 * @author: huang 
 * @date: 2018-01-12 09:23:08
 * @since 1.0.0
 */
public class HomeBanner extends BaseEntity {
private static final long serialVersionUID = 1L;
	

	/**
	 * 课题id
	 */
	private String subjectId;

	/**
	 * 添加时间
	 */
	private Date addDate;

	/**
	 * 图片地址
	 */
	private String imageUrl;

	/**
	 * 是否有效
	 */
	private Integer isValid;


	/**
	 * 课题id
	 */
	public void setSubjectId(String subjectId){
		this.subjectId = subjectId;
	}
	
	/**
	 * 课题id
	 */
	public String getSubjectId(){
		return  subjectId;
	}

	/**
	 * 添加时间
	 */
	public void setAddDate(Date addDate){
		this.addDate = addDate;
	}
	
	/**
	 * 添加时间
	 */
	public Date getAddDate(){
		return  addDate;
	}

	/**
	 * 图片地址
	 */
	public void setImageUrl(String imageUrl){
		this.imageUrl = imageUrl;
	}
	
	/**
	 * 图片地址
	 */
	public String getImageUrl(){
		return  imageUrl;
	}

	/**
	 * 是否有效
	 */
	public void setIsValid(Integer isValid){
		this.isValid = isValid;
	}
	
	/**
	 * 是否有效
	 */
	public Integer getIsValid(){
		return  isValid;
	}
}