<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>6_exception</title>
		<!-- 
			날짜 : 2023/07/26
			이름 : 정채윤
			내용 : JSP exception 내장객체 실습하기
			
			exception
			 - JSP 예외가 발생했을 때 예외를 처리하기 위한 내장객체 
			 - 간접적으로 exception 객체를 통해 에러코드별 에러 페이지 작성 
			
			주요 응답코드 
			- 200 : 요청 성공 
			- 3xx : 리다이렉트 
			- 404 : 페이지 없음 
			- 500 : 서버 내부 에러
		 -->
	</head>
	<body>
		<h3>6.exception 내장객체 </h3>
		
		<a href="./sample.jsp">404 에러</a>
		<a href="./proc/exception.jsp">500 에러</a>
	</body>
</html>