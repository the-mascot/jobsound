<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>'
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% String context = request.getContextPath(); %>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous"><link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">	<!-- 부트스트랩 icon CDN -->
<link href="<%=context %>/common/style.css" rel="stylesheet" type="text/css">	<!-- style.css link -->
<script type="text/javascript" uri=></script>

<meta charset="UTF-8">
<title>마이페이지-개인정보조회</title>
<script type="text/javascript" src="../js/jquery.js"></script>
<script type="text/javascript">
	$(function() {
		$('#chk').click(function(){
			var id = $('#id').val();
			var inqNum = $('#inqNum').val();
			var passwd = $('#passwd').val();
			var sendData = 'id='+id+'&passwd='+passwd +'&inqNum='+inqNum+'&isBjax=1';
			$.ajax({
				url : 'myInquirePass.jsp',
				data : sendData,
				dataType : 'text',
				success : function(value) {
					$('#msg').html(value);
				}
			})
		})
	})
</script>
<style type="text/css">
	.center{
		margin : 40px;
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
		<h2><center>문의사항 삭제</center></h2>
	</div>
	<br><br>
		<div>
			<h3>비밀번호 입력</h3>
		</div>
	<br>
		<div>본인 확인을 위해 비밀번호를 한 번 더 입력해 주세요.</div>
	<p/>
	<hr>
	<div class="row">
		<div class="col-3"><center>비밀번호</center></div>
		<div class="col-9"><input type="password" name="passwd" id="passwd">
		<input type="hidden" name="id" id="id" value="${id}">
		<input type="hidden" name="inqNum" id="inqNum" value="${param.inqNum }">
		<input type="button" class="btn btn-primary btn-sm" value=" 확인 " id="chk">
		<span id="msg"></span>
		</div>
	</div>
	<hr>
</section>

</main>
<!-- footer 영역 -->
<%@include file ="../common/footer.jsp" %>
</body>
</html>