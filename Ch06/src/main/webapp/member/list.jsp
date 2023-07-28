<%@page import="vo.MemberVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	// 데이터베이스 처리 
	String host = "jdbc:mysql://127.0.0.1:3306/testdb";
	String user = "root";
	String pass = "1234";
	
	List<MemberVO> members = new ArrayList<>();
	
	try{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = 	DriverManager.getConnection(host, user, pass);
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT `uid`,a.`name`,`hp`,`pos`,b.`name`,`rdate` "
									+"FROM `member` AS a "
									+ "JOIN `department` AS b "
									+ "ON a.dep=b.depNo");
		
		while(rs.next()){
			MemberVO member = new MemberVO();
			member.setUid(rs.getString(1));
			member.setName(rs.getString(2));
			member.setHp(rs.getString(3));
			member.setPos(rs.getString(4));
			member.setDepName(rs.getString(5));
			member.setRdate(rs.getString(6));
			
			members.add(member);
		}
		rs.close();
		stmt.close();
		conn.close();
		
	}catch(Exception e){
		e.printStackTrace();
	}
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>member::list</title>
	</head>
	<body>
		<h3>Member 목록</h3>
		<a href="/Ch06/1_JDBC.jsp">처음으로</a>
		<a href="/Ch06/member/register.jsp">Member 등록</a>
		
		<table border="1">
			<tr>
				<th>아이디</th>
				<th>이름</th>
				<th>휴대폰</th>
				<th>직급</th>
				<th>부서</th>
				<th>입사일자</th>
				<th>관리</th>
			</tr>
			<%
				for(MemberVO member : members){
			%>
			<tr>
				<td><%=member.getUid() %></td>
				<td><%=member.getName() %></td>
				<td><%=member.getHp() %></td>
				<td><%=member.getPos() %></td>
				<td><%=member.getDepName() %></td>
				<td><%=member.getRdate() %></td>
				<td>
					<a href="/Ch06/member/modify.jsp?uid=<%=member.getUid()%>">수정</a>
					<a href="/Ch06/member/deleteProc.jsp?uid=<%=member.getUid()%>">삭제</a>
				</td>
			</tr>
			<%  
				}
			%>
		</table>
	</body>
</html>