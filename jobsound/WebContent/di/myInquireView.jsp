<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<% String context = request.getContextPath(); %>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous"><link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">	<!-- 부트스트랩 icon CDN -->
<link href="<%=context %>/common/style.css" rel="stylesheet" type="text/css">	<!-- style.css link -->
<script type="text/javascript" src="../js/jquery.js"></script>
<meta charset="UTF-8">
<title>마이페이지-내가 쓴 댓글</title>
<script type="text/javascript">
	function inquireUpdate(inqNum){
		if(confirm("문의사항 수정 시 해당 답변은 삭제됩니다. 수정하시겠습니까?")==true){
			location.href="<%=context %>/di/myInquireUpdateForm.do?inqNum="+inqNum;
		} else {
			return false;
		}
	}
	function inquireDelete(){
		if(confirm("문의사항을 삭제하면 복구할 수 없습니다. 삭제하시겠습니까?")==true){
		var id = $('#id').val();
		var inqNum = $('#inqNum').val();
			location.href="../di/myInquireDeleteCheck.jsp?id="+id+"&inqNum="+inqNum;
		} else {
			return false;
		}
	}
</script>
<style type="text/css">
	.center{
		margin : 40px;
	}
	th.texthead{
		text-align: left;
	}
	th.texthead1{
		text-align: right;
	}
	td.center{
		text-align: center;
	}
	td.form-check{
		margin:0 auto;
	}
</style>
</head>
<body>
<%@include file ="../common/header.jsp" %>
<!-- 상단 navbar 영역 -->
<%@include file ="../common/headNav.jsp" %>
<main>
<!-- left side 영역 -->
<%@include file ="../common/myLeftSide.jsp" %>
<!-- section 영역 -->
<section>
	<div class="center">
		<h2><center>내가 쓴 문의사항</center></h2>
	</div>
	<br><br>
	<input type="hidden" id="id" name="id" value="${id }">
	<input type="hidden" id="inqNum" name="inqNum" value="${inqNum }">
	<%-- <input type="hidden" name="chkType" value="${chkType }"> --%>
	<table class="table table-sm">
		<c:forEach var="list" items="${list }">
			<thead>
		  		<tr>
		  			<th class="texthead" width="600"><h3>${list.inqTitle}</h3></th>
		  			<th class="texthead1" width="200"><h6>작성자 ${id}</h6></th>
		  			<th class="texthead1" width="200"><h6>작성일 ${list.writeDate}</h6></th>
		  		</tr>
  			</thead>
  			<tbody>
			  	<tr>
			  		<td colspan="3" width="1000" height="300">${list.inqContent}</td>
			  	</tr>
			</tbody>
	  	</c:forEach>
	</table>
	<div class="d-grid gap-2 d-md-flex justify-content-md-end">
		<button type="button" class="btn btn-primary btn-sm" id="write" onclick="inquireUpdate(${inqNum })">수정</button>
		<button type="button" class="btn btn-primary btn-sm" id="write" onclick="inquireDelete()">삭제</button>
	</div>
	<c:if test="${askCnt >0 }">
		<c:forEach var="list" items="${list }">
			<table class="table table-sm">
				<thead>
			  		<tr>
			  			<th colspan="3">1 comment(s)</th>
			  		</tr>
	  			</thead>
	  			<tbody>
					  	<tr>
			  				<td width="100">관리자</td>
			  				<td>${list.askContent}</td>
			  				<td width="150">답변일 ${list.askDate}</td>
					  	</tr>
				</tbody>
			</table>
		</c:forEach>
	</c:if>
	<c:if test="${askCnt ==0 }">
		<table class="table table-sm">
			<thead>
		  		<tr>
		  			<td colspan="3">0 comment(s)</td>
		  		</tr>
	  		</thead>
	  		<tbody>
	  			<tr>
	  				<td>답변이 없습니다.</td>
	  			</tr>
	  		</tbody>
		</table>
	</c:if>
</section>
</main>
<!-- footer 영역 -->
<%@include file ="../common/footer.jsp" %>
</body>
</html>