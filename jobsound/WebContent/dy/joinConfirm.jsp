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
		alert("회원이 되신걸 축하드립니다.");
	    location.href="<%=context %>/dy/main.do";
	</script>
</c:if>
<c:if test="${result==0 }">
	<script type="text/javascript">
		alert("회원 등록에 실패하였습니다.");
	    location.href="<%=context %>/dy/join.do";
	</script>
</c:if>

</body>
</html>