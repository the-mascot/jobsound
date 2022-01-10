<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>header</title>
<!-- 부트스트랩 CDN -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">	<!-- 부트스트랩 icon CDN -->
<link href="../common/style.css" rel="stylesheet" type="text/css">	<!-- style.css link -->
<script type="text/javascript">
function totchk() {
	if(!frmhead.keyWord.value) {
		alert("검색어를 입력하세요");
		frmhead.keyWord.focus();
		return false;
	}
	var blank_pattern = /^\s+|\s+$/g;
	if(frmhead.keyWord.value.replace( blank_pattern, '' ) == "" ){
	    alert(' 공백만 입력되었습니다 ');
	    return false;
	}
	return true;
}
</script>
</head>
<body>
	<header>
		<a href="../dy/main.do" class="logo"><img src="../common/images/jobsound_logo.png" class="img_logo"></a>
		<div class="mx-auto mt-5">
			<form action="../sh/totalSearch.do" id="frmhead" onsubmit="return totchk()">	<!-- 전체 검색 이동 -->
				<fieldset>
					<legend class="visually-hidden">검색영역</legend>
					<div class="search_box">
						<input type="text" name="keyWord" maxlength="255">
						<button type="submit">	<!-- submit -->
							<img src="../common/images/search_button.png">
						</button>
					</div>
				</fieldset>
			</form>
		</div>
	</header>
</body>
</html>