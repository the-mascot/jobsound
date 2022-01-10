<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% String context = request.getContextPath(); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="../js/jquery.js"></script>
<script type="text/javascript">
		function commWrite(b_type, b_num) {
			var commUpdateContent = $('#commUpdateContent').val();
			console.log(commContent);
			if(confirm("댓글을 수정하시겠습니까?")==true){
				location.href="<%=context%>/yr/notiCommUpdatePro.do?b_type="+b_type+"&b_num="+b_num+"&commUpdateContent="+commUpdateContent;
			} else{
				return false;
			}
		}
</script>
</head>
<body>
	<c:if test="${list != null}">
		<c:forEach var="list" items="${list}">
			<table>
				<tr>
					<td width="200px" style="text-align: left; vertical-align: top">　<strong>작성자 </strong>${list.id}</td>
					<td width="1000px"><textarea class="form-control" id="commUpdateContent" style="min-height: 100px; width: 100%" name="commUpdateContent">${list.commContent }</textarea>
					<td width="165px" style="text-align: center">
						<button type="button" class="btn btn-secondary btn-sm" onclick="commWrite('${list.b_type}',${list.b_num })">수정하기</button>
					</td>
				</tr>
			</table>
		</c:forEach>
	</c:if>
	<c:if test="${list == null}">
		<script type="text/javascript">
			location.href="<%=context%>/yr/notiContent.do?b_num="+${b_num};
		</script>
	</c:if>
</body>
</html>