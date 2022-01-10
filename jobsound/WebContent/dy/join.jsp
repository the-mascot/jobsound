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
<title>회원가입</title>
<!-- 부트스트랩 CDN -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">	<!-- 부트스트랩 icon CDN -->
<link href="../common/style.css" rel="stylesheet" type="text/css">	<!-- style.css link -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<script type="text/javascript">
function chkPasswd() {
	if(joinfrm.passwd.value!=joinfrm.checkpasswd.value) {
		alert("비밀번호와 확인 비밀번호가 다릅니다.");
		joinfrm.checkpasswd.focus();
		return false;
	}
	return true;
}
</script>
<style>
body { background-color: #F5F5F5; }

/*---------상단부-------------  */
.img_sori{
	position: absolute;
	 left: 690px;
	margin-top: 10px;
	width: 150px;
	height: auto; 
	
}

/*--------중단부--------------  */
.wrapper_join {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
}
.container_join {
	/* border : 1px solid red; */

	height : 600px;
	width : auto;
	margin-top: 100px;
}
.signinfo{
	border:2px solid #D4D4D4;
	height:50px;
	width:400px;
	background-color: white;
	}
.inputbox{
	margin-top: 10px;
	
}


/*---------------하단부---------------*/
.signbtn{
	margin-top: 10px;
}

</style>
<body>
<!-- header -->
<div class="header_join">
<a href="<%=context %>/dy/main.do" class="logo"><img src="../common/images/jobsound_logo.png"  align class="img_sori"></a>
</div>

<!-- section -->
<div class="wrapper_join">
	<form action="<%=context %>/dy/join.do" id="joinfrm" onsubmit="return chkPasswd()" method="post">
	<div class="container_join" >
		
			<div class="form-floating mb-1">
				  <input type="text" class="form-control input-sm" id="id" name="id"  placeholder="Id">
				  <label for="floatingInput">Id</label>
			</div>
			<div class="form-floating mb-1">
				  <input type="password" class="form-control input-sm" id="passwd" name="passwd" placeholder="Password">
				  <label for="floatingInput">Password</label>
			</div>
			<div class="form-floating mb-1">
				  <input type="password" class="form-control input-sm" id="checkpasswd" name="checkpasswd" placeholder="Check Password">
				  <label for="floatingInput">Password-check</label>
			</div>
		
		<br>
	
			<div class="form-floating mb-1">
				  <input type="text" class="form-control input-sm" id="name" name="name" placeholder="name@example.com">
				  <label for="floatingInput">이름</label>
			</div>
			<div class="signinfo">
				<div style="top:50% left:50% margin:auto;">
					<div class="btn-group btn-group-toggle" data-toggle="buttons">
					  <label class="btn btn-secondary active"> 
					    <input type="radio"  name="gender" value="1" checked> 남자
					  </label>
					  <label class="btn btn-secondary">
					    <input type="radio" name="gender" value="2"> 여자
					  </label>
				  	</div>
				</div>	
		  	</div>
		
			<div class="form-floating mb-1">
			  <input type="date" class="form-control input-sm" id="birth" name="birth" placeholder="name@example.com">
			  <label for="floatingInput">날짜</label>
		    </div>
			<div class="form-floating mb-1">
			  <input type="email" class="form-control input-sm" id="email" name="email" placeholder="name@example.com" required="required">
			  <label for="floatingInput">이메일</label>
		    </div>
			<div class="form-floating mb-1">
			  <input type="text" class="form-control input-sm" id="tel" name="tel" placeholder="name@example.com">
			  <label for="floatingInput">전화번호</label>
		    </div>
			<div class="form-floating mb-1">
			  <input type="text" class="form-control input-sm" id="nickname" name="nickname" placeholder="name@example.com">
			  <label for="floatingInput">닉네임</label>
		    </div>
		
		<div class="signbtn">
		<button type="submit" class="btn btn-primary btn-lg btn-block col-lg-12">&nbsp&nbsp&nbsp&nbsp&nbsp 가입완료 &nbsp&nbsp&nbsp&nbsp&nbsp</button></div>
		
		
		
	</div>
	</form>
</div>
<!-- footer -->

</body>
</html>