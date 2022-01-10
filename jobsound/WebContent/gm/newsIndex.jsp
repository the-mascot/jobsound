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
<title>news index</title>
<!-- 부트스트랩 CDN -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">	<!-- 부트스트랩 icon CDN -->
<link href="../common/style.css" rel="stylesheet" type="text/css">   <!-- style.css link -->
<style type="text/css">
.list_title {
	background-color: rgba(13, 110, 253, 0.1);
	padding-top: 5px;
	width:91.6%;
}

.best_news {
	width:91.6%;
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
           location.href="<%=context%>/gm/newsindex.do?pageSize="+pageSize+"&pageNum="+pageNum;
       });
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
			<h2 class="mt-5 mb-3 mx-5 fw-bold" style="font-family: 'jalnan'; color: #5798D7">가장 많이 본 뉴스</h2>
		</div>
		<div>
			<p class="mt-2 mx-5 fs-6 text-muted fst-italic" style="font-family: 'jalnan'">핫한 뉴스만 골라보세요</p>
		</div>
	</div>
<!-- 10개씩 보기 -->
<div class="view_count_parent">
    <select class="view_count form-select" id="viewCount" name="viewCount" style="margin-top: -15px">
        <option value="10" <c:if test="${pageSize=='10'}">selected='selected'</c:if>>10개씩</option>
        <option value="15" <c:if test="${pageSize=='15'}">selected='selected'</c:if>>15개씩</option>
        <option value="20" <c:if test="${pageSize=='20'}">selected='selected'</c:if>>20개씩</option>
        <option value="30" <c:if test="${pageSize=='30'}">selected='selected'</c:if>>30개씩</option>
     </select>
</div>

<!-- 썸네일 - 카드 --> <!-- 조회수에 따라 노출 -->
<div class="container_fluid">
<c:forEach var="board" items="${list }">
	<div class="best_news card" style="margin-top: 15px;">
	  <div class="row no-gutters">
	    <div class="col-md-4">
	    <a href='<%=context %>/gm/content.do?b_num=${board.b_num}&pageNum=${currentPage}&b_type=${board.b_type}' style="text-decoration:none; color:black;">
	    	<img src="${board.imagepath }" class="card-img-top" style="height: 200px;">
	    	</a>
	    </div>
	   	 	<div class="col-md-8">
	    		<div class="card-body">
			      	<a href='<%=context %>/gm/content.do?b_num=${board.b_num}&pageNum=${currentPage}&b_type=${board.b_type}' style="text-decoration:none; color:black;">
			        	<h5 class="card_title card-title">${board.title }</h5>
			        <p class="card_content card-text" style="font-size: 12px">${board.content }</p>  
			        </a>      
			        <p class="card-text"><small class="text-muted" style="font-size: 10px">작성일 : ${board.uploaddate}</small>&emsp;
			        					 <small class="text-muted" style="font-size: 10px; padding-bottom: -10px">
				        					 <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-eye" viewBox="0 0 16 16">
								  				<path d="M16 8s-3-5.5-8-5.5S0 8 0 8s3 5.5 8 5.5S16 8 16 8zM1.173 8a13.133 13.133 0 0 1 1.66-2.043C4.12 4.668 5.88 3.5 8 3.5c2.12 0 3.879 1.168 5.168 2.457A13.133 13.133 0 0 1 14.828 8c-.058.087-.122.183-.195.288-.335.48-.83 1.12-1.465 1.755C11.879 11.332 10.119 12.5 8 12.5c-2.12 0-3.879-1.168-5.168-2.457A13.134 13.134 0 0 1 1.172 8z" />
								 				<path d="M8 5.5a2.5 2.5 0 1 0 0 5 2.5 2.5 0 0 0 0-5zM4.5 8a3.5 3.5 0 1 1 7 0 3.5 3.5 0 0 1-7 0z" />
								 			</svg>&nbsp;&nbsp;${board.views}
								 		</small></p>
	      		</div>
	    	</div>
	  	</div>
	</div>
</c:forEach>	
</div>
<!-- 게시판 내 검색 -->
	<c:if test="${sessionScope.sessionID == 'admin' }">
	<div class="write_btn_parent" style="margin-top: 15px">
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
						<a class="pagenav_a page-link" href="<%=context %>/gm/newsindex.do?pageNum=${startPage-blockSize }&pageSize=${pageSize }" aria-label="Previous"> 
						<span aria-hidden="true">&laquo;</span></a>
					</li>
				</c:when>
				<c:otherwise>
					<li class="page-item" style="visibility: hidden;">
						<a class="pagenav_a page-link" href="<%=context %>/gm/newsindex.do?pageNum=${startPage-blockSize }&pageSize=${pageSize }" aria-label="Previous"> 
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
						<a class="pagenav_a page-link" href="<%=context %>/gm/newsindex.do?pageNum=${pageNum-1 }" aria-label="Previous">
						<span aria-hidden="true">&lt;</span></a>
					</li>
				</c:otherwise>
			</c:choose>
			<!-- 페이지 목록 -->
			<c:forEach var="i" begin="${startPage }" end="${endPage }">
				<li class="page-item">
					<a class="pagenav_a page-link" id="pageNav${i}" href="<%=context %>/gm/newsindex.do?pageNum=${i }&pageSize=${pageSize }">[${i }]</a></li>
			</c:forEach>
			<!-- 다음 페이지 -->
			<c:choose>
				<c:when test="${pageNum eq pageCnt }">
					<li class="page-item" style="visibility: hidden;">
						<a class="pagenav_a page-link" href="<%=context %>/gm/newsindex.do?pageNum=${pageNum+1 }" aria-label="Previous">
						<span aria-hidden="true">&gt;</span></a>
					</li>
				</c:when>
				<c:otherwise>
					<li class="page-item">
						<a class="pagenav_a page-link" href="<%=context %>/gm/newsindex.do?pageNum=${pageNum+1 }" aria-label="Previous">
						<span aria-hidden="true">&gt;</span></a>
					</li>
				</c:otherwise>
			</c:choose>
			<!-- 다음 10개 페이지 -->
			<c:choose>
				<c:when test="${endPage<pageCnt }">
					<li class="page-item">
						<a class="pagenav_a page-link" href="<%=context %>/gm/newsindex.do?pageNum=${startPage+blockSize }" aria-label="Next">
						<span aria-hidden="true">&raquo;</span></a>
					</li>
				</c:when>
				<c:otherwise>
					<li class="page-item" style="visibility: hidden;">
						<a class="pagenav_a page-link" href="<%=context %>/gm/newsindex.do?pageNum=${startPage+blockSize }" aria-label="Next">
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