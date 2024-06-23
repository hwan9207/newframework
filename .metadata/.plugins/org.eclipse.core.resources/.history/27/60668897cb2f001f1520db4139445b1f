package com.kh.spring.board.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.kh.spring.board.model.service.BoardService;
import com.kh.spring.board.model.vo.Board;
import com.kh.spring.common.model.vo.PageInfo;
import com.kh.spring.common.template.PageTemplate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
@RequiredArgsConstructor
public class BoardController {

	private final BoardService boardService;
	
	
	// menubar.jsp에서 클릭 시 => boardlist
	// 페이징바 클릭 =>  boardlist?page=요청페이지	=> @RequestParam 이용(MemberController 2번 참고)
	@GetMapping("boardList")
	public String forwarding(@RequestParam(value="page", defaultValue="1") int page, Model model) {
		
		// -- 페이징 처리 --
		
		// RowBounds 안 쓴 거
		// RowBounds 쓴 거
		
		// 필요한 변수들
		int listCount;		// 현재 일반게시판의 게시글 총 개수 => BOARD테이블로부터 SELECT COUNT(*) 활용해서 조회
		int currentPage;	// 현재 페이지(사용자가 요청한 페이지) => 앞에서 넘길 것
		int pageLimit;		// 페이지 하단에 보일 페이징바의 최대 개수 => 10개로 고정
		int boardLimit;		// 한 페이지에 보여질 게시글의 최대 개수 => 10개로 고정
		
		int maxPage;		// 가장 마지막 페이지가 몇 번 페이지인지(총 페이지의 개수)
		int startPage;		// 페이지 하단에 보여질 페이징바의 시작 수
		int endPage;		// 페이지 하단에 보여질 페이징바의 끝 수
		
		// * listCount : 총 게시글 수
		listCount = boardService.boardCount();
		
		// currentPage : 현재 페이지(사용자가 요청한 페이지)
		currentPage = page;
		
		log.info("게시글의 총 개수 : {}, 현재 요청 페이지 : {}", listCount, currentPage);
		
		// * pageLimit = 10; : 페이징바의 최대 개수
		pageLimit = 10;
		
		// * boardLimit = 한 페이지에 보여질 게시글의 최대 개수
		boardLimit = 10;
		
		// * maxPage : 가장 마지막 페이지가 몇 번 페이지인지(총 페이지 개수) => 유동적
		/*
		 * listCount, boardLimit에 영향을 받음
		 * 
		 * - 공식 구하기
		 *   단, boardLimit이 10이라는 가정 하에 규칙 찾기
		 *   
		 *   총 개수(listCount)			게시글 개수(boardLimit)			maxPage(마지막페이지)
		 *   100						10						==		10.0	10페이지
		 *   106						10						==		11페이지
		 *   111						10						==		12페이지
		 *   
		 *   => 나눗셈 결과에 소수점 붙여서 올림처리 할 경우 maxPage가 됨
		 *   
		 *   스텝
		 *   1. listCount를 double로 변환
		 *   2. listCount / boardLimit
		 *   3. Math.ceil() => 결과를 올림처리
		 *   4. 결과값을 int로 형변환
		 */
		
		maxPage = (int)Math.ceil((double)listCount / boardLimit);
		
		//Math math = new Math();
		/*
		 * Math 클래스는 생성자는 있으나 생성되지 않음
		 * WHY? -> 생성할 필요가 없음. static 클래스라서
		 * 
		 * 모든 메서드에 static이 붙어있음
		 * Math 등의 클래스들은 static이므로 heap메모리에 올라가지 않음. 항상 존재
		 * Math.random()
		 */
		
		// * startPage
		/*
		 * currentPage, pageList에 영향을 받음
		 * 
		 * - 공식
		 * 	단, pageLimit이 10이라고 가정
		 * 
		 * - startPage : 1, 11, 21, 31, 41 => n * 10 + 1
		 * 
		 * 만약 pageLimit이 5다 !
		 * - startPage : 1, 6, 11, 16... => n * 5 + 1
		 * 
		 * 즉, startPage = n * pageLimit + 1
		 * 
		 * 
		 * currentPage			startPage
		 * 		1					1	
		 * 		5					1
		 * 		9					1
		 * 		10					1
		 * 		13					11
		 * 		15					11
		 * 		20					11
		 * 		21					21
		 * 
		 * n = (currentPage - 1) / pageLimit;
		 * 
		 * startPage = (currentPage - 1) / pageLimit * pageLimit + 1;
		 */
		startPage = (currentPage - 1) / pageLimit * pageLimit + 1;

		// * endPage : 페이지 하단에 보여질 페이징바의 끝 수
		/*
		 * startPage, pageLimit에 영향받음(단, maxPage도 마지막 페이징바에 대해 영향을 끼침)
		 * 
		 * - 공식
		 *   단, pageLimit이 10이라고 가정
		 *   
		 * startPage : 1 => endPage : 10
		 * startPage : 11 => endPage : 20
		 * startPage : 21 => endPage : 30
		 * 
		 * => endPage = startPage + pageLimit - 1;
		 */
		endPage = startPage + pageLimit - 1;
		// startPage가 1이라서 endPage가 10이 들어갔는데
		// maxPage가 2다??
		// endPage를 maxPage값으로 변경
		if(endPage > maxPage) endPage = maxPage;
		
		// 변수가 너무 많음. 한 군데에 담아야겠는데 뭐에 담지???
		// 자바에서 다뤄야 할 값이 많을 때
		
		// 1. 클래스(common/PageInfo.java)
		/*
		PageInfo pageInfo = new PageInfo(listCount, currentPage, pageLimit, boardLimit, maxPage, startPage, endPage);
		=> 매개변수 지저분하고 헷갈림
		*/
		// @Builder 애노테이션 이용 - 넣고 싶은 필드만도 이용 가능
		PageInfo pageInfo = PageInfo.builder()
									.listCount(listCount)
									.currentPage(currentPage)
									.pageLimit(pageLimit)
									.boardLimit(boardLimit)
									.maxPage(maxPage)
									.startPage(startPage)
									.endPage(endPage)
									.build();	//각 필드에 값을 넣어 객체를 만들어줌!!!
									// 값을 잘못 넣을 일이 없음, 매개변수 넣는 순서 상관 없음
		
		/*
		 * boardLimit이 10이라는 가정 하에
		 * 
		 * currentPage == 1 => 시작값 1, 끝값 10
		 * currentPage == 2 => 시작값 11, 끝값 20
		 * currentPage == 3 => 시작값 21, 끝값 30
		 * 
		 * 시작값 = (currentPage - 1) * boardLimit + 1;
		 * 끝값 = 시작값 + boardLimit -1;
		 */
		
		
		// 2. Map 그냥 맵 쓸래
		Map<String, Integer> map = new HashMap();
		
		int startValue = (currentPage - 1) * boardLimit + 1;
		int endValue = startValue + boardLimit - 1;
		
		map.put("startValue", startValue);
		map.put("endValue", endValue);
		
		List<Board> boardList = boardService.findAll(map);
		/*
		log.info("조회된 게시물의 개수 : {}", boardList.size());
		log.info("-----------------------------------------");
		log.info("조회된 게시글 목록 : {}", boardList);
		*/
		model.addAttribute("list", boardList);
		model.addAttribute("pageInfo", pageInfo);
		
		
		
		return "board/list";
	}
	
