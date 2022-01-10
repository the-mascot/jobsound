<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% String context = request.getContextPath(); %>

<!DOCTYPE html>
<html>
<head>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU"
	crossorigin="anonymous">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
<!-- 부트스트랩 icon CDN -->
<link href="<%=context %>/common/style.css" rel="stylesheet" type="text/css">
<!-- style.css link -->
<script type="text/javascript" uri=></script>

<meta charset="UTF-8">
<title>마이페이지-비밀번호 변경</title>
<script type="text/javascript" src="../js/jquery.js"></script>
<script type="text/javascript">
	function cancel() {
		location.href = "<%=context %>/di/mypageUpdateForm.do";
	}
	$(function(){
		$('#chk').click(function(){
			var passwd = $('#passwd').val();
			var repasswd = $('#repasswd').val();
			var repasswdchk = $('#repasswdchk').val();
			var sendData = 'passwd='+passwd+'&repasswd='+repasswd+'&repasswdchk='+repasswdchk+'&isBjax=1';
			$.ajax({
				url : 'myPwUpdateCheck.jsp',
				data : sendData,
				dataType : 'text',
				success : function(value){
					$('#pwchkmsg').html(value);
				}
			})
		})
	})
</script>
<style type="text/css">
.center {
	margin: 40px;
}

.fontex {
	font-size: 15px;
	font-weight: bold;
}

.btntable {
	margin-left: auto;
	margin-right: auto;
}
</style>
</head>
<body>
<%@include file="../common/header.jsp"%>
<!-- 상단 navbar 영역 -->
<%@include file="../common/headNav.jsp"%>
<main>
<!-- left side 영역 -->
<%@include file="../common/myLeftSide.jsp"%>
<!-- section 영역 -->
	<section>
		<div class="center">
			<h2>
				<center>비밀번호 변경</center>
			</h2>
		</div>
		<br>
		<div class="fontex">
			개인정보의 보호를 위해 주기적으로 비밀번호를 변경해주세요.
			<p>
		</div>
		<br>
		<table>
			<tr>
				<td><center>비밀번호 입력</center>
					<p></td>
				<td>　</td>
				<td><input class="form-control" type="password" name="passwd"
					id="passwd" required="required">
				<p></td>
				<td></td>
				<td><span class="text-primary">현재 비밀번호를 </span>입력하세요.
				<p></td>
			</tr>
			<tr>
				<td><center>변경할 비밀번호</center>
				<p></td>
				<td>　</td>
				<td><input class="form-control" type="password" name="repasswd" id="repasswd" required="required">
				<p></td>
				<td></td>
				<td>최대<span class="text-primary"> 15자까지 </span>입력하세요.<span id="msg"></span>
				<p></td>
			</tr>
			<tr>
				<td>변경할 비밀번호 확인<p></td>
				<td>　</td>
				<td><input class="form-control" type="password" name="repasswdchk" id="repasswdchk" required="required">
				<p></td>
				<td></td>
				<td><span class="text-primary">변경할 비밀번호</span>를 다시 입력하세요.<p></td>
			</tr>
		</table>
		<table class="btntable">
			<tr>
				<td>　</td>
			</tr>
			<tr>
				<td>
					<button type="button" class="btn btn-primary btn-sm" id="chk">확인</button>
					<button type="button" class="btn btn-secondary btn-sm" onclick="cancel()">취소</button>
				</td>
			</tr>
		</table>
		<table class="btntable">
			<tr>
				<td>　</td>
			</tr>
			<tr>
				<td><div><span id="pwchkmsg"></span></div></td>
			</tr>
		</table>
	</section>
</main>
<!-- footer 영역 -->
<%@include file="../common/footer.jsp"%>
</body>
</html>