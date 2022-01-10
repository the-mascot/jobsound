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
<c:if test="${result>0 }">
	<script type="text/javascript">
		alert("${participant_id}님의 스터디 상태가 변경되었습니다.")
		location.href="<%=context %>/di/studyRecruitView.do?stdNum=${stdNum}&applyPn=${applyPn}&stdPn=${stdPn}";
	</script>
</c:if>
<c:if test="${result==0 }">
	<script type="text/javascript">
		alert("스터디 상태 변경에 실패하였습니다.")
		location.href="<%=context %>/di/studyRecruitView.do?stdNum=${stdNum}&applyPn=${applyPn}&stdPn=${stdPn}";
	</script>
</c:if>
</body>
</html>