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
<title>Insert title here</title>
</head>
<body>
<c:if test="${empty id}">
	<script type="text/javascript">
		alert("정보를 다시 입력해주세요");
	    location.href="acntSch.jsp";
	</script>
</c:if>
<c:if test="${empty name}">
	<script type="text/javascript">
		alert("정보를 다시 입력해주세요");
		location.href="acntSch.jsp";
	</script>
</c:if>
<c:if test="${empty email}">
	<script type="text/javascript">
		alert("정보를 다시 입력해주세요");
		location.href="acntSch.jsp";
	</script>
</c:if>
</body>
</html>