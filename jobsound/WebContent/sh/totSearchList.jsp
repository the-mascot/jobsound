<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<%
   String context = request.getContextPath();
%>
<head>
<meta charset="UTF-8">
<meta name="view=viewport" content="width=device-width", initial-scale="1.0">
<title>index</title>
<style type="text/css">
.highlight {
    background-color: yellow;
    border-radius: 5px;
}
</style>
<!-- 부트스트랩 CDN -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">	<!-- 부트스트랩 icon CDN -->
<link href="../common/style.css" rel="stylesheet" type="text/css">	<!-- style.css link --> 
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script> 

<script type="text/javascript">
$(document).ready(function(){
	
	if ($('#skeyWord').length) {
	    var skeyWord = '${keyWord}';
	    var text = $(".btitle").html();
	    $(".btitle").each(function(){ 
            $(this).html($(this).html().replace(eval("/"+skeyWord+"/gi"), "<span class='highlight'>"+skeyWord+"</span>"));
        });
	    $(".writer").each(function(){ 
            $(this).html($(this).html().replace(eval("/"+skeyWord+"/gi"), "<span class='highlight'>"+skeyWord+"</span>"));
        });
	    $(".content").each(function(){ 
            $(this).html($(this).html().replace(eval("/"+skeyWord+"/gi"), "<span class='highlight'>"+skeyWord+"</span>"));
        });
	}
});
</script>
<body>
<!-- header 부분 -->
<%@include file ="../common/header.jsp" %>

<!-- 상단 navbar 영역 -->
<%@include file ="../common/headNav.jsp" %>

<!-- main 영역 : main 안에 left_side - section - right_side로 3등분 됨 2:6:2 비율 -->
<main>
<!-- left side 영역 -->
<%@include file ="../common/comuLeftSide.jsp" %>

