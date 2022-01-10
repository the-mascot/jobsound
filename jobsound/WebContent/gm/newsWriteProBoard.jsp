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
<!-- style.css link -->
<meta name="view=viewport" content="width=device-width" initial-scale="1.0">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">	<!-- 부트스트랩 icon CDN -->
<link href="../common/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<c:if test="${result > 0 }">
	<script type="text/javascript">
		alert("등록이 완료되었습니다.");
		location.href="<%=context%>/gm/boardlist.do?pageNum=${pageNum}";
	</script>
</c:if>
<c:if test="${result == 0 }">
	<script type="text/javascript">
		alert("등록에 실패하였습니다.");
		location.href="<%=context%>/gm/writeForm.do?b_num=${b_num}&pageNum=${pageNum}";
	</script>
</c:if>
</body>
</html>