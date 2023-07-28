<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>페이지를 찾을 수 없습니다.</title>
	</head>
	<body>
		<h3>요청하신 페이지가 없습니다. 다시 확인하시기 바랍니다.</h3>
		<p>
		
			에러 종류 : <%=exception.getClass().getName() %> <br>
			에러 내용 : <%=exception.getMessage() %>
		</p>
		<a href="../6_exception.jsp">뒤로가기</a>
	</body>
</html>