<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스프링 연습</title>
<style>
	.innerOuter{
		height: 500px;
	}
</style>
</head>
<body>
	<jsp:include page="common/menubar.jsp"/>
	
	<div class="innerOuter">
		<h3>게시글 조회수 TOP 5</h3>
		<br>
		<a href="boardList" style="float:right; color:lightgray; font-size:14px; font-weight:800">더 보기</a>
	  	<br><br>
	  	
	  	<table class="table table-hover" align="center" id="boardList">
	  		<thead>
	  			<tr>
	  				<th>글번호</th>
                    <th>제목</th>
                    <th>작성자</th>
                    <th>조회수</th>
                    <th>작성일</th>
	  			</tr>
	  		</thead>
	  		<tbody>
	  		
	  		</tbody>
	  	</table>
	  	<br>
	  	<table id="board-detail" class="table">
	  	
	  	</table>
	  
	  	
	</div>
	
	<script>
		/*
			상세보기
			조회된 게시글 목록에서
			게시글을 클릭하면
			선택한 글번호를 가지고 
			하나의 게시글 정보를
			AJAX 요청을 통해 응답 받아서
			ID속성값이 board-detail인 table에 자식요소를 만들어서 출력
		*/
		
		$(document).on('click', '#boardList > tbody > tr', e =>  {
			const boardNo = $(e.currentTarget).children().eq(0).text();
			$.ajax({
				url : 'boards/' + boardNo,
				type : 'get',
				success : result => {
					let value = '<tr><td colspan="3">게시글 상세보기</td></tr>';
					
					value += '<tr>'
 						+ '<td width ="300">' + result.boardTitle   + '</td>'
 						+ '<td width ="600">' + result.boardContent + '</td>'
 						+ '<td width ="200">' + result.boardWriter  + '</td>'
 						+ '</tr>';
 						
 						document.getElementById('board-detail').innerHTML = value;
				}
			});
		});
		
		
	    // 동적인 요소 - 처음 화면에 정의되어 있지 않고 요청에 의해 나중에 생기는..
	    // 그래서 동적인 요소로 생성된 .. class id 접근이 안된다
		$(() => {
			findTopFiveBoard();
		})
		
		function findTopFiveBoard(){
			$.ajax({
				url : 'boards',
				type : 'get',
				success : boards => {
					//console.log(boards);
					
					let value = "";
					for(let i in boards){
						value += '<tr>'
	 						+ '<td>' + boards[i].boardNo + '</td>'
	 						+ '<td>' + boards[i].boardTitle + '</td>'
	 						+ '<td>' + boards[i].boardWriter + '</td>'
	 						+ '<td>' + boards[i].count + '</td>'
	 						+ '<td>' + boards[i].createDate + '</td>'
	 						+ '<td>';
	 						if(boards[i].originName != null){
	 							value += '★';
	 						}
	 						+'</td></tr>';
					}
					$('#boardList tbody').html(value);
				}
				
			});
		}
	</script>
	<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
	
	<jsp:include page="common/footer.jsp"/>
</body>
</html>