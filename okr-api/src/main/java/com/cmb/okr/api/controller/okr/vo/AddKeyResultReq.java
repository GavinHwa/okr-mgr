package com.cmb.okr.api.controller.okr.vo;

public class AddKeyResultReq {

	private String id;

	/**
	 * 目标id
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

	public String getObjectiveId() {
		return objectiveId;
	}

	public void setObjectiveId(String objectiveId) {
		this.objectiveId = objectiveId;
	}

	public String getResultInfo() {
		return resultInfo;
	}

	public void setResultInfo(String resultInfo) {
		this.resultInfo = resultInfo;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public Integer getProgress() {
		return progress;
	}

	public void setProgress(Integer progress) {
		this.progress = progress;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
