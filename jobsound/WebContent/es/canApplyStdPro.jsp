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
		alert("스터디가 취소 되었습니다.");
		location.href="<%=context %>/es/recuContent.do?pageNum=${pageNum}&pageSize=${pageSize}&b_num=${b_num}";
	</script>
</c:if>
<c:if test="${result==1 }">
	<script type="text/javascript">
		alert("스터디 취소에 실패하였습니다.");
		location.href="<%=context %>/es/recuContent.do?pageNum=${pageNum}&pageSize=${pageSize}&b_num=${b_num}";
	</script>
</c:if>
</body>
</html>