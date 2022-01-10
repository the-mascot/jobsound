<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<% String context = request.getContextPath(); %>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
<script type="text/javascript" src="../js/jquery.js"></script>
<meta charset="UTF-8">
<title>마이페이지-내가 쓴 댓글</title>
<script type="text/javascript">
	function approval(participant_id, stdNum, applyPn, stdPn){
		if(confirm("스터디 참여에 승인하시겠습니까?")==true){
			location.href="<%=context %>/di/studyApply.do?participant_id="+participant_id+"&stdNum="+stdNum+"&apply_chk=1&applyPn="+applyPn+"&stdPn="+stdPn;
		} else {
			return false;
		}
	}
	function refuse(participant_id, stdNum, applyPn, stdPn){
		if(confirm("스터디 참여를 거절하시겠습니까?")==true){
			location.href="<%=context %>/di/studyApply.do?participant_id="+participant_id+"&stdNum="+stdNum+"&apply_chk=2&applyPn="+applyPn+"&stdPn="+stdPn;
		} else {
			return false;
		}
	}
	function list(pageNum){
		if(confirm("목록으로 돌아가시겠습니까?")==true){
			location.href="<%=context %>/di/studyRecruitList.do?pageNum="+pageNum;
		} else {
			return false;
		}
	}
</script>
<style type="text/css">
	.center{
		margin : 40px;
	}
	#texthead{
		text-align: center;
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
		<h2><center>스터디 신청 현황</center></h2>
	</div>
	<br><br>
	<input type="hidden" id="id" name="id" value="${id }">
	<input type="hidden" id="stdNum" name="stdNum" value="${stdNum }">
	<input type="hidden" id="stdPn" name="stdPn" value="${stdPn }">
	<input type="hidden" id="applyPn" name="applyPn" value="${applyPn }">
	<%-- <input type="hidden" name="chkType" value="${chkType }"> --%>
	<c:if test="${listChk>0 }">
		<table class="table table-hover">
			<tr>
				<th id="texthead" width="200px">신청자</th>
				<th id="texthead" width="200px">신청일자</th>
				<th id="texthead" width="200px">참여일자</th>
				<th id="texthead" width="600px">신청내용</th>
				<th id="texthead" width="200px">신청상태</th>
			</tr>
			<c:forEach var="list" items="${list }">
				<tr>
					<td style="text-align: center" rowspan="2">${list.participant_id}</td>
					<td style="text-align: center" rowspan="2">${list.stdApply_Date}</td>
					<td style="text-align: center" rowspan="2">
						<c:choose>
							<c:when test="${list.stdParti_date == null}">-</c:when>
							<c:when test="${list.stdParti_date != null}">${list.stdParti_date}</c:when>
						</c:choose>
					</td>
					<td style="text-align: center" rowspan="2">${list.apply_Content}</td>
					<td style="text-align: center; border-bottom: none">
						<c:choose>
							<c:when test="${list.apply_chk == 0}">승인대기</c:when>
							<c:when test="${list.apply_chk == 1}">승인 중</c:when>
							<c:when test="${list.apply_chk == 2}">거절</c:when>
						</c:choose>
					</td>
					<!-- 아래 tr로 옮겨야함 -->
				</tr>
				<tr>
					<td align="center">
						<c:choose>
							<c:when test="${list.apply_chk == 0 and applyPn<stdPn}">
								<button type="button" class="btn btn-primary btn-sm" onclick="approval('${list.participant_id}', ${stdNum },'${applyPn }','${stdPn }')">승인</button>
								<button type="button" class="btn btn-secondary btn-sm" onclick="refuse('${list.participant_id}', ${stdNum },'${applyPn }','${stdPn }')">거절</button>
							</c:when>
							<c:when test="${list.apply_chk == 0 and applyPn==stdPn}">정원마감</c:when>
							<c:when test="${list.apply_chk == 1}">
								<button type="button" class="btn btn-secondary btn-sm" onclick="refuse('${list.participant_id}', ${stdNum },'${applyPn }','${stdPn }')">거절</button>
							</c:when>
							<c:when test="${list.apply_chk == 2 and applyPn<stdPn}">
								<button type="button" class="btn btn-primary btn-sm" onclick="approval('${list.participant_id}', ${stdNum },'${applyPn }','${stdPn }')">승인</button>
							</c:when>
							<c:when test="${list.apply_chk == 2 and applyPn==stdPn}">정원마감</c:when>
						</c:choose>
					</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	<c:if test="${listChk == 0}">
		<div class="center">
		<h4><center>스터디 신청자가 없습니다.</center></h4>
	</div>
	</c:if>
		<div class="center">
			<center><button type="button" class="btn btn-primary btn-lg" onclick="list('${pageNum}')">목록으로</button></center>
		</div>
</section>
</main>
<!-- footer 영역 -->
<%@include file ="../common/footer.jsp" %>
</body>
</html>