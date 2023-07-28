<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="vo.User5VO"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
 	request.setCharacterEncoding("UTF-8");

	String uid = request.getParameter("uid");
	
	String host = "jdbc:mysql://127.0.0.1:3306/userdb";
	String user = "root";
	String pass = "1234";
	User5VO vo = new User5VO();
	try{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = 	DriverManager.getConnection(host, user, pass);
		PreparedStatement psmt = conn.prepareStatement("SELECT * FROM `user5` WHERE `uid`=?");
		psmt.setString(1, uid);
		ResultSet rs = psmt.executeQuery();
		if(rs.next()){
			vo.setUid(rs.getString(1));
			vo.setName(rs.getString(2));
			vo.setBirth(rs.getString(3));
			vo.setAge(rs.getInt(4));
			vo.setAddress(rs.getString(5));
			vo.setHp(rs.getString(6));
			
		}

		rs.close();
		psmt.close();
		conn.close();
		
	}catch(Exception e){
		e.printStackTrace();
	}
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>user5::modify</title>
	</head>
	<body>
		<h4>user5 수정</h4>
		<a href="/Ch06/1_JDBC.jsp">처음으로</a>
		<a href="/Ch06/user5/list.jsp">User5 목록</a>
		
		
		<form action="/Ch06/user5/modifyProc.jsp" method="post">
			<table border="1">	
				<tr>
					<td>아이디</td>
					<td><input type="text" name="uid" readonly="readonly" value="<%=uid %>"></td>
				</tr>
				<tr>
					<td>이름</td>
					<td><input type="text" name="name" value="<%=vo.getName() %>"></td>
				</tr>
				<tr>
					<td>생년월일</td>
					<td><input type="date" name="birth" value="<%=vo.getBirth() %>"></td>
				</tr>
				<tr>
					<td>나이</td>
					<td><input type="number" name="age" value="<%=vo.getAge() %>"></td>
				</tr>
				<tr>
					<td>주소</td>
					<td><textarea  name="addr"><%=vo.getAddress() %></textarea></td>
				</tr>
				<tr>
					<td>휴대폰</td>
					<td><input type="text"  name="hp" value="<%=vo.getHp() %>"></textarea></td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" vlaue="제출">
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>