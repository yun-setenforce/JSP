<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>user4::register</title>
	</head>
	<body>
		<h4>user4 등록</h4>
		<a href="/Ch06/1_JDBC.jsp">처음으로</a>
		<a href="/Ch06/user4/list.jsp">User4 목록</a>
		<form action="/Ch06/user4/registerProc.jsp" method="post">
			<table border="1">	
				<tr>
					<td>이름</td>
					<td><input type="text" name="name"></td>
				</tr>
				<tr>
					<td>성별</td>
					<td>
						<label><input type="radio" name="gender"  value="1">남</label>
						<label><input type="radio" name="gender"  value="2">여</label>
					</td>
				</tr>
				<tr>
					<td>나이</td>
					<td><input type="number" name="age"></td>
				</tr>
				<tr>
					<td>주소</td>
					<td><textarea  name="addr"></textarea></td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" vlaue="제">
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>