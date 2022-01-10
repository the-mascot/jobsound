<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<% String context = request.getContextPath(); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FAQ</title>
<!-- 부트스트랩 CDN -->

<style type="text/css">
.center {
	margin: 40px;
}

.accordion-item {
	width: 1000px;
	display: block;
    margin-left: auto;
    margin-right: auto
	
}
</style>
</head>
<body>
<%@include file ="../common/header.jsp" %>

<!-- 상단 navbar 영역 -->
<%@include file ="../common/headNav.jsp" %>

<!-- FAQ left side 영역 -->

<%@include file ="../common/faqLeftSide.jsp" %>

	<div class="center">
		<h1 style="color: rgb(87, 152, 215);">
			<strong>FAQ</strong>
		</h1>
		<h3>
			&nbsp;&nbsp;<em>- 자주 묻는 질문입니다.</em>
		</h3>
	</div>
<div style="margin-top: 100px; margin-bottom: 100px">
<div class="accordion accordion-flush" id="accordionFlushExample">
  <div class="accordion-item">
    <h2 class="accordion-header" id="flush-headingOne">
      <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseOne" aria-expanded="false" aria-controls="flush-collapseOne">
    <img src='<%=context %>/common/images/Question.png' height="20px" width="20px" ><span style="margin-left: 10px; margin-right: 82px">사이트이용</span><strong>회원가입에 연령 제한이 있나요?</strong>
      </button>
    </h2>
    <div id="flush-collapseOne" class="accordion-collapse collapse" aria-labelledby="flush-headingOne" data-bs-parent="#accordionFlushExample">
      <div class="accordion-body">&emsp;&emsp;<img src='<%=context %>/common/images/Answer1.png' height="20px" width="20px" >&nbsp;&nbsp;<span style="color: #8224e3;"><strong>저희 JoB소리는 회원가입에 대한 연령 제한이 없으므로 전 연령이 회원가입을 진행 할 수 있습니다. 회원가입은 메인페이지에서 진행이 가능하십니다.</strong></span></div>
    </div>
  </div>
  <div class="accordion-item">
    <h2 class="accordion-header" id="flush-headingTwo">
      <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseTwo" aria-expanded="false" aria-controls="flush-collapseTwo">
 <img src='<%=context %>/common/images/Question.png' height="20px" width="20px" ><span style="margin-left: 10px; margin-right: 82px">사이트이용</span><strong>탈퇴는 어디에서 할 수 있나요?</strong>
      </button>
    </h2>
    <div id="flush-collapseTwo" class="accordion-collapse collapse" aria-labelledby="flush-headingTwo" data-bs-parent="#accordionFlushExample">
      <div class="accordion-body">&emsp;&emsp;<img src='<%=context %>/common/images/Answer1.png' height="20px" width="20px" >&nbsp;&nbsp;<span style="color: #8224e3;"><strong> 회원가입 탈퇴 내용 2입니다.</strong></span></div>
    </div>
  </div>
  <div class="accordion-item">
    <h2 class="accordion-header" id="flush-headingThree">
      <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseThree" aria-expanded="false" aria-controls="flush-collapseThree">
 <img src='<%=context %>/common/images/Question.png' height="20px" width="20px" ><span style="margin-left: 10px; margin-right: 82px">사이트이용</span><strong>탈퇴를 하게된다면 기존에 작성했던 게시물도 삭제되나요?</strong>
      </button>
    </h2>
    <div id="flush-collapseThree" class="accordion-collapse collapse" aria-labelledby="flush-headingThree" data-bs-parent="#accordionFlushExample">
      <div class="accordion-body">&emsp;&emsp;<img src='<%=context %>/common/images/Answer1.png' height="20px" width="20px" >&nbsp;&nbsp;<span style="color: #8224e3;"><strong>아니용</strong></span></div>
    </div>
  </div>
</div>
</div>
	<%@include file="../common/footer.jsp"%>
</body>
</html>