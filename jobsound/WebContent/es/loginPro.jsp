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
<c:if test="${result==1 }">
	<script type="text/javascript">
		alert("환영 합니다 ${sessionScope.sessionID}님");
		location.href="<%=context %>/dy/main.do"

	</script>
</c:if>
<c:if test="${result==0 }">
	<script type="text/javascript">
		alert("아이디 혹은 비밀번호가 틀립니다.");
		history.go(-1);
	</script>
</c:if>
<c:if test="${result==-1 }">
	<script type="text/javascript">
		alert("아이디가 존재하지 않습니다.");
		history.go(-1);
	</script>
</c:if>
</body>
</html>