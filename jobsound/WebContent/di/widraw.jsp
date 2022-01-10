<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% String context = request.getContextPath(); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:if test="${result>0 }">
	<script type="text/javascript">
		alert("회원탈퇴되었습니다. 감사합니다.")
		location.href="../di/testLogin.jsp";
	</script>
</c:if>
<c:if test="${result==0 }">
	<script type="text/javascript">
		alert("회원탈퇴에 실패하였습니다.")
		location.back();
	</script>
</c:if>
</body>
</html>