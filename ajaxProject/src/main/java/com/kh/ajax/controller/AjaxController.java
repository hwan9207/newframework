package com.kh.ajax.controller;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.kh.ajax.model.vo.Menu;

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
	 * 			따라서 스프링을 사용해서 응답데이터를 반활 할때는 
	 * 	 	=> MessageConverter로 이동하게끔 해주어야함 => @ResponseBody
	 */
	 @ResponseBody   //
	   @GetMapping(value="ajax2.do",produces="application/json; charset=UTF-8")
	   public String selectMenu(int menuNumber) {
	      
	      // 요청처리를 잘했다는 가정 하에~~~ 데이터 응답
	      
	      /*
	       * DB에 존재하는 메뉴 테이블
	       * --------------------------------------------
	       * | 메뉴번호 | 메뉴이름 |  가격  |  재료  |
	       * |     1    |  순두부  |  9500  | 순두부 |
	       * ....
	       * --------------------------------------------
	       * => 왜 조회해? : 화면에 출력하려고(4개)
	       * => VO객체는 >>>자바의 객체(메모리주소값)<<<임... 우리는 지금 >>>자바스크립트<<<한테 값을 전달해야 한다.
	       * 
	       *      * 옛날에는 xml로 넘겼다.
	       *       <menuNumber>1</menuNumber>
	       *       <menuName>순두부</menuName>
	       *       <price>9500</price>
	       *       <material>순두부</material>
	       * 
	       * 지금은 >>>!!! ★-- JSON(Java Script Object Notaion) --★ !!!<<< : 자바스크립트 객체 표기법
	       * 
	       * [] : 배열형태
	       * {} : 객체형태
	       * 
	       * But. JAVA와 JSON은 연관이 없다 !!
	       */
	      
	      /* 
	       * 예시
	       * {
	       * "menuNumber" : "1",
	       * "menuName" : "순두부",
	       * "price" : "9500",
	       * "material" : "순두부"
	       * }
	       */
	      
	      // 요청 보내서 결과 잘 받았음.
	      // menuService.findByMenuNumber(menuNumber);
	      Menu menu = new Menu(1, "순두부", 9500, "순두부");   // 자바스크립트가 해석할 수 있는 형태로 보내줘야 함!
	      
	   // 자바스크립트 형태로 보내라고 하면 보낼 수는 있음...
//	      StringBuilder sb = new StringBuilder();
//	      sb.append("{");
//	      sb.append("menuNumber :" + "'" + menu.getMenuNumber() + "',");
//	      sb.append("menuName :"   + "'" + menu.getMenuName()   + "',");
//	      sb.append("price :"      + "'" + menu.getPrice()      + "',");
//	      sb.append("material :"   + "'" + menu.getMaterial()   + "',");
//	      sb.append("}");
	      
	   //   return sb.toString();
	      
	      JSONObject jObj = new JSONObject();
	      jObj.put("menuNumber", menu.getMenuNumber());
	      jObj.put("menuName", menu.getMenuName());
	      jObj.put("price", menu.getPrice());
	      jObj.put("material", menu.getMaterial());
	      
	      return jObj.toJSONString();
	   }
	
	 @ResponseBody
	 @GetMapping(value="ajax3.do",produces="application/json; charset=UTF-8" )
	 public String ajax3Method(int menuNumber) {
		 Menu menu = new Menu(menuNumber, "순두부찌개", 9500 , "순두부");
		 
		 return new Gson().toJson(menu);
	 }
	 
	 @ResponseBody
	 @GetMapping(value="find.do",produces="application/json; charset=UTF-8" )
	 public String findAll() {
		 List<Menu>menus = new ArrayList<>();
		 menus.add(new Menu(1, "순두부찌개", 9500, "순두부"));
		 menus.add(new Menu(2, "김치찌개", 8500, "김치"));
		 menus.add(new Menu(3, "된장찌개", 7500, "된장"));
		 
//		 for(int i=1; i <menus.size(); i++) {
//			 JSONObject jObj = new JSONObject();
//			 jObj.
//		 }
		 
		 return new Gson().toJson(menus);
	 }
}
