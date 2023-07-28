<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>scopeResult</title>
	</head>
	<body>
		<h4>내장객체 값 확인</h4>
		<p>
		
			pageContext 확인 : <%=pageContext.getAttribute("name") %> <br>
			request 확인 : <%=request.getAttribute("name") %> <br>
			<!-- /다른 브라우저에서 이 페이지로 바로 접속하면 	session값이 null -->
			session 확인 : <%=session.getAttribute("name") %> <br>
			<!-- /서버 껐다 키고 이 페이지로 바로 접속하면 null -->
			application 확인 : <%=application.getAttribute("name") %> <br>
		</p>
		
		<%

		request.setAttribute("name", "dd");
		 %>
		<a href="/Ch03/proc/scopeResult.jsp">결과확인</a>
	</body>
</html>