	//검색기능(조건 조회 + 페이징 처리_)
	@GetMapping("search.do")
	public String search(String condition,
						String keyword,
						@RequestParam(value="page", defaultValue = "1") int page,
						Model model) {
		
		log.info("검색 조건 : {}",condition);
		log.info("검색 키워드 : {}",keyword);
		
		// 사용자가 선택한 조건과 입력한 키워드를 가지고
		// 페이징 처리를 끝낸 후 검색 결과를 들고 가야 함.
		
		// "writer", "title", "content"
		// 사용자가 입력한 키워드 (뭐가 들어올지 모름)
		// 이 두 개를 DB까지 가져가야 하는데
		// => String[], List, ((Set)-제외. 사용자가 똑같은 걸 쓸 수 있다?), Map, Class
		
		// Map 쓰겠다(통일성 있어야 함)
		Map<String, String> map = new HashMap();
		map.put("condition", condition);
		map.put("keyword", keyword);
		
		//검색결과 수
		int searchCount = boardService.searchCount(map);
		log.info("검색 조건에 부합하는 행의 수 : {}", searchCount);
		int currentPage = page;
		int pageLimit = 3;
		int boardLimit = 3;
		
		/* 페이징 넣는 페이지들의 공통영역으로 common/PageTemplate에 넣어둠
		
		int maxPage = (int)Math.ceil((double)searchCount / boardLimit);
		int startPage = (currentPage - 1) / pageLimit * pageLimit + 1;
		int endPage = startPage + pageLimit -1;
		if(endPage > maxPage) endPage = maxPage;
		
		PageInfo pageInfo = PageInfo.builder().pageLimit(pageLimit)
										.startPage(startPage)
										.endPage(endPage)
										.listCount(searchCount)
										.currentPage(currentPage)
										.maxPage(maxPage)
										.boardLimit(boardLimit)
										.build();
										
		*/			
		PageInfo pageInfo = PageTemplate.getPageInfo(searchCount,
													 currentPage,
													 pageLimit,
													 boardLimit);
		
		
		// MyBatis에서 제공하는 >>>RowBounds()<<<
		// offset, limit
		RowBounds rowBounds = new RowBounds();
		
		// MyBatis에서는 페이징 처리를 위해 RowBounds라는 클래스를 제공
		// * offset, limit
		
		/*
		 * boardLimit이 3일 경우				건너뛸 숫자(offset)
		 * 
		 *   currentPage : 1 ->		1~3 	==> 	0
		 *   currentPage : 2 ->		4~6 	==> 	3
		 *   currentPage : 3 ->		7~9 	==> 	6
		 *   
		 *   (currentPage() - 1) * boardLimit()
		 */
		
		List<Board> boardList = boardService.findByConditionAndKeyword(map, rowBounds);
		
		model.addAttribute("list", boardList);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("keyword", keyword);	//model에 값을 담아 jsp에서 el로 띄워줄 수 있음
		model.addAttribute("condition", condition);
		
		return "board/list";
	}
	
