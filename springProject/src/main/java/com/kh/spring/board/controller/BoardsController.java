package com.kh.spring.board.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.spring.board.model.service.BoardService;
import com.kh.spring.board.model.vo.Board;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/*
 * Mapping 값은 notice로 통일
 * 
 * C : INSERT => POSTMapping
 * R : SELECT => GETMapping
 * U : UPDATE => PUTMapping
 * D : DELETE => DELETEMapping
 * 
 */

// 이 컨트롤러는 /boards로 시작하는 요청이 들어오면 처리를 해줄 ..
// @RestController 
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value="boards", produces="application/json; charset=UTF-8")
public class BoardsController {
	
	private final BoardService boardService;
	
	@GetMapping
	public List<Board> findTopFiveBoard() {
		return boardService.findTopFiveBoard();
	}
	
	@GetMapping("/{boardNo}")
	public Board findByBoardNo(@PathVariable int boardNo) {
		//log.info("넘어온 num : {}",boardNo);
		return boardService.findById(boardNo);
	}
}
