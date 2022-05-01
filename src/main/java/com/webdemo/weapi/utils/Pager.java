package com.webdemo.weapi.utils;

import com.webdemo.weapi.action.domain.ApiConconts;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 
 * @Description: 分页对象  mysql
 * @author: 许智皓
 * @date: 2017年6月21日 下午5:34:38
 * 
 * @version: V1.0
 * 
 * @updateDate: 
 * @update:
 */
@SuppressWarnings({ "serial", "hiding" })
public class Pager implements Serializable {

	private Long totalRows; // 总行数
	private int pageSize = 10; // 每页显示的行数
	private int currentPage; // 当前页号
	private int totalPages; // 总页数
	private int startRow; // 当前页在数据库中的起始行
	private int endRow; // 结束行 此为oracle查询需要增加


	/** 分页菜单默认从第一条开始 */
	public static final int PAGE_NUM = 1;
	
	/** 分页菜单默认一次最多取10条 */
	public static final int PAGE_SIZE = 10;
	
	
	public Pager() {
	}

	public Pager(long _totalRows , Integer _pageSize) {
		
		totalRows = _totalRows;
		if(null != _pageSize & 0 < _pageSize){
			pageSize = _pageSize;
		}
		setTotalRows(totalRows);
	
		
		totalPages = (int) (totalRows / pageSize);
		int mod = (int) (totalRows % pageSize);
		if (mod > 0) {
			totalPages++;
		}
		currentPage = 1;
		startRow = 0;
		endRow = pageSize;
	}
	
	
	/**
	 * 
	 * @Title: mathTotalPages
	 * @Description: 计算总页数
	 * @author: 支付开发部-许智皓
	 * @param totalRows
	 * @param totalPages
	 * @return
	 * int
	 */
	public static int mathTotalPages(Long totalRows , int totalPages){
		
		int tp = 0;
		tp = (int) (totalRows/totalPages);
		if(totalRows%totalPages != 0){
			++tp;
		}
		return tp;
	} 

