package com.sanker.service.utils;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class PageVO<T> implements Serializable {
	/**
	 * 当前页码
	 */
	private int page = 0;
	/**
	 * 每页显示的条数
	 */
	private int pageSize = 10;
	/**
	 * 返回的数据
	 */
	private List<T> rows;
	/**
	 * 总条数
	 */
	private int records = 0;
	/**
	 * 总页数
	 */
	@SuppressWarnings("unused")
	private int total = 0; 
	
	
	public PageVO(){
		
	}
	
	/**
	 * 将PageVo转换成json格式
	 * @return
	 */
//	public String convertObjectToJson(){
//		Map<String, Object> pageInfo = new HashMap<String, Object>();
//		pageInfo.put("records", this.getTotNumber());
//		pageInfo.put("total", this.getTotalPage());
//		pageInfo.put("page", this.getPage());
//		pageInfo.put("rows", this.getData());
////		JSONObject object = new JSONObject(pageInfo);
//		return JSONHelper.Serialize(pageInfo);
//	}
	
	public PageVO(int page, int pageSize){
		this.page = page;
		this.pageSize = pageSize;
	}
	
	public int getStartIndex(){
		if(0 == this.page){
			return 0;
		}
		return page * this.pageSize - this.pageSize;
	}

	public int getTotal() {
		return records % pageSize == 0 ? records / pageSize : records / pageSize + 1;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	public int getRecords() {
		return records;
	}

	public void setRecords(int records) {
		this.records = records;
	}
}
