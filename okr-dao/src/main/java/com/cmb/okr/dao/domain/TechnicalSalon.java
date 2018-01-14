package com.cmb.okr.dao.domain;

import com.cmb.okr.frame.db.BaseEntity;
import java.util.Date;
import java.util.Date;

/**
 * 该类是映射到表的实体类，负责与表进行一一对应。
 * @author: huang 
 * @date: 2018-01-12 09:21:52
 * @since 1.0.0
 */
public class TechnicalSalon extends BaseEntity {
private static final long serialVersionUID = 1L;
	

	/**
	 * 名称
	 */
	private String name;

	/**
	 * 发布时间
	 */
	private Date publishDate;

	/**
	 * 举办时间
	 */
	private Date holdDate;

	/**
	 * 预告图片
	 */
	private String previewImage;

	/**
	 * 小结图片
	 */
	private String summaryImage;

	/**
	 * 主讲人
	 */
	private String speechmaker;

	/**
	 * 描述信息
	 */
	private String descp;

	/**
	 * 嘉宾
	 */
	private String guests;


	/**
	 * 名称
	 */
	public void setName(String name){
		this.name = name;
	}
	
	/**
	 * 名称
	 */
	public String getName(){
		return  name;
	}

	/**
	 * 发布时间
	 */
	public void setPublishDate(Date publishDate){
		this.publishDate = publishDate;
	}
	
	/**
	 * 发布时间
	 */
	public Date getPublishDate(){
		return  publishDate;
	}

	/**
	 * 举办时间
	 */
	public void setHoldDate(Date holdDate){
		this.holdDate = holdDate;
	}
	
	/**
	 * 举办时间
	 */
	public Date getHoldDate(){
		return  holdDate;
	}

	/**
	 * 预告图片
	 */
	public void setPreviewImage(String previewImage){
		this.previewImage = previewImage;
	}
	
	/**
	 * 预告图片
	 */
	public String getPreviewImage(){
		return  previewImage;
	}

	/**
	 * 小结图片
	 */
	public void setSummaryImage(String summaryImage){
		this.summaryImage = summaryImage;
	}
	
	/**
	 * 小结图片
	 */
	public String getSummaryImage(){
		return  summaryImage;
	}

	/**
	 * 主讲人
	 */
	public void setSpeechmaker(String speechmaker){
		this.speechmaker = speechmaker;
	}
	
	/**
	 * 主讲人
	 */
	public String getSpeechmaker(){
		return  speechmaker;
	}

	/**
	 * 描述信息
	 */
	public void setDescp(String descp){
		this.descp = descp;
	}
	
	/**
	 * 描述信息
	 */
	public String getDescp(){
		return  descp;
	}

	/**
	 * 嘉宾
	 */
	public void setGuests(String guests){
		this.guests = guests;
	}
	
	/**
	 * 嘉宾
	 */
	public String getGuests(){
		return  guests;
	}
}