<%@page import="com.douzone.mysite.vo.GuestbookVo"%>
<%@page import="com.douzone.mysite.dao.GuestbookDao"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<% 
	List<GuestbookVo> list = new GuestbookDao().findAll();
%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="<%=request.getContextPath() %>/assets/css/guestbook.css"
	rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<jsp:include page="/WEB-INF/views/includes/header.jsp"></jsp:include>

		<div id="content">
			<div id="guestbook">
				<form action="<%=request.getContextPath() %>/gb?a=add" method="post">
					<input type="hidden" name="a" value="add">
					<table>
						<tr>
							<td>이름</td>
							<td><input type="text" name="name"></td>
							<td>비밀번호</td>
							<td><input type="password" name="password"></td>
						</tr>
						<tr>
							<td colspan=4><textarea name="message" id="content"></textarea></td>
						</tr>
						<tr>
							<td colspan=4 align=right><input type="submit" VALUE=" 확인 "></td>
						</tr>
					</table>
				</form>
				
				<ul>
				<%
				for(GuestbookVo vo : list) {
					int no = list.size() - list.indexOf(vo) ;
				%>
					<li>
						<table>
							<tr>
								<td>[<%=no %>]</td>
								<td><%=vo.getName() %></td>
								<td><%=vo.getDate() %></td>
								<td><a href="<%=request.getContextPath() %>/gb?a=deleteform&no=<%=vo.getNo() %>">삭제</a></td>
							</tr>
							<tr>
								<td colspan=4><%=vo.getMessage().replaceAll("\n", "<br>") %><br>
								</td>
							</tr>
						</table> <br>
						
					</li>
					<%
						}	
					%>
				</ul>
			</div>
		</div>
		<jsp:include page="/WEB-INF/views/includes/navigation.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/views/includes/footer.jsp"></jsp:include>
	</div>
</body>
</html>