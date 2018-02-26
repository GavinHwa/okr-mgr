package com.cmb.okr.dao.domain.okr;

import java.util.Date;

import com.cmb.okr.frame.db.BaseEntity;

/**
 * 该类是映射到表的实体类，负责与表进行一一对应。
 * @author: huang 
 * @date: 2018-01-27 07:42:35
 * @since 1.0.0
 */
public class KeyResult extends BaseEntity {
private static final long serialVersionUID = 1L;
	

	/**
	 * 
	 */
	private String objectiveId;

	/**
	 * 结果描述
	 */
	private String resultInfo;

	/**
	 * 在所有关键结果中的权重
	 */
	private Integer weight;

	/**
	 * 当前关键结果的完成进度
	 */
	private Integer progress;

	/**
	 * 
	 */
	private String confidenceIndex;

	/**
	 * 最后修改时间
	 */
	private Date lastModifiedTime;

	/**
	 * 创建时间
	 */
	private Date createTime;


	/**
	 * 
	 */
	public void setObjectiveId(String objectiveId){
		this.objectiveId = objectiveId;
	}
	
	/**
	 * 
	 */
	public String getObjectiveId(){
		return  objectiveId;
	}

	/**
	 * 结果描述
	 */
	public void setResultInfo(String resultInfo){
		this.resultInfo = resultInfo;
	}
	
	/**
	 * 结果描述
	 */
	public String getResultInfo(){
		return  resultInfo;
	}

	/**
	 * 在所有关键结果中的权重
	 */
	public void setWeight(Integer weight){
		this.weight = weight;
	}
	
	/**
	 * 在所有关键结果中的权重
	 */
	public Integer getWeight(){
		return  weight;
	}

	/**
	 * 当前关键结果的完成进度
	 */
	public void setProgress(Integer progress){
		this.progress = progress;
	}
	
	/**
	 * 当前关键结果的完成进度
	 */
	public Integer getProgress(){
		return  progress;
	}

	/**
	 * 
	 */
	public void setConfidenceIndex(String confidenceIndex){
		this.confidenceIndex = confidenceIndex;
	}
	
	/**
	 * 
	 */
	public String getConfidenceIndex(){
		return  confidenceIndex;
	}

	/**
	 * 最后修改时间
	 */
	public void setLastModifiedTime(Date lastModifiedTime){
		this.lastModifiedTime = lastModifiedTime;
	}
	
	/**
	 * 最后修改时间
	 */
	public Date getLastModifiedTime(){
		return  lastModifiedTime;
	}

	/**
	 * 创建时间
	 */
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	
	/**
	 * 创建时间
	 */
	public Date getCreateTime(){
		return  createTime;
	}
}