<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<head>
<title>회원제 게시판 예제</title>
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
	text-align: center;
	font-style: 맑은고딕;
	border: 1px;
}

nav ul li a {
	color: #DBA901;
	text-decoration: none;
	margin-left: 5px;
}

form {
	text-align: center;
	border: 3px solid black;
	width: 30%;
	margin-left: 35%;
	margin-top: 8%;
	border-radius: 10px;
	background-color: #f2f2f2;
}

a {
	text-decoration-line: none;
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
			<li><a href="main.do">홈으로 돌아가기</a></li>
		</ul>
	</nav>
	<form action="">
		<u:isLogin>
			<br>
			<br>
			<br>
			<h3>CT: ${authUser.name}님, 안녕하세요.</h3>
			<br>
			<br>
			<br>
			<br>
			<br>
			<br>
			<a href="/board/main.do">[메인화면가기]</a>
			<a href="logout.do">[로그아웃하기]</a>
			<a href="changePwd.do">[암호변경하기]</a>
			<br>
			<br>
			<br>
			<br>
			<br>
			<br>
		</u:isLogin>
		<u:notLogin>
	CT: <a href="join.do">[회원가입하기]</a>
			<a href="login.do">[로그인하기]</a>
		</u:notLogin>
	</form>
	<footer>
		<p>&copy; 2024 헬스 웹사이트. All rights reserved.</p>
	</footer>
</body>
</html>