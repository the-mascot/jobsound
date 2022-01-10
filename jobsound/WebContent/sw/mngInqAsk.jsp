<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>     
    
<!DOCTYPE html>
<html>

<%
	String context = request.getContextPath();
%>

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
			<h2 class="mt-5 mb-3 mx-5 fw-bold" style="font-family: pretend; color: #5798D7">문의사항 답변 페이지</h2>
		</div>
		<div>
			<p class="mt-2 mx-5 fs-6 text-muted fst-italic" style="font-family: pretend">- 유저들의 문의사항에 답변해야 합니다.</p>
		</div>
	</div>

	
	<div class="mx-auto my-5">
	
<form action = "<%=context%>/sw/mngInqAsk.do" method="post">
<table class="board_table table mx-auto table-hover table-responsive">
	<tr><td width="100" id="c">문의번호</td><td>${inquire.inqnum}</td></tr>
	<tr><td id="c">문의제목</td><td>${inquire.inqtitle}</td></tr>
	<tr><td id="c">아이디</td><td>${inquire.id}</td></tr>
	<tr><td id="c">문의전송일</td><td>${inquire.writedate}</td></tr>
	<tr><td id="c">문의사항</td><td>${inquire.inqcontent}</td></tr>
	
	<!-- 문의답변 내용이 있다면, 답변 내용을 불러온다. -->
	<c:if test="${inquire.askcontent != null}">
	<tr><td id="c">답변내용</td><td>${inquire.askcontent}</td></tr>
	</c:if>
	
	<!-- 문의 답변 내용이 없다면, 입력 창을 불러온다. -->
	<c:if test="${inquire.askcontent == null}">
	<tr>
	<td id="c">답변내용</td>
	<td>
	<textarea rows="10" cols="40" name="content" required="required"></textarea>
	</td>
	</tr>
	
	<tr><input type="hidden" name="inqnum" value="${inquire.inqnum}"></tr>
	</c:if>
	
	<tr><td id="c">답변시간</td><td>${inquire.askdate}</td></tr>
	
	<tr>
	<td colspan="2"><input type="submit" value="답변하기"></td>
	</tr>
	
	</table>
	</form>
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