package com.icss.employeemanager.utils;

import java.util.List;

/**
 * ��ҳ������
 * 
 * @author Giselle
 */
public class ResultPage<T> {
	private int currentPage; // ��ǰҳ
	private int totalPage;// ��ҳ��
	private int totalCount;// ������
	private List<T> lists;// ��ǰҳ��ʾ�������б�

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
