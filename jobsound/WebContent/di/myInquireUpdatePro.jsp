<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% String context = request.getContextPath(); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문의사항수정 확인</title>
</head>
<body>
<c:if test="${result>0 }">
	<script type="text/javascript">
		alert("문의가 정상적으로 수정되었습니다.");
		/* alert("수정되었습니다."); */
		location.href="<%=context %>/di/myInquireList.do";
	</script>
</c:if>
<c:if test="${result==0 }">
	<script type="text/javascript">
		alert("문의사항 수정에 실패하였습니다.");
		location.href="<%=context %>/di/myInquireList.do";
	</script>
</c:if>
</body>
</html>