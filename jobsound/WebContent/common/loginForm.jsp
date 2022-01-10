<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>right side</title>
<!-- 부트스트랩 CDN -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">	<!-- 부트스트랩 icon CDN -->
<link href="../common/style.css" rel="stylesheet" type="text/css">	<!-- style.css link -->
<script type="text/javascript">

</script>
</head>
<body>
<aside class="right_side">
	<div class="side_login_c mt-5 container border border-1">
		<div class="container">
			<div class="row">
				<div class="col-4">
					<c:choose>
						<c:when test="${sessionScope.gender==1 }">
							<img class="rounded-circle img-fluid border border-secondary mt-3" src="../common/images/boy.jpg"  alt="...">
						</c:when>
						<c:when test="${sessionScope.gender==2 }">
							<img class="rounded-circle img-fluid border border-secondary mt-3" src="../common/images/girl.jpg"  alt="...">
						</c:when>
					</c:choose>
				</div>
				<div class="col">
					<div>
						<p class="mt-3 mb-1 fs-3" style="font-family: pretend">${sessionScope.nickname }님</p>
					</div>
					<div class="d-flex">
						<div>
							<p class="pe-2" style="border-right: 1px solid rgba(65, 75, 70, 0.5)">ID : ${sessionScope.sessionID }</p>
						</div>
						<div class="ms-2">
							<a href="../di/mypageCheck.jsp" class="text-decoration-none text-muted">내정보</a>
						</div>
					</div>
				</div>
			</div>
			<div class="clearfix">
				<div class="float-end">
					<a href="../es/logout.do"><input type="button" class="rounded-pill px-2 mb-3" value="로그아웃" 
						style="border: none; background-color: #9ACDE6; font-family: pretend; color: white; font-size: 20px;"></a>
				</div>
			</div>
		</div>
	</div>
	<input class="btn btn-outline-primary" type="button" onclick="window.scrollTo(0,0);" value="TOP" style="position: fixed; right: 50px; bottom: 50px;">
</aside>
</body>
</html>