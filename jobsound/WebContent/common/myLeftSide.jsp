<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>left side</title>
<!-- 부트스트랩 CDN -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">	<!-- 부트스트랩 icon CDN -->
<link href="../common/style.css" rel="stylesheet" type="text/css">	<!-- style.css link -->
<script type="text/javascript">
	function logoutChk(){
		if(confirm("로그아웃 하시겠습니까?")==true){
			location.href="../di/mypageLogout.do";
		} else{
			return false;
		}
	}
</script>
</head>
<body>
	<aside class=left_side>
		<nav class="navbar navbar-expand-lg mt-5">
			<div class="mx-auto mt-3">
				<div class="card">
					<img src="../common/images/side_nav.png" class="card-img">
					<div class="card-img-overlay mt-3">
						<p class="card-title text-center"
							style="font-family: 'jalnan'; font-size: 30px; color: white;">마이페이지</p>	 <!-- el 표기로 parameter 받아서 표시해야함 -->
					</div>
				</div>
				<ul class="list-unstyled mx-auto">
					<!-- href 설정 -->
					<li class="nav-item py-1"><a
						class="side_nav nav-link border-bottom border-2" href="../di/mypageCheck.jsp">개인정보 조회 및 수정</a></li>
					<li class="nav-item py-1"><a
						class="side_nav nav-link border-bottom border-2" href="../di/mypasswdUpdateForm.jsp">비밀번호 수정</a></li>
					<li class="nav-item py-1"><a
						class="side_nav nav-link border-bottom border-2" href="../di/studyRecruitList.do">모집 중인 스터디</a></li>
					<li class="nav-item py-1"><a
						class="side_nav nav-link border-bottom border-2" href="../di/myBoardSelect.jsp">내가 쓴 게시글</a></li>
					<li class="nav-item py-1"><a
						class="side_nav nav-link border-bottom border-2" href="../di/mypageCommentList.do?pageNum=${pageNum }">내가 쓴 댓글</a></li>
					<li class="nav-item py-1"><a
						class="side_nav nav-link border-bottom border-2" href="../di/myInquireList.do?pageNum=${pageNum }">내가 쓴 문의사항</a></li>
					<li class="nav-item py-1"><a
						class="side_nav nav-link border-bottom border-2" href="../di/mypageStudyApplyList.do?pageNum=${pageNum }">신청한 스터디</a></li>
					<li class="nav-item py-1"><a
						class="side_nav nav-link border-bottom border-2" href="../di/widrawCheck.jsp">회원탈퇴</a></li>
					<li class="nav-item py-1"><a
						class="side_nav nav-link border-bottom border-2" onclick="logoutChk()">로그아웃</a></li>	
				</ul>
			</div>
		</nav>
	</aside>
</body>
</html>