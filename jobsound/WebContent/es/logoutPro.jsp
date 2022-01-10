<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%
   String context = request.getContextPath();
%>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<script type="text/javascript">
		alert("로그아웃 되었습니다");
		location.href="<%=context %>/dy/main.do"
	</script>
</head>
<body>

</body>
</html>