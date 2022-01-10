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
<title>커뮤니티 게시판 - ${board.title}</title>
<!-- 부트스트랩 CDN -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">	<!-- 부트스트랩 icon CDN -->
<link href="../common/style.css" rel="stylesheet" type="text/css">	<!-- style.css link --> 
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script> 

<script type="text/javascript">

	$(document).ready(function(){
		$('.addComm').on("click", function(event) {
			event.preventDefault();
		});
		
	});
	
	var con_num = "";
	var context = "<%=context%>";
	
	function commDeleteBtn(b_num) {
		event.preventDefault();
		$('#exampleModal').modal('show');
		con_num = document.getElementById("con_num").value;
		document.getElementById("delBtn").setAttribute("onClick", "commDel("+b_num+","+con_num+")");
	};
	
	function ContentDeleteBtn(b_num) {
		event.preventDefault();
		$('#exampleModal').modal('show');
		document.getElementById("delBtn").setAttribute("onClick", "contentDel("+b_num+")");
	};
	
	function commDel(b_num,con_num) {
		location.href=context+'/sh/ComuCommDel.do?b_num='+b_num+"&con_num="+con_num;
	};
	
	function contentDel(b_num) {
		location.href=context+'/sh/ComuContentDel.do?b_num='+b_num;
	};
		
	function commEdit(b_num,id,commcontent) {
		event.preventDefault();
		var htmls = "";
		var str = "'commupform'"
		con_num = document.getElementById("con_num").value;
		htmls += '<div class="accordion-item" style="border: none;" id="id'+b_num+'">';
		htmls += '<div class="d-flex justify-content-between mb-0" style="font-family: pretend">';
		htmls += '<p class="mb-0" id="'+id+'">작성자 '+id+'<p>';
		htmls += '<p class="mb-0" id="commdate"> </p>';
		htmls += '</div>';
		htmls += '<form action="<%=context%>/sh/commUpdate.do" method="post" id="commupform">';
		htmls += '<div class="d-flex justify-content-between pb-0">';
		htmls += '<input type="hidden" name="b_num" value="'+b_num+'">';
		htmls += '<input type="hidden" name="con_num" value="'+con_num+'">';
		htmls += '<textarea name="editContent" id="editContent'+b_num+'" class="form-control" rows="3" required="required">';
		htmls += commcontent;
		htmls += '</textarea>';
		htmls += '</div>';
		htmls += '<div>';
		htmls += '</form>';
		htmls += '<a href="#" onclick="document.getElementById('+str+').submit();" class="text-dark">[완료]</a>';
		htmls += '<a href="javascript:void(0)" onclick="location.reload();">[취소]</a>';
		htmls += '</div>';
		htmls += '</div>';
		$('#id' + b_num).replaceWith(htmls);

	};
	
</script>
<style type="text/css">
.th {
	font-size : 16px;
}
.replyboard {
	color: black;
	font-size: 15px;
}
.content {
	color: black;
	font-size: 16px;
	min-height : 500px;
	/* max-width: 40%; */
	white-space: pre-line;
}

.modal {
        text-align: center;
}
 
@media screen and (min-width: 768px) { 
        .modal:before {
                display: inline-block;
                vertical-align: middle;
                content: " ";
                height: 100%;
        }
}
 
