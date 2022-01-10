<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>left side</title>
<!-- 부트스트랩 CDN -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">	<!-- 부트스트랩 icon CDN -->
<link href="../common/style.css" rel="stylesheet" type="text/css">	<!-- style.css link -->
</head>
<body>
	<aside class=left_side>
		<nav class="navbar navbar-expand-lg mt-5">
			<div class="mx-auto mt-3">
				<div class="card">
					<img src="../common/images/side_nav.png" class="card-img">
					<div class="card-img-overlay mt-3">
						<p class="card-title text-center"
							style="font-family: 'jalnan'; font-size: 30px; color: white;">커뮤니티</p>	 <!-- el 표기로 parameter 받아서 표시해야함 -->
					</div>
				</div>
				<ul class="list-unstyled mx-auto">
					<!-- href 설정 -->
					<c:choose>
						<c:when test="${sessionScope.page==1 }">
							<li class="nav-item py-1"><a
								class="side_nav nav-link border-bottom border-2" id="nav1" href="../sh/comuBoardList.do" style="color: red;">커뮤니티 게시판</a></li>
							<li class="nav-item py-1"><a
								class="side_nav nav-link border-bottom border-2" id="nav2" href="../es/recuBoardList.do">스터디 모집 게시판</a></li>
						</c:when>
						<c:otherwise>
							<li class="nav-item py-1"><a
								class="side_nav nav-link border-bottom border-2" id="nav1" href="../sh/comuBoardList.do">커뮤니티 게시판</a></li>
							<li class="nav-item py-1 text-danger"><a
								class="side_nav nav-link border-bottom border-2" id="nav2" href="../es/recuBoardList.do" style="color: red;">스터디 모집 게시판</a></li>
						</c:otherwise>
					</c:choose>
				</ul>
			</div>
		</nav>
		<!-- 배너 광고 -->	
		<div id="carouselExampleDark" class="carousel carousel-dark slide mx-auto" data-bs-ride="carousel" style="width: 190px;">
			<div class="carousel-indicators">
		    	<button type="button" data-bs-target="#carouselExampleDark" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
		    	<button type="button" data-bs-target="#carouselExampleDark" data-bs-slide-to="1" aria-label="Slide 2"></button>
		    	<button type="button" data-bs-target="#carouselExampleDark" data-bs-slide-to="2" aria-label="Slide 3"></button>
		  </div>
		  <div class="carousel-inner" style="width: 190px;">
		  	<div class="carousel-item active" data-bs-interval="5000">
		    	<a href="https://www.jobkorea.co.kr/Theme/djjobfair">
		        	<img src="../common/images/djjobfair_190_250.jpg" alt="대전 온라인 일자리박람회">
		         </a>
		    </div>
		    <div class="carousel-item" data-bs-interval="5000" style="width: 190px;">
		    	<a href="https://hansik.or.kr/kr/board/no/view/001?seq=59526&amp;menuId=31&amp;experience_notice">
		        	<img src="../common/images/211015_hansik_190250.png" alt="한식 콘텐츠 스토리텔링 공모전">
		      	</a>
		      </div>
		    <div class="carousel-item" style="width: 190px;">
  	    		 <a href="https://jffic.kotra-micehub.com/fairDash.do">
 	        		<img src="../common/images/외국인투자기업채용박람회_190x250.png" alt="외국인투자기업 채용박람회">
	       		</a>
	    	  </div>
		  </div>
		  <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleDark" data-bs-slide="prev">
		    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
		    <span class="visually-hidden">Previous</span>
		  </button>
		  <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleDark" data-bs-slide="next">
		    <span class="carousel-control-next-icon" aria-hidden="true"></span>
		    <span class="visually-hidden">Next</span>
		  </button>
		</div>		
	</aside>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous"></script>
</body>
</html>