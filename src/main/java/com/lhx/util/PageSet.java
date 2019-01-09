package com.lhx.util;

import java.util.List;

/**
 * @author tianwen
 *
 */
public class PageSet<T> {
	private int currentPage=1;	//当前显示页码
	private int pageOfSize=10;	//每页显示条数
	private int showPage=10;	//需要显示页数
	private int recordTotal;	//记录总数
	private List<T> items;
	private int pageTotal;
	private boolean next;
	private boolean prev;
	private int startPage;
	private int endPage;
	
	public PageSet(Integer currentPage, Integer pageOfSize, int recordTotal){
		this.recordTotal = recordTotal;
		if(currentPage != null && pageOfSize != null){
			this.currentPage=currentPage;
			this.pageOfSize=pageOfSize;
			initParam();
		}
	}
	
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageOfSize() {
		return pageOfSize;
	}
	public void setPageOfSize(int pageOfSize) {
		this.pageOfSize = pageOfSize;
	}
	public void setShowPage(int showPage) {
		this.showPage = showPage;
	}
	public int getShowPage() {
		return showPage;
	}
	public void setRecordTotal(int recordTotal) {
		this.recordTotal = recordTotal;
	}
	public int getRecordTotal() {
		return recordTotal;
	}
	public void setItems(List<T> items) {
		this.items = items;
	}
	public List<T> getItems() {
		return items;
	}
	public int getPageTotal() {
		return this.pageTotal;
	}
	public boolean getNext() {
		return this.next;
	}
	public boolean getPrev() {
		return this.prev;
	}
	public int getEndPage() {
		return this.endPage;
	}
	public int getStartPage() {
		return this.startPage;
	}
	private void initParam(){
		this.currentPage=this.currentPage<1?1:this.currentPage;
		this.pageOfSize=this.pageOfSize<1?10:this.pageOfSize;
		this.showPage=this.showPage<1?10:this.showPage;
		this.pageTotal=(this.recordTotal+this.pageOfSize-1)/this.pageOfSize;
		this.currentPage=this.currentPage>this.pageTotal?this.pageTotal:this.currentPage;
		
		this.next=this.currentPage<this.pageTotal;
		this.prev=this.currentPage>1;
		this.startPage=(this.currentPage-1)/this.showPage*this.showPage+1;
		this.endPage=(this.currentPage-1)/this.showPage*this.showPage+this.showPage;
		this.endPage=this.endPage>this.pageTotal?this.pageTotal:this.endPage;
	}

}
