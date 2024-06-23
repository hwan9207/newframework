package com.kh.spring.common.template;

import com.kh.spring.common.model.vo.PageInfo;

//페이지네이션을 쓸 모든 페이지들이 공통으로 가져갈 요소
// 리스트전체개수, 현재페이지, 페이지최대개수, 한페이지에띄울수
public class PageTemplate {

	public static PageInfo getPageInfo(int listCount,
									   int currentPage,
									   int pageLimit,
									   int boardLimit) {
		
		int maxPage = (int)Math.ceil((double)listCount / boardLimit);
		int startPage = (currentPage - 1) / pageLimit * pageLimit + 1;
		int endPage = startPage + pageLimit -1;
		if(endPage > maxPage) endPage = maxPage;
		
		return 	PageInfo.builder().pageLimit(pageLimit)
				.startPage(startPage)
				.endPage(endPage)
				.listCount(listCount)
				.currentPage(currentPage)
				.maxPage(maxPage)
				.boardLimit(boardLimit)
				.build();
	}
	
}
