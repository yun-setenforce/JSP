<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>cookieConfirm</title>
	</head>
	<body>
	<h3>쿠키확인</h3>
	<%
		request.setCharacterEncoding("UTF-8");
		Cookie[] cookies = request.getCookies();
		for(Cookie cookie : cookies){
	%>
		<p>
			쿠키명 : <%=cookie.getName() %> <br>
			쿠키값 : <%=cookie.getValue() %>
		</p>
	<% 
		}
	%>
		
	</body>
</html>