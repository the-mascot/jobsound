<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:if test="${chk==false}">
	<script type="text/javascript">
		alert("작성자만 수정/삭제가 가능합니다.");
		history.back();
	</script>
</c:if>
</body>
</html>