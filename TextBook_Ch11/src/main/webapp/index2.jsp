<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" errorPage="errors/boardError.jsp"
session="false"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Title</title>
	</head>
	<body>
		<main>
			<h1><font color="red"><%=request.getParameter("id").toString() %></font>님 환영합니다.</h1>
			<%HttpSession session = request.getSession(); %>
			세션 아이디 : <%=session.getId() %>
		</main>
	</body>
</html>