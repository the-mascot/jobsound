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
<c:choose>
	<c:when test="${result > 0 }">
		<script type="text/javascript">
			alert("댓글 수정에 성공하였습니다.")
		</script>
	</c:when>
	<c:when test="${result == 0 }">
		<script type="text/javascript">
			alert("댓글 수정에 실패하였습니다.")
		</script>
	</c:when>
</c:choose>
<script type="text/javascript">
	location.href="<%=context%>/yr/notiContent.do?b_num=${b_num}";
</script>
</body>
</html>