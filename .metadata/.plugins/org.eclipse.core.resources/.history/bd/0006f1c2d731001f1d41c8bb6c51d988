package com.kh.spring.board.model.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.kh.spring.board.model.vo.Board;

@Repository
public class BoardRepository {

	public int boardCount(SqlSessionTemplate sqlSession) {
		return sqlSession.selectOne("boardMapper.boardCount");
	}
	
	public List<Board> findAll(SqlSessionTemplate sqlSession, Map<String, Integer> map) {
		return sqlSession.selectList("boardMapper.findAll",map);
		//sqlSession.selectList("boardMapper.findAll", null, rowBounds);
		// RowBounds 객체를 넘겨야 할 경우
		// selectList()의 오버로딩된 형태중 매개변수가 3개인 메소드로 반드시 꼭 꼭 꼭 무조건 절대로 이걸로 호출해야함!!
	    // 두 번째 인자값으로 전달할 값이 없다면 null값을 넘기면됨!

	}
	
	public int searchCount(SqlSessionTemplate sqlSession, Map<String, String> map) {
		return sqlSession.selectOne("boardMapper.searchCount", map);
	}
	
	// RowBounds 이용 List 출력
	public List<Board> findByConditionAndKeyword(SqlSessionTemplate sqlSession, Map<String, String> map,
			RowBounds rowBounds) {
		return sqlSession.selectList("boardMapper.findByConditionAndKeyword", map, rowBounds);
	}
	
	// Board객체 insert(글+첨부파일 업로드)
	public int insert(SqlSessionTemplate sqlSession, Board board) {
		return sqlSession.insert("boardMapper.insert", board);
	}
	
}
