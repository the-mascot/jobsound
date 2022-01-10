<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>right side</title>
<!-- 부트스트랩 CDN -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">	<!-- 부트스트랩 icon CDN -->
<link href="../common/style.css" rel="stylesheet" type="text/css">	<!-- style.css link -->
</head>
<body>
<aside class="right_side">
	<div class="side_login_c mt-5 container border border-1">
		<form action="../es/login.do" class="mx-auto mt-1" method="post">
			<div><img src="../common/images/jobsound_logo.png" class="login_logo"></div>
    		<h4 class="h4 mb-3 fw-normal text-center" style="font-family: pretend">회원 로그인</h4>
    		<div class="container border border-1">
    			<div class="row">
    				<div class="col-lg-1 pt-2">
    					<!--<svg> 부트스트랩 icon 불러오기 -->
      					<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-file-earmark-person" viewBox="0 0 16 16">
  						<path d="M11 8a3 3 0 1 1-6 0 3 3 0 0 1 6 0z"/>
  						<path d="M14 14V4.5L9.5 0H4a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h8a2 2 0 0 0 2-2zM9.5 3A1.5 1.5 0 0 0 11 4.5h2v9.255S12 12 8 12s-5 1.755-5 1.755V2a1 1 0 0 1 1-1h5.5v2z"/>
						</svg>
					</div>
					<div class="col-lg-10">
     					<input type="text" class="login_id_in"id="id" name="id" placeholder="아이디" required="required"> <!-- 아이디 -->
      				</div>
      			</div>
    		</div>
    		<div class="container border border-1 mt-2 mb-4">
    			<div class="row">
    				<div class="col-lg-1 pt-2">
      					<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-key" viewBox="0 0 16 16">
  						<path d="M0 8a4 4 0 0 1 7.465-2H14a.5.5 0 0 1 .354.146l1.5 1.5a.5.5 0 0 1 0 .708l-1.5 1.5a.5.5 0 0 1-.708 0L13 9.207l-.646.647a.5.5 0 0 1-.708 0L11 9.207l-.646.647a.5.5 0 0 1-.708 0L9 9.207l-.646.647A.5.5 0 0 1 8 10h-.535A4 4 0 0 1 0 8zm4-3a3 3 0 1 0 2.712 4.285A.5.5 0 0 1 7.163 9h.63l.853-.854a.5.5 0 0 1 .708 0l.646.647.646-.647a.5.5 0 0 1 .708 0l.646.647.646-.647a.5.5 0 0 1 .708 0l.646.647.793-.793-1-1h-6.63a.5.5 0 0 1-.451-.285A3 3 0 0 0 4 5z"/>
 						<path d="M4 8a1 1 0 1 1-2 0 1 1 0 0 1 2 0z"/>
						</svg>
    				</div>
    				<div class="col-lg-10">
      					<input type="password" class="login_passwd_in" id="password" name="passwd" placeholder="비밀번호" required="required"> <!-- 패스워드 -->   
   					</div>
    			</div>
    		</div>
		   	<button class="w-100 btn btn-lg btn-primary" style="font-family: pretend" type="submit">로그인</button> <!-- submit -->
			<div class="container mt-2 mb-2">
				<div class="row">
					<div class="col-lg-6 px-0">
						<div class="container" style="margin-left: 2px;">
							<div class="row">
								<div class="col-lg-3 px-0">
		   							<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person-plus-fill" viewBox="0 0 16 16">
		 								<path d="M1 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H1zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6z"/>
		 								<path fill-rule="evenodd" d="M13.5 5a.5.5 0 0 1 .5.5V7h1.5a.5.5 0 0 1 0 1H14v1.5a.5.5 0 0 1-1 0V8h-1.5a.5.5 0 0 1 0-1H13V5.5a.5.5 0 0 1 .5-.5z"/>
									</svg>
		  				 		</div>
		   						<div class="col-lg-9 px-0">
		   							<a href="../dy/join.jsp" style="font-size: 14px; color: black;">회원가입</a> <!-- 회원가입 link -->
								</div>		
							</div>
						</div>
					</div>
					<div class="col-lg-6 px-0">
						<div class="container px-0">
							<div class="row">
				    			<div class="col-lg-3 pl-2">
				    				<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-unlock-fill" viewBox="0 0 16 16">
		 							<path d="M11 1a2 2 0 0 0-2 2v4a2 2 0 0 1 2 2v5a2 2 0 0 1-2 2H3a2 2 0 0 1-2-2V9a2 2 0 0 1 2-2h5V3a3 3 0 0 1 6 0v4a.5.5 0 0 1-1 0V3a2 2 0 0 0-2-2z"/>
									</svg>
				    			</div>
				    			<div class="col-lg-9 px-0">
				    				<a href="../dy/acntSch.jsp" style="font-size: 14px; color: black;">ID/PW 찾기</a> <!-- id/pw 찾기 link -->
				    			</div>
							</div>
						</div>
					</div>	
				</div>
			</div>
		</form>
	</div>
	<input class="btn btn-outline-primary" type="button" onclick="window.scrollTo(0,0);" value="TOP" style="position: fixed; right: 50px; bottom: 50px;">
</aside>
</body>
</html>