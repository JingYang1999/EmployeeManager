package com.icss.employeemanager.utils;

import java.util.List;

/**
 * 分页工具类
 * 
 * @author Giselle
 */
public class ResultPage<T> {
	private int currentPage; // 当前页
	private int totalPage;// 总页数
	private int totalCount;// 总条数
	private List<T> lists;// 当前页显示的数据列表

	public int getCurrentPage() {
		return currentPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public List<T> getLists() {
		return lists;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public void setLists(List<T> lists) {
		this.lists = lists;
	}

	@Override
	public String toString() {
		return "ResultPage [currentPage=" + currentPage + ", totalPage=" + totalPage + ", totalCount=" + totalCount
				+ ", lists=" + lists + "]";
	}

}
