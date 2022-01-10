<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<% String context = request.getContextPath(); %>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous"><link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">	<!-- 부트스트랩 icon CDN -->
<link href="<%=context %>/common/style.css" rel="stylesheet" type="text/css">	<!-- style.css link -->
<script type="text/javascript" src="../js/jquery.js"></script>
<meta charset="UTF-8">
<title>마이페이지-내가 쓴 게시글</title>
<script type="text/javascript">
	function checkAll(){
		if($('#chkall').is(":checked")){
			$("input[name=boardchk]").prop("checked", true);
		} else{
			$("input[name=boardchk]").prop("checked", false);
		}
	}
	
	function stdCancel(){
		var cnt = $("input[name=boardchk]:checked").length;
		var arr = new Array();
			$("input[name=boardchk]:checked").each(function(){
				arr.push($(this).val());
			});
		
		if(cnt==0){
			alret("선택된 게시물이 없습니다.");
			return false;
		} else{
			$.ajax({
				url : 'myStudyCancel.jsp',
				data : "arr="+arr.join(","), // 스트링형으로 만들고 ,를 넣어서 구분해줌
				dataType : 'text',
				success : function(data){
					alert(data);
					location.reload();
				}
			})
		}
	}
</script>
<style type="text/css">
	.center{
		margin : 40px;
	}
	th.texthead{
		text-align: center;
	}
	td.center{
		text-align: center;
	}
	td.form-check{
		margin:0 auto;
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
		<h2><center>신청한 스터디</center></h2>
	</div>
	<br>
	<hr>
	<table class="table table-borderless">
		<thead>
	  		<tr>
	  			<th class="texthead" width="70">선택</th>
	  			<th class="texthead" width="70">글 번호</th>
	  			<th class="texthead" colspan="2">스터디제목</th>
	  			<th class="texthead" width="100">모집시작일</th>
	  			<th class="texthead" width="100">모집종료일</th>
	  			<th class="texthead" width="100">신청상태</th>
	  			<th class="texthead" width="100">모집상태</th>
	  		</tr>
  		</thead>
  		<tbody>
  		<c:if test="${totCnt>0}">
	  		<c:forEach var = "board" items="${list }" varStatus="status">
			  	<tr>
			  		<td>
			  			<!-- <div class="form-check"> -->
			  			<div class="d-flex justify-content-center">
					  		<input class="form-check-input" type="checkbox" value="${board.stdNum }" id="flexCheckDefault" name="boardchk">
					  	</div>
					</td>
			  		<td class="center">${totCnt-cnt-status.index}</td> <!-- startNum - status.index  -->
			  		<td colspan="2">${board.stdTitle }</td>
			  		<td class="center">${board.recru_date }</td>
			  		<td class="center">${board.recu_date }</td>
					<c:choose>
			  			<c:when test="${board.apply_chk == 0 }">
					  		<td class="center">승인대기</td>
			  			</c:when>
			  			<c:when test="${board.apply_chk == 1 }">
					  		<td class="center">승인완료</td>
			  			</c:when>
			  			<c:when test="${board.apply_chk == 2 }">
					  		<td class="center">거절</td>
			  			</c:when>
			  		</c:choose>
			  		<c:choose>
			  			<c:when test="${board.stdStatus == 0 }">
					  		<td class="center"><img src="<%=context %>/common/images/Recruiting.png" width="60px" height="25px"></td>
			  			</c:when>
			  			<c:when test="${board.stdStatus == 1 }">
					  		<td class="center"><img src="<%=context %>/common/images/Recruitment completed.png" width="60px" height="25px"></td>
			  			</c:when>
			  			<c:when test="${board.stdStatus == 2 }">
					  		<td class="center"><img src="<%=context %>/common/images/Cancellation of recruitment.png" width="60px" height="25px"></td>
			  			</c:when>
			  		</c:choose>
			  	</tr>
	  		</c:forEach>
			  	<tr>
			  		<td>
			  			<div class="d-flex justify-content-center">
					  		<input class="form-check-input" type="checkbox" id="chkall" name="chkall" onclick="checkAll()">
					  	</div>
			  		</td>
			  		<td colspan="6">전체선택</td>
			  		<td>
			  			<div class="d-grid gap-2 d-md-flex justify-content-md-end">
							<button type="button" class="btn btn-primary btn-sm" id="write" onclick="stdCancel()">신청취소</button></div>
						</td>
			  	</tr>
	  	</c:if>
	  	<c:if test="${totCnt==0 }">
			<tr>
				<td colspan=7><center>신청한 스터디가 없습니다.</center></td>
			</tr>
		</c:if>
	</table>
	<nav aria-label="Page navigation example">
        <ul class="pagination justify-content-center mt-5">
            <!-- 이전 10개 페이지 -->
            <c:choose>
                <c:when test="${startPage>blockSize }">
                    <li class="page-item">
                        <a class="pagenav_a page-link" href="<%=context %>/di/mypageStudyApplyList.do?pageNum=${startPage-blockSize }" aria-label="Previous"> 
                        <span aria-hidden="true">&laquo;</span></a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="page-item" style="visibility: hidden;">
                        <a class="pagenav_a page-link" href="<%=context %>/di/mypageStudyApplyList.do?pageNum=${startPage-blockSize }" aria-label="Previous"> 
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
                        <a class="pagenav_a page-link" href="<%=context %>/di/mypageStudyApplyList.do?pageNum=${pageNum-1 }" aria-label="Previous">
                        <span aria-hidden="true">&lt;</span></a>
                    </li>
                </c:otherwise>
            </c:choose>
            <!-- 페이지 목록 -->
            <c:forEach var="i" begin="${startPage }" end="${endPage }">
                <li class="page-item">
                    <a class="pagenav_a page-link" href="<%=context %>/di/mypageStudyApplyList.do?pageNum=${i }">[${i }]</a></li>
            </c:forEach>
            <!-- 다음 페이지 -->
            <c:choose>
                <c:when test="${pageNum eq pageCnt }">
                    <li class="page-item" style="visibility: hidden;">
                        <a class="pagenav_a page-link" href="<%=context %>/di/mypageStudyApplyList.do?pageNum=${pageNum+1 }" aria-label="Previous">
                        <span aria-hidden="true">&gt;</span></a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="page-item">
                        <a class="pagenav_a page-link" href="<%=context %>/di/mypageStudyApplyList.do?pageNum=${pageNum+1 }" aria-label="Previous">
                        <span aria-hidden="true">&gt;</span></a>
                    </li>
                </c:otherwise>
            </c:choose>
            <!-- 다음 10개 페이지 -->
            <c:choose>
                <c:when test="${endPage<pageCnt }">
                    <li class="page-item">
                        <a class="pagenav_a page-link" href="<%=context %>/di/mypageStudyApplyList.do?pageNum=${startPage+blockSize }" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span></a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="page-item" style="visibility: hidden;">
                        <a class="pagenav_a page-link" href="<%=context %>/di/mypageStudyApplyList.do?pageNum=${startPage+blockSize }" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span></a>
                    </li>
                </c:otherwise>
            </c:choose>
        </ul>
    </nav>
</section>

</main>
<!-- footer 영역 -->
<%@include file ="../common/footer.jsp" %>
</body>
</html>