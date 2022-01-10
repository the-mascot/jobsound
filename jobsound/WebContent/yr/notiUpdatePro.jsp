<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 수정하기 확인</title>
</head>
<body>
<c:if test="${result>0 }">
	<script type="text/javascript">
		alert("공지사항 수정이 완료 되었습니다!");
		location.href="notiList.do"; /* 수정이 완료가 된다면 조회 페이지로 이동*/
	</script>
</c:if>
<c:if test="${result==0 }">
	<script type="text/javascript">
		alert("공지사항 작성이 실패 하였습니다. 다시 작성 해주세요");
		location.href="notiUpdateForm.do"; /* 수정 실패가 된다면 새로 작성하게끔 다시 수정폼으로 이동 */
	</script>
</c:if>
</body>
</html>