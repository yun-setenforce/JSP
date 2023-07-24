<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" errorPage="errors/boardError.jsp"
session="true"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Title</title>
	</head>
	<body>
		<%@include file="../layout/header.jsp" %>
		<main>
			<h1><font color="red"><%=request.getParameter("id").toString() %></font>님 환영합니다.</h1>
			세션 아이디 : <%=session.getId() %>
		</main>
		<%@include file="../layout/footer.jsp" %>
	</body>
</html>