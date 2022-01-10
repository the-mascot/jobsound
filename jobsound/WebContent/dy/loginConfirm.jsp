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
<c:if test="${fail > 0 }">
	<script type="text/javascript">
		alert("입력완료");
	    location.href="<%=context %>/dy/main.do";
	</script>
</c:if>
<c:if test="${fail == 0 }">
	<script type="text/javascript">
		alert("비밀번호 오류");
		location.href="<%=context %>/dy/login.do";
	</script>
</c:if>
<c:if test="${fail == -1 }">
	<script type="text/javascript">
		alert("아이디 입력 오류");
		location.href="<%=context %>/dy/login.do";
	</script>
</c:if>
</body>
</html>