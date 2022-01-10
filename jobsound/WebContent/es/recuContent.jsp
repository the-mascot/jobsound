<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<%
   String context = request.getContextPath();
%>
<head>
<meta charset="UTF-8">
<meta name="view=viewport" content="width=device-width", initial-scale="1.0">
<title>index</title>
<!-- 부트스트랩 CDN -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">	<!-- 부트스트랩 icon CDN -->
<link href="<%=context %>/common/style.css" rel="stylesheet" type="text/css">	<!-- style.css link --> 
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script> 

<script type="text/javascript">
	$(document).ready(function(){
		$('.addComm').on("click", function(event) {
			event.preventDefault();
		})
		
		$("#doCanApply").on("click", function() {
			$("#canApplyStdForm").submit();
		});
		
 		$('#recuDelModal').on('show.bs.modal', function(event) {          
			 rdf_b_num=$(event.relatedTarget).data('rdf_b_num');
			 re_level=$(event.relatedTarget).data('re_level');
			 re_step=$(event.relatedTarget).data('re_step');
			 ref=$(event.relatedTarget).data('ref');
			$("#rdf_b_num").val(rdf_b_num);
			$("#rdf_re_level").val(re_level);
			$("#rdf_re_step").val(re_step);
			$("#rdf_ref").val(ref);
		}); 
 		
		$("#doRecuDel").on("click", function() {
			$("#recuDelForm").submit();
		});
		
		$(".commUpBtn").on("click", function() {
			var commUpBtn=$(this).attr("id");
			var b_num=commUpBtn.substring(10);
			console.log(b_num);
			
    			$.ajax({
    			type	: 'GET',
    			url 	:'<%=context %>/es/AjaxcommUpForm.do?b_num='+b_num,
				success	:function(data) {
					console.log(data)
					$('#upCommContent-'+b_num).val(data);
				},
				error	:function(e) {
					alert("댓글 내용 로드 실패");
					console.log("error : ",e);
				}
		
			});  
		});
	
	});

</script>


</head>
<body>

<!-- header 부분 -->
<%@include file ="../common/header.jsp" %>

<!-- 상단 navbar 영역 -->
<%@include file ="../common/headNav.jsp" %>

<!-- main 영역 : main 안에 left_side - section - right_side로 3등분 됨 2:6:2 비율 -->
<main>
<!-- left side 영역 -->
<%@include file ="../common/comuLeftSide.jsp" %>

