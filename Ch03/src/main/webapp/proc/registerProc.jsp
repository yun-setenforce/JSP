<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	// 수신 데이터 인코딩 설정
	request.setCharacterEncoding("UTF-8");

	String uid 	= request.getParameter("uid");
	String pass	= request.getParameter("pass");
	String name = request.getParameter("name");
	String birth = request.getParameter("birth");
	String gender = request.getParameter("gender");
	String addr = request.getParameter("addr");
	
	// 여러개의 전송 데이터 수신 
	String[] hobbies = request.getParameterValues("hobby");
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>registerProc</title>
	</head>
	<body>
		<h3>회원가입 수신 데이터</h3>
		아이디 : <%= uid %> <br>
		비밀번호 : <%= pass %> <br>
		이름 : <%= name %> <br>
		생년월일 : <%= birth %> <br>
		성별 : <%=(gender.equals("1")?"남자":"여자") %> <br>
		주소 : <%= addr %> <br>
		취미 : <%=hobbies[0] %>
		<%
			for(int i=1; i<hobbies.length; i++){
				out.print(", " + hobbies[i]);
			}
		%>
		 <br>
		
		
		<a href="../1_request.jsp">뒤로가기</a>
	</body>
</html>