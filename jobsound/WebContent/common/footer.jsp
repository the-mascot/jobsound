<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>footer</title>
<!-- 부트스트랩 CDN -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">	<!-- 부트스트랩 icon CDN -->
<link href="../common/style.css" rel="stylesheet" type="text/css">	<!-- style.css link -->
</head>
<body>
	<footer class="py-1 my-2">
		<ul class="nav justify-content-center border-bottom pb-1 mb-1">
			<!-- href 설정 -->
			<li class="nav-item"><a href="../dy/main.do" class="fot_nav nav-link px-3 text-muted">Home</a></li>
			<li class="nav-item"><a href="#" class="fot_nav nav-link px-3 text-muted">개인정보처리방침</a></li>
			<li class="nav-item"><a href="https://m.facebook.com/meggooltip/photos/pcb.2470601506373821/2470601166373855/?type=3&source=48&locale2=zh_CN&__tn__=EH-R" class="fot_nav nav-link px-3 text-muted">고먐미</a></li>
			<c:if test="${sessionScope.sessionID!=null }">
				<li class="nav-item"><a href="../yr/faqListSub1.jsp" class="fot_nav nav-link px-3 text-muted">FAQs</a></li>
			</c:if>
		</ul>
		<p class="text-center text-muted" style="font-size: 13px;">© 2021 Job소리, Inc</p>
	</footer>
</body>
</html>