.modal-dialog {
        display: inline-block;
        text-align: left;
        vertical-align: middle;
}
</style>

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
			<h2 class="mt-5 mb-3 mx-5 fw-bold"
				style="font-family: pretend; color: #5798D7">커뮤니티 게시판</h2>
		</div>
		<div>
			<p class="mt-2 mx-5 fs-6 text-muted fst-italic"
				style="font-family: pretend">- 커뮤니티 게시판입니다.</p>
		</div>
	
	<!-- 게시물 테이블 -->
				<!-- 본문 영역 -->
				<input type="hidden" id="con_num" value="${board.b_num}">
				<div class="col-md-10 my-5 board container">
					<div class="row align-items-start mb-3 pb-2 th"
						style="border-bottom: 1px solid #dee2e6;">
						<p class="fs-4 text-start">${board.title }</p>
						<div class="col me-auto">
							작성자 ${board.id }
						</div>
						<div class="col">
							<div class="col mx-0 px-0 text-end">
								<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
									fill="currentColor" class="bi bi-eye" viewBox="0 0 16 16">
		  					<path d="M16 8s-3-5.5-8-5.5S0 8 0 8s3 5.5 8 5.5S16 8 16 8zM1.173 8a13.133 13.133 0 0 1 1.66-2.043C4.12 4.668 5.88 3.5 8 3.5c2.12 0 3.879 1.168 5.168 2.457A13.133 13.133 0 0 1 14.828 8c-.058.087-.122.183-.195.288-.335.48-.83 1.12-1.465 1.755C11.879 11.332 10.119 12.5 8 12.5c-2.12 0-3.879-1.168-5.168-2.457A13.134 13.134 0 0 1 1.172 8z" />
		 					<path d="M8 5.5a2.5 2.5 0 1 0 0 5 2.5 2.5 0 0 0 0-5zM4.5 8a3.5 3.5 0 1 1 7 0 3.5 3.5 0 0 1-7 0z" /></svg>
								${board.views }
							</div>
						</div>
						<div class="col text-end mx-0 px-0">${board.uploaddate } 작성</div>
					</div>
					<div>
						<p>
							<img src="${board.imagepath}" class="image"
								style="max-width: 100%; height: auto;">
						</p>
						<pre class="content">
							${board.content}
						</pre>
					</div>
					<div class="d-flex justify-content-between border-bottom mb-3">
						<div class="justify-content-left">
							<table>
								<tr>
									<td style="color: #5798D7;">${fn:length(list)} comment(s)</td>
								</tr>
							</table>
						</div>
						<div class="justify-content-right mb-3">
						<c:if test="${sessionScope.sessionID eq board.id}">
								<button type="button" class="btn btn-light btn-xs"
									onclick="location.href='<%=context%>/sh/boardUpdateForm.do?b_num=${board.b_num}'">수정</button>
								<button type="button" class="btn btn-light btn-xs"
									onclick="ContentDeleteBtn(${board.b_num})">삭제</button>
						</c:if>
						<a href="<%=context %>/sh/comuBoardList.do?pageNum=${pageNum}&pageSize=${pageSize}"><button type="button" class="btn btn-secondary">목록</button></a>
						</div>
						
					</div>

					<div class="card">
						<div class="card-header bg-light">댓글</div>
						<div class="card-body">
							<div class="accordion py-1" id="accordionExample"
								style="border-bottom-style: groove;">
								<c:forEach var="comm" items="${list}" varStatus="status">
									<div class="accordion-item" style="border: none;"
										id="id${comm.b_num}">
										<div class="d-flex justify-content-between mb-0"
											style="font-family: pretend">
											<c:choose>
												<c:when test="${comm.re_level eq 2 }">
													<p class="mb-0 ms-4" id="commid">작성자 ${comm.id}</p>
												</c:when>
												<c:otherwise>
													<p class="mb-0" id="commid">작성자 ${comm.id}
													<p>
												</c:otherwise>
											</c:choose>
											<p class="mb-0" id="commdate">${comm.commdate}</p>
										</div>
										<div class="d-flex justify-content-between pb-0">
											<div>
												<c:choose>
													<c:when test="${comm.re_level eq 2 }">
														<p id="p_commcontent">
															<svg xmlns="http://www.w3.org/2000/svg" width="16"
																height="16" fill="currentColor"
																class="bi bi-arrow-return-right" viewBox="0 0 16 16">
  												<path fill-rule="evenodd"
																	d="M1.5 1.5A.5.5 0 0 0 1 2v4.8a2.5 2.5 0 0 0 2.5 2.5h9.793l-3.347 3.346a.5.5 0 0 0 .708.708l4.2-4.2a.5.5 0 0 0 0-.708l-4-4a.5.5 0 0 0-.708.708L13.293 8.3H3.5A1.5 1.5 0 0 1 2 6.8V2a.5.5 0 0 0-.5-.5z" />
												</svg>
															&nbsp; ${comm.commcontent }
														</p>
													</c:when>
													<c:otherwise>
														<p>${comm.commcontent }</p>
													</c:otherwise>
												</c:choose>
											</div>
											<div>
												<c:if test="${comm.id == sessionScope.sessionID}">
													<a href="#"
														onclick="commEdit('${comm.b_num}','${comm.id}','${comm.commcontent}')"
														class="text-dark" style="text-decoration: none; color: black;">수정</a>
													<a href="#" class="commDeleteBtn"
														onclick="commDeleteBtn('${comm.b_num}')" style="text-decoration: none; color=black;">삭제</a>
												</c:if>
												<a href="#" class="addComm text-secondary"
													style="text-decoration: none;" data-bs-toggle="collapse"
													data-bs-target="#collapse${status.count }"
													aria-expanded="true" aria-controls="collapseOne">답글 달기</a>
											</div>
										</div>
										<c:choose>
										<c:when test="${not empty sessionScope.sessionID }">
										<div class="accordion-body py-0">
											<div id="collapse${status.count }"
												class="accordion-collapse collapse"
												aria-labelledby="headingOne"
												data-bs-parent="#accordionExample">
												<div class="form-control">
													<form
														action="<%=context%>/sh/comuCommAddRe.do?b_num=${comm.b_num}&bnum=${b_num}"
														method="post">
														<input type="hidden" value="${b_num}"> <input
															type="hidden" value="${b_type}">
														<div>
															<textarea class="form-control" name="commcontent"
																placeholder="답글을 남겨보세요"
																style="min-height: 13px; width: 100%; border: none;"
																required="required"></textarea>
														</div>
														<div class="clearfix mt-2">
															<input type="submit"
																class="btn float-end bg-primary text-white bg-opacity-75"
																value="답글 쓰기">
														</div>
													</form>
												</div>
											</div>
										</div>
										</c:when>
										<c:otherwise>
										<div class="accordion-body py-0">
											<div id="collapse${status.count }"
												class="accordion-collapse collapse"
												aria-labelledby="headingOne"
												data-bs-parent="#accordionExample">
												<div class="form-control">
													<form
														action="<%=context%>/sh/comuCommAddRe.do?b_num=${comm.b_num}&bnum=${b_num}"
														method="post">
														<input type="hidden" value="${b_num}"> <input
															type="hidden" value="${b_type}">
														<div>
															<textarea class="form-control" name="commcontent"
																placeholder="답글을 남기려면 로그인해주세요"
																style="min-height: 13px; width: 100%; border: none;"
																required="required" disabled="disabled"></textarea>
														</div>
														<div class="clearfix mt-2">
															<input type="submit"
																class="btn float-end bg-primary text-white bg-opacity-75"
																value="답글 쓰기" disabled="disabled">
														</div>
													</form>
												</div>
											</div>
										</div>
										</c:otherwise>
										</c:choose>

									</div>
								</c:forEach>
							</div>
							<c:choose>
							<c:when test="${not empty sessionScope.sessionID }">
							<div class="form-control mt-2">
								<form action="<%=context%>/sh/comuCommAdd.do?b_num=${b_num}"
									method="post">
									<input type="hidden" value="${b_num}" id="b_num">
									<div>
										<textarea class="form-control" id="commcontent"
											name="commcontent" placeholder="댓글을 남겨보세요"
											style="min-height: 13px; width: 100%; border: none;" required="required"></textarea>
									</div>
									<div class="clearfix mt-2">
										<input type="submit"
											class="btn float-end bg-primary text-white bg-opacity-75"
											value="댓글 쓰기">
									</div>
								</form>
							</div>
							</c:when>
							<c:otherwise>
							<div class="form-control mt-2">
								<form action="<%=context%>/sh/comuCommAdd.do?b_num=${b_num}"
									method="post">
									<input type="hidden" value="${b_num}" id="b_num">
									<div>
										<textarea class="form-control" id="commcontent"
											name="commcontent" placeholder="댓글을 남기려면 로그인이 필요합니다."
											style="min-height: 13px; width: 100%; border: none;" disabled="disabled"></textarea>
									</div>
									<div class="clearfix mt-2">
										<input type="submit"
											class="btn float-end bg-primary text-white bg-opacity-75"
											value="댓글 쓰기" disabled="disabled">
									</div>
								</form>
							</div>
							</c:otherwise>
							</c:choose>
						</div>
					</div>
				</div>

				<!-- 본문 영역 -->
				<!-- 댓글 -->
	
			<!-- Modal -->
			<div class="modal fade" id="exampleModal" tabindex="-1"
				aria-labelledby="exampleModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-body">
							<div class="form-group">
								<label for="reply_text">정말 삭제하시겠습니까?</label>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default pull-left"
								data-dismiss="modal">취소</button>
							<button type="button" class="btn btn-danger modalDelBtn" id="delBtn" onclick="">삭제</button>
						</div>
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