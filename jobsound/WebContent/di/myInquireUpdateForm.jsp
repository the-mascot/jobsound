<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<% String context = request.getContextPath(); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문의사항 작성</title>
<!--  jQuery, bootstrap -->
    <link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
    <script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
    <script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script>

    <!-- summernote css/js-->
    <link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.css" rel="stylesheet">
    <script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.js"></script>
	<script type="text/javascript">
	$(document).ready(function () {
	    $('#summernote').summernote({
	        placeholder: '내용을 작성하세요. 최대 2048자까지 입력할 수 있습니다',
	        height: 300,
	        maxHeight: 300
	    });
	});
	function cancelChk(){
		if(confirm("글 수정을 취소하고 메인페이지로 이동하시겠습니까?")==true){
			location.href="#"; /* 메인페이지로 연결해야함 */
		} else{
			return false;
		}
	}

</script>
<style type="text/css">
	.center{
		margin : 40px;
	}
	#writeBtn{
		margin: 0 auto;
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
		<h2 style="color: rgb(87,152,215);">문의하기</h2>
		<h4>&nbsp;&nbsp;관리자에게 문의사항을 보낼 수 있습니다.</h4>
	</div>
	<form action="<%=context %>/di/myInquireUpdatePro.do" method="post">
		<input type="hidden" name="id" id="id" value="${id}">
		<input type="hidden" name="inqNum" id="inqNum" value="${inqNum}">
		<c:forEach var="list" items="${list }">
			<table class="table table-sm">
				<tr>
					<td><div class="mb-3">
						<input type="text" class="form-control" id="inqTitle" name="inqTitle" value="${list.inqTitle }">
						</div>
					</td>
				</tr>
				<tr>
					<td><textarea id="summernote" name="inqContent">${list.inqContent }</textarea></td>
				</tr>
			</table>
		</c:forEach>
		<nav class="navbar navbar-expand-lg mt-3">
			<div class="mx-auto mt-1">
				<div class="d-grid gap-2 d-md-flex justify-content-md-end">
					<!-- <button type="button" class="btn btn-primary btn-lg" onclick="writeChk()">신청</button> -->
					<input type="submit" class="btn btn-primary btn-lg" value="수정하기">
					<button type="button" class="btn btn-primary btn-lg" onclick="cancelChk()">취소</button>
				</div>
			</div>
		</nav>
	</form>
</section>

</main>
<!-- footer 영역 -->
<%@include file ="../common/footer.jsp" %>
</body>
</html>