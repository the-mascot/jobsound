<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>right side</title>
<!-- 부트스트랩 CDN -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">	<!-- 부트스트랩 icon CDN -->
<link href="../common/style.css" rel="stylesheet" type="text/css">	<!-- style.css link -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script> 
<script type="text/javascript">
$(document).ready(function(){
	
	$('#loginBtn').on("click", function() {
		
		var id=$('#id').val();
		var passwd=$('#passwd').val();
		var sendData="id="+id+"&passwd="+passwd;
		
		$.ajax({
			type	: 'POST',
			url 	:'../es/Ajaxlogin.do',
			data	: sendData,
			dataType: 'html',
			success	:function(data) {
				console.log(data);
				if(data!=null) {	
					alert("로그인 되었습니다!")
				$("#side").html(data);
				}
				else if(data==0) {
					alert("비밀번호가 틀립니다.");					
				}
				else if(data==-1) {
					alert("아이디가 없습니다.");					
				}
			},
			error	:function(e) {
				console.log("error : ",e);
			}
	
		});  
		
	});
	
});
</script>
</head>
<body>
<aside class="right_side" id="right_side">
	<div class="side_login_c mt-5 container border border-1">
		<div id="side">

		</div>
	</div>
	<input class="btn btn-outline-primary" type="button" onclick="window.scrollTo(0,0);" value="TOP" style="position: fixed; right: 50px; bottom: 50px;">
</aside>
</body>
</html>