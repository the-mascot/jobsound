<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<% String context = request.getContextPath(); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>
<link
   href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css"
   rel="stylesheet"
   integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU"
   crossorigin="anonymous">
<script
   src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
   integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ"
   crossorigin="anonymous"></script>
<script
   src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"
   integrity="sha384-W8fXfP3gkOKtndU4JGtKDvXbO53Wy8SZCQHczT5FMiiqmQfUpWbYdTil/SxwZgAN"
   crossorigin="anonymous"></script>
<script
   src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.min.js"
   integrity="sha384-skAcpIdS7UcVUC05LJ9Dxay8AXcDYfBJqt1CJ85S/CFujBsIzCIv+l9liuYLaMQ/"
   crossorigin="anonymous"></script>
<style type="text/css">
   #divBox{
      width: 300px;
      height: 40px;
   }
</style>
<script type="text/javascript" src="../js/jquery.js"></script>
<script type="text/javascript">
      function deleteChk(b_num){
         if(confirm("공지사항을 삭제하시겠습니까?")==true){
            location.href="<%=context%>/yr/notiDelete.do?b_num="+b_num;
         } else{
            return false;
         }
      }
      function commDelete(b_num, b_type) {
         if(confirm("댓글을 삭제하시겠습니까?")==true){
            var sendData = 'b_type='+b_type+'&b_num='+b_num+'&isBjax=1';
            $.ajax({
               url : '<%=context%>/yr/notiCommDelete.do',
               data : sendData,
               dataType : 'text',
               success : function(value){
                  alert("정상적으로 삭제되었습니다.");
                  location.reload();
                  /* $('#rePly').html(value); */
               }
            })
         } else{
            return false;
         }
      }
      function commWrite(b_type, b_num) {
         var commContent = $('#commContent').val();
         console.log(commContent);
         if(confirm("댓글을 작성하시겠습니까?")==true){
            location.href="<%=context%>/yr/notiCommWrite.do?b_type="+b_type+"&b_num="+b_num+"&commContent="+commContent;
         } else{
            return false;
         }
      }
      function commUpdate(b_num, b_type) {
         var sendData = 'b_num='+b_num+'&b_type='+b_type;
         $.ajax({
            url : 'notiCommUpdateForm.do',
            data : sendData,
            dataType : 'text',
            success : function(value){
               $('#commUpdateForm'+b_num).html(value);
            }
         })
      }
      function doDisplay(b_num){
         var con = document.getElementById("myDIV"+b_num);
         if(con.style.display=='block'){
            con.style.display = 'none';
         }else{
            con.style.display = 'block';
         }
      }
      function rePlyWrite(ref, re_step, b_num) {
         if(confirm("댓글을 작성하시겠습니까?")==true){
            var rePlyContent = $('#rePlyContent'+b_num).val();
            var sendData = 'ref='+ref+'&re_step='+re_step+'&rePlyContent='+rePlyContent+'&isBjax=1';
            $.ajax({
               url : '<%=context%>/yr/notiRePlyWrite.do',
               data : sendData,
               dataType : 'text',
               success : function(value){
                  location.reload();
               }
            })
         } else{
            return false;
         }
      }
   </script>
