package com.kh.spring.member.model.vo;

import java.sql.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @NoArgsConstructor
 * @AllArgsConstructor
 * @Builder
 * @Getter
 * @Setter
 * @ToString
 *
 * @Data
 * 
 * - Lombok 사용 시 주의사항!!
 * 
 * - 시작 글자가 외자인 필드명은 안된다.
 * - productName -> 올바른 표기법
 * - pName	  -> getPName()
 * - userName -> getUserName()
 * 
 * => EL표기법을 이용할 때 내부적으로 getter사용방법 !
 * 
 * ${ pName } == getpName()
 * 
 * 필드명 작성 시 최소 소문자 두 글자 이상으로 시작할 것!
 * 
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Member {
	
	private String userId;
	private String userPwd;
	private String userName;
	private String email;
	private String gender;
	private String age;
	private String phone;
	private String address;
	private Date enrollDate;
	private Date modifyDate;
	private String status;
}