	@GetMapping("boardForm.do")
	public String boardFormForwording() {
	    return "board/insertForm";
	}
	
	@PostMapping("insert.do")
	public String insert(Board board,
						 HttpSession session,	//프로젝트 내 파일의 경로 찾기 위해 applicationContext 쓸 목적
						 MultipartFile upfile,	// MultipartFile[] 여러 개의 파일이 배열로 한 번에 들어옴
						 Model model) {	
		//파일의 정보는 MultipartFile 객체 'upfile'에 담겨서 옴. (객체명은 jsp의 form의 name속성에 설정한 이름으로)
		
		// log.info("게시글정보 : {}", board);
		// log.info("파일 정보 : {}", upfile);
		
		// 첨부파일 존재 O / X
		// Multipart객체는 무조건 생성됨!!!!!
		// => fileName필드에 원본명이 존재하는가 / 없는가로 존재유무 구분
		
		// 전달된 파일이 존재할 경우 => 파일 업로드!!
		
		if(!upfile.getOriginalFilename().equals("")) {	//파일첨부명이 빈 문자열과 같지 않으면(비어있지 않으면)
			// 파일명이 다른 파일과 겹치면 업로드될 수 없음!
			// KH_년월일시분초_랜덤한값.확장자	<= 카카오톡 파일저장명처럼 만들 것임
			
			//확장자 가져오기
			String originName = upfile.getOriginalFilename();	//원래 이름을 가져와서
			String ext = originName.substring(originName.lastIndexOf("."));	// 파일명에서 substring()(originName의 마지막"."을 기준으로) 뒤의 문자열을 가져오겠다
			// "abc.ddd.txt"
			
			//랜덤한 수 뽑아내기
			int num = (int)Math.random() * 900 + 1;	// 곱하기 값 : 값의 범위	// 더하기 값 : 시작값

					// 0.0 ~ 0.999999...
					// 0.0 ~ 99.999999
					// 0 ~ 99
					// 1(시작값) ~ 100(값의범위(100개))
					// 10 ~ 109
					// 100 ~199
			
			// 날짜
			// log.info("currentTime : {}", new Date());		//뭔... 영어로 나옴
			String currentTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());	//이런 클래스가 있음. 지정한 대로 Date() 함수의 값을 뽑아줌
			
			// 파일 저장할 곳 지정
			String savePath = session.getServletContext().getRealPath("/resources/uploadFiles");	//세션 이용, 본 프로젝트 내 >>>여기서<<< 파일 가져오겠다.
			
			// 이름용으로 만든 거(KH_현재시각_랜덤수_확장자) 다 합치기
			String changeName = "KH_" + currentTime + "_" + num + ext;
			
			// 새 파일 만들기
			// 서버에 파일 업로드 확인 => 제대로 됐으면 파일로의 직접 접근 가능함(누르면 새 창에서 열림)
			try {
				upfile.transferTo(new File(savePath + changeName));		// 새 파일 생성 - File() 메서드(io패키지)	//저장경로 + 새이름
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			// 첨부파일이 존재한다.?
			// 1. 업로드 완료
			// 2. Board객체에 파일의 정보(originName + changeName) 담기
			board.setOriginName(originName);
			board.setChangeName(savePath + changeName);
		}
		
		// 이 이후로 DB 감~
		
		// 첨부파일이 존재하지 않을 경우 board : 제목 / 내용 / 작성자
		// 첨부파일이 존재할 경우 board :		제목 / 내용 / 작성자 / 원본명 / 변경된 경로와 이름
		if(boardService.insert(board) > 0) {
			
			session.setAttribute("alertMsg", "게시글 작성 성공");
			
			// !!! 무조건 리다이렉트 해야 함. 왜? => 현재 DB에 있는 board 데이터를 들고 가야 해서
			// 그냥 return "board/list"; 하면 값이 아무것도 없는 페이지가 나타남
			return "redirect:boardList";
			
		} else {
			
			model.addAttribute("errorMsg", "게시글 작성 실패");
			
			
			return "common/errorPage";
		}
	}

}

