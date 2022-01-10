<%@page import="dao.di.MemberInfoDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String context = request.getContextPath(); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호변경 확인</title>
</head>
<body>
<%
	String id = (String)session.getAttribute("sessionID");
	String passwd = request.getParameter("passwd");
	MemberInfoDao mid = MemberInfoDao.getInstance();
	int result = mid.passwdChk(id, passwd);
	if(result==1){
		String repasswd = request.getParameter("repasswd");
		String repasswdchk = request.getParameter("repasswdchk");
		if(repasswd.equals(repasswdchk)){
			int result1 = mid.passwdUpdate(id, repasswd);
			%>
			<script type="text/javascript">
				alert("비밀번호가 변경되었습니다.");
				location.href="../di/mypasswdUpdateForm.jsp";
			</script>
			<%
		} else {
			out.println("변경할 비밀번호가 틀립니다.");
		}
	} else {
		out.println("비밀번호가 틀렸습니다.");
	}

%>
</body>
</html>