	/**
	 * 
	 * @Title: getPageHtml
	 * @Description: 生成分页页码
	 * @author: 许智皓
	 * @param itemUrl
	 * @param params
	 * @param page
	 * @param layout 布局，  right/null:默认为右边  left:左边 
	 * @return
	 * String
	 */                       
	public static String getPageHtml(String itemUrl, Map<String,Object> params, Pager page, String layout, String onClick){
		
		
		StringBuffer param = new StringBuffer();
		StringBuffer ph = new StringBuffer();

		Iterator<Entry<String, Object>> entryKeyIterator = params.entrySet().iterator();  
	    while (entryKeyIterator.hasNext()) {  
	    	   
	    	Entry<String, Object> e = entryKeyIterator.next();  
	    	if(!"startRow".equals(e.getKey()) && !"endRow".equals(e.getKey())){

	    		param.append("&");param.append(e.getKey());param.append("=");
	    		
	    		if(null == e.getValue() || "null".equals(e.getValue()) || "NULL".equals(e.getValue())){
	    			param.append("");
	    		}
	    		else{
	    			param.append(e.getValue());
	    		}
	    		
	    	}
	    } 

		if(1 < page.getTotalPages()){
			
			if(null == onClick || "".equals(onClick)) {
				onClick = "";
			}else {
				onClick = "onclick=\""+onClick+"\"";
			}
			
			ph.append("<span style=\"display:inline-block;margin-top:7px;margin-right:10px;\">共");
			ph.append(page.getTotalRows());ph.append("条记录&nbsp;&nbsp;");ph.append(page.getCurrentPage());
			ph.append("/");ph.append(page.getTotalPages());ph.append("页&nbsp;</span>");
			
			if("left".equals(layout)){
				ph.append("<div style=\"float: right;\"><ul class=\"pagination\">");
			}
			else{
				ph.append("<div style=\"float: right;\"><ul class=\"pagination\">");
			}
			
		
			if(1 == page.getCurrentPage()){
				
				ph.append("<li class=\"page-item disabled\"><a class=\"page-link\" href='javascript:;'>首页</a></li><li class=\"page-item disabled\"><a class=\"page-link\" href='javascript:;'>上一页</a></li>");
			}
			else{
				ph.append("<li class=\"page-item\"><a class=\"page-link\" href='javascript:;'");
				ph.append(onClick.replace("#", "1"));ph.append(">"); ph.append("首页</a></li>");
				
				ph.append("<li class=\"page-item\"><a class=\"page-link\"  href='javascript:;'");
				ph.append(onClick.replace("#", String.valueOf(page.getCurrentPage()-1)));ph.append(">"); ph.append("上一页</a></li>");
			}
			
		    int begin = 1;//开始的页数
		    int end = 0;//结束的页数
		    
		    if(6 > page.getCurrentPage()){

		    	end = 10;//结束的页数
		    }
		    else{
		    	end = page.getCurrentPage() + 5;//结束的页数
		    }
		    
		    if(page.getCurrentPage() >= 5){
		    	
		    	begin = page.getCurrentPage() - 4;
	        }
		    
		    if( end >= page.getTotalPages()){ //结束页数 大于等于总页数
		    	
		    	end = page.getTotalPages();
	        }
		    for(int i = begin; i <= end; i++){
				if(i == page.getCurrentPage()){
					ph.append("<li class=\"page-item active\"><a class=\"page-link\" href='javascript:;'>");
					ph.append(i);ph.append("</a></li>");
				}
				else{
					
					ph.append("<li class=\"page-item\"><a class=\"page-link\" href='javascript:;' ");
					ph.append(onClick.replace("#", String.valueOf(i)));
					ph.append(onClick);ph.append(">");ph.append(i);ph.append("</a></li>");
					
					
				}
			}
	
			if(page.getTotalPages() == page.getCurrentPage()){
				
				ph.append("<li class=\"page-item disabled\"><a class=\"page-link\" href='javascript:;'>下一页</a</li>");
				
				ph.append("<li class=\"page-item disabled\"><a class=\"page-link\" href='javascript:;'>尾页</a</li>");

			}
			else{
				
				ph.append("<li class=\"page-item\"><a class=\"page-link\" href='javascript:;'");
				ph.append(onClick.replace("#", String.valueOf(page.getCurrentPage()+1)));
				ph.append(">"); 
				ph.append("下一页</a></li>");
				
				ph.append("<li class=\"page-item\"><a class=\"page-link\"  href='javascript:;'");
				ph.append(onClick.replace("#", String.valueOf(page.getTotalPages())));
				ph.append(">"); ph.append("尾页</a></li>");
			}
		}

		ph.append("</ul></div>");
		return ph.toString();
		
	  
	}
	
	
	
