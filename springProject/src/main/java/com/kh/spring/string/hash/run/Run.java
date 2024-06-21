package com.kh.spring.string.hash.run;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.kh.spring.string.hash.model.vo.Student;

public class Run {

	public static void main(String[] args) {
		
		// HashSet
		// Value값만 저장, index가 존재하지 않음!
		// 순서보장 x, 중복 x
		
		// 문자열만 담을 수 있는 HashSet
		HashSet<String> set = new HashSet<String>();

		ArrayList<String> list = new ArrayList<String>();
		List l = new ArrayList();
		
		/*
		 * - 제네릭 사용 이유
		 * 
		 * 1. 개발자의 편의를 위해서 사용 -> 강제 형변환을 할 필요가 없음
		 * 2. 실수 방지용. 혹여나 의도하지 않는 타입의 값이 들어가지 않도록 하기 위함
		 * 
		 */
		
		l.add("Hello");
		System.out.println(((String)l.get(0)).charAt(0));
		
		list.add("Hello");
		System.out.println(list.get(0).charAt(0));
		
		//System.out.println(set.toString());
		
		set.add("안녕하세요");
		set.add("안녕히가세요");
		set.add("반갑습니다");
		set.add("안녕하세요");
		set.add(new String("안녕하세요"));
		
		System.out.println(set);
		
		System.out.println("\n\n\n\n");
		
		//Student
		Set<Student> students = new HashSet<Student>();
		
		students.add(new Student("이승철", 10, 50));
		students.add(new Student("홍길동", 15, 100));
		students.add(new Student("제임스고슬링", 60, 80));
		students.add(new Student("홍길동", 15, 100));
		
		System.out.println(students);
		// 저장 순서 보장 X, 중복 저장 X
		//				-> 중복 저장 O
		
		System.out.println(new Student("abc", 1, 1).hashCode());
		System.out.println(new Student("abc", 1, 1).hashCode());
		System.out.println(new Student("abc", 2, 1).hashCode());
		
		// 이유? 동일 객체로 판단하지 않기 때문!
		// HashSet : 요소가 새롭게 추가 될 때마다 equals()와 hashCode()로 비교 후
		// 			 둘 다 결과가 true일 경우 동일 객체 판단
		
		// equals() : 현재 객체의 주소값을 비교해서 결과를 반환 : boolean
		// hashCode() : 현재 객체의 주소값을 해싱알고리즘을 돌려서 10진수로 반환
	}
}



















