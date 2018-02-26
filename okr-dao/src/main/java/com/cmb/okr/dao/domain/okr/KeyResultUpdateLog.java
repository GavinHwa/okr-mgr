package com.cmb.okr.dao.domain.okr;

import com.cmb.okr.frame.db.BaseEntity;
import java.util.Date;

/**
 * 该类是映射到表的实体类，负责与表进行一一对应。
 * @author: huang 
 * @date: 2018-01-25 06:44:39
 * @since 1.0.0
 */
public class KeyResultUpdateLog extends BaseEntity {
private static final long serialVersionUID = 1L;
	

	/**
	 * 关键结果id
	 */
	private String keyResultId;

	/**
	 * 更新前进度
	 */
	private Integer progressBefore;

	/**
	 * 更新后进度
	 */
	private Integer progressAfter;

	/**
	 * 更新说明
	 */
	private String remark;

	/**
	 * 更新时间
	 */
	private Date updateDate;


	/**
	 * 关键结果id
	 */
	public void setKeyResultId(String keyResultId){
		this.keyResultId = keyResultId;
	}
	
	/**
	 * 关键结果id
	 */
	public String getKeyResultId(){
		return  keyResultId;
	}

	/**
	 * 更新前进度
	 */
	public void setProgressBefore(Integer progressBefore){
		this.progressBefore = progressBefore;
	}
	
	/**
	 * 更新前进度
	 */
	public Integer getProgressBefore(){
		return  progressBefore;
	}

	/**
	 * 更新后进度
	 */
	public void setProgressAfter(Integer progressAfter){
		this.progressAfter = progressAfter;
	}
	
	/**
	 * 更新后进度
	 */
	public Integer getProgressAfter(){
		return  progressAfter;
	}

	/**
	 * 更新说明
	 */
	public void setRemark(String remark){
		this.remark = remark;
	}
	
	/**
	 * 更新说明
	 */
	public String getRemark(){
		return  remark;
	}

	/**
	 * 更新时间
	 */
	public void setUpdateDate(Date updateDate){
		this.updateDate = updateDate;
	}
	
	/**
	 * 更新时间
	 */
	public Date getUpdateDate(){
		return  updateDate;
	}
}