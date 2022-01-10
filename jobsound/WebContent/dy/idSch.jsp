<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<%
   String context = request.getContextPath();
%>
<head>
<meta charset="UTF-8">
<title>아이디 찾기 완료</title>
<!-- 부트스트랩 CDN -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">	<!-- 부트스트랩 icon CDN -->
<link href="../common/style.css" rel="stylesheet" type="text/css">	<!-- style.css link -->
</head>
<style>

/*----------------상단부---------------*/
.logo_idsch{
	width: 1200px;
	height: 100px;
	/* border: 1px solid yellow; */
	top: 50%;
	left: 50%;
	margin: auto;
}
.p_idsch{
	font-size: 50px;
	text-align: center;
}
.wrapper_idsch{
	/* border: 2px solid black; */
	width: 1200px;
	height: 520px;
	top: 50%;
	left: 50%;
	margin: auto;
	
	
}
/*----------------중반부----------------*/
.midbox_idsch{
	width: 800px;
	height: 410px;
	border: 2px solid #F5F5F5;
	top: 50%;
	left: 50%;
	margin: auto;
	background-color: #D4D4D4;
}
.graybox_idsch{
	padding: 100px;
	width: auto;
	height: 260px;
	
}
.blueline_idsch{
	background-color:white;
	border: 3px solid #00DEFF;
	width: 400px;
	height: 50px;
	top: 50%;
	left: 50%;
	margin:auto;
	
}
.whitebox_idsch{
	background-color: white;
	width:auto;
	height: 145px;
	
}

.bluebox1_idsch{
	
	position:relative;
	float: left;
	width: 250px;
	height: 50px;
	left: 140px;
	top: 50px;
	border:none;
}
.bluebox2_idsch{
	
	position:relative;
	float: left;
	width: 250px;
	height: 50px;
	left: 220px;
	top: 50px;
	border:none;
}

</style>
<body>
<div class="logo_idsch">
	<div class="header col-lg-offset-5 col-lg-2">
		<a href="<%=context %>/dy/main.do" class="logo"><img src="../common/images/jobsound_logo.png"  align class="img_sori"></a>
	</div>
</div>	
	
	<hr>
	
	<div class="wrapper_idsch">
		<div style="margin-left:200px;">
		<h6>아이디 찾기가 완료되었습니다.</h6>
		</div>
		<div class="midbox_idsch">
			<div class="graybox_idsch">
				<div class="blueline_idsch"><p align="center">${id }</p></div>
			</div>
			<div class="whitebox_idsch">
				<div class="bluebox1_idsch"><button class="btn btn-primary btn-lg btn-block" type="submit" onclick="location.href='<%=context %>/dy/main.do'">&nbsp로그인하기&nbsp</button></div>
				<div class="bluebox2_idsch"><button class="btn btn-primary btn-lg btn-block" type="submit" onclick="location.href='acntSch.jsp'">비밀번호찾기</button></div>
			</div>
		</div>
	</div>
															<!-- footer부분 -->
<!-- footer 영역 -->
<%@include file ="../common/footer.jsp" %> 
	



</body>
</html>