package com.kh.ajax.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller

public class AjaxController {
	
	/*
	 *  1. HttpServletResponse 객체로 응답데이터를 응답하기 (Stream을 이용한 방식)
	 *  
	 */
	
	/*
	@GetMapping("ajax1.do")
	public void calSum(String menu,
						int amount,
						HttpServletResponse response) throws IOException { // input value는 문자열로 보내고 int로 받으려면 integer로 parseInt 해야 하는데 
									  //(input요소 값 입력 안하고 보내면 빈문자열로 보냄)빈문자열은 할 수 없어서 오류
		System.out.println("사용자가 입력학 메뉴 : " + menu);
		System.out.println("사용자가 입력학 수량 : " + amount);
	
		int price = 0;
		switch(menu) {
		case "알밥" : price = 10000; break;
		case "김치찜" : price = 12000; break;
		case "돈까스" : price = 15000; break;
		case "막국수" : price = 9000; break;
		case "서브웨이" : price = 8000; 
		}
		
		price *= amount;
		System.out.println(price);
		
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().print(price);
	}
	*/
	
	/*
	 * 응답할 데이터를 문자열로 반환
	 * 		=> HttpServletResPonse를 사용하지 않는 방법
	 * 		=> String 반환하면 포워딩 => 응답뷰의 경로로 인식을해서 뷰 리졸버로 전달
	 * 따라서 스프링을 사용해서 응답데이터를 반활 할때는 
	 * 	 	=> MessageConverter로 이동하게끔 해주어야함 => @ResponseBody
	 */
	@ResponseBody
	@GetMapping(value="ajax1.do", produces="text/html; charset=UTF-8") //produces - 내가 응답해줄 방식 지정
	public String calSum(String menu,int amount) {
						
		int price = 0;
		switch(menu) {
		case "알밥" : price = 10000; break;
		case "김치찜" : price = 12000; break;
		case "돈까스" : price = 15000; break;
		case "막국수" : price = 9000; break;
		case "서브웨이" : price = 8000; 
		}
		
		price *= amount;
		return String.valueOf(price);
	}
}
