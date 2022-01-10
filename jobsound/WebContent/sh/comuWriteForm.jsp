<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
   String context = request.getContextPath();
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>커뮤니티 게시판 - 글쓰기</title>
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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script> 
<!-- jQuery CDN -->
<script type="text/javascript">
	$(document).ready(function(){
		
		$("#imagepath").on("change", function(){
			var fileName = $("#imagepath").val();
			fileName = fileName.slice(fileName.indexOf(".") + 1).toLowerCase();
			if(fileName != "jpg" && fileName != "png" &&  fileName != "gif" &&  fileName != "bmp"){
			alert("이미지 파일은 (jpg, png, gif, bmp) 형식만 등록 가능합니다.");
			$("#imagepath").val("");
			return;
			}
		});
	  
	     
	});

</script>
<style type="text/css">
.board {
	font-family: pretend; 
	color: #5798D7;
}

</style>

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
	<c:choose>
	<c:when test = "${empty board}">
	<div class="container">
		<div class="col-md-10 mx-auto my-1" style="min-height: 700px;">
			<form action="<%=context%>/sh/comuWritePro.do" method="post" enctype="multipart/form-data">
				<div class="col-12 mt-5 mb-5">
					<div>
						<label for="title">글 제목</label>
					</div>
					<div>
						<input type="text" class="form-control" id="title" name="title"
							placeholder="글 제목을 입력하세요" required="required">	
					</div>
				</div>
				<div class="mt-3">
					<div>
						<label for="content">글 내용</label>
					</div>
					<div>
						<textarea class="form-control" id="content" name="content"
							placeholder="글 내용을 입력하세요" style="min-height: 300px;"></textarea>	
					</div>
				</div>
				<div class="mt-3">
				  <label for="imagepath" class="form-label">사진 파일 업로드</label>
				  <input class="form-control" type="file" id="imagepath" name="imagepath">
				</div>
				<div class="col-2 mx-auto my-5">
					<input type="submit" class="btn btn-outline-dark" value="등록">
				</div>

			</form>
		</div>
	</div>
	</c:when>
	<c:otherwise>
	<div class="container">
		<div class="col-md-10 mx-auto my-1" style="min-height: 700px;">
			<form action="<%=context%>/sh/boardUpdatePro.do" method="post" enctype="multipart/form-data">
			<input type="hidden" name="b_num" id="b_num" value="${board.b_num}">
				<div class="col-12 mt-5 mb-5">
					<div>
						<label for="title">글 제목</label>
					</div>
					<div>
						<input type="text" class="form-control" id="title" name="title"
							placeholder="글 제목을 입력하세요" required="required" value="${board.title}">
					</div>
				</div>
				<div class="mt-3">
					<div>
						<label for="content">글 내용</label>
					</div>
					<div>
						<textarea class="form-control" id="content" name="content"
							placeholder="글 내용을 입력하세요" style="min-height: 300px;">${board.content}</textarea>	
					</div>
				</div>
				<div class="mt-3">
				  <label for="imagepath" class="form-label">사진 파일 업로드</label>
				  <input class="form-control" type="file" id="imagepath" name="imagepath">
				</div>
				<div class="col-2 mx-auto my-5">
					<input type="submit" class="btn btn-outline-dark" value="등록">
				</div>
			</form>
		</div>
	</div>
	</c:otherwise>
</c:choose>
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