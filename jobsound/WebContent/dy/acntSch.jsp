<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%
   String context = request.getContextPath();
%>
<head>
<meta charset="UTF-8">
<title>계정찾기</title>
<!-- 부트스트랩 CDN -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">	<!-- 부트스트랩 icon CDN -->
<link href="../common/style.css" rel="stylesheet" type="text/css">	<!-- style.css link -->
</head>
<style>

/*----------------상단부---------------*/

.img_acnt{
	left: 15px;
	margin-top: 20px;
	width: 140px;
	height: auto;
}

/*-------------중단부-----------------*/
.wrapper_acnt{
	width:1200px;
	height: 575px;
	 border: 1px solid lightblue; 
	top: 50%;
	left: 50%;
	margin:auto;
}
.blue_page{
	position: relative;
	width: 595px;
	height: 575px;
	float:left;
	/* border: 2px solid red; */
	background-color:lightblue;
}
.basic_page{
	width: 595px;
	height:575px;
	float:left;
	/* border: 2px solid red; */
	
}
.move_acntSch{
	margin-left:40px;
	margin-top:50px;
}

/*-----------하단부-----------------*/



</style>
<body>

<div class="logo_idsch">
	<div class="header col-lg-offset-5 col-lg-2">
		<a href="<%=context %>/dy/main.do" class="logo"><img src="../common/images/jobsound_logo.png"  align class="img_sori"></a>
	</div>
</div>	

	<div class="wrapper_acnt">											<!-- section부분 -->
		<div class="blue_page">
			<div class="move_acntSch">
				<ul><li>아이디 찾기</li></ul>
					<p>※아이디는 8자 이상 12자 이하여야 합니다.<br>
					   ※아이디는 영문대소문자, 숫자로 구성해야 합니다.<br>
					   ※아이디에 특수문자 사용할 수 없습니다.</p><br>	
					   
				<form action="acntidSch.do">
					<div class="row mb-3">
					    <label for="name" class="col-sm-2 col-form-label">이름</label>
					    <div class="col-sm-8">
					      <input type="text" class="form-control"  name="name">
					    </div>
					  </div>
					  <div class="row mb-3">
					    <label for="email" class="col-sm-2 col-form-label">이메일</label>
					    <div class="col-sm-8">
					      <input type="email" class="form-control" name="email">
					    </div>
					  </div>
					   <br>
					   <br>
					  <button type="submit" class="btn btn-light" >아이디 찾기</button>
				</form>
			</div>	
		</div>
		<div class="basic_page">
			<div class="move_acntSch">
				<ul><li>비밀번호 찾기</li></ul>
				<p>※비밀번호는 8자 이상 12자 이하여야 합니다.<br>
				   ※비밀번호는 영문대소문자, 숫자,특수문자를 포함해야 합니다.<br>
				   ※비밀번호에 아이디를 포함할 수 없습니다.</p><br>
			  
			   <form action="acntpwSch.do">
					  <div class="row mb-3">
					    <label for="id" class="col-sm-2 col-form-label">아이디</label>
					    <div class="col-sm-8">
					      <input type="text" class="form-control" name="id" id="id">
					    </div>
					  </div>
					  <div class="row mb-3">
					    <label for="name" class="col-sm-2 col-form-label">이름</label>
					    <div class="col-sm-8">
					      <input type="text" class="form-control" name="name" id="name">
					    </div>
					  </div>
					  <div class="row mb-3">
					    <label for="email" class="col-sm-2 col-form-label">이메일</label>
					    <div class="col-sm-8">
					      <input type="email" class="form-control" name="email" id="email">
					    </div>
					  </div>
					 
					  <button type="submit" required="required" class="btn btn-light" >비밀번호 찾기</button>
			   </form>	
			</div>     
		</div>
	</div>


															<!-- footer부분 -->
<!-- footer 영역 -->
<%@include file ="../common/footer.jsp" %>
	
</body>
</html>