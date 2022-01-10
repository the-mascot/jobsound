<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<% String context = request.getContextPath(); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="view=viewport" content="width=device-width" initial-scale="1.0">
<title>공지사항 리스트</title>
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
<!-- style.css link -->
<script type="text/javascript" src="../js/jquery.js"></script>
<script type="text/javascript">
	$(document).ready(   /* 페이지네이션을 위한 Jquery */
			function() {
				$("#viewCount").change(
						function() {
							var pageNum = "${pageNum}";
							var pageSize = $(this).val();
							var search = $("#search").val();
							location.href = "<%=context%>/yr/notiSearchList.do?pageSize=" + pageSize
									+ "&pageNum=" + pageNum+'&search='+search;
						});
			});
	function search(b_type) {
		var search = $("#search").val();
		location.href="<%=context%>/yr/notiSearchList.do?search="+search+"&b_type="+b_type;
	}
</script>
</head>
<body>

	<!-- header 부분 -->
	<%@include file="../common/header.jsp"%>

	<!-- 상단 navbar 영역 -->
	<%@include file="../common/headNav.jsp"%>


	<!-- main 영역 : main 안에 left_side - section - right_side로 3등분 됨 2:6:2 비율 -->
	<main>
		<!-- left side 영역 -->
		<%@include file="../common/notiLeftSide.jsp"%>

		<!-- section 영역 -->
		<section>

			<div>
				<div>
					<h2 class="mt-5 mb-3 mx-5 fw-bold"
						style="font-family: pretend; color: #5798D7">공지사항 게시판</h2>
				</div>
				<div>
					<p class="mt-2 mx-5 fs-6 text-muted fst-italic"
						style="font-family: pretend">- 게시판설명란</p>
				</div>
			</div>
			<div class="board_search_parent">
				<div class="board_search d-flex">
					<div>
						<input type="text" class="form-control" placeholder="Search"
							maxlength="255" name="search" id="search" value="${search }">
					</div>
					<div>
						<input type="button" class="board_search_button btn" value="검 색"
							style="background-color: #5798D7; margin-right: 20px;" onclick="search(${b_type})">
					</div>
				</div>
			</div>

		<!-- 10개씩 보기 -->
			<div class="view_count_parent">
				<select class="view_count form-select" id="viewCount"
					name="viewCount">
					<option value="10"
						<c:if test="${pageSize=='10'}">selected='selected'</c:if>>10개씩</option>
					<option value="15"
						<c:if test="${pageSize=='15'}">selected='selected'</c:if>>15개씩</option>
					<option value="20"
						<c:if test="${pageSize=='20'}">selected='selected'</c:if>>20개씩</option>
					<option value="30"
						<c:if test="${pageSize=='30'}">selected='selected'</c:if>>30개씩</option>
				</select>
			</div>

		<!-- 게시판 테이블 -->
			<div class="mx-auto my-5">
				<table
					class="board_table table mx-auto table-hover table-responsive text-center">
					<thead style="background-color: rgba(13, 110, 253, 0.1)">
						<tr>
							<th class="texthead" width="70">글번호</th>
							<th class="texthead" width="300">제목</th>
							<th class="texthead" width="100">작성자</th>
							<th class="texthead" width="100">작성일</th>
							<th class="texthead" width="100">조회</th>
						</tr>
					</thead>
					<c:if test="${listSize > 0 }">

						<c:forEach var="board" items="${list }" varStatus="status">
							<tr>
								<td>${totCnt -cnt- status.index}</td>
								<td>
									 <a href='notiContent.do?b_num=${board.b_num }&pageNum=${currentPage}' style="float: left">
											${board.title }</a>
								</td>
								<td>${board.id }</td>
								<td>${board.uploadDate }</td>
								<td>${board.views }</td>
							</tr>
							<c:set var="startNum" value="${startNum-1 }" />
						</c:forEach>
					</c:if>
					<c:if test="${listSize == 0 }">
						<tr>
							<td colspan=7>데이터가 없음</td>
						</tr>
					</c:if>
				</table>
			</div>
			<!-- 게시판 글쓰기-->
			<c:if test="${id eq 'admin' }">
				<div class="write_btn_parent">
					<div class="write_btn">
						<a href="notiWriterForm.do?pageNum=${currentPage }"
							class="write_btn btn btn-outline-primary"
							style="border: 1px solid rgba(65, 75, 70, 0.5); color: black; margin-right: 50px;">
							<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
								fill="currentColor" class="bi bi-pen" viewBox="0 0 16 16">
	  			<path
									d="m13.498.795.149-.149a1.207 1.207 0 1 1 1.707 1.708l-.149.148a1.5 1.5 0 0 1-.059 2.059L4.854 14.854a.5.5 0 0 1-.233.131l-4 1a.5.5 0 0 1-.606-.606l1-4a.5.5 0 0 1 .131-.232l9.642-9.642a.5.5 0 0 0-.642.056L6.854 4.854a.5.5 0 1 1-.708-.708L9.44.854A1.5 1.5 0 0 1 11.5.796a1.5 1.5 0 0 1 1.998-.001zm-.644.766a.5.5 0 0 0-.707 0L1.95 11.756l-.764 3.057 3.057-.764L14.44 3.854a.5.5 0 0 0 0-.708l-1.585-1.585z" />
				</svg>글쓰기
						</a>
					</div>
				</div>
			</c:if>

		<!-- 페이지 네비게이터 -->
			<nav aria-label="Page navigation example">
				<ul class="pagination justify-content-center mt-5">
				<!-- 이전 10개 페이지 -->
					<c:choose>
						<c:when test="${startPage>blockSize }">
							<li class="page-item"><a class="pagenav_a page-link"
								href="notiSearchList.do?pageNum=${startPage-blockSize }&pageSize=${pageSize }&search=${search}&b_type=${b_type}"
								aria-label="Previous"> <span aria-hidden="true">&laquo;</span></a>
							</li>
						</c:when>
						<c:otherwise>
							<li class="page-item" style="visibility: hidden;"><a
								class="pagenav_a page-link"
								href="notiSearchList.do?pageNum=${startPage-blockSize }&pageSize=${pageSize }&search=${search}&b_type=${b_type}"
								aria-label="Previous"> <span aria-hidden="true">&laquo;</span></a>
							</li>
						</c:otherwise>
					</c:choose>
				<!-- 이전 페이지 -->
					<c:choose>
						<c:when test="${pageNum eq '1' }">
							<li class="page-item" style="visibility: hidden;"><a
								class="pagenav_a page-link" href="#" aria-label="Previous">
									<span aria-hidden="true">&lt;</span>
							</a></li>
						</c:when>
						<c:otherwise>
							<li class="page-item"><a class="pagenav_a page-link"
								href="notiSearchList.do?pageNum=${pageNum-1 }&search=${search}&b_type=${b_type}" aria-label="Previous">
									<span aria-hidden="true">&lt;</span>
							</a></li>
						</c:otherwise>
					</c:choose>
				<!-- 페이지 목록 -->
					<c:forEach var="i" begin="${startPage }" end="${endPage }">
						<li class="page-item"><a class="pagenav_a page-link"
							href="notiSearchList.do?pageNum=${i }&pageSize=${pageSize }&search=${search}&b_type=${b_type}">[${i }]</a></li>
					</c:forEach>
				<!-- 다음 페이지 -->
					<c:choose>
						<c:when test="${pageNum eq pageCnt }">
							<li class="page-item" style="visibility: hidden;"><a
								class="pagenav_a page-link"
								href="notiSearchList.do?pageNum=${pageNum+1 }&search=${search}&b_type=${b_type}" aria-label="Previous">
									<span aria-hidden="true">&gt;</span>
							</a></li>
						</c:when>
						<c:otherwise>
							<li class="page-item"><a class="pagenav_a page-link"
								href="notiSearchList.do?pageNum=${pageNum+1 }&search=${search}&b_type=${b_type}" aria-label="Previous">
									<span aria-hidden="true">&gt;</span>
							</a></li>
						</c:otherwise>
					</c:choose>
				<!-- 다음 10개 페이지 -->
					<c:choose>
						<c:when test="${endPage<pageCnt }">
							<li class="page-item"><a class="pagenav_a page-link"
								href="notiSearchList.do?pageNum=${startPage+blockSize }&search=${search}&b_type=${b_type}"
								aria-label="Next"> <span aria-hidden="true">&raquo;</span></a></li>
						</c:when>
						<c:otherwise>
							<li class="page-item" style="visibility: hidden;"><a
								class="pagenav_a page-link"
								href="notiSearchList.do?pageNum=${startPage+blockSize }&search=${search}&b_type=${b_type}"
								aria-label="Next"> <span aria-hidden="true">&raquo;</span></a></li>
						</c:otherwise>
					</c:choose>
				</ul>
			</nav>
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
	<%@include file="../common/footer.jsp"%>
</body>
</html>