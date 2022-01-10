<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<%
	String context = request.getContextPath();
%>
<head>
<meta charset="UTF-8">
<meta name="view=viewport" content="width=device-width" initial-scale="1.0">
<title>JOB Board List</title>
<!-- 부트스트랩 CDN -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">	<!-- 부트스트랩 icon CDN -->
<link href="../common/style.css" rel="stylesheet" type="text/css">   <!-- style.css link -->
<style type="text/css">
.highlight {
    background-color: yellow;
    border-radius: 5px;
}
.list_title {
	background-color: rgba(13, 110, 253, 0.1);
	padding-top: 5px;
	width:91.6%;
}

.best_news {
	width:89%;
	height: 200px;
	margin-left: 45px;
}
/* newsindexList.jsp */
/* card news content */
.card_content {
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: normal;
        line-height: 2;
        height: 8em;
        text-align: left;
        word-wrap: break-word;
        display: -webkit-box;
        -webkit-line-clamp: 4 ;
        -webkit-box-orient: vertical;
}
.card_title {
	    overflow: hidden;
        text-overflow: ellipsis;
        white-space: normal;
        line-height: 1.2;
        height: 1.2em;
        text-align: left;
        word-wrap: break-word;
        display: -webkit-box;
        -webkit-line-clamp: 1 ;
        -webkit-box-orient: vertical;
}
.boardlist_title {
	 text-align: left;
	 overflow: hidden;
	 text-overflow: ellipsis;
	 white-space:nowrap;
	 display: inline-block;
	 width: 400px;
}
</style>
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){

	 $("#viewCount").change(function() {
	    var pageNum="${pageNum}";
	    var pageSize=$(this).val();
	    location.href="<%=context%>/gm/boardlist.do?pageSize="+pageSize+"&pageNum="+pageNum;
	});
       
	if ($(keyWord!=null)) {
	    var keyWord = '${keyWord}';
	    var text = $(".btitle").html();
	    $(".btitle").each(function(){ 
	              $(this).html($(this).html().replace(eval("/"+keyWord+"/gi"), "<span class='highlight'>"+keyWord+"</span>"));
	          });
	    $(".writer").each(function(){ 
	              $(this).html($(this).html().replace(eval("/"+keyWord+"/gi"), "<span class='highlight'>"+keyWord+"</span>"));
	          });
	}
	
	$("#pageNav${pageNum}").css("color", "red");
      
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
<%@include file ="../common/newsLeftSide.jsp" %>

<!-- section 영역 -->
<section>
<!-- 게시판 설명 -->
<div>
	<div>
		<h2 class="mt-5 mb-3 mx-5 fw-bold" style="font-family: 'jalnan'; color: #5798D7">JOB 뉴스</h2>
	</div>
	<div>
		<p class="mt-2 mx-5 fs-6 text-muted fst-italic" style="font-family: 'jalnan'">취업과 관련된 모든 뉴스를 만나보세요.</p>
	</div>
</div>
<!-- 게시판 내 검색 -->
<form action="<%=context %>/gm/newsSearch.do" method="post">
	<input type="hidden" value="${pageNum }" id="pageNum">
	<div class="board_search_parent">
		<div class="board_search d-flex">
			<div>
				<input type="text" class="form-control" placeholder="Search" maxlength="255" id="search" name="search">
			</div>
			<div>
				<input type="submit" class="board_search_button btn" value="검 색" style="background-color: #5798D7; margin-left: 5px;">
			</div>
		</div>
	</div>
</form>
<!-- 10개씩 보기 -->
<div class="view_count_parent">
    <select class="view_count form-select" id="viewCount" name="viewCount">
        <option value="10" <c:if test="${pageSize=='10'}">selected='selected'</c:if>>10개씩</option>
        <option value="15" <c:if test="${pageSize=='15'}">selected='selected'</c:if>>15개씩</option>
        <option value="20" <c:if test="${pageSize=='20'}">selected='selected'</c:if>>20개씩</option>
        <option value="30" <c:if test="${pageSize=='30'}">selected='selected'</c:if>>30개씩</option>
     </select>
</div>
<!-- 게시판 테이블 -->
	<div class="mx-auto my-5">
		<table class="board_table table mx-auto table-hover table-responsive text-center" style="font-size: 14px;">
			<thead style="background-color: rgba(13, 110, 253, 0.1)">
				<tr>
					<th>번호</th>
					<th style="width: 40%;">제목</th>
					<th>작성자</th>
					<th>작성일</th>
					<th>조회수</th>
				</tr>
			</thead>
				<c:if test="${totCnt>0}">
					<c:forEach var="board" items="${list}">
						<tr>
							<td>${startNum }</td>
							<td class="text-start text-truncate" style="max-width: 400px">
								<a href="<%=context %>/gm/content.do?b_num=${board.b_num}&pageNum=${currentPage}&pageSize=${pageSize}&b_type=${board.b_type}" style="text-decoration : none; color:black;" class="btitle">${board.title} </a>
							<c:if test="${not empty board.imagepath}">
								<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-images" viewBox="0 0 16 16">
			  						<path d="M4.502 9a1.5 1.5 0 1 0 0-3 1.5 1.5 0 0 0 0 3z"/>
			  						<path d="M14.002 13a2 2 0 0 1-2 2h-10a2 2 0 0 1-2-2V5A2 2 0 0 1 2 3a2 2 0 0 1 2-2h10a2 2 0 0 1 2 2v8a2 2 0 0 1-1.998 2zM14 2H4a1 1 0 0 0-1 1h9.002a2 2 0 0 1 2 2v7A1 1 0 0 0 15 11V3a1 1 0 0 0-1-1zM2.002 4a1 1 0 0 0-1 1v8l2.646-2.354a.5.5 0 0 1 .63-.062l2.66 1.773 3.71-3.71a.5.5 0 0 1 .577-.094l1.777 1.947V5a1 1 0 0 0-1-1h-10z"/>
								</svg>
							</c:if> 
							<c:if test="${board.uploaddate eq today}">
								<img src="../common/images/new.png">
							</c:if>
							</td>
							<td class="writer">${board.id}</td>
							<td>${board.uploaddate}</td>
							<td>${board.views}</td>
						</tr>
						<c:set var="startNum" value="${startNum - 1 }" />
					</c:forEach>
				</c:if>
				<c:if test="${totCnt==0 }">
					<tr>
						<td colspan=7>데이터가 없네</td>
					</tr>
				</c:if>
		</table>
	</div>
<!--끌쓰기 버튼 -->
	<c:if test="${sessionScope.sessionID == 'admin' }">
	<div class="write_btn_parent">
		<div class="write_btn">
			<a href="<%=context %>/gm/writeForm.do" class="write_btn btn btn-outline-primary" style="border: 1px solid rgba(65, 75, 70, 0.5); color: black; margin-right: 50px;">
			<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pen" viewBox="0 0 16 16">
  			<path d="m13.498.795.149-.149a1.207 1.207 0 1 1 1.707 1.708l-.149.148a1.5 1.5 0 0 1-.059 2.059L4.854 14.854a.5.5 0 0 1-.233.131l-4 1a.5.5 0 0 1-.606-.606l1-4a.5.5 0 0 1 .131-.232l9.642-9.642a.5.5 0 0 0-.642.056L6.854 4.854a.5.5 0 1 1-.708-.708L9.44.854A1.5 1.5 0 0 1 11.5.796a1.5 1.5 0 0 1 1.998-.001zm-.644.766a.5.5 0 0 0-.707 0L1.95 11.756l-.764 3.057 3.057-.764L14.44 3.854a.5.5 0 0 0 0-.708l-1.585-1.585z"/>
			</svg>글쓰기</a>	<!-- 로그인 여부 확인할 것 - 관리자 -->
		</div>
	</div>	
	</c:if>
<!-- 페이지 네비게이터 -->
	<nav aria-label="Page navigation example">
		<ul class="pagination justify-content-center mt-5">
			<!-- 이전 10개 페이지 -->
			<c:choose>
				<c:when test="${startPage>blockSize }">
					<li class="page-item">
						<a class="pagenav_a page-link" href="<%=context %>/gm/boardlist.do?pageNum=${startPage-blockSize }&pageSize=${pageSize }&keyWord=${keyWord}" aria-label="Previous"> 
						<span aria-hidden="true">&laquo;</span></a>
					</li>
				</c:when>
				<c:otherwise>
					<li class="page-item" style="visibility: hidden;">
						<a class="pagenav_a page-link" href="<%=context %>/gm/boardlist.do?pageNum=${startPage-blockSize }&pageSize=${pageSize }&keyWord=${keyWord}" aria-label="Previous"> 
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
						<a class="pagenav_a page-link" href="<%=context %>/gm/boardlist.do?pageNum=${pageNum-1 }&keyWord=${keyWord}" aria-label="Previous">
						<span aria-hidden="true">&lt;</span></a>
					</li>
				</c:otherwise>
			</c:choose>
			<!-- 페이지 목록 -->
			<c:forEach var="i" begin="${startPage }" end="${endPage }">
				<li class="page-item">
					<a class="pagenav_a page-link" id="pageNav${i}" href="<%=context %>/gm/boardlist.do?pageNum=${i }&pageSize=${pageSize }&keyWord=${keyWord}">[${i }]</a></li>
			</c:forEach>
			<!-- 다음 페이지 -->
			<c:choose>
				<c:when test="${pageNum eq pageCnt }">
					<li class="page-item" style="visibility: hidden;">
						<a class="pagenav_a page-link" href="<%=context %>/gm/boardlist.do?pageNum=${pageNum+1 }&keyWord=${keyWord}" aria-label="Previous">
						<span aria-hidden="true">&gt;</span></a>
					</li>
				</c:when>
				<c:otherwise>
					<li class="page-item">
						<a class="pagenav_a page-link" href="<%=context %>/gm/boardlist.do?pageNum=${pageNum+1 }&keyWord=${keyWord}" aria-label="Previous">
						<span aria-hidden="true">&gt;</span></a>
					</li>
				</c:otherwise>
			</c:choose>
			<!-- 다음 10개 페이지 -->
			<c:choose>
				<c:when test="${endPage<pageCnt }">
					<li class="page-item">
						<a class="pagenav_a page-link" href="<%=context %>/gm/boardlist.do?pageNum=${startPage+blockSize }&keyWord=${keyWord}" aria-label="Next">
						<span aria-hidden="true">&raquo;</span></a>
					</li>
				</c:when>
				<c:otherwise>
					<li class="page-item" style="visibility: hidden;">
						<a class="pagenav_a page-link" href="<%=context %>/gm/boardlist.do?pageNum=${startPage+blockSize }&keyWord=${keyWord}" aria-label="Next">
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
</body>
</html>