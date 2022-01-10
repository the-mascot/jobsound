<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>'
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% String context = request.getContextPath(); %>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous"><link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">	<!-- 부트스트랩 icon CDN -->
<link href="<%=context %>/common/style.css" rel="stylesheet" type="text/css">	<!-- style.css link -->
<script type="text/javascript" uri=></script>

<meta charset="UTF-8">
<title>회원탈퇴 주의사항</title>
<script type="text/javascript" src="../js/jquery.js"></script>
<script type="text/javascript">
	function widraw() {
		var widrawChk = document.getElementById('widrawChk');
		if($(widrawChk).prop("checked")){
			location.href="<%=context %>/di/widraw.do";
		} else{
			alert("회원탈퇴 동의여부에 체크해주세요");
			return false;
		}
	}
	function cancel() {
		if(confirm("회원탈퇴를 취소하시겠습니까?")==true){
			location.href="../di/widrawCheck.jsp";
		} else {
			return false;
		}
	}
</script>
<style type="text/css">
	.center{
		margin : 40px;
	}
	.ddStyle{
		margin-left: 40px;
		margin-top: 40px;
	}
	.ddSub{
		margin-left: 40px;
		margin-top: 30px;
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
		<h2><center>회원탈퇴</center></h2>
	</div>
	<br>
	<dl>
		<dt><h4>탈퇴안내</h4></dt>
		<dt>회원탈퇴를 신청하기 전에 안내 사항을 꼭 확인해주세요.</dt>
		<dd class="ddStyle"><img src="<%=context %>/common/images/widrawChk.png" width="20px" height="20px"><strong>&emsp;사용하고 계신 아이디는 탈퇴할 경우 재사용 및 복구가 불가능합니다.</strong><br>
			<span style="color: red; margin-left: 36px">탈퇴한 아이디는 본인과 타인 모두 재사용 및 복구가 불가능하오니</span> 신중하게 선택하시기 바랍니다.</dd>
		<dd class="ddSub"><img src="../images/widrawChk.png" width="20px" height="20px"><strong>&emsp;탈퇴 후 회원정보 및 개인형 서비스 이용기록은 모두 삭제되며, 삭제된 데이터는 복구되지 않습니다.<br>
			<span style="margin-left: 36px">필요한 데이터는 미리 백업해주세요.</span></strong><br>
				<br>
				<span style="margin-left : 65px">개인정보내역 및 신청한 스터디 현황 내역 삭제</span>
			</dd>
		<dd class="ddSub"><img src="<%=context %>/common/images/widrawChk.png" width="20px" height="20px"><strong>&emsp;탈퇴 후에도 게시판형 서비스에 등록한 게시물은 그대로 남아있습니다.</strong> 삭제를 원하는 게시글이 있다면<br>
			<span style="margin-left: 36px; color: red">반드시 탈퇴 전 비공개 처리하거나 삭제하시기 바랍니다.</span> 탈퇴 후에는 회원정보가 삭제되어 본인 여부를<br>
			<span style="margin-left: 36px">확인할 수 있는 방법이 없어 게시글을 임의로 삭제해드릴 수 없습니다.<br>
				<br>
				<span style="margin-left : 65px">각 게시판 게시물 및 댓글</span>
			</span>
		</dd>
		<dd class="ddSub"><input type="checkbox" name="widrawChk" id="widrawChk"><span style="margin-left: 20px"><strong>안내 사항을 모두 확인하였으며, 이에 동의합니다.</strong></span></dd>
	</dl>
	<div style="margin-top: 40px; margin-bottom: 40px;">
		<center><button type="button" class="btn btn-primary btn-lg" onclick="widraw()">확인</button>
		<button type="button" class="btn btn-secondary btn-lg" onclick="cancel()">취소</button></center>
	</div>
		
		
</section>

</main>
<!-- footer 영역 -->
<%@include file ="../common/footer.jsp" %>
</body>
</html>