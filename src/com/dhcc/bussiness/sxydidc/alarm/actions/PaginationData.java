package com.dhcc.bussiness.sxydidc.alarm.actions;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

/*
 * 带分页信息的分页数据
 * 其中有 当前页号
 * 每页条数
 * 总条数
 * 当前页开始索引（从0开始）
 * 当前页结束索引（不包括结束）
 */
public class PaginationData<T> extends ActionSupport {

	private List<T> list = new ArrayList();
	private int currentPageNumber;
	private int totalCount;
	private  int countPerPage;//默认10条
	private int currentPageStart = 0;
	private int currentPageEnd = 9;
	private int totalPageCount;
	
	
	/**
	 * @param currentPageStart the currentPageStart to set
	 */
	public void setCurrentPageStart(int currentPageStart) {
		this.currentPageStart = currentPageStart;
	}

	/**
	 * @param currentPageEnd the currentPageEnd to set
	 */
	public void setCurrentPageEnd(int currentPageEnd) {
		this.currentPageEnd = currentPageEnd;
	}

	/**
	 * @param totalPageCount the totalPageCount to set
	 */
	public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}

	/**
	 * @param currentPageNumber
	 * @param countPerPage
	 */
	public PaginationData(int currentPageNumber, int countPerPage) {
		super();
		this.setCurrentPageNumber(currentPageNumber);
		this.setCountPerPage(countPerPage);
}

	/**
	 * 
	 */
	public PaginationData() {
		
		super();
	}

	/**
	 * @return the totalPageCount
	 */
	public int getTotalPageCount() {
		return totalPageCount;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(List<T> list) {
		this.list = list;
	}

	/**
	 * @return the currentPageStart
	 */
	public int getCurrentPageStart() {
		this.currentPageStart = getCurrentPageNumber() *this.getCountPerPage();
		return currentPageStart;
	}

	/**
	 * @return the currentPageEnd
	 */
	public int getCurrentPageEnd() {
	
		this.currentPageEnd = (getCurrentPageNumber()+1)*this.getCountPerPage();
		return currentPageEnd;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PaginationData [list=" + list + ", currentPageNumber="
				+ currentPageNumber + ", totalCount=" + totalCount
				+ ", countPerPage=" + countPerPage + ", currentPageStart="
				+ currentPageStart + ", currentPageEnd=" + currentPageEnd
				+ ", totalPageCount=" + totalPageCount + "]";
	}

	/**
	 * @return the list
	 */
	public List<T> getList() {
		return list;
	}


	/**
	 * @return the currentPageNumber
	 */
	public int getCurrentPageNumber() {
		return currentPageNumber;
	}

	/**
	 * @param currentPageNumber the currentPageNumber to set
	 */
	public void setCurrentPageNumber(int currentPageNumber) {
//		检查 页号不能小于0
		this.currentPageNumber = currentPageNumber;
//		this.currentPageNumber = currentPageNumber<0?0:currentPageNumber;
	}

	/**
	 * @return the totalCount
	 */
	public int getTotalCount() {
		return totalCount;
	}

	/**
	 * @param totalCount the totalCount to set
	 */
	public void setTotalCount(int totalCount) {
		this.totalPageCount = totalCount%countPerPage>0?(totalCount/countPerPage+1):(totalCount/countPerPage);
		this.totalCount = totalCount;
	}

	/**
	 * @return the countPerPage
	 */
	public int getCountPerPage() {
		return countPerPage;
	}

	/**
	 * @param countPerPage the countPerPage to set
	 */
	public void setCountPerPage(int countPerPage) {
		countPerPage = countPerPage > 0?countPerPage:10;
		this.countPerPage = countPerPage;
	}

}