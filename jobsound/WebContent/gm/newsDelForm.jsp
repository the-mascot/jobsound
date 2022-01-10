<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<%
	String context = request.getContextPath();
%>
<head>
<meta charset="UTF-8">
<title>글 삭제</title>
<!-- 부트스트랩 CDN -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
<!-- 부트스트랩 icon CDN -->
<link href="../common/style.css" rel="stylesheet" type="text/css">
</head>
<body>

<!-- header 부분 -->
<%@include file ="../common/header.jsp" %>

<!-- 상단 navbar 영역 -->
<%@include file ="../common/headNav.jsp" %>

<!-- main 영역 : main 안에 left_side - section - right_side로 3등분 됨 2:6:2 비율 -->
<main>
<!-- left side 영역 -->
<%@include file ="../common/newsLeftSide.jsp" %>

<!-- section 영역 -->
<section>
	<input type="hidden" name="pageNum" value="${board.pageNum }">
	<input type="hidden" name="b_num" value="${board.b_num }">
	<input type="hidden" name="b_type" value="${board.b_type }">
	<!-- 게시판 설명 -->
	<div>
		<div>
			<h2 class="mt-5 mb-3 mx-5 fw-bold" style="font-family: 'jalnan'; color: #5798D7">JOB 뉴스</h2>
		</div>
		<div>
			<p class="mt-2 mx-5 fs-6 text-muted fst-italic" style="font-family: 'jalnan'">취업과 관련된 모든 뉴스를 만나보세요.</p>
		</div>
	</div>
	<div class="board_search_parent">
		<div class="board_search d-flex">
			<div>
				<input type="text" class="form-control" placeholder="Search" maxlength="255">
			</div>
			<div>
				<input type="button" class="board_search_button btn" value="검 색" style="background-color: #5798D7; margin-left: 5px;">
			</div>
		</div>
	</div>
	<!-- 삭제 묻기 -->
	<div class="container" align="center">
		<h3 align="center" style="margin-top: 60px; font-family: 'jalnan'">정말 삭제하시겠습니까?</h3>
		<div class="container" style="margin-top: 30px">
			<a class="btn btn-primary" href='<%=context %>/gm/deletePro.do?b_num=${b_num}&pageNum=${pageNum}&b_type=${b_type}' style="margin-right: 15px" role="button">확인</a>
			<a class="btn btn-primary" href='<%=context %>/gm/content.do?b_num=${b_num}&pageNum=${pageNum}&b_type=${b_type}' role="button">취소</a>
		</div>
	</div>

</section>

<!-- right side 영역 -->
<c:choose>
	<c:when test="${sessionScope.sessionID==null }">
		<%@include file ="../common/guestForm.jsp" %>
	</c:when>
	<c:otherwise>
		<%@include file ="../common/loginForm.jsp" %>
	</c:otherwise>
</c:choose>
</main>

<!-- footer 영역 -->
<%@include file ="../common/footer.jsp" %>
</body>
</html>