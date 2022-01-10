<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>left side</title>
<!-- 부트스트랩 CDN -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">	<!-- 부트스트랩 icon CDN -->
<link href="../common/style.css" rel="stylesheet" type="text/css">	<!-- style.css link -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</head>
<body>
	<aside class=left_side>
		<nav class="navbar navbar-expand-lg mt-5">
			<div class="mx-auto mt-3">
				<div class="card">
					<img src="../common/images/side_nav.png" class="card-img">
					<div class="card-img-overlay mt-3">
						<p class="card-title text-center"
							style="font-family: 'jalnan'; font-size: 30px; color: white;">문의사항</p>	 <!-- el 표기로 parameter 받아서 표시해야함 -->
					</div>
				</div>
				<ul class="list-unstyled mx-auto">
					<!-- href 설정 -->
					<li class="nav-item py-1"><a
						class="side_nav nav-link border-bottom border-2" href="../di/inquireWriteForm.do">문의하기</a></li>
					<!-- <li class="nav-item py-1"><a
						class="side_nav nav-link border-bottom border-2" href="../yr/faqListSub1.jsp">FAQ</a></li>   버튼 누르면 게시판 목록으로 되돌아가는 url 붙이기 -->
 
 <!-- FAQ DropDown -->
					<div class="dropdown">
					  <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false" style="background-color: white; color: black; width: 230px" >
					    <span style="font-size: 20px"><strong>FAQ</strong></span>
					  </button>
					  <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1" style="width: 230px">
					    <li><a class="dropdown-item" href="../yr/faqListSub1.jsp">회원가입/탈퇴</a></li>
					    <li><a class="dropdown-item" href="../yr/faqListSub2.jsp">사이트이용</a></li>
					    <li><a class="dropdown-item" href="../yr/faqListSub3.jsp">고객센터 안내</a></li>
					  </ul>
					</div>
					
					<div class="dropdown">
				</ul>
			</div>
		</nav>
	</aside>
</body>
</html>