<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<title>ajax</title>
</head>
<body>
	<h1>Spring에서의 비동기 통신 활용법</h1>
	
	<h3>AJAX</h3>
	
	<p> 
	
	Asynchronous JavaScript And XML의 약자 <br> 
	요청을 보내서 화면 전체를 받아오는게 아니고 특정 데이터 부분만 변경 가능하도록 한다 <br>
	서버로부터 데이터를 응답받아 전체 페이지를 다시 만드는것이 아니라 일부만 내용물을 변경 할 수 있는 기법 <br><br>
	
	a태그 / form 태그 이용해서 요청한 방식은 동기식 요청 <br>
	=> HTML 전체를 응답받아서 브라우저는 처음부터 끝까지 화면을 랜더링해야 결과를 확인할 수 있었음 <br>
	
	비동기 처리를 위해서는 AJAX라는 기술을 사용 <br>
	* AJAX구현 방식 : JavaScript / jQuery / fetchAPI / axios .. <br>
	* 동기 / 비동기 차이 <br>
	- 동기식 : 서버가 요청 처리 후 응답 HTML 데이터가 돌아와야만 그 다음 작업이 가능 <br>
			만약 서버에서 응답페이지를 돌려주는 시간이 지연되면 무작정 기다리고 있어야함 <br>
			응답데이터가 돌아오면 전체화면을 피싱 <br>
	- 비동기식 : 현재 페이지를 그대로 유지하는 동안 중간중간에 추가 요청을 보낼 수 있음 <br>
			  요청을 보낸다고해서 다른 페이지가 새롭게 랜더링 되는것이 아님 (현재 페이지가 그대로 유지) <br>
			  요청을 보내놓고 응답이 올때까지 다른 작업을 할 수 있음 <br> 
			  
	  EX) 예약 , 중복확인 , 검색어 자동완성 등 오늘 날 SPA는 전부 비동기 처리 <br>
	  
	  * 비동기식 단점 <br>
	  - 요청 후 돌아온 응답데이터를 가지고 현재 페이지에서 새로운 요소를 동적으로 만들어줘야함 <br>
	  => DOM 요소를 새롭게 만드는 구문을 잘 익혀둬야함 <br>
	  - 페이지 내 복잡도가 기하급수적으로 증가 <br>
	
	</p>
	
	<pre>
		*jQuery에서의 AJAX통신 <br>
		1. 객체 || 2. 배열
		
		[표현법] <br>
		
		$.ajax({                 <br>
			속성명 : 속성값 ,        <br>
			속성명 : 속성값 ,        <br>
			속성명 : 속성값          <br> 
			...
		
		});
		
		*주요속성 : 
		- url : 요청할 url (필수로 작성) = > form태그의 action속성           <br>
		- type : 요청 전송방식(GET/POST/PUT/DELETE = > 생략 시 기본값은 get) <br>
		- data : 요청시 전달할 값 ({키:벨류, 키:벨류..}) => form 태그의 input요소의 value 속성 <br>
		- success : AJAX통신 성공 시 콜백함수를 정의 <br>
		----------------------------------------------------<br>
		- error : AJAX통신 실패시 콜백함수를 정의 <br>
		- complete : AJAX통신이 성공하든 실패하든 무조건 끝나면 실행 할 콜백함수 <br>
		- async : 서버와 비동기 처리방식 설정 여부 (기본값 true) <br>
		- contentType : 요청시 데이터 인코딩 방식 정의 (보내는 측의 데이터 인코딩) <br>
		- dataType : 서버에서 응답시 돌아오는 데이터의 형태 설정, 작성 안하면 스마트하게 판단함 <br>
		
		
	</pre>
	
	<h4> AJAX로 요청 보내고 응답 받아오기</h4>
	<h5> 1. 서버로 요청 시 인자값을 전달하고 응답데이터를 받아서 화면에 출력</h5>
	
	<label>오늘 먹을거 :</label> 알밥, 돈까스, 서브웨이, 김치찜, 막국수 <br>

	메뉴 : <input type="text" id="menu" /> <br>
	수량 : <input type="number" id="amount" value ="0" required /> <br>
	
	<button id="btn">서버로 전송</button>
	 
	 <div id="resultMsg">
	 
	 </div>
	 
	 <!-- 버튼을 클릭 시 => 메뉴에 입력한 음식명과 수량에 입력한 개수를 가지고 서버에 요청해서 => 
	       응답받은 응답 데이티를 div요소의 content영역에 출력 -->
	 
	<script>
		
		document.getElementById('btn').onclick = () => {
			
			const menuValue = document.getElementById('menu').value;
			const amountValue = document.getElementById('amount').value;
			
			$.ajax({
				url : 'ajax1.do', //필수정의 속성(매핑값)
				type : 'get',	  //요청 시 전달 방식
				data : { // 요청시 전달 값 (key-value)
					menu : menuValue,
					amount : amountValue
				},
				success : result => {
					//console.log(result);
					const resultValue = '선택하신 메뉴' + menuValue +''+ amountValue + '개의 가격은' +  result + '원 입니다.';
					document.getElementById('resultMsg').innerHTML= resultValue;
				},
				error : () => {
					console.log('오류');
				}
			});
		}
			
	</script>
	
</body>
</html>