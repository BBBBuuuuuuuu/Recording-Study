<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
body {
	font-family: Arial, sans-serif;
	margin: 0;
	padding: 0;
	background-color: #ffffff;
}

header {
	background: linear-gradient(to right, #000000, #1C1C1C, #2E2E2E, #1C1C1C, #000000);
	color: #ffffff;
	padding: 20px 0;
	text-align: center;
}

h1 {
	color: #DBA901;
}

nav {
	background-color: #151515;
	color: #AEB404;
	padding: 10px 0;
	text-align: center;
}

nav ul {
	list-style-type: none;
	margin: 0;
	padding: 0;
}

nav ul li {
	display: inline;
	margin: 0 10px;
	font-style: 맑은고딕;
	border: 1px;
}

nav ul li a {
	color: #DBA901;
	text-decoration: none;
	margin-left: 40px;
}

body {
	font: "맑은고딕";
}

body table {
	margin: 300px auto;
}

table {
	width: 400px;
	height: 350px;
	border: 1px solid black;
	padding: 5px;
}

table tr {
	border: 1px solid black;
	text-align: center;
}

table tr td {
	margin: auto;
}

table tr .list {
	width: 150px;
	float: left;
}

table tr td .input {
	width: 150px;
	float: left;
}

table tr td .phone1 {
	width: 100%;
	float: left;
}

table tr td .phone {
	width: 100px;
	float: left;
}

select {
	text-align: center;
}

footer {
	position: fixed; /* 화면의 아래에 고정 */
	left: 0;
	bottom: 0;
	width: 100%;
	background-color: #151515;
	color: #ffffff;
	padding: 20px 0;
	text-align: center;
	z-index: 999; /* 다른 요소들보다 위에 표시 */
}
</style>

</head>
<body>

	<header>
		<h1>DO IT</h1>
	</header>
	<nav>
		<ul>
			<li><a href="/board/main.do">홈</a></li>
			<c:if test="${ empty sessionScope.authUser }"><li><a href="/board/login.do">로그인</a></li></c:if>
			<li><a href="/board/main.do">게시판</a></li>
		</ul>
	</nav>
	<form action="join.do" method="post">
		<table>
			<tr>
				<td class="list">아이디</td>
				<td><input type="text" name="id" class="input" /></td>
			</tr>
			<tr>
				<td class="list">비밀번호</td>
				<td><input type="password" name="password" class="input" /></td>
			</tr>
			<tr>
				<td class="list">비밀번호 확인</td>
				<td><input type="password" name="confirmPassword" class="input" /></td>
			</tr>
			<tr>
				<td class="list">이름</td>
				<td><input type="text" name="name" class="input" /></td>
			</tr>
			<tr>
				<td class="list">전화번호</td>
				<td><input type="text" name="phone" class="phone" /></td>
			</tr>
			<tr>
				<td class="list">계좌번호</td>
				<td><input type="text" name="accNum" size="10" class="input" /></td>
			</tr>
			<tr>
				<td class="list">성별</td>
				<td><input type="radio" name="gender" value="M" checked />남</td>
				<td><input type="radio" name="gender" value="F" />여</td>
			</tr>
			<tr class="join">
				<td><input type="submit" value="회원가입" /></td>
			</tr>
			<footer>
				<p>&copy; 2024 헬스 웹사이트. All rights reserved.</p>
			</footer>
		</table>
	</form>

</body>
</html>
