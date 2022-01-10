<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>headNav</title>
<!-- 부트스트랩 CDN -->

</head>
<body>
	<nav class="navbar navbar-expand-lg">
		<div class="container-fluid">
			<div class="collapse navbar-collapse justify-content-md-center">
				<ul class="navbar-nav">
					<!-- href 설정 -->
					<li class="nav-item px-5"><a class="head_nav nav-link"	
						href="../yr/notiList.do">공지사항</a></li>
					<li class="nav-item px-5"><a class="head_nav nav-link"
						href="../sh/comuBoardList.do">커뮤니티</a></li>
					<li class="nav-item px-5"><a class="head_nav nav-link"
						href="../gm/newsindex.do">취업기사</a></li>
				<c:choose>
					<c:when test="${sessionScope.sessionID=='admin' }">
							<li class="nav-item px-5"><a class="head_nav nav-link"
							href="../sw/mngMemList.do">관리자페이지</a></li>
					</c:when>
					<c:when test="${sessionScope.sessionID==null }">
							<li class="nav-item px-5"><a class="head_nav nav-link"
							href="#">마이페이지</a></li>
					</c:when>
					<c:otherwise>
							<li class="nav-item px-5"><a class="head_nav nav-link"
							href="../di/mypageCheck.jsp">마이페이지</a></li>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${sessionScope.sessionID eq null}">
						 
					</c:when>
					<c:when test="${sessionScope.sessionID eq 'admin'}">
						 
					</c:when>
					<c:otherwise>
						<li class="nav-item px-5"><a class="head_nav nav-link"
							href="../di/inquireWriteForm.do">문의사항</a></li>
						<!-- <li class="nav-item px-5"><a class="head_nav nav-link"
							href="../di/inquireWriteForm.do">문의사항</a></li> -->
					</c:otherwise>
				</c:choose>
				</ul>
			</div>
		</div>
	</nav>
</body>
</html>