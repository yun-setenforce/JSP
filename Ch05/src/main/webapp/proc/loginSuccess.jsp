<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String sessid = (String) session.getAttribute("sessid");
	
	if(sessid == null){
		response.sendRedirect("./loginForm.jsp");
		return;
	}
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>loginSuccess</title>
	</head>
	<body>
		<h3>로그인 성공</h3>
		<p>
			<%=sessid %>님 반갑습니다.<br>
			<a href="./logout.jsp">로그아웃</a>
		</p>
	</body>
</html>