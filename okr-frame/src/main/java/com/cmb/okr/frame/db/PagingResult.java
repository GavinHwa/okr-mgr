package com.cmb.okr.frame.db;

import java.util.List;

/**
 * 分页查询结果
 * 
 * @author hf
 *
 * @param <T>
 */
public class PagingResult<T> {

	/**
	 * 总页数
	 */
	private int totalPage;

	/**
	 * 总数
	 */
	private int totalNum;

	/**
	 * 查询结果
	 */
	private List<T> result;

	public <E> PagingResult<E> copy(List<E> result) {
		PagingResult<E> response = new PagingResult<>();
		response.setResult(result);
		response.setTotalNum(this.totalNum);
		response.setTotalPage(this.getTotalPage());
		return response;
	}
	
	public void initTotalPage(int pageSize){
		this.totalPage = totalNum % pageSize == 0 ? totalNum / pageSize : totalNum / pageSize + 1;
	}

	public int getTotalPage() {
		return this.totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}

	public List<T> getResult() {
		return result;
	}

	public void setResult(List<T> result) {
		this.result = result;
	}

}
