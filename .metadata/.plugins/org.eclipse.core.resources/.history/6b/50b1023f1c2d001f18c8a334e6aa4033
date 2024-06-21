package com.kh.spring.member.model.repository;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.kh.spring.member.model.vo.Member;

// Repository : 저장소
// 영속성 작업 : DB CRUD 작업
// sql문 실행하고 결과 받아오는 것 외의 다른 일은 들어가면안됨.
@Repository
public class MemberRepository {
	
	public Member login(SqlSessionTemplate sqlSession, Member member) {
		return sqlSession.selectOne("memberMapper.login", member);
	}
}