<!-- section 영역 -->
		<section>
		<input type="hidden" id="skeyWord" value="${keyWord }">
		<div>
		<div>
			<h2 class="mt-5 mb-3 mx-5 fw-bold" style="font-family: pretend; color: #5798D7">통합 검색 결과</h2>
		</div>
		<div>
			<p class="mt-2 mx-5 fs-6 text-muted fst-italic" style="font-family: pretend">- 제목과 내용, 작성자 아이디를 포함한 검색 결과입니다.</p>
		</div>
	</div>
			<!-- 게시판 설명 -->
			 <div class="mx-auto my-5">
		<table class="board_table table mx-auto table-hover table-responsive text-center" style="font-size: 14px;">
		 	<thead style="background-color: rgba(13, 110, 253, 0.1)">
		 		<tr>
		 			<th style="width: 20%">게시판</th>
		 			<th style="width: 40%">제목</th>
		 			<th style="width: 20%">작성자</th>
		 			<th style="width: 20%">작성일</th>
		 		</tr>
		 	</thead>
		 	<tbody>
	
	<c:if test="${totCnt>0 }">
		<c:forEach var="board" items="${list }">
			<tr>
				<c:choose>
				<c:when test="${board.b_type eq 1}">
				<td><a href="#">공지사항</a></td>
				</c:when>
				<c:when test="${board.b_type eq 2}">
				<td><a href="<%=context %>/es/recuBoardList.do">스터디 모집</a></td>
				</c:when>
				<c:when test="${board.b_type eq 3}">
				<td><a href="<%=context %>/sh/comuBoardList.do">커뮤니티</a></td>
				</c:when>
				<c:when test="${board.b_type eq 5}">
				<td><a href="#">Q&A</a></td>
				</c:when>
				<c:when test="${board.b_type eq 6}">
				<td><a href="#">취업기사</a></td>
				</c:when>
				<c:otherwise>
				<td>어디임?</td>
				</c:otherwise>
				</c:choose>
				<td class="text-start">
				<c:choose>
					<c:when test="${board.b_type eq 1 }">
					<a class="btitle fs-6" href='<%=context %>/es/recuContent.do?b_num=${board.b_num }&pageNum=${pageNum }&pageSize=${pageSize}' style="text-decoration: none; color: black;">
					${board.title }</a>
					<c:if test="${board.imagepath!=null }">
						<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-images" viewBox="0 0 16 16">
  						<path d="M4.502 9a1.5 1.5 0 1 0 0-3 1.5 1.5 0 0 0 0 3z"/>
  						<path d="M14.002 13a2 2 0 0 1-2 2h-10a2 2 0 0 1-2-2V5A2 2 0 0 1 2 3a2 2 0 0 1 2-2h10a2 2 0 0 1 2 2v8a2 2 0 0 1-1.998 2zM14 2H4a1 1 0 0 0-1 1h9.002a2 2 0 0 1 2 2v7A1 1 0 0 0 15 11V3a1 1 0 0 0-1-1zM2.002 4a1 1 0 0 0-1 1v8l2.646-2.354a.5.5 0 0 1 .63-.062l2.66 1.773 3.71-3.71a.5.5 0 0 1 .577-.094l1.777 1.947V5a1 1 0 0 0-1-1h-10z"/>
						</svg>
					</c:if>
					</c:when>
					<c:when test="${board.b_type eq 2 }">
					<a class="btitle fs-6" href='<%=context %>/es/recuContent.do?b_num=${board.b_num }&pageNum=${pageNum }&pageSize=${pageSize}' style="text-decoration: none; color: black;">
					${board.title }</a>
					<c:if test="${board.imagepath!=null }">
						<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-images" viewBox="0 0 16 16">
  						<path d="M4.502 9a1.5 1.5 0 1 0 0-3 1.5 1.5 0 0 0 0 3z"/>
  						<path d="M14.002 13a2 2 0 0 1-2 2h-10a2 2 0 0 1-2-2V5A2 2 0 0 1 2 3a2 2 0 0 1 2-2h10a2 2 0 0 1 2 2v8a2 2 0 0 1-1.998 2zM14 2H4a1 1 0 0 0-1 1h9.002a2 2 0 0 1 2 2v7A1 1 0 0 0 15 11V3a1 1 0 0 0-1-1zM2.002 4a1 1 0 0 0-1 1v8l2.646-2.354a.5.5 0 0 1 .63-.062l2.66 1.773 3.71-3.71a.5.5 0 0 1 .577-.094l1.777 1.947V5a1 1 0 0 0-1-1h-10z"/>
						</svg>
					</c:if>
					</c:when>
					<c:when test="${board.b_type eq 3 }">
					<a class="btitle fs-6" href='<%=context %>/sh/comuContent.do?b_num=${board.b_num }&pageNum=${pageNum }&pageSize=${pageSize}' style="text-decoration: none; color: black;">
					${board.title }</a>
					<c:if test="${board.imagepath!=null }">
						<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-images" viewBox="0 0 16 16">
  						<path d="M4.502 9a1.5 1.5 0 1 0 0-3 1.5 1.5 0 0 0 0 3z"/>
  						<path d="M14.002 13a2 2 0 0 1-2 2h-10a2 2 0 0 1-2-2V5A2 2 0 0 1 2 3a2 2 0 0 1 2-2h10a2 2 0 0 1 2 2v8a2 2 0 0 1-1.998 2zM14 2H4a1 1 0 0 0-1 1h9.002a2 2 0 0 1 2 2v7A1 1 0 0 0 15 11V3a1 1 0 0 0-1-1zM2.002 4a1 1 0 0 0-1 1v8l2.646-2.354a.5.5 0 0 1 .63-.062l2.66 1.773 3.71-3.71a.5.5 0 0 1 .577-.094l1.777 1.947V5a1 1 0 0 0-1-1h-10z"/>
						</svg>
					</c:if>
					</c:when>
					<c:when test="${board.b_type eq 5 }">
					<a class="btitle fs-6" href='#' style="text-decoration: none; color: black;">
					${board.title }</a>
					<c:if test="${board.imagepath!=null }">
						<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-images" viewBox="0 0 16 16">
  						<path d="M4.502 9a1.5 1.5 0 1 0 0-3 1.5 1.5 0 0 0 0 3z"/>
  						<path d="M14.002 13a2 2 0 0 1-2 2h-10a2 2 0 0 1-2-2V5A2 2 0 0 1 2 3a2 2 0 0 1 2-2h10a2 2 0 0 1 2 2v8a2 2 0 0 1-1.998 2zM14 2H4a1 1 0 0 0-1 1h9.002a2 2 0 0 1 2 2v7A1 1 0 0 0 15 11V3a1 1 0 0 0-1-1zM2.002 4a1 1 0 0 0-1 1v8l2.646-2.354a.5.5 0 0 1 .63-.062l2.66 1.773 3.71-3.71a.5.5 0 0 1 .577-.094l1.777 1.947V5a1 1 0 0 0-1-1h-10z"/>
						</svg>
					</c:if>
					</c:when>
					<c:when test="${board.b_type eq 6 }">
					<a class="btitle fs-6" href='#' style="text-decoration: none; color: black;">
					${board.title }</a>
					<c:if test="${board.imagepath!=null }">
						<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-images" viewBox="0 0 16 16">
  						<path d="M4.502 9a1.5 1.5 0 1 0 0-3 1.5 1.5 0 0 0 0 3z"/>
  						<path d="M14.002 13a2 2 0 0 1-2 2h-10a2 2 0 0 1-2-2V5A2 2 0 0 1 2 3a2 2 0 0 1 2-2h10a2 2 0 0 1 2 2v8a2 2 0 0 1-1.998 2zM14 2H4a1 1 0 0 0-1 1h9.002a2 2 0 0 1 2 2v7A1 1 0 0 0 15 11V3a1 1 0 0 0-1-1zM2.002 4a1 1 0 0 0-1 1v8l2.646-2.354a.5.5 0 0 1 .63-.062l2.66 1.773 3.71-3.71a.5.5 0 0 1 .577-.094l1.777 1.947V5a1 1 0 0 0-1-1h-10z"/>
						</svg>
					</c:if>
					</c:when>
				</c:choose>
				<p class="content text-truncate fs-8" style="max-width: 500px">${board.content}</p>
				</td>
				<td class="writer">${board.id }</td>
				<td>${board.uploaddate }</td>
		
			</tr>
		</c:forEach>
	</c:if>

	<c:if test="${totCnt==0 }">
		<tr>
			<td colspan=6>검색 결과가 없습니다</td>
		</tr>
	</c:if>
		</tbody>

		</table>
	</div>
			<!-- 이전 10개 페이지 -->
			<nav aria-label="Page navigation example">
		<ul class="pagination justify-content-center mt-5">
			<c:choose>
				<c:when test="${startPage>blockSize }">
					<li class="page-item">
						<a class="pagenav_a page-link" href="<%=context%>/sh/totalSearch.do?pageNum=${startPage-blockSize }&pageSize=${pageSize }&keyWord=${keyWord}" aria-label="Previous"> 
						<span aria-hidden="true">&laquo;</span></a>
					</li>
				</c:when>
				<c:otherwise>
					<li class="page-item" style="visibility: hidden;">
						<a class="pagenav_a page-link" href="<%=context%>/sh/totalSearch.do?pageNum=${startPage-blockSize }&pageSize=${pageSize }&keyWord=${keyWord}" aria-label="Previous"> 
						<span aria-hidden="true">&laquo;</span></a>
					</li>
				</c:otherwise>
			</c:choose>
			<!-- 이전 페이지 -->
			<c:choose>
				<c:when test="${pageNum eq '1' }">
					<li class="page-item" style="visibility: hidden;">
						<a class="pagenav_a page-link" href="#" aria-label="Previous">
						<span aria-hidden="true">&lt;</span></a>
					</li>
				</c:when>
				<c:otherwise>
					<li class="page-item">
						<a class="pagenav_a page-link" href="<%=context%>/sh/totalSearch.do?pageNum=${pageNum-1 }&keyWord=${keyWord}" aria-label="Previous">
						<span aria-hidden="true">&lt;</span></a>
					</li>
				</c:otherwise>
			</c:choose>
			<!-- 페이지 목록 -->
			<c:forEach var="i" begin="${startPage }" end="${endPage }">
				<li class="page-item">
					<a class="pagenav_a page-link" href="<%=context%>/sh/totalSearch.do?pageNum=${i }&pageSize=${pageSize }&keyWord=${keyWord}">[${i }]</a></li>
			</c:forEach>
			<!-- 다음 페이지 -->
			<c:choose>
				<c:when test="${pageNum eq pageCnt }">
					<li class="page-item" style="visibility: hidden;">
						<a class="pagenav_a page-link" href="<%=context%>/sh/totalSearch.do?pageNum=${pageNum+1 }&keyWord=${keyWord}" aria-label="Previous">
						<span aria-hidden="true">&gt;</span></a>
					</li>
				</c:when>
				<c:otherwise>
					<li class="page-item">
						<a class="pagenav_a page-link" href="<%=context%>/sh/totalSearch.do?pageNum=${pageNum+1 }&keyWord=${keyWord}" aria-label="Previous">
						<span aria-hidden="true">&gt;</span></a>
					</li>
				</c:otherwise>
			</c:choose>
			<!-- 다음 10개 페이지 -->
			<c:choose>
				<c:when test="${endPage<pageCnt }">
					<li class="page-item">
						<a class="pagenav_a page-link" href="<%=context%>/sh/totalSearch.do?pageNum=${startPage+blockSize }&keyWord=${keyWord}" aria-label="Next">
						<span aria-hidden="true">&raquo;</span></a>
					</li>
				</c:when>
				<c:otherwise>
					<li class="page-item" style="visibility: hidden;">
						<a class="pagenav_a page-link" href="<%=context%>/sh/totalSearch.do?pageNum=${startPage+blockSize }&keyWord=${keyWord}" aria-label="Next">
						<span aria-hidden="true">&raquo;</span></a>
					</li>
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
<%@include file ="../common/footer.jsp" %>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous"></script>
</body>
</html>