<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page info="copyright yun-setenforce" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Directive</title>
		<%--
			날짜 : 2023/07/25
			이름 : 정채윤
			내용 : JSP 지시자 실습하기
			
			지시다(Directives)
			 - JSP 페이지에 추가적인 정보를 설정하는 스크립트 문법 
			 - 스크립트릿, 표현식과 함꼐 JSP 기본 구성요소 
			 - page ,include ,taglib 지시자 등 
		 --%>
	</head>
	<body>
		<h3>5.지시자 </h3>

		<h4>page 지시자</h4>
		<p>
			page copyright : <%=getServletInfo() %>
		</p>
		<h4>include 지시자</h4>
		<%@include file="./inc/_header.html"  %>
		<%@include file="./inc/_footer.html"  %>
	</body>
</html>