	/**
	 * 
	 * @Title: getPageHtml3
	 * @Description: 生成分页页码
	 * @author: 支付开发部-许智皓
	 * @param itemUrl
	 * @param params
	 * @param page
	 * @param layout 布局，  right/null:默认为右边  left:左边 
	 * @return
	 * String
	 */
	public static String getPageHtml3(String itemUrl,Map<String,Object> params,Pager page,String layout){
		
		StringBuffer param = new StringBuffer();
		StringBuffer ph = new StringBuffer();

		Iterator<Entry<String, Object>> entryKeyIterator = params.entrySet().iterator();  
	    while (entryKeyIterator.hasNext()) {  
	    	   
	    	Entry<String, Object> e = entryKeyIterator.next();  
	    	if(!"startRow".equals(e.getKey()) && !"endRow".equals(e.getKey())){

	    		param.append("&");param.append(e.getKey());param.append("=");
	    		
	    		if(null == e.getValue() || "null".equals(e.getValue()) || "NULL".equals(e.getValue())){
	    			param.append("");
	    		}
	    		else{
	    			param.append(e.getValue());
	    		}
	    		
	    	}
	    } 

		if(1 < page.getTotalPages()){

			ph.append(" <div class='table_bottom clearfix'>");
			
			if("left".equals(layout)){
				ph.append(" <div class='page left'> ");
			}
			else{
				
				ph.append(" <div class='page right'> ");
			}

			ph.append("<span>共");ph.append(page.getTotalRows());ph.append("条记录&nbsp;&nbsp;");ph.append(page.getCurrentPage());
			ph.append("/");ph.append(page.getTotalPages());ph.append("页&nbsp;</span>");
			
			if(1 == page.getCurrentPage()){
				
				ph.append("<a href='javascript:;' class='pre2' title='首页'  >首页</a>&nbsp;<a class='pre' href='javascript:;' title='上一页' >上一页</a>&nbsp;");
			}
			else{
				ph.append("<a class='pre2' href='");ph.append(itemUrl);ph.append("?currentPage=1");
				ph.append(param.toString());ph.append("' title='首页' ></a>&nbsp;");
				
				ph.append("<a class='pre' href='");ph.append(itemUrl);ph.append("?currentPage=");ph.append(page.getCurrentPage()-1);
				ph.append(param.toString());ph.append("' title='上一页' ></a>&nbsp;");
			}
			
		    int begin = 1;//开始的页数
		    int end = 0;//结束的页数
		    
		    if(6 > page.getCurrentPage()){

		    	end = 10;//结束的页数
		    }
		    else{
		    	end = page.getCurrentPage() + 5;//结束的页数
		    }
		    
		    if(page.getCurrentPage() >= 5){
		    	
		    	begin = page.getCurrentPage() - 4;
	        }
		    
		    if( end >= page.getTotalPages()){ //结束页数 大于等于总页数
		    	
		    	end = page.getTotalPages();
	        }
		    for(int i = begin; i <= end; i++){
				if(i == page.getCurrentPage()){
					ph.append("<a class='current' href='javascript:;'>");
					ph.append(i);ph.append("</a>&nbsp;");
				}
				else{
					
					ph.append("<a href='");ph.append(itemUrl);ph.append("?currentPage=");ph.append(i);
					ph.append(param.toString());ph.append("'>");ph.append(i);ph.append("</a>&nbsp;");
				}
			}
	
			if(page.getTotalPages() == page.getCurrentPage()){
				
				ph.append("<a class='next' href='javascript:;' title='下一页'></a>&nbsp;<a class='next2'  href='javascript:;' title='尾页' ></a>");
			}
			else{
				
				ph.append("<a class='next' href='");ph.append(itemUrl);ph.append("?currentPage=");ph.append(page.getCurrentPage()+1);
				ph.append(param.toString());ph.append("' title='下一页' ></a>&nbsp;");
				
				ph.append("<a class='next2'  href='");ph.append(itemUrl);ph.append("?currentPage=");ph.append(page.getTotalPages());
				ph.append(param.toString());ph.append("' title='尾页' ></a>");
			}
		}

		ph.append("</div></div>");
		return ph.toString();
		
	}
	
	
	//分页计算
	public Pager setPagerContent(String page_ei, String rows_ei, Pager page,
			Long totalRows, Integer pageSize) {

		try {
			if (null == rows_ei || "".equals(rows_ei)) {
				rows_ei = Pager.PAGE_SIZE + "";
			}

			if (null == page_ei || "".equals(page_ei)) {
				page_ei = "1";
			}

			pageSize = Integer.parseInt(rows_ei);
		} catch (Exception e) {
		}

		if (null == page) {

			page = new Pager(totalRows, pageSize);

		} else {

			try {
				page.setCurrentPage(Integer.parseInt(page_ei));
			} catch (Exception e) {
			}

			page.setPageSize(pageSize);
			if (0 >= page.getCurrentPage()) { // 查看当前页码
				page.setCurrentPage(1);
			}
			page.setStartRow((page.getPageSize() * page.getCurrentPage())
					- page.getPageSize());// 开始 - 页条数
			page.setEndRow(page.getPageSize() * page.getCurrentPage());// 结束 -
																		// 页条数
		}

		page.setTotalRows(totalRows); // 总行数
		page.setTotalPages(Pager.mathTotalPages(totalRows, page.getPageSize()));// 总页数

		return page;
	}


	//Oracle 分页计算
	public static Pager setPagerOracle(Pager page, Long totalRows, Integer pageSize) {

			if (null == page) {
				page = new Pager(totalRows, pageSize);
			} else {

				if (null != pageSize) {
					page.setPageSize(pageSize);
				}

				if (0 >= page.getCurrentPage()) { // 查看当前页码
					page.setCurrentPage(1);
				}
				page.setStartRow((page.getPageSize() * page.getCurrentPage())
						- page.getPageSize());// 开始 - 页条数
				page.setEndRow(page.getPageSize() * page.getCurrentPage());// 结束 -
																			// 页条数
			}
			page.setTotalRows(totalRows); // 总行数
			page.setTotalPages(Pager.mathTotalPages(totalRows, page.getPageSize()));// 总页数

			//调整分页细节
			
			page.setStartRow(page.getStartRow() + 1);
			
			return page;
		}
	
	

