package com.cmb.okr.dao.domain.okr;

import java.util.Date;

import com.cmb.okr.dao.domain.okr.enums.ObjectiveType;
import com.cmb.okr.frame.db.BaseEntity;

/**
 * 该类是映射到表的实体类，负责与表进行一一对应。
 * @author: huang 
 * @date: 2018-01-27 07:42:35
 * @since 1.0.0
 */
public class ObjectiveInfo extends BaseEntity {
private static final long serialVersionUID = 1L;
	

	/**
	 * 课题ID
	 */
	private String subjectId;

	/**
	 * 父目标
            只有个人目标才能有父目标
	 */
	private String parentId;

	/**
	 * 目标名称
	 */
	private String objectiveContent;

	/**
	 * 目标类型
            team：团队
            personal：个人
	 */
	private ObjectiveType objectiveType;

	/**
	 * 目标开始时间
	 */
	private Date startDate;

	/**
	 * 目标结束时间
	 */
	private Date endDate;

	/**
	 * 创建人
	 */
	private String createId;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 最后修改时间
	 */
	private Date lastModifiedTime;


	/**
	 * 课题ID
	 */
	public void setSubjectId(String subjectId){
		this.subjectId = subjectId;
	}
	
	/**
	 * 课题ID
	 */
	public String getSubjectId(){
		return  subjectId;
	}

	/**
	 * 父目标
            只有个人目标才能有父目标
	 */
	public void setParentId(String parentId){
		this.parentId = parentId;
	}
	
	/**
	 * 父目标
            只有个人目标才能有父目标
	 */
	public String getParentId(){
		return  parentId;
	}

	/**
	 * 目标名称
	 */
	public void setObjectiveContent(String objectiveContent){
		this.objectiveContent = objectiveContent;
	}
	
	/**
	 * 目标名称
	 */
	public String getObjectiveContent(){
		return  objectiveContent;
	}

	/**
	 * 目标类型
            team：团队
            personal：个人
	 */
	public void setObjectiveType(ObjectiveType objectiveType){
		this.objectiveType = objectiveType;
	}
	
	/**
	 * 目标类型
            team：团队
            personal：个人
	 */
	public ObjectiveType getObjectiveType(){
		return  objectiveType;
	}

	/**
	 * 目标开始时间
	 */
	public void setStartDate(Date startDate){
		this.startDate = startDate;
	}
	
	/**
	 * 目标开始时间
	 */
	public Date getStartDate(){
		return  startDate;
	}

	/**
	 * 目标结束时间
	 */
	public void setEndDate(Date endDate){
		this.endDate = endDate;
	}
	
	/**
	 * 目标结束时间
	 */
	public Date getEndDate(){
		return  endDate;
	}

	/**
	 * 创建人
	 */
	public void setCreateId(String createId){
		this.createId = createId;
	}
	
	/**
	 * 创建人
	 */
	public String getCreateId(){
		return  createId;
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
}