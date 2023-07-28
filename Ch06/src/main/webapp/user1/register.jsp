<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>user1::register</title>
	</head>
	<body>
		<h4>user1 등록</h4>
		<a href="/Ch06/1_JDBC.jsp">처음으로</a>
		<a href="/Ch06/user1/list.jsp">User1 목록</a>
		<form action="/Ch06/user1/registerProc.jsp" method="post">
			<table border="1">
				<tr>
					<td>아이디</td>
					<td><input type="text" name="uid"></td>
				</tr>
				<tr>
					<td>이름</td>
					<td><input type="text" name="name"></td>
				</tr>
				<tr>
					<td>휴대폰</td>
					<td><input type="text" name="hp"></td>
				</tr>
				<tr>
					<td>나이</td>
					<td><input type="number" name="age"></td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" vlaue="등록">
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>