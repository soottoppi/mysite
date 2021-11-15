<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div id="navigation">
	<ul>
		<c:if test='${not empty authUser && authUser.role == "ADMIN" }'>
			<li><a href="${pageContext.request.contextPath }/admin">관리자 페이지</a></li>
		</c:if>
		<c:if test='${authUser.role != "ADMIN" }'>
			<li><a href="${pageContext.request.contextPath }">김수형</a></li>
		</c:if>

		<li><a href="${pageContext.request.contextPath }/guestbook">방명록</a></li>
		<li><a href="${pageContext.request.contextPath }/guestbook/spa">방명록(SPA)</a></li>
		<li><a href="${pageContext.request.contextPath }/board">게시판</a></li>
		<li><a href="${pageContext.request.contextPath }/gallery">갤러리</a></li>
		
		
	</ul>
</div>