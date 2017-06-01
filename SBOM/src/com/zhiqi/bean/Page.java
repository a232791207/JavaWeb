package com.zhiqi.bean;

import java.io.Serializable;
import java.util.List;

public class Page implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 * ��ǰҳ��
	 */
	private int thispage;
	/**
	 * ÿҳ����
	 */
	private int rowperpage;
	/**
	 * ������
	 */
	private int countrow;
	/**
	 * ��ҳ��
	 */
	private int countpage;
	/**
	 * ��һҳ
	 */
	private int prepage;
	/**
	 * ��һҳ
	 */
	private int nextpage;
	/**
	 * ��ҳ
	 */
	private int firstpage;
	/**
	 * βҳ
	 */
	private int lastpage;
	/**
	 * ��ҳ�������
	 */
	private List list;
	
	public Page() {
	}
	
	public Page(int thispage, int rowperpage, int countrow, int countpage, int prepage, int nextpage, int firstpage,
			int lastpage, List list) {
		this.thispage = thispage;
		this.rowperpage = rowperpage;
		this.countrow = countrow;
		this.countpage = countpage;
		this.prepage = prepage;
		this.nextpage = nextpage;
		this.firstpage = firstpage;
		this.lastpage = lastpage;
		this.list = list;
	}

	public int getThispage() {
		return thispage;
	}

	public void setThispage(int thispage) {
		this.thispage = thispage;
	}

	public int getRowperpage() {
		return rowperpage;
	}

	public void setRowperpage(int rowperpage) {
		this.rowperpage = rowperpage;
	}

	public int getCountrow() {
		return countrow;
	}

	public void setCountrow(int countrow) {
		this.countrow = countrow;
	}

	public int getCountpage() {
		return countpage;
	}

	public void setCountpage(int countpage) {
		this.countpage = countpage;
	}

	public int getPrepage() {
		return prepage;
	}

	public void setPrepage(int prepage) {
		this.prepage = prepage;
	}

	public int getNextpage() {
		return nextpage;
	}

	public void setNextpage(int nextpage) {
		this.nextpage = nextpage;
	}

	public int getFirstpage() {
		return firstpage;
	}

	public void setFirstpage(int firstpage) {
		this.firstpage = firstpage;
	}

	public int getLastpage() {
		return lastpage;
	}

	public void setLastpage(int lastpage) {
		this.lastpage = lastpage;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
