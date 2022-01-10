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
<!-- 부트스트랩 CDN -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">	<!-- 부트스트랩 icon CDN -->
<link href="../common/style.css" rel="stylesheet" type="text/css">	<!-- style.css link --> 
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<style type="text/css">
.highlight {
    background-color: yellow;
    border-radius: 5px;
}
</style>
<script type="text/javascript">
	function chk() {
		if(!frm.keyWord.value) {
			alert("검색어를 입력하세요");
			frm.keyWord.focus();
			return false;
		}
		var blank_pattern = /^\s+|\s+$/g;
		if(frm.keyWord.value.replace( blank_pattern, '' ) == "" ){
		    alert(' 공백만 입력되었습니다 ');
		    frm.keyWord.focus();
		    return false;
		}
		return true;
		
	}
	$(document).ready(function(){
		$("#viewCount").change(function() {
			var pageSize=$(this).val();
			location.href="<%=context %>/es/recuBoardList.do?pageSize="+pageSize;
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
</head>
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
<!-- 게시판 설명 -->
	<div>
		<div>
			<h2 class="mt-5 mb-3 mx-5 fw-bold" style="font-family: pretend; color: #5798D7">스터디 모집 게시판</h2>
		</div>
		<div>
			<p class="mt-2 mx-5 fs-6 text-muted fst-italic" style="font-family: pretend">- 스터디 모집 합니다~</p>
		</div>
	</div>
<!-- 	게시판 내 검색 -->
	<div class="board_search_parent">
		<form action="<%=context %>/es/recuSearch.do?pageSize=${pageSize }" id="frm" onsubmit="return chk()">
			<div class="board_search d-flex">
				<div>
				<input type="text" class="form-control" name="keyWord" placeholder="Search" maxlength="255">
				</div>
				<div>
				<input type="submit" class="board_search_button btn" value="검 색" style="margin-left: 5px">
				</div>
				</div>
		</form>
	</div>
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
		 			<th>글번호</th>
		 			<th style="width: 30%">제목</th>
		 			<th>작성자</th>
		 			<th>스터디 분류</th>
		 			<th style="width: 170px;">스터디 기간</th>
		 			<th>모집상태</th>
		 		</tr>
		 	</thead>
		 	<tbody>
	
	<c:if test="${totCnt>0 }">
		<c:forEach var="board" items="${list }">
			<tr>
				<td>${startNum }</td>
				<td class="text-start text-truncate" style="max-width: 400px;">
				<a href='<%=context %>/es/recuContent.do?b_num=${board.b_num }&pageNum=${pageNum }&pageSize=${pageSize}' style="text-decoration: none; color: black;" class="btitle">
					${board.title }</a>
					<c:if test="${board.imagepath!=null }">
						<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-images" viewBox="0 0 16 16">
  						<path d="M4.502 9a1.5 1.5 0 1 0 0-3 1.5 1.5 0 0 0 0 3z"/>
  						<path d="M14.002 13a2 2 0 0 1-2 2h-10a2 2 0 0 1-2-2V5A2 2 0 0 1 2 3a2 2 0 0 1 2-2h10a2 2 0 0 1 2 2v8a2 2 0 0 1-1.998 2zM14 2H4a1 1 0 0 0-1 1h9.002a2 2 0 0 1 2 2v7A1 1 0 0 0 15 11V3a1 1 0 0 0-1-1zM2.002 4a1 1 0 0 0-1 1v8l2.646-2.354a.5.5 0 0 1 .63-.062l2.66 1.773 3.71-3.71a.5.5 0 0 1 .577-.094l1.777 1.947V5a1 1 0 0 0-1-1h-10z"/>
						</svg>
					</c:if>
					<c:if test="${board.uploaddate eq today}">
							<img src="<%=context %>/common/images/new.png" class="ms-1">
					</c:if>
				</td>
				<td class="writer">${board.id }</td>
				<td>${board.stddiv}</td>
				<td><c:set var="startDate" value="${board.stdstart_date }" ></c:set>
 					${fn:substring(startDate,5,10) }
 					<span> ~ </span>
					<c:set var="endDate" value="${board.stdend_date }" ></c:set>
 					${fn:substring(endDate,5,10) }
				</td>
				<c:choose>
					<c:when test="${board.stdstatus eq 0 }">
						<td class="text-primary">모집중</td>
					</c:when>
					<c:when test="${board.stdstatus eq 1 }">
						<td class="text-secondary">모집완료</td>
					</c:when>
					<c:otherwise>
						<td class="text-danger">모집취소</td>
					</c:otherwise>
				</c:choose>
				
		
			</tr>
			<c:set var="startNum" value="${startNum-1 }"/>
		</c:forEach>
	</c:if>

	<c:if test="${totCnt==0 }">
		<tr>
			<td colspan=6>데이터가 없습니다</td>
		</tr>
	</c:if>
		</tbody>

		</table>
	</div>
	
	<!-- 글쓰기 버튼 -->
	<c:if test="${sessionScope.sessionID!=null }">
		<div class="write_btn_parent">
			<div class="write_btn">
				<a href="recuWriteForm.jsp?pageNum=${pageNum }&pageSize=${pageSize }&today=${today}" class="write_btn btn btn-outline-primary" style="border: 1px solid rgba(65, 75, 70, 0.5); color: black; margin-right: 50px;">
				<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pen" viewBox="0 0 16 16">
	  			<path d="m13.498.795.149-.149a1.207 1.207 0 1 1 1.707 1.708l-.149.148a1.5 1.5 0 0 1-.059 2.059L4.854 14.854a.5.5 0 0 1-.233.131l-4 1a.5.5 0 0 1-.606-.606l1-4a.5.5 0 0 1 .131-.232l9.642-9.642a.5.5 0 0 0-.642.056L6.854 4.854a.5.5 0 1 1-.708-.708L9.44.854A1.5 1.5 0 0 1 11.5.796a1.5 1.5 0 0 1 1.998-.001zm-.644.766a.5.5 0 0 0-.707 0L1.95 11.756l-.764 3.057 3.057-.764L14.44 3.854a.5.5 0 0 0 0-.708l-1.585-1.585z"/>
				</svg>글쓰기</a>
			</div>
		</div>	
	</c:if>

	<nav aria-label="Page navigation example">
		<ul class="pagination justify-content-center mt-5">
			<!-- 이전 10개 페이지 -->
			<c:choose>
				<c:when test="${startPage>blockSize }">
					<li class="page-item">
						<a class="pagenav_a page-link" href="<%=context %>/es/recuBoardList.do?pageNum=${startPage-blockSize }&pageSize=${pageSize }&keyWord=${keyWord}" aria-label="Previous"> 
						<span aria-hidden="true">&laquo;</span></a>
					</li>
				</c:when>
				<c:otherwise>
					<li class="page-item" style="visibility: hidden;">
						<a class="pagenav_a page-link" href="<%=context %>/es/recuBoardList.do?pageNum=${startPage-blockSize }&pageSize=${pageSize }&keyWord=${keyWord}" aria-label="Previous"> 
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
						<a class="pagenav_a page-link" href="<%=context %>/es/recuBoardList.do?pageNum=${pageNum-1 }&keyWord=${keyWord}" aria-label="Previous">
						<span aria-hidden="true">&lt;</span></a>
					</li>
				</c:otherwise>
			</c:choose>
			<!-- 페이지 목록 -->
			<c:forEach var="i" begin="${startPage }" end="${endPage }">
				<li class="page-item">
					<a class="pagenav_a page-link" id="pageNav${i}" href="<%=context %>/es/recuBoardList.do?pageNum=${i }&pageSize=${pageSize }&keyWord=${keyWord}">[${i }]</a></li>
			</c:forEach>
			<!-- 다음 페이지 -->
			<c:choose>
				<c:when test="${pageNum eq pageCnt }">
					<li class="page-item" style="visibility: hidden;">
						<a class="pagenav_a page-link" href="<%=context %>/es/recuBoardList.do?pageNum=${pageNum+1 }&keyWord=${keyWord}" aria-label="Previous">
						<span aria-hidden="true">&gt;</span></a>
					</li>
				</c:when>
				<c:otherwise>
					<li class="page-item">
						<a class="pagenav_a page-link" href="<%=context %>/es/recuBoardList.do?pageNum=${pageNum+1 }&keyWord=${keyWord}" aria-label="Previous">
						<span aria-hidden="true">&gt;</span></a>
					</li>
				</c:otherwise>
			</c:choose>
			<!-- 다음 10개 페이지 -->
			<c:choose>
				<c:when test="${endPage<pageCnt }">
					<li class="page-item">
						<a class="pagenav_a page-link" href="<%=context %>/es/recuBoardList.do?pageNum=${startPage+blockSize }&keyWord=${keyWord}" aria-label="Next">
						<span aria-hidden="true">&raquo;</span></a>
					</li>
				</c:when>
				<c:otherwise>
					<li class="page-item" style="visibility: hidden;">
						<a class="pagenav_a page-link" href="<%=context %>/es/recuBoardList.do?pageNum=${startPage+blockSize }&keyWord=${keyWord}" aria-label="Next">
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