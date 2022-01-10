<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% String context = request.getContextPath(); %>

<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous"><link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">	<!-- 부트스트랩 icon CDN -->
<link href="<%=context %>/common/style.css" rel="stylesheet" type="text/css">	<!-- style.css link -->
<script type="text/javascript" uri=></script>

<meta charset="UTF-8">
<title>마이페이지-개인정보수정</title>
<script type="text/javascript" src="../js/jquery.js"></script>
<script type="text/javascript">
	function cancel(){
		location.href="#"; // 메인페이지로 이동
	}
</script>
<style type="text/css">
	.center{
		margin : 40px;
	}
	.fontex{
		font-size : 15px;
		font-weight: bold;
	}
	table {
	  	margin-left:auto; 
    	margin-right:auto;
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
		<h2><center>개인정보 조회 및 수정</center></h2>
	</div>
	<br>
		<div class="fontex" >
			회원정보는 개인정보 취급방침에 따라 안전하게 보호되며 회원님의 명백한 동의 없이는 공개 또는 제3자에게 제공되지 않습니다.<p>
		</div>
	<br>
	<form action="<%=context %>/di/mypageUpdatePro.do" method="post">
		<table>
			<tr>
				<td><div><center>이름</center></div><p></td><td>　</td><td><input id="name" name="name" class="form-control" type="text" value="${mi.name }" aria-label="readonly input example" readonly><p></td>
			</tr>
			<tr>
				<td><div><center>아이디</center></div><p></td><td>　</td><td><input id="id" name="id" class="form-control" type="text" value="${mi.id }" aria-label="readonly input example" readonly><p></td>
			</tr>
			<tr>
				<td><div><center>닉네임</center></div><p></td><td>　</td><td><input id="nickname" name="nickname" class="form-control" type="text" value="${mi.nickname }" aria-label="readonly input example" readonly><p></td>
			</tr>
			<tr>
				<td><div><center>성별</center></div><p></td><td>　</td><td>
					<c:set var="gender" value="${mi.gender }"/>
					<c:choose>
						<c:when test="${gender eq '1'}">남자</c:when>
						<c:when test="${gender eq '0'}">여자</c:when>
					</c:choose>
					<p></td>
			</tr>
			<tr>
				<td><div><center>연락처</center></div><p></td><td>　</td><td><input class="form-control" type="text" name="tel" id="tel" value="${mi.tel }" required="required"><p></td>
			</tr>
			<tr>
				<td><div><center>이메일</center></div><p></td><td>　</td><td><input class="form-control" type="text" name="email" id="email" value="${mi.email }" required="required"><p></td>
			</tr>
			<tr>
				<td><div><center>주소</center></div><p></td><td>　</td><td><input class="form-control" type="text" name="addr" id="addr" value="${mi.addr }" required="required"><p></td>
			</tr>
		</table>
		<table>
			<tr><td>　</td></tr>
			<tr>
				<td>
					<button type="submit" class="btn btn-primary btn-sm">　확인　</button>
					<button type="button" class="btn btn-secondary btn-sm" onclick="cancel()">　취소　</button></td>
			</tr>
			<tr><td>　</td></tr>
		</table>
	</form>
</section>

</main>
<!-- footer 영역 -->
<%@include file ="../common/footer.jsp" %>
</body>
</html>