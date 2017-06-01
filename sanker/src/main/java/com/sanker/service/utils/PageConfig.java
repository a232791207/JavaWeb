package com.sanker.service.utils;

import java.util.List;

/**
 * author psh
 * 
 * class PageConfig.java
 * 
 * version 1.0
 * 
 * create 2009-9-15
 */
@SuppressWarnings("serial")
public class PageConfig implements java.io.Serializable {

	/**
	 * 当前页
	 */
	private Integer pageIndex = 1;

	/**
	 * 每页大小
	 */
	private Integer pageSize = 20;
	
	/**
	 * 总记录行数
	 */
	private Integer rowsSum = 0;
	
	
	/**
	 * 查询结果
	 */
	private List<Object> result;
	
	public List<Object> getResult() {
		return result;
	}

	public void setResult(List<Object> result) {
		this.result = result;
	}

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getRowsSum() {
		return rowsSum;
	}

	public void setRowsSum(Integer rowsSum) {
		this.rowsSum = rowsSum;
	}
	/**
	 * 得到开始下标
	 * @return
	 */
	public Integer getStartNumber() {
		if(0 == this.pageIndex){
			return 0;
		}
		return (this.pageIndex - 1) * this.pageSize;
	
	}
	/**
	 * 得到总页数
	 * @return
	 */
	public Integer getPageSum(){
		if(this.rowsSum==0)
			return 0;
		return this.rowsSum%this.pageSize==0?this.rowsSum/this.pageSize:this.rowsSum/this.pageSize+1;
	}

}
