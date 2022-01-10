<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
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
<c:if test="${result > 0 }">
	<script type="text/javascript">
		location.href="<%=context%>/gm/content.do?b_num=${b_num}&pageNum=${pageNum}&b_type=${b_type}";
	</script>
</c:if>
<c:if test="${result == 0 }">
	<script type="text/javascript">
		alert("등록 실패");
		location.href="content.do?b_num=${b_num}&pageNum=${pageNum}&b_type=${b_type}";
	</script>
</c:if>
</body>
</html>