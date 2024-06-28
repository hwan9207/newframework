package com.kh.spring.notice.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.spring.notice.model.vo.Notice;

@Mapper
public interface NoticeMapper {

	List<Notice> findAll();
	
	Notice findById(int noticeNo);
	
	int save(Notice notice);
	
	int update(Notice notice);
	
	int delete(int notice);
	
	//이런식으로 하면 어떤지.. 다시 정리하자
	
	/*
	 * mybatis 설정
	 *mapper namespace  <mapper namespace="package com.kh.spring.notice.model.dao.NoticeMapper">
	 */
}