	/**
	 * 
	 * @Title: setPagerMysql
	 * @Description: mysql 分页计算
	 * @author: 许智皓
	 * @param page
	 * @param totalRows 总数
	 * @param pageSize 每页多少行
	 * @return
	 * Pager
	 */
	public static Pager setPagerMysql(Pager page, Long totalRows, Integer pageSize) {

			if (null == page) {
				page = new Pager(totalRows, pageSize);
			} else {

				if (null != pageSize) {
					page.setPageSize(pageSize);
				}

				if (0 >= page.getCurrentPage()) { // 查看当前页码
					page.setCurrentPage(1);
				}
				page.setStartRow((page.getPageSize() * page.getCurrentPage())
						- page.getPageSize());// 开始 - 页条数
			
				page.setEndRow(page.getPageSize());
			}
			page.setTotalRows(totalRows); // 总行数
			page.setTotalPages(Pager.mathTotalPages(totalRows, page.getPageSize()));// 总页数

			return page;
		}
	
	public int getStartRow() {
		return startRow;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setTotalRows(Long totalRows) {
		this.totalRows = totalRows;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public Long getTotalRows() {
		return totalRows;
	}

	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}

	public void first() {
		currentPage = 1;
		startRow = 0;
	}

	public void previous() {
		if (currentPage == 1 || currentPage == 0) {
			return;
		}
		currentPage--;
		startRow = (currentPage - 1) * pageSize;
	}

	public void next() {
		if (currentPage < totalPages) {
			currentPage++;
		}
		startRow = (currentPage - 1) * pageSize;
	}

	public void last() {
		currentPage = totalPages;
		startRow = (currentPage - 1) * pageSize;
	}

	public int getEndRow() {
		return endRow;
	}
	
	
	public static Map<String, Object>  getParamPage(Map<String, Object> param, long tableCount, int pageSize) {
		
		Pager pager = new Pager(tableCount, pageSize);
		
		if(null != param.get(ApiConconts.PAGE)) {
			try {
				pager.setCurrentPage(Integer.parseInt(param.get(ApiConconts.PAGE).toString()));	
			} catch (Exception e) {
				pager.setCurrentPage(1);	
			}
		}else {
			pager.setCurrentPage(1);	
		}
		
		pager = Pager.setPagerMysql(pager, pager.getTotalRows(), pager.getPageSize());
		
		param.put("startRow", pager.getStartRow());
		param.put("endRow", pager.getEndRow());
		
//		if(null != param.get(ApiConconts.ORDER_BY) && StringBase.checkStr(param.get(ApiConconts.ORDER_BY).toString())) {
//			
//			param.put(ApiConconts.ORDER_BY, param.get(ApiConconts.ORDER_BY).toString());
//			
//			if(null != param.get(ApiConconts.DESC_OR_ASC) && StringBase.checkStr(param.get(ApiConconts.DESC_OR_ASC).toString())) {
//				
//				param.put(ApiConconts.DESC_OR_ASC, param.get(ApiConconts.DESC_OR_ASC).toString());
//			}
//		}
		
		return param;
	}
	

	public static void main(String[] args) {
	    
	    Long deptCount = (long) 47;
	    int paDept = 10;
	    
	    Pager page = new Pager(deptCount, paDept); // 生成默认第一页
	    
	    System.out.println(page.getCurrentPage() +"    "+page.getStartRow()+"  "+page.getEndRow());
	    
	    page.setCurrentPage(2);
	    page = Pager.setPagerOracle(page, deptCount, null);
	   
	    System.out.println(page.getCurrentPage() +"    "+page.getStartRow()+"  "+page.getEndRow());
	    
	    
	    page.setCurrentPage(3);
	    page = Pager.setPagerOracle(page, deptCount, null);
	   
	    System.out.println(page.getCurrentPage() +"    "+page.getStartRow()+"  "+page.getEndRow());
	}

}