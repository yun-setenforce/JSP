<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>requestFile</title>
	</head>
	<body>
		<h3>requestFile</h3>
		<%
			// 해당 페이지를 파일 다운로드로 응답 
			response.setHeader("Content-Type", "application/octet-stream");
		%>
	</body>
</html>