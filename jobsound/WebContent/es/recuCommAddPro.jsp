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
<c:if test="${result>0 }">
	<script type="text/javascript">
		alert("댓글이 등록되었습니다.");
		location.href="<%=context %>/es/recuContent.do?id=${id}&b_num=${b_num}";
	</script>
</c:if>
<c:if test="${result==0 }">
	<script type="text/javascript">
		alert("댓글이 제대로 작성되지 않았습니다.");
		history.go(-1);
	</script>
</c:if>
</body>
</html>