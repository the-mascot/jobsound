<%-- <%@page import="dao.dy.BoardDao"%> --%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<%

String context = request.getContextPath();

%>
<head>
<meta charset="UTF-8">
<meta name="view=viewport" content="width=device-width", initial-scale="1.0">
<title>main</title>
<!-- 부트스트랩 CDN -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">	<!-- 부트스트랩 icon CDN -->
<link href="../common/style.css" rel="stylesheet" type="text/css">   <!-- style.css link -->
</head>

<style>

/*-----------------중앙------------------- */
.titlebar_main{
	width: 454px;
	height: 40px;
	border-bottom:3px solid #F5F5F5;
}


.bigimg_main{
	width: 100px;
	height: 100px;
	
	
}

.container_main{
	width: 910px;
	height: 640px;
	/* border: 1px solid blue; */
	
}
.article_main{
	position:relative;
	width: 454px;
	height: 350px;
	/* border: 1px solid red; */
	float:left
}
.contents1_main{
	width: 450px;
	height: 280px;
}



.img_newsmain{
	max-width: 30%;
	max-height: auto;
	width: auto;
	height: 89px;
	position:relative;
	bottom: 20px;
}




.notice_main{
	position:relative;
	width: 454px;
	height: 350px;
	/* border: 1px solid red; */
	float:left;
	
}
.comu_main{
	position:relative;
	width: 454px;
	height: 305px;
	/* border: 1px solid red; */
	float:left;
}
.study_main{
	position:relative;
	width: 454px;
	height: 305px;
	/* border: 1px solid red; */
	float:left;
}

.bdtitle_main{
	position:relative;
	float:left;
	margin-top:10px;
	width: 100px;
	height: auto;
	left:20px;
	font-family: Jalnan;
}
.btn btn-link{
	position:relative;
	float:right;
	margin-top:20px;
}
.btnmore_main{
	display: inline-block;
	float: right;
	margin-top:10px;
}
.btitle{
	font-family : Pretendard.woff2;
}

/* a:link { text-decoration: none; text-shadow: 0 0 24px; } 
a:visited { text-decoration: none; text-shadow: 0 0 24px; } */
a:link { text-decoration: none; color: black; }
a:visited { /*링크를 방문한 이후*/
    color: black;
}
a:hover { /*마우스를 올려놓을 때*/
    color: blue;
}

ul{
list-style: none;
padding-left: 0px;

}
.font{
    font-family: 'jalnan';
    font-size: 13px;
}
</style>
<body>

<!-- header 부분 -->
<%@include file ="../common/header.jsp" %>

<!-- 상단 navbar 영역 -->
<%@include file ="../common/headNav.jsp" %>

<!-- main 영역 : main 안에 left_side - section - right_side로 3등분 됨 2:6:2 비율 -->
<main>
<!-- leftside 빈공간영역-->
<aside class=left_side>
		<nav class="navbar navbar-expand-lg mt-5">
			<div class="mx-auto mt-3"></div>
		</nav>
	</aside>
<!-- section 영역 -->
<section>


	<div class="container_main mx-auto mt-4">
	
