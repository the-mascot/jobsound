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
<title>수정 여부 확인</title>
</head>
<body>
<c:if test="${result > 0 }">
	<script type="text/javascript">
		alert("수정완료");
		location.href="<%=context%>/gm/content.do?b_num="+${b_num}+"&b_type=6";
	</script>
</c:if>
<c:if test="${result == 0 }">
	<script type="text/javascript">
		alert("수정실패");
		history.go(-1);
	</script>
</c:if>
</body>
</html>