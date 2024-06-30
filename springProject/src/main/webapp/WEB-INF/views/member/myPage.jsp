<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    
</head>
<body>
    
    <jsp:include page="../common/menubar.jsp" />

    <div class="content">
        <br><br>
        <div class="innerOuter">
            <h2>마이페이지</h2>
            <br>
            
        

            <form action="update.do" method="post">
                <div class="form-group">
                    <label for="userId">* ID : </label>
                    <input type="text" class="form-control" id="userId" value="${sessionScope.loginUser.userId}" name="userId" readonly> <br>

                    <label for="currentPWd">* Current PW : </label> <br>
                    <input style="width:100%; float: left;" type="password" class="form-control" id="currentPWd"  > <br>
                    <div id="checkResult" style="display:none; font-size:0.7em;"></div> <br>								
					<button type="button" style="margin-left:15px; margin-bottom:30px; " id="pwCheck" class="btn btn-primary" onclick="sibar();">확 인</button> <br>

                    <label for="userPwd2">* New PW : </label>
                    <input type="password" class="form-control" id="userPwd2"  name="userPwd" readonly> <br>
                    <div id="checkResult1" style="display:none; font-size:0.7em;"></div> <br>								

                    <label for="userPwd1">* Check PW : </label>
                    <input type="password" class="form-control" id="userPwd1"  readonly> <br>
                    
                    <label for="userName">* Name : </label>
                    <input type="text" class="form-control" id="userName" value="${sessionScope.loginUser.userName}" name="userName" required> <br>

                    <label for="email"> &nbsp; Email : </label>
                    <input type="text" class="form-control" id="email" value="${sessionScope.loginUser.email}" name="email"> <br>

                    <label for="age"> &nbsp; Age : </label>
                    <input type="number" class="form-control" id="age" value="${sessionScope.loginUser.age}" name="age"> <br>

                    <label for="phone"> &nbsp; Phone : </label>
                    <input type="tel" class="form-control" id="phone" value="${sessionScope.loginUser.phone}" name="phone"> <br>
                    
                    <label for="address"> &nbsp; Address : </label>
                    <input type="text" class="form-control" id="address" value="${sessionScope.loginUser.address}" name="address"> <br>
                    
                    <label for=""> &nbsp; Gender : </label> &nbsp;&nbsp;
                    <input type="radio" id="Male" value="M" name="gender">
                    <label for="Male">남자</label> &nbsp;&nbsp;
                    <input type="radio" id="Female" value="F" name="gender">
                    <label for="Female">여자</label> &nbsp;&nbsp;
                    
                    <script>
                    	window.onload = () => {
                    		document.querySelector(`input[value=${sessionScope.loginUser.gender}]`).checked = true;
                    	}
                    	/*
                    		$(()=> {
                    			$('input[value=${sessionScope.loginUser.gender}]').attr('checked',true);
                    		})
                     	*/
                    </script>
                </div> 
                <br>
                <div class="btns" align="center">
                    <button type="submit" class="btn btn-primary" id="editBtn">수정하기</button>
                    <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#deleteForm">회원탈퇴</button>
                </div>
            </form>
        </div>
        <br><br>
        
    </div>

    <!-- 회원탈퇴 버튼 클릭 시 보여질 Modal -->
    <div class="modal fade" id="deleteForm">
        <div class="modal-dialog modal-sm">
            <div class="modal-content">

                <!-- Modal Header -->
                <div class="modal-header">
                    <h4 class="modal-title">회원탈퇴</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>

                <form action="delete.do" method="post">
                	<input type="hidden" value="${sessionScope.loginUser.userId }" name="userId"/>
                    <!-- Modal body -->
                    <div class="modal-body">
                        <div align="center">
                            탈퇴 후 복구가 불가능합니다. <br>
                            정말로 탈퇴 하시겠습니까? <br>
                        </div>
                        <br>
                            <label for="userPwd" class="mr-sm-2">Password : </label>
                            <input type="text" class="form-control mb-2 mr-sm-2" placeholder="Enter Password" id="userPwd" name="userPwd"> <br>
                    </div>
                    <!-- Modal footer -->
                    <div class="modal-footer" align="center">
                        <button type="submit" class="btn btn-danger" onclick="return deletePrompt();">탈퇴하기</button>
                    </div>
                </form>
                <script>
                
	                $(document).ready(function() {
				           // 비밀번호 입력 필드의 keyup 이벤트에 이벤트 핸들러 추가
				           $('#userPwd2, #userPwd1').keyup(function() {
				               const userPwd2 = $('#userPwd2').val();
				               const userPwd1 = $('#userPwd1').val();
				               const checkResult1 = $('#checkResult1');
				               
				               if (userPwd2 === userPwd1) {
				                   checkResult1.show().css('color', 'green').text('비밀번호가 일치합니다.');
				               } else {
				                   checkResult1.show().css('color', 'red').text('비밀번호가 일치하지 않습니다.');
				               }
				           });
				       });
                
                	function deletePrompt(){
                		//const value = prompt("탈퇴 사유를 입력해주세요.");
                		//console.log(value);
                		/* 
                		if(value ===){
                			// submit 요청을 보내 탈퇴 처리
                			return true;
                		} else {
                			// submit 요청을 보내지 못하도록 
                			return false;
                		}
                		*/
                		return prompt("탈퇴를 원하시면 어쩌고언 입력해주세요.") === '어쩌고언' ? true : false;                
               		}
                	
			       	// 로그인한 회원의 session 비밀번호랑 입력한 비밀번호가 같은지 확인
			       function sibar() {
			            const checkPwd = $('#currentPWd').val();
			            const checkResult = $('#checkResult');
			            if (checkPwd != "") {
			                $.ajax({
			                    url: 'pwCheck',
			                    type: 'GET',
			                    data: { checkPwd: checkPwd },
			                    success: function(result) {
			                        if (result == 'success') {
			                            checkResult.show().css('color', 'green').text('비밀번호가 일치합니다.');
			                            $('#userPwd2').removeAttr("readonly");
			                            $('#userPwd1').removeAttr("readonly");
			                        } else {
			                            checkResult.show().css('color', 'red').text('비밀번호가 일치하지 않습니다.');
			                        }
			                    },
			                    error: function() {
			                        checkResult.show().css('color', 'red').text('오류가 발생했습니다.');
			                    }
			                });
			            } else {
			                checkResult.show().css('color', 'red').text('비밀번호를 입력해주세요.');
			            }
			        }
			       	
			       
       				
			       	/*
			       	function newPwChek(){
			       		
			            const checkResult = $('#checkResult1');
			            
			       		if($('#userPwd2').val() eq $('#userPwd1').val()){
                            checkResult.show().css('color', 'green').text('비밀번호가 일치합니다.');
			       			return true;
			       		}else{
			       			checkResult.show().css('color', 'red').text('비밀번호가 일치하지 않습니다.');
			       			return false;
			       		}
			       	}
			       	
			       	*/
			       	/*
			       	$(() => {
			       		const newpWd = $('.form-group #userPwd1');
	            		const checkResult = $('#checkResult1');
	            		const editBtn = $('#editBtn');
	            		
	            		newpWd.keyup(() => {
	            			if($('#userPwd2').val() eq $('#userPwd1').val()){
	                            checkResult.show().css('color', 'green').text('비밀번호가 일치합니다.');
				       			return true;
				       		}else{
				       			checkResult.show().css('color', 'red').text('비밀번호가 일치하지 않습니다.');
				       			editBtn.attr('disabled',true);
				       			return false;
				       		}
	            		}
			       	}
			      */ 	
                </script>
            </div>
        </div>
    </div>

    <jsp:include page="../common/footer.jsp" />

</body>
</html>