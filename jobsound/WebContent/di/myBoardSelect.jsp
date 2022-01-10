<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String context = request.getContextPath(); %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous"><link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">	<!-- 부트스트랩 icon CDN -->
<link href="<%=context %>/common/style.css" rel="stylesheet" type="text/css">	<!-- style.css link -->
<script type="text/javascript" src="../js/jquery.js"></script>
<meta charset="UTF-8">
<title>마이페이지-내가 쓴 게시글 선택</title>
<style type="text/css">
	.center{
		margin : 40px;
	}
</style>
</head>
<body>
<%@include file ="../common/header.jsp" %>
<!-- 상단 navbar 영역 -->
<%@include file ="../common/headNav.jsp" %>
<main>
<!-- left side 영역 -->
<%@include file ="../common/myLeftSide.jsp" %>
<!-- section 영역 -->
<section>
	<div class="center">
		<h2><center>내가 쓴 게시글</center></h2>
	</div>
		<nav class="navbar navbar-expand-lg mt-5">
			<div class="mx-auto mt-3">
				<ul class="list-unstyled mx-auto">
					<!-- href 설정 -->
					<li class="nav-item py-1"><a
						class="side_nav nav-link border-bottom border-2" href="<%=context %>/di/mypageBoardList.do?b_type=3&pageNum=${pageNum }">커뮤니티 게시판</a></li>
					<li class="nav-item py-1"><a
						class="side_nav nav-link border-bottom border-2" href="<%=context %>/di/mypageStudyList.do?b_type=2&pageNum=${pageNum }">스터디 모집 게시판</a></li>
				</ul>
			</div>
		</nav>
</section>

</main>
<!-- footer 영역 -->
<%@include file ="../common/footer.jsp" %>
</body>
</html>