<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="vo.MemberVO"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
 	request.setCharacterEncoding("UTF-8");

	String uid = request.getParameter("uid");
	
	String host = "jdbc:mysql://127.0.0.1:3306/testdb";
	String user = "root";
	String pass = "1234";
	MemberVO member = new MemberVO();
	try{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = 	DriverManager.getConnection(host, user, pass);
		PreparedStatement psmt = conn.prepareStatement("SELECT * "
												+"FROM `member` "
												+ "WHERE `uid`=?");
		psmt.setString(1, uid);
		ResultSet rs = psmt.executeQuery();
		if(rs.next()){
			member.setUid(rs.getString(1));
			member.setName(rs.getString(2));
			member.setHp(rs.getString(3));
			member.setPos(rs.getString(4));
			member.setDep(rs.getInt(5));
			member.setRdate(rs.getString(6));
			
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
		<title>member::modify</title>
	</head>
	<body>
		<h4>member 수정</h4>
		<a href="/Ch06/1_JDBC.jsp">처음으로</a>
		<a href="/Ch06/member/list.jsp">Member 목록</a>
		<form action="/Ch06/member/modifyProc.jsp" method="post">
			<table border="1">	
				<tr>
					<td>아이디</td>
					<td><input type="text" name="uid" readonly="readonly" value=<%=member.getUid() %>></td>
				</tr>
				<tr>
					<td>이름</td>
					<td><input type="text" name="name"  value=<%=member.getName() %>></td>
				</tr>
				<tr>
					<td>휴대폰</td>
					<td><input type="text" name="hp" value=<%=member.getHp() %>></td>
				</tr>
				<tr>
					<td>직급</td>
					<td>
						<select name="pos">
							<option value="사원" <%=(member.getPos().equals("사원"))?"selected":"" %>>사원</option>
							<option value="대리" <%=(member.getPos().equals("대리"))?"selected":"" %>>대리</option>
							<option value="과장" <%=(member.getPos().equals("과장"))?"selected":"" %>>과장</option>
							<option value="차장" <%=(member.getPos().equals("차장"))?"selected":"" %>>차장</option>
							<option value="부장" <%=(member.getPos().equals("부장"))?"selected":"" %>>부장</option>
							<option value="사장" <%=(member.getPos().equals("사장"))?"selected":"" %>>사장</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>부서</td>
					<td>
						<select name="dep">
							<option value="101" <%=(member.getDep()==101)?"selected":"" %>>영업1부</option>
							<option value="102" <%=(member.getDep()==102)?"selected":"" %>>영업2부</option>
							<option value="103" <%=(member.getDep()==103)?"selected":"" %>>영업3부</option>
							<option value="104" <%=(member.getDep()==104)?"selected":"" %>>영업4부</option>
							<option value="105" <%=(member.getDep()==105)?"selected":"" %>>영업5부</option>
							<option value="106" <%=(member.getDep()==106)?"selected":"" %>>영업지원부</option>
							<option value="107" <%=(member.getDep()==107)?"selected":"" %>>인사부</option>
						</select>
					</td>
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