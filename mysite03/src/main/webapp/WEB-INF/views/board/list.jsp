<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/board.css"
	rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="board">
				<form id="search_form" action="${pageContext.servletContext.contextPath }/board" method="post">
					<input type="hidden" name="a" value="list">
					<input type="hidden" name="search" value="search">
					<input type="text" id="kwd" name="kwd" value="${kwd }"> 
					<input type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>
					<c:set var='count' value='${fn:length(list) }' />
					<c:set var='newline' value='\n' />
					<c:forEach items='${list }' var='vo' varStatus='status'>
						<tr>
							<td>${vo.no }</td>
							<td style="text-align:left; padding-left:${vo.depth * 10}px" >
								<c:if test="${vo.depth > 0 }">
									<img src='${pageContext.servletContext.contextPath }/assets/images/reply.png' />
								</c:if>
								<a  href="${pageContext.request.contextPath }/board/view/${vo.no}?page=${currentPage}">${vo.title }</a>
							</td>
							<td>${vo.userName }</td>
							<td>${vo.hit }</td>
							<td>${vo.regDate }</td>
							<td>
								<c:if test="${authUser.no eq vo.userNo }">
									<a href="${pageContext.request.contextPath }/board?a=deleteform&postNo=${vo.no}&page=${currentPage}">
										<img width="15px" src='${pageContext.servletContext.contextPath }/assets/images/recycle.png'/>
									</a>
								</c:if>
							</td>
							
						</tr>
					</c:forEach>

				</table>
				
				<!-- pager 추가 -->
				<div class="pager">
					<ul>
						<c:if test="${currentPage > pageBlock}">
							<li><a href="${pageContext.request.contextPath }/board?no=${endPage - pageBlock}&page=${endPage - pageBlock}">◀</a></li>
						</c:if>
						<c:forEach var="i" begin="${startPage }" end="${endPage }" step="1" varStatus="status" >
							<c:if test="${currentPage == i }">
								<li class="selected">${i }</li>
							</c:if>
							<c:if test="${currentPage != i && i <= pageCount}">
								<li><a href="${pageContext.request.contextPath }/board?page=${i}&kwd=${kwd}">${i }</a></li>
							</c:if>
							<c:if test="${currentPage != i && i > pageCount }">
								<li>${i }</li>
							</c:if>
						</c:forEach>
						
						<c:if test="${endPage < pageCount}">
							<li><a href="${pageContext.request.contextPath }/board?no=${endPage + 1 }&page=${endPage + 1}">▶</a></li>
						</c:if>
					</ul>
				</div>					
				<!-- pager 추가 -->
				
				<div class="bottom">
					<a href="${pageContext.request.contextPath }/board/write?page=${currentPage}" id="new-book">글쓰기</a>
					
				</div>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp" />
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>