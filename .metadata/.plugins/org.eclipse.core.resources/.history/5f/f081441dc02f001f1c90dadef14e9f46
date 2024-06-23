package com.kh.spring.string.hash.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Builder
public class Student {

	private String name;
	private int age;
	private int score;
	
	// hashCode()
	@Override
	public int hashCode() {
		// new Student("홍길동",15,100);
		// 홍길동15100
		// 홍길동 1 5100
		// 홍길동 151 00
		// 아래 return : 다 합쳐서 hashCode() 주소값으로 비교 시 다 같은 결과로 인식하는 문제가 발생
		
		
		return (name + age + score).hashCode();		//String 문자열(주소)을 int 10진수로
	}
	
	// equals()
	@Override
	public boolean equals(Object obj) {
		Student other = (Student)obj;
		
		//내가 가진 name 필드와
		//매개변수로 전달받은 Student 객체의 name필드값을 비교 !
		//Object 매개변수(부모클래스)는 Student(자식클래스) 이용 불가
		
		//이름, 나이, 점수
		// 셋 중 하나라도 다르면 false값 반환
		return true;
	}
	
}
