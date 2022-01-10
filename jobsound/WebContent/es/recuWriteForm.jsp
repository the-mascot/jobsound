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
<meta name="view=viewport" content="width=device-width", initial-scale="1.0">
<title>index</title>
<!-- 부트스트랩 CDN -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">	<!-- 부트스트랩 icon CDN -->
<link href="<%=context %>/common/style.css" rel="stylesheet" type="text/css">	<!-- style.css link --> 
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
					url		: '<%=context %>/es/AjaxupLoadImg.do',
					type	: 'POST',
					data	: formData,
					async	: false,
					contentType	: false,
					processData	: false,
					timeout	: 600000,
					success	:function(data) {
						console.log(data);
						$('#imagepath').val(data);
						$('#msg').text("이미지가 업로드 되었습니다");
					},
					error	:function(e) {
						alert("업로드 실패");
						console.log("error : ",e);
					}
				});
				
			}
		});
	  	
		function getToday() {
			var date=new Date();
			return date.getFullYear()+"-"+("0"+(date.getMonth()+1)).slice(-2)+"-"+("0"+date.getDate()).slice(-2);
		}
		
		$("#stdstart_date, #stdend_date").on("change", function() {
	        var startDate=$("#stdstart_date").val();
	        var endDate=$("#stdend_date").val();
	        var result1="※종료일이 시작일 보다 빠릅니다";
	        var result2="※시작일을 오늘 이후로 설정 하십시오";
			today=getToday();
			console.log(today)
	        console.log(endDate)
	      	if((startDate>endDate) && (endDate!="")) {
		        $('#checkDate').html(result1);
				$("#stdend_date").val("");
			} else if(startDate<today) {
				$('#checkDate').html(result2);
				$("#stdend_date").val("");				
			}
			else {
				$('#checkDate').html("");
			}
		});
		
		$("#recru_date, #recu_date").on("change", function() {
	        var startDate=$("#recru_date").val();
	        var endDate=$("#recu_date").val();
	        var result1="※종료일이 시작일 보다 빠릅니다";
	        var result2="※시작일을 오늘 이후로 설정 하십시오";
			today=getToday();
			console.log(today)
	        console.log(endDate)
	      	if((startDate>endDate) && (endDate!="")) {
		        $('#checkDate2').html(result1);
				$("#stdend_date").val("");
			} else if(startDate<today) {
				$('#checkDate2').html(result2);
				$("#stdend_date").val("");				
			}
			else {
				$('#checkDate2').html("");
			}
		});
		
	
		  document.getElementById('recru_date').value = new Date().toISOString().substring(0, 10);

		
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
	<div>
		<div>
			<h2 class="mt-5 mb-3 mx-5 fw-bold" style="font-family: pretend; color: #5798D7">스터디 모집 게시판</h2>
		</div>
		<div>
			<p class="mt-2 mx-5 fs-6 text-muted fst-italic" style="font-family: pretend">- 스터디 모집 합니다~</p>
		</div>
	</div>
	<div class="container">
		<div class="col-md-10 mx-auto my-1" style="min-height: 700px;">
			<form action="<%=context %>/es/recuWrite.do" method="post">
				<input type="hidden" value="${pageNum }" name="pageNum">
				<input type="hidden" value="${pageSize }" name="pageSize">
				<!-- 등록 / 취소 버튼 -->
				<div class="row mt-5 mb-1" style="float: right;">
					<div class="col">
						<input type="submit" class="btn btn-outline-dark" value="등록">
					</div>
					<div class="col">
						<a href="<%=context %>/es/recuBoardList.do?pageNum=${pageNum}&pageSize=${pageSize}"><button type="button" class="btn btn-outline-dark">취소</button></a>
					</div>
				</div> 
				<!--글 제목라인 -->
				<div class="writeFormTitle col-12 mt-5 mb-5" style="clear: both;">
					<div>
						<label for="title">글 제목</label>
					</div>
					<div>
						<input type="text" class="form-control" id="title" name="title"
							placeholder="글 제목을 입력하세요" required="required">	
					</div>
				</div>
				<!-- 스터디 정보 입력 -->
				<div style="border: 1px solid rgba(65, 75, 70, 0.2);">
					<div class="col-8 my-3 ms-3">
						<label for="stdtitle" class="form-label">스터디 제목</label> 
						<input type="text" class="form-control" id="stdtitle" name="stdtitle"
							placeholder="스터디 제목을 입력하세요" required="required">
					</div>
					<div class="col-3 my-3 ms-3">
						<label for="stddiv" class="form-label">스터디 분류 [10글자 이내]</label> 
						<input type="text" class="form-control" id="stddiv" name="stddiv"
							placeholder="스터디 분류를 입력하세요" required="required" maxlength="10">
					</div>
					<div class="col-2 mb-4 ms-3">
						<label for="stdpn" class="form-label">스터디 인원</label> 
						<select class="form-select" id="stdpn" name="stdpn">
							<option value="3">3명</option>
							<option value="4">4명</option>
							<option value="5">5명</option>
							<option value="6">6명</option>
							<option value="7">7명</option>
							<option value="8">8명</option>
							<option value="9">9명</option>
							<option value="10">10명</option>
						</select>
					</div>
					<div class="container mb-4">
						<div class="row">
							<div class="col-2">
								<div>
									<label for="stdstart_date">스터디 시작일</label>
								</div>
									<input type="date" id="stdstart_date" name="stdstart_date" required="required">
							</div>
							<div class="col-2 ms-5">
								<div>
									<label for="stdend_date">스터디 마감일</label>
								</div>
									<input type="date" id="stdend_date" name="stdend_date" required="required">
							</div>
							<div class="col ms-4 mt-4">
								<p class="text-danger" id="checkDate"></p>
							</div>
						</div>
					</div>		
					<div class="container mb-4">
						<div class="row">
							<div class="col-2">
								<div>
									<label for="recru_date">모집 시작일</label>								
								</div>
									<input type="date" id="recru_date" readonly="readonly" name="recru_date" required="required">
							</div>
							<div class="col-2 ms-5">
								<div>
									<label for="recu_date">모집 마감일</label>
								</div>
									<input type="date" id="recu_date" name="recu_date" required="required">
							</div>
							<div class="col ms-4 mt-4">
								<p class="text-danger" id="checkDate2"></p>
							</div>
						</div>
					</div>
				</div>
				<!-- 글 내용 영역 -->
				<div class="mt-3">
					<div>
						<label for="content">글 내용</label>
					</div>
					<div>
						<textarea class="form-control" id="content" name="content"
							placeholder="글 내용을 입력하세요" style="min-height: 300px;"></textarea>	
					</div>
				</div>
					<input type="text" hidden="hidden" id="imagepath" name="imagepath">
			</form>
			<!-- 사진 업로드 영역 -->
			<form method="post" enctype="multipart/form-data" id=upLoadForm>
				<div class="my-3">
				  <label for="uploadpath" class="form-label">사진 파일 업로드</label>
				  <input class="form-control" type="file" id="uploadpath" name="uploadpath">
				  <p class="text-danger" id=msg></p>
				</div>
			</form>
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