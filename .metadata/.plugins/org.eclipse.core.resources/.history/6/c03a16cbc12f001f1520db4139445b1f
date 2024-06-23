package com.kh.spring.string;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StringController extends Object {	//모든 클래스는 최고클래스인 Object클래스를 상속받음

	// String 클래스 => 불변
	
//* String 클래스의 객체 생성 방법
	
	//2. 생성자를 호출해서 String 객체로 만들어주는 방법
	public void constructorString() {
		String str1 = new String("Hello");	//Hello = 리터럴
		String str2 = new String("Hello");
		
		String[] strArr = {};
		
		System.out.println(str1.toString());	//println은 출력을 위해 자동으로 .toString() 메서드가 붙음.
			//.toString()은 최고 클래스인 Object 클래스의 기본 제공 메서드. 오버라이딩??
		System.out.println(str2);
		
		System.out.println(strArr);
		
		
		// 1. String 클래스의 toString의 경우
		// 실제 담겨있는 문자열 리터럴을 반환하게끔 오버라이딩(by Object 클래스)
		
		// equals()
		System.out.println(str1.equals(str2));		// => true 나올 것.
		// true
		// 주소값 비교가 아닌 실제 문자열 리터럴값을 비교하도록 오버라이딩

		// hashCode()
		// 16진수는 알아보기 힘드니까 => int형 10진수
		System.out.println(str1.hashCode());
		System.out.println(str2.hashCode());
		System.out.println("Hello".hashCode());
		
		// 주소값을 해싱하는 것이 아니라 실제 담긴 문자열을 기반으로 해시코드값을 만들어서 반환
		
		// 진짜 식별할 수 있는 값
		//System.identityHashCode()
		System.out.println(System.identityHashCode(str1));
		System.out.println(System.identityHashCode(str2));
	}
	
	
	//리터럴 대입 방식
	public void assignToString() {
		
		String str1 = "Hello";
		String str2 = "Hello";
		
		//toString()
		System.out.println(str1);
		System.out.println(str2);
		
		//equals()
		System.out.println(str1.equals(str2));
		
		//hashCode()	//주소값 같음
		System.out.println(str1.hashCode());
		System.out.println(str2.hashCode());
		
		//System.identityHashCode()		//주소값 같음
		System.out.println(System.identityHashCode(str1));
		System.out.println(System.identityHashCode(str2));
		
		//리터럴 같음
		System.out.println(str1 == str2);
	}
	
	public void stringPool() {
		
		String str = "Hello";
		//대입연산자를 이용해 문자열 리터럴값을 대입 시
		// StringPool 영역에 string 값이 올라감.

		String newStr = "Hello";
		//StringPool 특징 : 동일한 내용의 문자열 리터럴이 존재할 수 있음.
		
		str = "Bye";
		System.out.println(System.identityHashCode(str));
		//연결이 끊긴 문자열은 가비지콜렉터가 정리해줌.("Hello")
		//객체는 불변
		//참조변수는 새로운 주소값을 참조!
		
		str += "!!";
		
		str = str + "!!";
		
		
		String a = "a";
		String b = "a";
		System.out.println("결과 : " + a == b);
		// 연산자 우선순위 : +연산자 > 동등비교연산자
		// "결과 : a" == "a" 로 비교됨.
		
		log.info("결과 : {}", a == b);
	}
	
}
