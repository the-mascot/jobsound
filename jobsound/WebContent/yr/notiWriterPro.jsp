<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 작성 확인</title>
</head>
<body>
<c:if test="${result>0 }">
	<script type="text/javascript">
		alert("공지사항 작성이 완료 되었습니다!");
		location.href="notiList.do?pageNum=${currentPage}";
	</script>
</c:if>
<c:if test="${result==0 }">
	<script type="text/javascript">
		alert("공지사항 작성이 실패 하였습니다. 다시 작성 해주세요");
		location.href="notiWriterForm.jsp";
	</script>
</c:if>

</body>
</html>