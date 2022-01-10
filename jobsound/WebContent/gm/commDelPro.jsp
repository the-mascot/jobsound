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
<title>commDelPro</title>
</head>
<body>
<c:if test="${result > 0 }">
	<script type="text/javascript">
		alert("댓글 삭제 완료");
		location.href="<%=context%>/gm/content.do?b_num=${b_num}";
	</script>
</c:if>
<c:if test="${result == 0 }">
	<script type="text/javascript">
		alert("댓글 삭제 실패");
		history.back();
	</script>
</c:if>
</body>
</html>