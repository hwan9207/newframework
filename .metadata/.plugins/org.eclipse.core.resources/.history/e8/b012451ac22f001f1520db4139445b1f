package com.kh.spring.string.run;

import java.util.HashSet;
import java.util.Set;

import com.kh.spring.string.hash.model.vo.Student;

public class Run {

	public static void main(String[] args) {

		// HashSet
		// Value값만 저장, 배열과 달리 index가 존재하지 않음! (Set의 특징)
		// 순서보장X, 중복X (Set의 특징)
		
		// 문자열만 담을 수 있는 HashSet
		HashSet<String> set = new HashSet();	//<String> : Generic제네릭. 문자열만 담을 수 있는 HashSet
								// 힙메모리에 새로운 HashSet 할당. 주소 저장
		
				//ArrayList<String> list = new ArrayList();
				//List l = new ArrayList();
				// List란? 자료 구조 기반 가상의 개념. JAVA 등 언어에서 실체화
				
		/*
		 개발자의 편의를 위해 사용
		 1. 실수 방지 목적. 혹여나 의도치 않은 타입의 값이 저장소에 들어가지 않도록
		 2. 개발의 편의성. -> 강제형변환 할 필요가 없음.
		 */
				
				//l.add("Hello");
				
				//charAt(0);
				
				//System.out.println(l.get(0).charAt(0));		//불가능. l은 지금 모든 타입을 받을 수 있음(Generic 안 써서)
				//System.out.println(((String)l.get(0)).charAt(0));		//String으로 강제 형변환 해야 charAt 쓸 수 있음(문자열출력)
				
				//list.add("Hello");
				//System.out.println(list.get(0).charAt(0));	//가능. list는 <String>으로 제한되어 있기 때문에
		
		System.out.println(set);	//System.out.println(set.toString());
									// = HashSet 클래스에는 Object클래스의 toString이 오버라이딩 되어 있겠구나
									// 정확히는 AbstractCollection에 상속되어 있음.
		
		// 요소 추가
		set.add("안녕하세요");
		set.add("안녕히가세요");
		set.add("반갑습니다");
		set.add("안녕하세요");
		set.add(new String("안녕하세요"));
		
		System.out.println(set);
		// 저장 순서 보장 X, 중복 저장 X
		
		
		//Student
		Set<Student> students = new HashSet();
		
		students.add(new Student("이승철", 10, 50));
		students.add(new Student("홍길동", 15, 100));
		students.add(new Student("제임스고슬링", 60, 80));
		students.add(new Student("홍길동", 15, 100));
		
		System.out.println(students);
		// 저장 순서 보장 X, 중복 저장 X
		//	-> 중복 저장 O
				//위 2번과 4번이 다 들어감(중복O)
		
		
		System.out.println(new Student("ab",1,1).equals(new Student());
		// 이유? 동일객체로 판단하지 않기 때문!
		//HashSet : 요소가 새롭게 추가될 때마다 equals()와 hashCode()로 비교 후
		//			둘 다 결과가 true일 경우 동일 객체로 판단
		
		//중복이 가능하도록? 하기 위해서는 Object 클래스에서 Student 클래스로 아래 걸 오버라이딩 해야 함.
		// equals() :  현재 객체의 주소값을 비교해서 결과를 반환 : boolean
		// hashCode() : 현재 객체의 주소값을 해싱알고리즘을 돌려서 10진수로 반환 : 
		
	
	}

}
