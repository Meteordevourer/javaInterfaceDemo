package com.webdemo.weapi.action.domain;

import java.util.List;

public class ResponsePageVO extends ResponseDataListVO
{
	private static final long serialVersionUID = 1L;

	private Integer page;//当前页码
	private Integer pageSize;//每页多少
	private Long totalRows;//总条数
	private Integer pageNum; //总页数
	
	public ResponsePageVO() {
		
	}
	
	public ResponsePageVO(Integer page_, Integer pageSize_, Long totalRows_, List data) {
		this.page = page_;
		this.pageSize = pageSize_;
		this.totalRows = totalRows_;
		this.setData(data);
				
	}
	
	public ResponsePageVO getList(ResponsePageVO responseData, Long totalRows_, List data) {
		
		responseData.setData(data);
		responseData.setTotalRows(totalRows_);//总条数
		responseData.setPageNum(responseData.getPageNum());
		return responseData;
	}
	

	public Integer getPageNum() {
	    int  totalPageNum = (int) ((totalRows % pageSize == 0) ? totalRows / pageSize : totalRows / pageSize + 1);
		return totalPageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
	public Long getTotalRows() {
		return totalRows;
	}
	
	public void setTotalRows(Long totalRows) {
		this.totalRows = totalRows;
	}
	
}
