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
<form action="../di/testloginAction.jsp" method="post">
	<table>
		<tr>
			<td>아이디</td><td><input type="text" name="id" id="id"></td>
		</tr>
		<tr>
			<td>비밀번호</td><td><input type="text" name="passwd" id="passwd"></td>
		</tr>
		<tr>
			<td><input type="submit" value="전송"></td>
			<td><input type="reset" value="취소"></td>
		</tr>
	</table>
</form>
</body>
</html>