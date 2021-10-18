<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />

		<div id="content">
			<div id="board">
				<form style="text-align: center; height: 100px" method="post" action = "${pageContext.request.contextPath }/board">
					<input type="hidden" name="a" value="delete">
					<input type='hidden' name="postNo" value="${param.postNo }">
					<input type='hidden' name="page" value="${param.page }">
					<label>비밀번호</label> <input	type="password" name="password"> 
					<input type="submit" value="확인">
				</form>
				<div class="bottom">
					<a href="${pageContext.request.contextPath }/board" id="new-book">취소</a>
				</div>
				
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp" />
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>