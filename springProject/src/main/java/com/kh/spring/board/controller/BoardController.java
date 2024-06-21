package com.kh.spring.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.spring.board.model.service.BoardService;
import com.kh.spring.board.model.vo.Board;
import com.kh.spring.common.model.vo.PageInfo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BoardController {
	
	private final BoardService boardService;

	/*
	 *  페이징 처리
	 *  RowBounds 사용
	 *  
	 *  RowBounds 미사용
	 */
		@GetMapping("boardlist")
		public String forwarding(@RequestParam(value="page" ,defaultValue="1")int page ,Model model) {
			//필요한 변수들
			int listCount;   // 현재 일반게시판의 게시글 총 개수 => board테이블로 부터 SELECT COUNT(*) 활용해서 조회
			int currentPage; // 현재 페이지(사용자가 요청한 페이지) => 앞에서 넘길것
			int pageLimit;   // 페이지 하단에 보여질 페이징비의 최대 개수 => 10개로 고정
			int boardLimit;  // 한 페이지에 보여질 게시글의 최대 개수 => 10개로 고정
			
			int maxPage;	// 가장 마지막 페이지가 몇 번 페이지인지 (총 페이지의 개수)
			int startPage;  // 페이징비의 시작 수
			int endPage;	// 페이징비의 마지막 수 
			
		// 총 게시글의 수 
			listCount = boardService.boardCount();
			currentPage = page;
			pageLimit = 10;
			boardLimit = 10;
			
			// Math.ceil() => 결과를 올림처리 (double로 반환) => 그래서 결과값을 int 형변환 해야함
			// Math은 static => 
			maxPage = (int)Math.ceil((double)listCount / boardLimit);
			startPage = (currentPage - 1) / pageLimit * pageLimit + 1;
			/*
			 *  startPage
			 *  currentPage , pageLimit 에 영향을 받음
			 *  startPage = n * pageLimit + 1
			 *  startPage = (currentPage -1) / 
			 *
			 *  endPage 
			 *  startPage, pageLimit 에 영향을 받음 (maxPage의 영향도 받음)
			 *  endPage = startPage + pageLimit -1;
			 */
			endPage = startPage + pageLimit -1;
			if(endPage>maxPage) endPage = maxPage;
			
			//builder 순서에 구애받지 않는다
			PageInfo pageInfo = PageInfo.builder()
										.listCount(listCount)
										.currentPage(currentPage)
										.pageLimit(pageLimit)
										.boardLimit(boardLimit)
										.maxPage(maxPage)
										.startPage(startPage)
										.endPage(endPage)
										.build();
			Map<String, Integer> map = new HashMap();
			
			int startValue = (currentPage-1)* boardLimit +1;
			int endValue = startValue + boardLimit -1;
			map.put("startValue", startValue);
			map.put("endValue", endValue);
			
			List<Board> boardList = boardService.findAll(map);
			
			model.addAttribute("list", boardList);
			model.addAttribute("pageInfo", pageInfo);
			
			return "board/list";
		}
		
		@GetMapping("search.do")
		public String search(String condition, 
							 String keyword,
							 @RequestParam(value="page",defaultValue="1") int page) {
			log.info("검색 조건 : {}", condition);
			log.info("검색 키워드 : {}", keyword); 
			
			//사용자가 선택한 조건과 입력한 키워드를 가지고
			//페이징처리를 끝낸 후 검색결과를 들고가야함
			
			Map<String, String> map = new HashMap<>();
			map.put("condition", condition );
			map.put("keyword", keyword );
			
			int searchCount = boardService.searchCount(map);
			int currentPage = page;
			int pageLimit = 3;
			int boardLimit = 3;
			
			//RowBounds 는 대용량에서는 성능이 저하된다. 
			
			return "board/list";
		}
		
}
