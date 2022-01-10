<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<c:if test="${result>0 }">
	<script type="text/javascript">
		alert("댓글 작성이 완료 되었습니다!");
		location.href="notiContent.do?b_num=${b_num}&b_type=${b_type}"; /* 댓글 작성을 하면 Content로 호출*/
	</script>
</c:if>
<c:if test="${result==0 }">
	<script type="text/javascript">
		alert("댓글 작성을 실패하였습니다.");
		location.href="notiContent.do?b_num=${b_num}&b_type=${b_type}"; /* 수정 실패가 된다면 새로 작성하게끔 다시 수정폼으로 이동  */
	</script>
</c:if>
</body>
</html>