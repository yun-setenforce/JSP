<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE ht>
<html>
	<head>
		<meta charset="UTF-8">
		<title>4_application</title>
		<%--
			날짜 : 2023/07/25
			이름 : 정채윤
			내용 : JSP application 내장객체 실습하기
			
			application
			 - 현재 웹 애플리케이션을 실행하는 WAS(톰캣)의 환경 객체
			 - 서버 환경값(Context)를 설정하고 참조, 로그작업 지원 
		 --%>
	</head>
	<body>
		<h3>4.application 내장객체</h3>
		
		<h4> 서버 정보</h4>
		<%=application.getServerInfo() %>
		
		<h4> 파라미터 정보</h4>
		<%
			///context에 설정된 값을 application내장객체로 가지고 온다 
			String value1 = application.getInitParameter("PARAM1");
			String value2 = application.getInitParameter("PARAM2");
		%>
		<p>
			value1 : <%=value1 %> <br>
			value2 : <%=value2 %>
		</p>
		
		<h4> 로그 정보</h4>
		<% 
			application.log("로그기록"); ///시스템 히스토리 기록  . 일반적으로는 파일로...지금은 콘솔로 출
		%>
		<h4> 자원 경로</h4>
		<%= application.getRealPath("./4_application.jsp") %>
		<%--실제 이클립스 경로랑 다르게 나옴  --%>
	</body>
</html>