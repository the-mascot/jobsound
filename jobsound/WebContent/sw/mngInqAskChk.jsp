<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>     
    
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

	<c:if test="${result > 0}">
		<script type="text/javascript">
			alert("문의사항에 답변을 정상적으로 등록되었습니다.");
			location.href = "<%=context%>/sw/mngInqList.do"
		</script>
	</c:if>
	
	<c:if test="${result == 0}">
		<script type="text/javascript">
			alert("문의사항 답변에 실패하였습니다. ㅠㅠ");
			location.href = "<%=context%>/sw/mngInqList.do"
		</script>
	</c:if>

</body>
</html>