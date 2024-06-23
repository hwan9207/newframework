package com.kh.spring.board.model.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.kh.spring.board.model.vo.Board;

public interface BoardService {

	// 게시글 전체 조회 + 페이징 처리
		// 페이징처리를 위해서 => >>>현재 Board테이블의 총 행의 개수<<< 알아야 함.
	int boardCount();
	
	// 그룹함수 5총사(반드시 알아야 함)
	//
	// SUM()
	// AVG()
	// MIN()
	// MAX()
	// COUNT()
	
	//게시글 목록 조회
	List<Board> findAll(Map<String, Integer> map);
		// 배열은 못 씀 : 인덱스의 개수를 선언하고 시작해야 하는데, board의 개수가 몇 개일지 모르므로
	
	// 게시글 검색 기능
	int searchCount(Map<String, String> map);
	
	// 검색 목록 조회
	List<Board> findByConditionAndKeyword(Map<String,String> map, RowBounds rowBounds);
	
	// 게시글 작성
	int insert(Board board);
	
	// 게시글 상세보기
	// 조회수 증가
	int increaseCount(int boardNo);	//성공할 수도, 실패할 수도 있음(눌렀을 때 게시글 삭제 등)
	
	// 상세조회
	Board findById(int boardNo);
	
	// 게시글 삭제하기
	int delete(int boardNo);
	
	// 게시글 수정하기
	int update(Board board);
	
	// ---------------------------------------- 댓글 관련 (AJAX)
	
	// 1. AJAX를 활용한 댓글 목록 조회 ---> 2. MyBatis 기술을 이용한 댓글 조회
	
	// 댓글 작성하기
	
	
	// ---------------------------------------- Top-N
}
