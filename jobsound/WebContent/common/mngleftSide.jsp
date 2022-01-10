<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
							style="font-family: 'jalnan'; font-size: 30px; color: white;">관리자페이지</p>	 <!-- el 표기로 parameter 받아서 표시해야함 -->
					</div>
				</div>
				<ul class="list-unstyled mx-auto">
					<!-- href 설정 -->
					<c:choose>
						<c:when test="${sessionScope.page==5 }">
							<li class="nav-item py-1"><a
								class="side_nav nav-link border-bottom border-2" id="nav1" href="../sw/mngMemList.do" style="color: red">회원 관리</a></li>
							<li class="nav-item py-1"><a
								class="side_nav nav-link border-bottom border-2" id="nav2" href="../sw/mngBoardList.do">게시물 관리</a></li>
							<li class="nav-item py-1"><a
								class="side_nav nav-link border-bottom border-2" id="nav3" href="../sw/mngCommList.do">댓글 관리</a></li>
							<li class="nav-item py-1"><a
								class="side_nav nav-link border-bottom border-2" id="nav4" href="../sw/mngInqList.do">문의사항처리</a></li>
						</c:when>
						<c:when test="${sessionScope.page==6 }">
							<li class="nav-item py-1"><a
								class="side_nav nav-link border-bottom border-2" id="nav1" href="../sw/mngMemList.do">회원 관리</a></li>
							<li class="nav-item py-1"><a
								class="side_nav nav-link border-bottom border-2" id="nav2" href="../sw/mngBoardList.do" style="color: red">게시물 관리</a></li>
							<li class="nav-item py-1"><a
								class="side_nav nav-link border-bottom border-2" id="nav3" href="../sw/mngCommList.do">댓글 관리</a></li>
							<li class="nav-item py-1"><a
								class="side_nav nav-link border-bottom border-2" id="nav4" href="../sw/mngInqList.do">문의사항처리</a></li>
						</c:when>
						<c:when test="${sessionScope.page==7 }">
							<li class="nav-item py-1"><a
								class="side_nav nav-link border-bottom border-2" id="nav1" href="../sw/mngMemList.do">회원 관리</a></li>
							<li class="nav-item py-1"><a
								class="side_nav nav-link border-bottom border-2" id="nav2" href="../sw/mngBoardList.do">게시물 관리</a></li>
							<li class="nav-item py-1"><a
								class="side_nav nav-link border-bottom border-2" id="nav3" href="../sw/mngCommList.do"  style="color: red">댓글 관리</a></li>
							<li class="nav-item py-1"><a
								class="side_nav nav-link border-bottom border-2" id="nav4" href="../sw/mngInqList.do">문의사항처리</a></li>
						</c:when>
						<c:when test="${sessionScope.page==8 }">
							<li class="nav-item py-1"><a
								class="side_nav nav-link border-bottom border-2" id="nav1" href="../sw/mngMemList.do">회원 관리</a></li>
							<li class="nav-item py-1"><a
								class="side_nav nav-link border-bottom border-2" id="nav2" href="../sw/mngBoardList.do">게시물 관리</a></li>
							<li class="nav-item py-1"><a
								class="side_nav nav-link border-bottom border-2" id="nav3" href="../sw/mngCommList.do">댓글 관리</a></li>
							<li class="nav-item py-1"><a
								class="side_nav nav-link border-bottom border-2" id="nav4" href="../sw/mngInqList.do" style="color: red">문의사항처리</a></li>
						</c:when>
					</c:choose>
				</ul>
			</div>
		</nav>
	</aside>
</body>
</html>