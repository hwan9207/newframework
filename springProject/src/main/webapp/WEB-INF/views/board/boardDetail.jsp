<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <style> 
        .content {
            background-color:rgb(247, 245, 245);
            width:80%;
            margin:auto;
        }
        .innerOuter {
            border:1px solid lightgray;
            width:80%;
            margin:auto;
            padding:5% 10%;
            background-color:white;
        }

        table * {margin:5px;}
        table {width:100%;}
    </style>
</head>
<body>
        
    <jsp:include page="../common/menubar.jsp" />

    <div class="content">
        <br><br>
        <div class="innerOuter">
            <h2>게시글 상세보기</h2>
            <br>

            <a class="btn btn-secondary" style="float:right;" href="">목록으로</a>
            <br><br>

            <table id="contentArea" align="center" class="table">
                <tr>
                    <th width="100">제목</th>
                    <td colspan="3">${ board.boardTitle}</td>
                </tr>
                <tr>
                    <th>작성자</th>
                    <td>${ board.boardWriter}</td>
                    <th>작성일</th>
                    <td>${ board.createDate}</td>
                </tr>
                <tr>
                    <th>첨부파일</th>
                    <c:choose>
                    	<c:when test="${empty board.originName }">
	        	            <td colspan="3">
								파일이 존재하지 않습니다.
	  	         	         </td>
                    	</c:when>
                    	<c:otherwise>
	        	            <td colspan="3">
	    	                    <a href="${ board.changeName }" 
	    	                       download="${ board.originName }">${ board.originName }</a>
	  	         	         </td>
                    	</c:otherwise>
                    </c:choose>
                </tr>
                <tr>
                    <th>내용</th>
                    <td colspan="3"></td>
                </tr>
                <tr>
                    <td colspan="4"><p style="height:150px;">${ board.boardContent}</p></td>
                </tr>
            </table>
            <br>

            <div align="center">
            	<c:if test="${ sessionScope.loginUser.userId eq requestScope.board.boardWriter }">
                <!-- 수정하기, 삭제하기 버튼은 이 글이 본인이 작성한 글일 경우에만 보여져야 함 -->
	                <a class="btn btn-primary" onclick="postSubmit(this.innerHTML);">수정하기</a>
                	<a class="btn btn-danger" onclick="postSubmit(this.innerHTML);">삭제하기</a>
                </c:if>
                <form action="" method="post" id="postForm">
	                <input type="hidden" name="boardNo" value="${board.boardNo }"/>
	                <input type="hidden" name="filePath" value="${board.changeName }"/>
                </form>
                
                <script>
                	function postSubmit(e1){
                		//consloe.log(e1);
                		/*
                		if('수정하기' === e1){
                			$('#postForm').attr('action', 'boardUpdateForm.do').submit();
                		} else {
                			$('#postForm').attr('action', 'boardDeleteForm.do').submit();
                		}
                		*/
                		const attrValue = '수정하기' === e1 ? 'boardUpdateForm.do' : 'boardDelete.do';
               			$('#postForm').attr('action', attrValue).submit();
                	}
                </script>
            </div>
            <br><br>

            <!-- 댓글 기능은 나중에 ajax 배우고 나서 구현할 예정! 우선은 화면구현만 해놓음 -->
            <table id="replyArea" class="table" align="center">
                <thead>
                
                	<c:choose>
	                    <c:when test="${ empty sessionScope.loginUser }">
		                    <tr>
		                        <th colspan="2">
		                            <textarea class="form-control" name="" id="content" cols="55" rows="2" style="resize:none; width:100%;"></textarea>
		                        </th>
		                        <th style="vertical-align:middle"><button class="btn btn-secondary">등록하기</button></th> 
		                    </tr>
	                    </c:when>
	                    <c:otherwise>
		                    <tr>
		                        <th colspan="2">
		                            <textarea class="form-control" name="" id="content" cols="55" rows="2" style="resize:none; width:100%;"></textarea>
		                        </th>
		                        <th style="vertical-align:middle"><button onclick="saveReply();" class="btn btn-secondary">등록하기</button></th> 
		                    </tr>
		                    <tr>
		                        <td colspan="3">댓글(<span id="rcount"></span>)</td>
		                    </tr>
	                    </c:otherwise>
                	</c:choose>

                </thead>
                <tbody>
                    
                </tbody>
            </table>
        </div>
        <br><br>

    </div>
    
    <script>
    	function saveReply(){
    		if($('#content').val().trim() != ''){
    			$.ajax({
    				url : 'reply',
    				data : {
    					refBoardNo : ${ board.boardNo },
    					replyContent : $('#content').val(),
    					replyWriter : '${ sessionScope.loginUser.userId}' //el은 서버에서 사용.. 
    					// java랑 script 구분...? ' '없이 el로 보내면 그냥 변수로 인식함 문자열로 데이타 보내줘야함
    				},
    				type : 'post',
    				success : result => {
    					if(result == 'success') {
    						selectReply();
    						$('#content').val('');
    					}
    				}
    				
    					
    			});
    		} else {
    			alert('장난치지마라 새갸');
    		}
    	}
    	
    	$(() =>{
    		selectReply();
    	});
    
    	function selectReply() {
    		$.ajax({
    			url  : 'reply',
    			type : 'get',
    		 	data : {
    		 		boardNo :  ${ board.boardNo } //게시판에 클릭해서 들어갈때 model에 게시판 객체 값이 저장되어 있음
    		 	},
    		 	success : result => {
    		 		//consloe.log(result);
    		 		
    		 		let resultStr = '';
    		 		
    		 		for(let i in result){
    		 			resultStr += '<tr>'
    		 						+ '<td>' + result[i].replyWriter + '</td>'
    		 						+ '<td>' + result[i].replyContent + '</td>'
    		 						+ '<td>' + result[i].createDate + '</td>'
    		 						+ '</tr>';
    		 		}
    		 		$('#replyArea tbody').html(resultStr);
    		 		$('#rcount').text(result.length);
    		 	}
    		});
    	}
    </script>
    
    <jsp:include page="../common/footer.jsp" />
    
</body>
</html>