package com.kh.spring.common.template;

public class PageTemplate {
	
	public static pageInfo getPageInfo(int listCount,
									   int currentPage,
									   int pageLimit,
									   int boardLimit) {
		
		int maxPage = (int)Math.ceil((double)listCount/boardLimit);
		int startPage = (currentPage-1) / pageLimit *  pageLimit +1 ;
		int endPage = startPage + pageLimit -1 ;
		
	}
}