</head>
<body>

   <!-- header 부분 -->
   <%@include file="../common/header.jsp"%>

   <!-- 상단 navbar 영역 -->
   <%@include file="../common/headNav.jsp"%>

   <!-- main 영역 : main 안에 left_side - section - right_side로 3등분 됨 2:6:2 비율 -->
   <main>
      <!-- left side 영역 -->
      <%@include file="../common/notiLeftSide.jsp"%>

      <!-- section 영역 -->
      <section>

         <div>
            <div>
               <h2 class="mt-5 mb-3 mx-5 fw-bold"
                  style="font-family: pretend; color: #5798D7">공지사항 게시판</h2>
            </div>
            <div>
               <p class="mt-2 mx-5 fs-6 text-muted fst-italic"
                  style="font-family: pretend">- 공지사항 게시판입니다</p>
            </div>
         </div>
         <br> <br>
         <table class="table">
            <thead>
               <tr>
                  <th class="texthead" width="600"><h3>${board.title }</h3></th>
                  <th class="texthead1" width="150"><h6>작성자 ${board.id}</h6></th>
                  <th class="texthead2" width="200"><h6>작성일
                        ${board.uploadDate }</h6></th>
                  <th class="texthead3" width="100"><h6>조회 ${board.views }</h6>
               </tr>
            </thead>
            <tbody>
               <tr>
                  <td colspan="3" width="1000" height="400">${board.content}</td>
               </tr>
            </tbody>
         </table>

         <c:if test="${sessionScope.sessionID == 'admin' }">
            <div class="d-grid gap-2 d-md-flex justify-content-md-end">
               <!-- 게시글 수정 -->
               <button class="btn btn-primary me-md-2" type="button"
                  onclick="location.href='notiUpdateForm.do?b_num=${board.b_num}'">수정</button>
               <!-- 게시글 삭제 -->
               <button class="btn btn-primary" type="button"
                  onclick=deleteChk(${board.b_num})>삭제</button>
            </div>
         </c:if>
         <!-- 댓글 폼 -->
         <c:if test="${listSize >0 }">
            <table class="table table-sm">
               <thead>
                  <tr>
                     <th colspan="3">${listSize} comment(s)</th>
                  </tr>
               </thead>
               <c:forEach var="list" items="${list }">
               <c:choose>
                     <c:when test="${list.re_level == 1 }">
                        <tbody>
                           <tr>
                              <%-- <td width="350px" style="text-align: left">　${list.id }</td> --%>
                              <td width="350px" style="text-align: left; border-bottom: none">　<strong>작성자</strong> ${list.id }</td>
                              <td width="165px" style="text-align: right; border-bottom: none"><strong>작성일</strong> ${list.commDate}</td>
                           </tr>
                           <tr>
                              <td width="350px" style="text-align: left; border-bottom: none">　${list.commContent }</td>
                              <c:if test="${list.id == sessionScope.sessionID }">
                                 <td width="165px" style="text-align: right; border-bottom: none">
                                    <button type="button" class="btn btn-secondary btn-sm" onclick="commUpdate(${list.b_num},${list.b_type })">수정</button>
                                    <button type="button" class="btn btn-secondary btn-sm" onclick="commDelete(${list.b_num},${list.b_type })">삭제</button>
                                 </td>
                              </c:if>
                           </tr>
                           <tr>
                              <c:if test="${not empty sessionScope.sessionID}">
                                 <td colspan="2" width="515px">
                                    <a href="javascript:doDisplay(${list.b_num});">>답글쓰기</a>
                                       <div id="myDIV${list.b_num }" style="display: none">
                                          <span>
                                                <textarea class="form-control" id="rePlyContent${list.b_num}" style="min-height: 100px; width: 100%" name="rePlyContent${list.b_num}"></textarea>
                                                <button type="button" class="btn btn-secondary btn-sm" onclick="rePlyWrite(${list.ref},${list.re_step},${list.b_num})">등록</button>
                                          </span>
                                       </div>
                                 </td>
                              </c:if>
                           </tr>
                           <tr>
                              <td colspan="2" width="515px">
                                 <div id="commUpdateForm${list.b_num }"></div>
                              </td>
                           </tr>
                        </tbody>
                     </c:when>
                  </c:choose>
                  <c:choose>
                     <c:when test="${list.re_level != 1 }">
                        <tbody>
                           <tr>
                              <%-- <td width="350px" style="text-align: left">　${list.id }</td> --%>
                              <td width="350px" style="text-align: left; border-bottom: none">　<img src ="../common/images/reply.png" width="20px" height="20px"><strong>작성자</strong> ${list.id }</td>
                              <td width="165px" style="text-align: right; border-bottom: none"><strong>작성일</strong> ${list.commDate}</td>
                           </tr>
                           <tr>
                              <td width="350px" style="text-align: left; border-bottom: none">　${list.commContent }</td>
                              <c:if test="${list.id == sessionScope.sessionID }">
                                 <td width="165px" style="text-align: right; border-bottom: none">
                                    <button type="button" class="btn btn-secondary btn-sm" onclick="commUpdate(${list.b_num},${list.b_type })">수정</button>
                                    <button type="button" class="btn btn-secondary btn-sm" onclick="commDelete(${list.b_num},${list.b_type })">삭제</button>
                                 </td>
                              </c:if>
                           </tr>
                           <tr>
                              <c:if test="${not empty sessionScope.sessionID}">
                                 <td colspan="2" width="515px">
                                    <a href="javascript:doDisplay(${list.b_num});">>답글쓰기</a>
                                       <div id="myDIV${list.b_num }" style="display: none">
                                          <span>
                                                <textarea class="form-control" id="rePlyContent${list.b_num}" style="min-height: 100px; width: 100%" name="rePlyContent${list.b_num}"></textarea>
                                                <button type="button" class="btn btn-secondary btn-sm" onclick="rePlyWrite(${list.ref},${list.re_step},${list.b_num})">등록</button>
                                          </span>
                                       </div>
                                 </td>
                              </c:if>
                           </tr>
                           <tr>
                              <td colspan="2" width="515px">
                                 <div id="commUpdateForm${list.b_num }"></div>
                              </td>
                           </tr>
                        </tbody>
                     </c:when>
                  </c:choose>
               </c:forEach>
            </table>
         </c:if>
         <c:if test="${listSize ==0 }">
            <table class="table table-sm">
               <thead>
                    <tr>
                       <td colspan="3">0 comment(s)</td>
                    </tr>
                 </thead>
                 <tbody>
                    <tr>
                       <td>댓글이 없습니다.</td>
                    </tr>
                 </tbody>
            </table>
         </c:if>
         <c:if test="${not empty sessionScope.sessionID}">
            <table>
               <tr>
                  <td width="200px" style="text-align: left; vertical-align: top">　<strong>작성자 </strong>${sessionScope.sessionID}</td>
                  <td width="1000px"><textarea class="form-control" id="commContent" style="min-height: 100px; width: 100%" name="commContent"></textarea>
                  <td width="165px" style="text-align: center">
                     <button type="button" class="btn btn-secondary btn-sm" onclick="commWrite('${b_type}',${b_num })">작성하기</button>
                  </td>
                  <%-- <a href="#" onclick="commWrite(${b_type},${b_num })">작성</a></td> --%>
               </tr>
            </table>
         </c:if>
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
   <%@include file="../common/footer.jsp"%>
</body>
</html>