<!-- gm 뉴스기사 -->
		<div class="article_main">
			<div class="titlebar_main">
				<span class="bdtitle_main">기사</span>
				<div class="btnmore_main">
				
					<a href='<%=context %>/gm/newsindex.do'><b>+더보기</b></a>
					
				</div>
			</div>
			<!-- 썸네일배치 -->
          <div class="card" style="max-width: 540px; border: none;">
              <div class="row container_fluid" style="margin-right: -25px">
                <div class="col-md-4">
                      <c:forEach var="newsboard" items="${newslist }" begin="0" end="0"> 
                     <img class="card-img-top" src="${newsboard.imagepath}" style="height: 60px; margin-bottom: 10px; padding-top: 10px;">       
                  </c:forEach> 
                </div>
                <div class="col-md-8" style="padding-top: 13px;">
                   <c:forEach var="newsboard" items="${newslist }" begin="0" end="0" varStatus="status"> 
                           <ul style="margin-left: -25px">                  
                              <li>
                                 <a href='<%=context %>/gm/content.do?b_num=${newsboard.b_num}&b_type=${newsboard.b_type}' style="text-decoration: none;" class="font btitle">${newsboard.title}</a>
                              </li>
                            </ul>          
                  </c:forEach>
                </div>
              </div>    
				  
              <div class="row container_fluid" style="margin-right: -25px">
                <div class="col-md-4">
                      <c:forEach var="newsboard" items="${newslist }" begin="1" end="1"> 
                     <img class="card-img-top" src="${newsboard.imagepath}" style="height: 70px; margin-bottom: 10px; padding-top: 10px;">       
                  </c:forEach> 
                </div>
                <div class="col-md-8" style="padding-top: 13px;">
                   <c:forEach var="newsboard" items="${newslist }" begin="1" end="1" varStatus="status"> 
                           <ul style="margin-left: -25px">                  
                              <li>
                                 <a href='<%=context %>/gm/content.do?b_num=${newsboard.b_num}&b_type=${newsboard.b_type}' style="text-decoration: none;" class="font btitle">${newsboard.title}</a>
                              </li>
                            </ul>          
                  </c:forEach>
                </div>
              </div> 
				  
              <div class="row container_fluid" style="margin-right: -25px">
                <div class="col-md-4">
                      <c:forEach var="newsboard" items="${newslist }" begin="2" end="2"> 
                     <img class="card-img-top" src="${newsboard.imagepath}" style="height: 70px; margin-bottom: 10px; padding-top: 10px;">       
                  </c:forEach> 
                </div>
                <div class="col-md-8" style="padding-top: 13px;">
                   <c:forEach var="newsboard" items="${newslist }" begin="2" end="2" varStatus="status"> 
                           <ul style="margin-left: -25px">                  
                              <li>
                                 <a href='<%=context %>/gm/content.do?b_num=${newsboard.b_num}&b_type=${newsboard.b_type}' style="text-decoration: none;" class="font btitle">${newsboard.title}</a>
                              </li>
                            </ul>          
                  </c:forEach>
                </div>
              </div> 
				   
              <div class="row container_fluid" style="margin-right: -25px">
                <div class="col-md-4">
                      <c:forEach var="newsboard" items="${newslist }" begin="3" end="3"> 
                     <img class="card-img-top" src="${newsboard.imagepath}" style="height: 70px; margin-bottom: 10px; padding-top: 10px;">       
                  </c:forEach> 
                </div>
                <div class="col-md-8" style="padding-top: 13px;">
                   <c:forEach var="newsboard" items="${newslist }" begin="3" end="3" varStatus="status"> 
                           <ul style="margin-left: -25px">                  
                              <li>
                                 <a href='<%=context %>/gm/content.do?b_num=${newsboard.b_num}&b_type=${newsboard.b_type}' style="text-decoration: none;" class="font btitle">${newsboard.title}</a>
                              </li>
                            </ul>          
                  </c:forEach>
                </div>
              </div> 
				    
				  </div>
			</div> 					
										<!-- article -->
<!-- yr 공지사항-->
		<div class="notice_main">
			<div class="titlebar_main">
				<span class="bdtitle_main" style="margin-left: 20px;">공지사항</span>
				<div class="btnmore_main">
					<a href='<%=context %>/yr/notiList.do'><b>+더보기</b></a>
				</div>
			</div>	
			<!-- 몸통 -->
				<div class="contents1_main" style="padding:13px;">
					<c:forEach var="notiboard" items="${notilist }" varStatus="status"> 
							<ul>						
								<li>
									<a href='<%=context%>/yr/notiContent.do?b_num=${notiboard.b_num }&pageNum=${currentPage}' class="font btitle">${notiboard.title}</a>
								</li>				
						    </ul> 		
					</c:forEach>	
				</div>	
		</div>
		
		
		
		
<!-- sh 커뮤니티-->		
		<div class="comu_main" style="padding-top: 30px">
			<div class="titlebar_main">
				<span class="bdtitle_main">커뮤니티</span>
				<div class="btnmore_main">
					<a href='<%=context %>/sh/comuBoardList.do'><b>+더보기</b></a>
				</div>
			</div>	
			<!-- 몸통 -->
			<br>
			
			<div class="contents1_main">					
				<c:forEach var="comuboard" items="${comulist }" varStatus="status"> 
						<ul>
							<li><a href='<%=context%>/sh/comuContent.do?b_num=${comuboard.b_num}&pageNum=${currentPage}&pageSize=${pageSize}&b_type=${b_type}' 
							style="text-decoration: none; color: black;" class="font btitle">${comuboard.title}</a></li>
						</ul>			
				</c:forEach>
			</div>
			
		</div>
		
		
		
		
		
<!-- es 스터디-->		
		<div class="study_main" style="padding-top: 30px">
			<div class="titlebar_main">
				<span class="bdtitle_main" style="margin-left: 20px;">스터디모집</span>
				<div class="btnmore_main">
					<a href='<%=context%>/es/recuBoardList.do'><b>+더보기</b></a>
				</div>
			</div>	
			<!-- 몸통 -->
			<br>
				<div class="contents1_main">					
					<c:forEach var="studyboard" items="${studylist }" varStatus="status"> 
							<ul>
								<li><a href='<%=context %>/es/recuContent.do?b_num=${studyboard.b_num }' 
								style="text-decoration: none; color: black;" class="font btitle">${studyboard.title }</a></li>
							</ul>
					</c:forEach>
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
</body>
</html>