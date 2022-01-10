<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 게시판 삭제</title>
</head>
<body>
<c:if test="${result>0 }">
	<script type="text/javascript">
		alert("공지사항이 삭제되었습니다.");
		location.href="notiList.do"; /* 삭제 성공시 공지사항 리스트로 돌아감 */
	</script>
</c:if>
<c:if test="${result==0 }">
	<script type="text/javascript">
		alert("공지사항 삭제에 실패하였습니다.");/* 삭제 실패시 공지사항 리스트로 돌아감 */
		location.href="notiList.do";
	</script>
</c:if>
</body>
</html>