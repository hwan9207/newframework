package com.kh.spring.string.controller;

public class StringController {
	
	// String 클래스 => 불변(변하지 않음)
	
	int num = 1;
	boolean flag = true;

	/**
		배열의 특징
		1. 배열은 논리적인 구조와 물리적인 구조가 동일하다.
			- 논리 : 실체가 없는 것(배열에서는 index)
			- 물리 : 실체가 있는 것
			
		2. 배열은 불변이다.
	*/
	
	// charArray
	String str;
	
	/**
	 * 	String 클래스의 객체 생성 방법
	 * 
	 * 	1. 대입연산자를 통해서 String 리터럴을 대입하는 방법
	 * 	2. 생성자를 호출해서 String 객체로 만들어주는 방법
	 */
	
	// 생성자를 호출해서 String 객체를 생성
	public void constructorString() {
		
		String str1 = new String("Hello");
		String str2 = new String("Hello");
		
		String[] strArr = {}; 
		
		// toString()
		System.out.println(str1.toString());
		System.out.println(str2);
		
		// System.out.println(strArr.toString());
		
		// 1. String클래스의 toString()의 경우
		// 실제 담겨있는 문자열 리터럴을 반환하게끔 오버라이딩 되어있다.
		
		// equals() : 원래는 객체의 주소값을 비교 
		// String클래스 에서는 주소값 비교가 아닌 문자열 리터럴 값을 비교하도록 오버라이딩
		System.out.println(str1.equals(str2));
		
		// '==' 연산자 : 두 객체의 참조(레퍼런스)를 비교
		// 두 객체가 동일한 메모리 주소를 가리키고 있는지를 확인
		System.out.println(str1 == str2);
		
		// hashCode()
		// 16진수는 알아보기 힘드니까 => int형 10진수
		System.out.println(str1.hashCode());
		System.out.println(str2.hashCode());
		System.out.println("Hello".hashCode());
		
		// 주소값을 해시하는 것이 아니라 실제 담긴 문자열을 기반으로 해시코드값을 만들어서 반환
		
		// 진짜 진짜 진짜 식별할 수 있는 값!
		// System.identityHashCode(참조형변수);
		System.out.println(System.identityHashCode(str1));
		System.out.println(System.identityHashCode(str2));
		// 실제 str1과 str2는 다르다!
		
		System.out.println(str1 == str2);
		
		/*
		 * VO와 DTO차이
		 * VO : 테이블의 값을 보관하기 위함. 값이 변해서는 안됨. 그래서 VO에는 setter가 있으면 안됨
		 * 
		 * DTO : 
		 */
	}
	
	//리터럴 대입 방식
	public void assignToString() {
		
		String str1 = "Hello";
		String str2 = "Hello";
		
		// toString()
		System.out.println(str1);
		System.out.println(str2);
		
		// equals()
		System.out.println(str1.equals(str2));
		
		// hashCode()
		System.out.println(str1.hashCode());
		System.out.println(str2.hashCode());
		
		// System.identityHashCode()
		System.out.println(System.identityHashCode(str1));
		System.out.println(System.identityHashCode(str2));
		
		System.out.println(str1 == str2);
		// A == B => A와 B가 같니?
		// A != B
		// A > B
		// A < B
		// 비교연산자는 의문형으로 작성 및 해석하기
	}
	
	public void stringPool() {
		
		String str = "Hello";
		// 대입연산자를 이용해서 문자열 리터럴 값을 대입 시!
		// StringPool 영역에 올라감
		
		System.out.println(System.identityHashCode(str));
		
		// String newStr = "Hello";
		// StringPool 특징 : 동일한 내용의 문자열 리터럴이 존재할 수 없음
		
		str = "Bye";
		System.out.println(System.identityHashCode(str));
		// 연결이 끊긴 문자열은 가비지콜렉터가 정리해 준다.
		// 객체는 불변
		// 참조변수는 새로운 주소값을 참조!
		
		str += "!!";
		System.out.println(System.identityHashCode(str));
		
		// StringBuffer와 StringBuilder의 차이
		// StringBuffer : 쓰레드 안전성을 보장하기 위해 성능이 약간 떨어질 수 있다.
		//				  메서드가 sychronized로 구현되어 있어서 여러 쓰레드가 동시 접근해도 문제 발생 x
		// StringBuilder : 동기화를 하지 않으므로 일반적으로 'StringBuffer'보다 빠르다.
		//				   단일 쓰레드 환경에서 성능이 더 좋다.
	}
}















