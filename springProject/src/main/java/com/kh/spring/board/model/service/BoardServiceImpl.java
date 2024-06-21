package com.kh.spring.board.model.service;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import com.kh.spring.board.model.repository.BoardRepository;
import com.kh.spring.board.model.vo.Board;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
	
	private final SqlSessionTemplate sqlSession;
	private final BoardRepository boardRepository;
	
	@Override
	public List<Board> findAll(Map<String, Integer> map) {
		
		return boardRepository.findAll(sqlSession,map);
	}

	@Override
	public int searchCount(Map<String, String> map) {
		return boardRepository.searchCount(sqlSession,map);
	}

	@Override
	public List<Board> searchAll() {
		
		return null;
	}

	@Override
	public int insert(Board board) {
		
		return 0;
	}

	@Override
	public int increaseCount(int boardNo) {
		
		return 0;
	}

	@Override
	public Board findById(int boardNO) {
		
		return null;
	}

	@Override
	public int delete(int boardNo) {
		
		return 0;
	}

	@Override
	public int update(int boardNo) {
		
		return 0;
	}

	@Override
	public int boardCount() {
		return boardRepository.boardCount(sqlSession);
	}

}
