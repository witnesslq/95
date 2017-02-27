package com.dhcc.bussiness.sxydidc.alarm.actions;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

/*
 * 带分页信息的分页数据
 * 其中有 
 * 当前页号
 * 每页条数
 * 总条数
 * 这三个是可以自定义去设置的，其他的属性都是通过这几个属性计算得出
 * 计算属性在调用其getXxx方法的时候计算
 * 当前页开始索引（从0开始）
 * 当前页结束索引（不包括结束）
 */
public class PaginationData<T> extends ActionSupport {

	private List<T> list = new ArrayList();
	private int currentPageNumber=0;
	private int totalCount;
	private  int countPerPage=10;//默认10条
	private int currentPageStart;
	private int currentPageEnd;
	private int totalPageCount;
	
	
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
		this.totalPageCount = totalCount%countPerPage>0?(totalCount/countPerPage+1):(totalCount/countPerPage);

		return this.totalPageCount;
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
		return this.currentPageStart;
	}

	/**
	 * @return the currentPageEnd
	 */
	public int getCurrentPageEnd() {
	
		this.currentPageEnd = (getCurrentPageNumber()+1)*this.getCountPerPage();
		return this.currentPageEnd;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PaginationData [list=" + list + ", currentPageNumber="
				+ currentPageNumber + ", totalCount=" + totalCount
				+ ", countPerPage=" + countPerPage + ", getTotalPageCount()="
				+ getTotalPageCount() + ", getCurrentPageStart()="
				+ getCurrentPageStart() + ", getCurrentPageEnd()="
				+ getCurrentPageEnd() + "]";
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