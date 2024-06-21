<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스프링 연습</title>
<style>
	#content{
		height: 500px;
	}
</style>
</head>
<body>
		<c:if test="${not empty alertMsg}">
			<script>
				alertify.alert('titleMsg','${alertMsg}');
			</script>
			<c:remove var = "alertMsg" scope="session"/>
		</c:if>
	<jsp:include page="common/menubar.jsp"/>
	
	<div id="content">
		
	</div>
	
	<jsp:include page="common/footer.jsp"/>
</body>
</html>