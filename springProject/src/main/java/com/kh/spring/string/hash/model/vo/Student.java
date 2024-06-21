package com.kh.spring.string.hash.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Student {
	
	private String name;
	private int age;
	private int score;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + score;
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (age != other.age)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (score != other.score)
			return false;
		return true;
	}
	
	/*
	// hashCode()
	@Override
	public int hashCode() {
		
		// new Student("홍길동", 15, 100);
		// 홍길동 15100
		// 홍길동 1 5100
		// 홍길동 151 00
		
		return (name + age + score).hashCode();
	}
	
	// equals()
	@Override
	public boolean equals(Object obj) {
		Student other = (Student) obj;
		
		// 내가 가진 name필드와
		// 매개변수로 전달받은 Student객체의 name필드값을 비교!
		
		// 이름, 나이, 점수
		// 셋 중 하나라도 다르면 false값 반환
		if(!other.name.equals(name) || other.age != this.age || other.score != this.score) {
			return false;
		}
		
		return true;
	}
	*/
}










