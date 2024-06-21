package com.kh.spring.board.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.kh.spring.board.model.vo.Board;


public interface BoardService {

	 
	/* 
	 * 게시글 전체 조회 + 페이징처리
	 * 현재 Board테이블의 총 행의 개수
	 * 
	 * SQL 그룹함수 5총사
	 * SUM() AVG() MIN() MAX() COUNT()
	*/
	// 게시글 목록 조회
	// Board[] boards = new Board[10]; 배열은 index값을 어느정도 인지 모르기 때문에 사용 부적합
	List<Board> findAll(Map<String, Integer> map);
	
	// 게시글 검색 기능
	 int searchCount();
	 
	// 검색 목록 조회
	 List<Board> searchAll();
	
	// 게시글 작성
	int insert(Board board);
	
	/*
	 * DML TRANSACTION
	 */
	// 게시글 상세보기
	// 조회수 증가
	int increaseCount(int boardNo);
	
	// 상세조회
	Board findById(int boardNO);
	
	// 게시글 삭제하기
	int delete(int boardNo);
	// 게시글 수정하기
	int update(int boardNo);

	int boardCount();
	
	// -------------------------------------------------------------------댓글 관련
	/*
	 * 1. ajax 활용한 댓글 목록 조회 2. MyBatis 기술을 이용한 댓글 조회
	 * 댓글 작성하기
	 */

	//---------------------------------------------------------------------Top-N

}
