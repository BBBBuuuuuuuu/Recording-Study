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

form {
	border: 1px solid;
	margin-top: 5%;
	width: 40%;
	text-align: center;
	margin-left: 30%;
}

input[type="text"], input[type="password"] {
	font-size: 15px;
	color: #222222;
	width: 300px;
	border: none;
	border-bottom: solid #aaaaaa 1px;
	padding-bottom: 10px;
	padding-left: 10px;
	position: relative;
	background: none;
	z-index: 5;
}

input[type="text"]::placeholder, input[type="password"]::placeholder {
	color: #aaaaaa;
}

input[type="text"]:focus, input[type="password"]:focus {
	outline: none;
}

/* 아이디와 비밀번호 입력칸에만 스타일 적용 끝 */

/* 로그인 버튼과 다시 입력 버튼에만 스타일 적용 */
input[type="submit"], input[type="reset"] {
	margin-top: 10px;
	background-color: #151515;
	color: #DBA901;
	border: none;
	padding: 10px 20px;
	text-decoration: none;
	border-radius: 5px;
	font-weight: bold;
	cursor: pointer;
}

input[type="submit"]:hover, input[type="reset"]:hover {
	background-color: #FFFFFF;
	color: #DBA901;
	border: 1px solid #DBA901;
}

button {
	float: left;
	margin-left: 49%;
	margin-top: -2%;
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
			<c:if test="${not empty sessionScope.authUser }"><li><a href="/board/logout.do">로그아웃</a></li></c:if>
			<c:if test="${ empty sessionScope.authUser }"><li><a href="/board/login.do">로그인</a></li></c:if>
			<li><a href="list.do">게시판</a></li>
		</ul>
	</nav>

	<form action="login.do" name="healthLogin" method="post">
		<br>아이디 : <input type="text" name="id" size="10"
			value="${param.id}"><br>
		<c:if test="${errors.id}">ID를 입력하세요.</c:if>
		<br>비밀번호 : <input type="password" name="password" size="15"><br>
		<c:if test="${errors.password}">암호를 입력하세요.</c:if>

		
		<c:if test="${errors.idOrPwNotMatch}">
		아이디와 암호가 일치하지 않습니다.
	</c:if>

		<br> <br> <br> <br> <input type="submit"
			value="로그인"> <input type="reset" value="다시입력"> <br>
		<br> <br> <br> <br> <br> <br> <br>
	</form>
	<button onclick="window.location.href = 'join.do' " value="회원가입">회원가입</button>
	<footer>
		<p>&copy; 2024 헬스 웹사이트. All rights reserved.</p>
	</footer>
</body>
</html>