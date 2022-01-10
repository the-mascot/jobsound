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
<title>삭제 여부 확인</title>
</head>
<body>
<c:if test="${result > 0 }">
	<script type="text/javascript">
		alert("삭제가 완료되었습니다.");
		location.href="<%=context%>/gm/boardlist.do?pageNum=${pageNum}";
	</script>
</c:if>
<c:if test="${result == 0 }">
	<script type="text/javascript">
		alert("삭제에 실패하였습니다.");
		location.href="<%=context%>/gm/content.do?b_num=${board.b_num}&pageNum=${currentPage}&b_type=${board.b_type}"
	</script>
</c:if>
<c:if test="${result == -1 }">
	<script type="text/javascript">
		alert("오류가 발생했습니다.");
		location.href="<%=context%>/gm/content.do?b_num=${board.b_num}&pageNum=${currentPage}&b_type=${board.b_type}"
	</script>
</c:if>
</body>
</html>