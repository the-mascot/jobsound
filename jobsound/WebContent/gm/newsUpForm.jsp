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
<title>글 수정</title>
<!-- 부트스트랩 CDN -->
<meta name="view=viewport" content="width=device-width" initial-scale="1.0">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
<!-- 부트스트랩 icon CDN -->
<link href="../common/style.css" rel="stylesheet" type="text/css">
<!-- Jquery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script> 
<script type="text/javascript">
	$(document).ready(function(){
    $("#uploadpath").on("change", function(){
       var fileName = $("#uploadpath").val();
       fileName = fileName.slice(fileName.indexOf(".") + 1).toLowerCase();
       if(fileName != "jpg" && fileName != "png" &&  fileName != "gif" &&  fileName != "bmp"){
       alert("이미지 파일은 (jpg, png, gif, bmp) 형식만 등록 가능합니다.");
       $("#uploadpath").val("");
       return;
       } else {
          var form=$('#upLoadForm')[0];
          var formData=new FormData(form);
          
          $.ajax({
             url      : '<%=context%>/es/AjaxupLoadImg.do',
             type   : 'POST',
             data   : formData,
             async   : false,
             contentType   : false,
             processData   : false,
             timeout   : 600000,
             success   :function(data) {
                console.log(data);
                $('#imagepath').val(data);
                $('#msg').text("이미지가 업로드 되었습니다");
             },
             error   :function(e) {
                alert("업로드 실패");
                console.log("error : ",e);
             }
          });
          
       }
    });
	});
</script>
<!-- style.css link -->
<style type="text/css">
.board {
	font-family: pretend; 
	color: #5798D7;
}
.replyboard {
	font-family: pretend;
	color: black;
	font-size: 15px;
}
.content {
	font-family: pretend;
	color: black;
	font-size: 16px;
	min-height : 500px;
	/* max-width: 40%; */
	white-space: pre-wrap;
}
.content_up {
	color: black;
	width: 90%;
}
</style>
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
<%-- <input type="hidden" name="pageNum" value="${board.pageNum }"> --%>
<%-- <input type="hidden" name="b_num" value="${board.b_num }"> --%>
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
	<!-- 게시물 테이블 -->
<div class="content_up mx-auto mx-5 board">
	<div>
		<c:forEach var="board" items="${list}">  <!-- 여기서부터 값 -->
		<c:if test="${board.b_num==b_num}">
	<!-- 글쓰기 폼 -->
		<form action="<%=context %>/gm/updatePro.do?b_type=${board.b_type }&b_num=${board.b_num }&pageNum=${pageNum }" method="post">
		<input type="text" hidden="hidden" id="imagepath" name="imagepath" value="${board.imagepath }">
			<div style="font-family: 'jalnan'; color: 'black'">
				<h2>글 수정</h2>
			</div>
						<!-- 버튼 -->
			<div class="container" align="right" style="margin-top: -20px;">
				<input type="submit" class="btn btn-outline-dark" value="등록">
				<button class="btn btn-outline-dark" onclick="history.back()" type="button">취소</button>
			</div>
			<div class="form-group">
			<!-- title -->
				<label for="title" style="margin-top: 15px">제목</label>
				<input class="form-control" name="title" id="title" type="text"
					   required="required" value="${board.title }">
			</div>
			<!-- update_id -->
			<div class="form-group">
				<label for="id" style="margin-top: 15px">작성자</label>
				<input class="form-control" name="id" id="id" type="text" value="${sessionScope.sessionID }" readonly="readonly" style="background-color: white">
			</div>
			<div class="form-group">
			<!-- update_content -->
				<label for="content" style="margin-top: 15px">내용</label>
				<textarea rows="7" class="form-control" name="content" id="content"
					      required="required">${board.content }</textarea>
			</div>
		</form>
	<!-- 사진 업로드 영역 -->
         <form method="post" enctype="multipart/form-data" id=upLoadForm>
            <div class="my-3">
              <label for="uploadpath" class="form-label">사진 파일 업로드</label>
              <input class="form-control" type="file" id="uploadpath" name="uploadpath">
              <p class="text-danger" id=msg></p>
            </div>
         </form>
		</c:if>
		</c:forEach>
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