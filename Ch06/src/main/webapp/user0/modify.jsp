<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="vo.User1VO"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>user0::modify</title>
    	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
		<script>
			$(function(){
				//수정데이터 요청하기 
				const urlParams = new URL(location.href).searchParams;
				//const url = "./user.jsp?uid=" +   urlParams.get('uid');
				const url="./user.jsp?uid="+new URLSearchParams(location.search).get('uid');
				$.get(url, function(data){
					console.log(data);
					
					$('input[name=uid]').val(data.uid);
					$('input[name=name]').val(data.name);
					$('input[name=hp]').val(data.hp);
					$('input[name=age]').val(data.age);
				});
				//수정데이터 전송하기 
				$('input[type=submit]').click(function(e){
					e.preventDefault();
					
					// 수정 데이터 가져오기 
					const uid = $('input[name=uid]').val();
					const name = $('input[name=name]').val();
					const hp = $('input[name=hp]').val();
					const age = $('input[name=age]').val();
					
					// JSON 생성 
					const jsonData = {
							"uid":uid,
							"name":name,
							"hp":hp,
							"age":age,
					};
					console.log(jsonData);
					
					
					$.post('./modifyProc.jsp',jsonData,function(data){
						if(data.result>=1){
							alert('데이터가 수정 되었습니다.');
							location.href='./list.jsp';
						}else{
							alert('데이터 수정이 실패했습니다.');
						}
					});
					
				});
			});
		</script>
	</head>
	<body>
		<h4>user0 수정</h4>
		<a href="/Ch06/1_JDBC.jsp">처음으로</a>
		<a href="/Ch06/user0/list.jsp">User0 목록</a>
		
		
		<form action="/Ch06/user2/modifyProc.jsp" method="post">
			<table border="1">
				<tr>
					<td>아이디</td>
					<td><input type="text" name="uid" readonly="readonly"></td>
				</tr>
				<tr>
					<td>이름</td>
					<td><input type="text" name="name"></td>
				</tr>
				<tr>
					<td>휴대폰</td>
					<td><input type="text" name="hp" ></td>
				</tr>
				<tr>
					<td>나이</td>
					<td><input type="number" name="age"  ></td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" vlaue="수정">
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>