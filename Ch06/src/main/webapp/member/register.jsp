<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>member::register</title>
	</head>
	<body>
		<h4>Member 등록</h4>
		<a href="/Ch06/1_JDBC.jsp">처음으로</a>
		<a href="/Ch06/member/list.jsp">Member 목록</a>
		<form action="/Ch06/member/registerProc.jsp" method="post">
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
					<td>직급</td>
					<td>
						<select name="pos">
							<option value="사원">사원</option>
							<option value="대리">대리</option>
							<option value="과장">과장</option>
							<option value="차장">차장</option>
							<option value="부장">부장</option>
							<option value="사장">사장</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>부서</td>
					<td>
						<select name="dep">
							<option value="101">영업1부</option>
							<option value="102">영업2부</option>
							<option value="103">영업3부</option>
							<option value="104">영업4부</option>
							<option value="105">영업5부</option>
							<option value="106">영업지원부</option>
							<option value="107">인사부</option>
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