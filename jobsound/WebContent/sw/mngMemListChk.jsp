<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!-- 부트스트랩 CDN -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">	<!-- 부트스트랩 icon CDN -->
<link href="../common/style.css" rel="stylesheet" type="text/css">	<!-- style.css link -->

<style>
#c { background-color: rgba(13, 110, 253, 0.1); }
</style>

</head>
<body>

<!-- header 부분 -->
<%@include file ="../common/header.jsp" %>

<!-- 상단 navbar 영역 -->
<%@include file ="../common/headNav.jsp" %>

<!-- main 영역 : main 안에 left_side - section - right_side로 3등분 됨 2:6:2 비율 -->
<main>
<!-- left side 영역 -->
<%@include file ="../common/mngleftSide.jsp" %>



<!-- section 영역 -->
<section>
<!-- 게시판 설명 -->
	<div>
		<div>
			<h2 class="mt-5 mb-3 mx-5 fw-bold" style="font-family: pretend; color: #5798D7">회원정보 자세히 보기</h2>
		</div>
		<div>
			<p class="mt-2 mx-5 fs-6 text-muted fst-italic" style="font-family: pretend">- 관리자는 자세한 '회원 정보'을 조회, 검색 할 수 있습니다.</p>
		</div>
	</div>


	<div class="mx-auto my-5">
		<table class="board_table table mx-auto table-hover table-responsive text-center">
			
			<c:set var="tel" value="${mi.tel}"/>
			<c:set var="birth" value="${mi.birth}"/>
			
			<tr>
			<td id="c">아이디</td><td>${mi.id}</td>
			</tr>
			<tr>
			<td id="c">이름</td><td>${mi.name}</td>
			</tr>
			<tr>
			<td id="c">닉네임</td><td>${mi.nickname}</td>
			</tr>
			
			<c:if test="${mi.gender==1}">
			<tr>
			<td id="c">성별</td><td>남자</td>
			</tr>
			</c:if>
			<c:if test="${mi.gender==2}">
			<tr>
			<td id="c">성별</td><td>여자</td>
			</tr>
			</c:if>
			
			<tr>
			<%-- <td id="c">전화번호</td><td>${mi.tel}</td> --%>
			<td id="c">전화번호</td><td>${fn:substring(tel,0,3)}-${fn:substring(tel,3,7)}-${fn:substring(tel,7,11)}</td>
			</tr>
			<tr>
			<td id="c">이메일</td><td>${mi.email}</td>
			</tr>
			<tr>
			<%-- <td id="c">생년월일</td><td>${mi.birth}</td> --%>
			<td id="c">생년월일</td><td>${fn:substring(birth,0,4)}년 ${fn:substring(birth,4,6)}월 ${fn:substring(birth,6,8)}일</td>
			
			</tr>
			<tr>
			<td id="c">상세주소</td><td>${mi.addr}</td>
			</tr>
			
			<c:if test="${mi.reg_chk==1}">
			<tr>
			<td id="c">가입동의여부</td><td>동의</td>
			</tr>
			</c:if>
			<c:if test="${mi.reg_chk==2}">
			<tr>
			<td id="c">가입동의여부</td><td>비동의</td>
			</tr>
			</c:if>
			
			<tr>
			<td id="c">회원가입일</td><td>${mi.regdate}</td>
			</tr>
		</table>
	</div>
</section>



<!-- right side 영역 -->
<c:choose>
   <c:when test="${sessionScope.sessionID==null }">
      <%@include file ="../common/guestForm.jsp" %>
   </c:when>
   <c:otherwise>
      <%@include file ="../common/loginForm.jsp" %>
   </c:otherwise>
</c:choose>
</main>

<!-- footer 영역 -->
<%@include file ="../common/footer.jsp" %>

</body>
</html>