<!-- section 영역 -->
<section>
<!-- 게시판 설명 -->
	<div>
		<div>
			<h2 class="mt-5 mb-3 mx-5 fw-bold" style="font-family: pretend; color: #5798D7">스터디 모집 게시판</h2>
		</div>
		<div>
			<p class="mt-2 mx-5 fs-6 text-muted fst-italic" style="font-family: pretend">- 스터디 모집 합니다~</p>
		</div>
	</div>

	<div class="container mt-5 mb-5">
		<div class="col-md-10 mx-auto">
			<p class="fs-4">${board.title }</p>
			<div class="container" style="border-bottom: 1px solid #dee2e6;">
				<div class="row">
					<div class="col">
						작성자 : <span>${board.id }</span>
					</div>
					<div class="col position-relative px-0">
						<div class="row position-absolute end-0 me-1">
						<div class="col mx-0 px-0">
							<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-eye" viewBox="0 0 16 16">
		  					<path d="M16 8s-3-5.5-8-5.5S0 8 0 8s3 5.5 8 5.5S16 8 16 8zM1.173 8a13.133 13.133 0 0 1 1.66-2.043C4.12 4.668 5.88 3.5 8 3.5c2.12 0 3.879 1.168 5.168 2.457A13.133 13.133 0 0 1 14.828 8c-.058.087-.122.183-.195.288-.335.48-.83 1.12-1.465 1.755C11.879 11.332 10.119 12.5 8 12.5c-2.12 0-3.879-1.168-5.168-2.457A13.134 13.134 0 0 1 1.172 8z"/>
		 					<path d="M8 5.5a2.5 2.5 0 1 0 0 5 2.5 2.5 0 0 0 0-5zM4.5 8a3.5 3.5 0 1 1 7 0 3.5 3.5 0 0 1-7 0z"/></svg>
						</div>
						<div class="col ps-1 pe-1">
							<p>${board.views }</p>
						</div>
						</div>
					</div>
					<div class="col-2 mx-0 px-0">
						<p class="text-end mx-0 px-0">${board.uploaddate } 작성</p>
					</div>
				</div>
			</div>
			<div style="margin-top: 70px;">
				<table class="table table-responsive mt-3 table-bordered text-center align-middle">
					<thead class="bg-primary text-white bg-opacity-75">
						<tr>
							<th>스터디 제목</th>
							<th>스터디 분류</th>
							<th>스터디 인원</th>
							<th>스터디 기간</th>
							<th>모집 기간</th>
						<tr>
					</thead>
					<tbody>
						<tr>
							<td>${board.stdtitle }</td>
							<td>${board.stddiv }</td>
							<c:choose>
								<c:when test="${applyCount>=board.stdpn }">
									<td class="text-danger">${applyCount} / ${board.stdpn }</td>
								</c:when>
								<c:otherwise>
									<td>${applyCount} / ${board.stdpn }</td>
								</c:otherwise>
							</c:choose>
							<td>${board.stdstart_date }<br> ~ ${board.stdend_date }</td>
							<td>${board.recru_date }<br> ~ ${board.recu_date}</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="mt-5 text-start" style="border-bottom: 1px solid #dee2e6; min-height: 200px;">
				<div>
					<img src="${board.imagepath }" style="max-width: 100%; height: auto;">
				</div>
				<div>
					<pre>${board.content }</pre>
				</div>
			</div>
			<!-- 스터디 신청 버튼 -->
			<div class="container my-5">
				<div class="row mx-auto">
					<div class="col position-relative">
						<c:choose>
							<c:when test="${sessionScope.sessionID==board.id || sessionScope.sessionID==null || applyCount>=board.stdpn || checkStd==0 || checkStd==1 || board.stdstatus==1 || board.stdstatus==2}">
									<button class="btn btn-outline-dark position-absolute end-0" data-bs-toggle="collapse" data-bs-target="#collapseExample" 
									aria-expanded="false" style="height: 60px; width: 120px;" disabled="disabled">스터디 신청</button>
							</c:when>
							<c:otherwise>
								<button class="btn btn-outline-dark position-absolute end-0" data-bs-toggle="collapse" data-bs-target="#stdText" 
									aria-expanded="false" style="height: 60px; width: 120px;">스터디 신청</button>
							</c:otherwise>
						</c:choose>
					</div>
					<div class="col">
							<c:choose>
								<c:when test="${sessionScope.sessionID==null || checkStd==-1 || checkStd==2 || board.stdstatus==1 || board.stdstatus==2}">
										<input type="button" class="btn btn-outline-dark" id="cancel" value="신청 취소" style="height: 60px; width: 120px;" disabled="disabled">									
								</c:when>
								<c:otherwise>
									<form action="<%=context %>/es/cancelApplyStd.do" method="post" id="canApplyStdForm">
										<input type="hidden" value="${pageNum }" name="pageNum">
										<input type="hidden" value="${pageSize }" name="pageSize">
										<input type="hidden" value="${b_num }" name="b_num">
										<input type="hidden" value="${board.stdnum}" name="stdnum"> 
									
									</form>
										<input type="button" class="btn btn-outline-dark" id="canApplyBtn" value="신청 취소" style="height: 60px; width: 120px;" 
												data-bs-toggle="modal" data-bs-target="#canStdModal">					
								</c:otherwise>
						</c:choose>
					</div>
					<div class="collapse mt-2" id="stdText">
						<form action="<%=context %>/es/applyStd.do" method="post" class="form-control">
							<div>
								<input type="hidden" value="${board.stdnum}" name="stdnum">
								<input type="hidden" value="${pageNum }" name="pageNum">
								<input type="hidden" value="${pageSize }" name="pageSize">
								<input type="hidden" value="${b_num }" name="b_num">
								<input type="hidden" value="${checkStd }" name="checkStd">
								<textarea class="form-control" id="applycontent" name="apply_content" placeholder="스터디 개설자에게 신청 사유를 남겨보세요" 
												maxlength="100" style="min-height: 13px; width: 100%; border: none;"></textarea>	
							</div>
							<div class="clearfix mt-2">
								<input type="submit" class="btn float-end bg-primary text-white bg-opacity-75" value="신청하기">			
							</div>
						</form>	
					</div>	
				</div>
			</div>
			
			<!-- 수정/삭제/목록 버튼 -->
			<div class="clearfix">
				<div class="btn-group float-end" role="group">
					<c:if test="${sessionScope.sessionID == board.id}">
						<form action="<%=context %>/es/recuUpdateForm.do" method="post">
							<input type="hidden" value="${pageNum }" name="pageNum">
							<input type="hidden" value="${pageSize }" name="pageSize">
							<input type="hidden" name="b_num" value="${board.b_num }">
							<button type="submit" class="btn btn-secondary">수정</button>
						</form>
						<button type="button" class="btn btn-secondary" data-bs-toggle="modal" data-bs-target="#recuDelModal" data-rdf_b_num="${board.b_num }"
								data-re_level="${board.re_level }" data-re_step="${board.re_step }" data-ref="${board.ref }">삭제</button>
					</c:if>	
						<a href="<%=context %>/es/recuBoardList.do?pageNum=${pageNum}&pageSize=${pageSize}"><button type="button" class="btn btn-secondary">목록</button></a>
				</div>
			</div>
	
			<!-- 댓글 -->
			<div class="mb-3">      
				<p style="font-family: pretend; color: #5798D7; font-size: 16px;">${fn:length(list)} comments</p>
 			</div>			
			
			<div class="card">
				<div class="card-header bg-light">댓글</div>
				<div class="card-body">
					<div class="accordion py-1" id="accordionExample" style="border-bottom-style: groove;">
						<c:forEach var="comm" items="${list}" varStatus="status">
							<div class="accordion-item" style="border: none;">
								<div class="d-flex justify-content-between mb-0" style="font-family: pretend">
									<c:choose>
										<c:when test="${comm.re_level eq 2 }">
											<p class="mb-0 ms-4">작성자 ${comm.id }</p>
										</c:when>
										<c:otherwise>
											<p class="mb-0">작성자 ${comm.id }<p>										
										</c:otherwise>
									</c:choose>
									<p class="mb-0"> ${comm.commdate }</p>	
								</div>
								<div class="row pb-0">
									<div class="col-md-9">
										<c:choose>
											<c:when test="${comm.re_level eq 2 }">
												<p><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-return-right" viewBox="0 0 16 16">
  												<path fill-rule="evenodd" d="M1.5 1.5A.5.5 0 0 0 1 2v4.8a2.5 2.5 0 0 0 2.5 2.5h9.793l-3.347 3.346a.5.5 0 0 0 .708.708l4.2-4.2a.5.5 0 0 0 0-.708l-4-4a.5.5 0 0 0-.708.708L13.293 8.3H3.5A1.5 1.5 0 0 1 2 6.8V2a.5.5 0 0 0-.5-.5z"/>
												</svg>&nbsp; ${comm.commcontent }</p>
											</c:when>
											<c:otherwise>
												<p>${comm.commcontent }</p>
											</c:otherwise>
										</c:choose>
									</div>
									<div class="col-md-3 px-0">
										<div class="float-end">
										<!-- 댓글 수정/삭제 -->
										<c:if test="${sessionScope.sessionID == comm.id}"> 
											<div class="btn-group me-2" role="group">
												<button type="button" class="commUpBtn btn btn-secondary" data-bs-toggle="collapse" data-bs-target="#upcollapse${status.count }" id="commUpbtn-${comm.b_num }">수정</button>
												<button type="button" class="btn btn-secondary" data-bs-toggle="modal" data-bs-target="#recuDelModal" data-rdf_b_num="${comm.b_num }"
														data-re_level="${comm.re_level }" data-re_step="${comm.re_step }" data-ref="${comm.ref }">삭제</button>
											</div>	
								 		</c:if>
								 		<!-- 답글 달기 -->
									 	<a href="#" class="addComm text-secondary" style="text-decoration: none;" data-bs-toggle="collapse" data-bs-target="#collapse${status.count }" 
									 			aria-expanded="true" aria-controls="collapseOne">답글 달기</a>
										</div>
									</div>
								</div>
								<!-- 답글 달기 콜랩스 -->
								 <div class="accordion-body py-0">
									<div id="collapse${status.count }" class="accordion-collapse collapse" aria-labelledby="headingOne" data-bs-parent="#accordionExample">
										<div class="form-control">
											<form action="<%=context %>/es/recuCommAdd.do" method="post">
												<input type="hidden" value="2" name="re_level">
												<input type="hidden" value="${b_num}" name="b_num"> 
												<input type="hidden" value="${comm.re_step}" name="re_step"> 
													<c:choose>
														<c:when test="${sessionScope.sessionID == null}">
															<div>
															<textarea class="form-control" name="commcontent" placeholder="답글을 남기려면 로그인 해주세요.." required="required" disabled="disabled"
																		style="min-height: 13px; width: 100%; border: none;"></textarea>	
															</div>
															<div class="clearfix mt-2">
																<input type="submit" class="btn float-end bg-primary text-white bg-opacity-75" value="답글 쓰기" disabled="disabled">			
															</div>
														</c:when>
														<c:otherwise>
															<div>
															<textarea class="form-control" name="commcontent" placeholder="답글을 남겨보세요" required="required"
																		style="min-height: 13px; width: 100%; border: none;"></textarea>	
															</div>
															<div class="clearfix mt-2">
																<input type="submit" class="btn float-end bg-primary text-white bg-opacity-75" value="답글 쓰기">			
															</div>
														</c:otherwise>
													</c:choose>		
											</form>
										</div>
									</div>
									<!-- 수정 콜랩스 -->
									<div id="upcollapse${status.count }" class="accordion-collapse collapse" aria-labelledby="headingOne" data-bs-parent="#accordionExample">
										<div class="form-control">
											<form action="<%=context %>/es/commUpPro.do" method="post">
												<input type="hidden" value="${pageNum }" name="pageNum">
												<input type="hidden" value="${pageSize }" name="pageSize">
												<input type="hidden" value="${b_num}" name="b_num">
												<input type="hidden" value="${comm.b_num }" name="commb_num">
												<div>
												<textarea class="form-control" name="commcontent" placeholder="답글을 남겨보세요" required="required" id="upCommContent-${comm.b_num }"
															style="min-height: 13px; width: 100%; border: none;"></textarea>	
												</div>
												<div class="clearfix mt-2">
													<input type="submit" class="btn float-end bg-primary text-white bg-opacity-75" value="수정">			
												</div>
											</form>
										</div>
									</div>								
								</div>
							</div>
						</c:forEach>
					</div>
					<!-- 댓글 콜랩스 -->
					<div class="form-control mt-2">
						<form action="<%=context %>/es/recuCommAdd.do?&re_level=1" method="post">
							<input type="hidden" value="${b_num}" name="b_num"> 
								<c:choose>
									<c:when test="${sessionScope.sessionID == null}">
										<div>
											<textarea class="form-control" id="commcontent" name="commcontent" placeholder="댓글을 남겨보세요" style="min-height: 13px; width: 100%; border: none;" disabled="disabled"></textarea>	
										</div>
										<div class="clearfix mt-2">
											<input type="submit" class="btn float-end bg-primary text-white bg-opacity-75" value="댓글 쓰기" disabled="disabled">			
										</div>
									</c:when>
									<c:otherwise>
										<div>
											<textarea class="form-control" id="commcontent" name="commcontent" placeholder="댓글을 남겨보세요" style="min-height: 13px; width: 100%; border: none;"></textarea>	
										</div>
										<div class="clearfix mt-2">
											<input type="submit" class="btn float-end bg-primary text-white bg-opacity-75" value="댓글 쓰기">			
										</div>
									</c:otherwise>
								</c:choose>
						</form>
					</div>	
				</div>
			</div>
		</div>
	</div>
	<!-- 글 삭제 Form -->
	<form action="<%=context %>/es/recuDelete.do" id="recuDelForm">
		<input type="hidden" id="rdf_b_num" name="rdf_b_num">
		<input type="hidden" id="rdf_re_level" name="re_level">
		<input type="hidden" id="rdf_re_step" name="re_step">
		<input type="hidden" id="rdf_ref" name="ref">
		<input type="hidden" value="${pageNum }" name="pageNum">
		<input type="hidden" value="${pageSize }" name="pageSize">
		<input type="hidden" value="${b_num }" name="b_num">
	</form>
	<!-- 스터디 취소 Modal -->
	<div class="modal fade" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true" id="canStdModal">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLabel">Cancel Study?</h5>
	        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	      </div>
	      <div class="modal-body">
	        정말로 스터디 신청을 취소 하시겠습니까?
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-primary" id="doCanApply">네</button>
	        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">아니오</button>
	      </div>
	    </div>
	  </div>
	</div>
		
	<!-- 삭제 Modal -->
	<div class="modal fade" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true" id="recuDelModal">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLabel">Are you sure you want to delete it?</h5>
	        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	      </div>
	      <div class="modal-body">
	      	정말로 삭제 하시겠습니까?
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-primary" id="doRecuDel">네</button>
	        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">아니오</button>
	      </div>
	    </div>
	  </div>
	</div>
	

</section>

<!-- right side 영역 -->
<c:choose>
	<c:when test="${sessionScope.sessionID==null }">
		<%@include file ="../common/guestForm.jsp" %>
	</c:when>
	<c:otherwise>
		<%@include file ="../common/loginForm.jsp" %>
	</c:otherwise>
</c:choose>
</main>

<!-- footer 영역 -->
<%@include file ="../common/footer.jsp" %>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous"></script>
</body>
</html>