<%@page import="vo.User0VO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>user0::list</title>
    	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
		<script>
			$.ajax({
				url:'./listProc.jsp',
				type:'POST',
				dataType:'json',
				success:function(data){
					for(let user of data){
						console.log(user);
						let tr=`<tr>
									<td>\${user.uid}</td>
									<td>\${user.name}</td>
									<td>\${user.hp}</td>
									<td>\${user.age}</td>
									<td>
										<a href='./modify.jsp?uid=\${user.uid}'>수정</a>
										<a href='./delete.jsp?uid=\${user.uid}'>삭제</a>
									</td>	
								</tr>`;
						$('table').append(tr);
					}
				},
				error:function(e){
					console.log(e);
				}
			});
		</script>
	</head>
	<body>
		<h3>User0 목록</h3>
		<a href="/Ch06/1_JDBC.jsp">처음으로</a>
		<a href="/Ch06/user0/register.jsp">User0 등록</a>
		
		<table border="1">
			<tr>
				<th>아이디</th>
				<th>이름</th>
				<th>휴대폰</th>
				<th>나이</th>
				<th>관리</th>
			</tr>
		</table>
	</body>
</html>