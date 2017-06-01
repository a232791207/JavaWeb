package com.sanker.comms.page;

import java.util.Collection;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PageResponse {

	@Expose
	@SerializedName("page")
	private int pageIndex;
	@Expose
	@SerializedName("total")
	private int totalPage;
	@Expose
	@SerializedName("records")
	private long totalCount;

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public PageResponse(int pageIndex, int totalPage, long totalCount, @SuppressWarnings("rawtypes") Collection list) {
		super();
		this.pageIndex = pageIndex;
		this.totalPage = totalPage;
		this.totalCount = totalCount;
		this.list = list;
	}
	public PageResponse( @SuppressWarnings("rawtypes") Collection list) {
		super();
		this.pageIndex = 1;
		this.totalPage = 1;
		this.totalCount = list.size();
		this.list = list;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	@SuppressWarnings("rawtypes")
	public Collection getList() {
		return list;
	}

	@SuppressWarnings("rawtypes")
	public void setList(Collection list) {
		this.list = list;
	}

	@SuppressWarnings("rawtypes")
	@Expose
	@SerializedName("rows")
	private Collection list;

}
