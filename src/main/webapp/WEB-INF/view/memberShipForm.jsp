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
    border: 1px solid; /* 테두리 두께를 로그인 폼과 동일하게 조정 */
    margin-top: 5%;
    width: 40%;
    text-align: center;
    margin-left: 30%;
    padding: 20px; /* 내부 여백 추가 */
    border-radius: 10px; /* 테두리 모서리를 둥글게 만듭니다 */
    background-color: #f2f2f2; /* 배경색 추가 */
}

input[type="text"],
input[type="password"] {
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
    margin-top: 10px; /* 입력란 간격 추가 */
}

input[type="text"]::placeholder,
input[type="password"]::placeholder {
    color: #aaaaaa;
}

input[type="text"]:focus,
input[type="password"]:focus {
    outline: none;
}

input[type="submit"] {
    margin-top: 20px; 
    background-color: #151515;
    color: #DBA901;
    border: none;
    padding: 10px 20px;
    text-decoration: none;
    border-radius: 5px;
    font-weight: bold;
    cursor: pointer;
}

input[type="submit"]:hover {
    background-color: #1C1C1C;
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
			<li><a href="article/list.do">게시판</a></li>
		</ul>
	</nav>
	<form action="membership.do" method="post">
		<table align = "center">
			<tr>
				<td>아이디 :</td>
				<td><input type="text" name=id value="${loginInfo.id }"
					disabled="disabled"></td>
			</tr>
			<tr>
				<td>비밀번호 :</td>
				<td><input type="password" name=pwd></td>
			</tr>
			<c:if test="${errors.notMatchPWD }">
				<tr>
					<td colspan=2 align=center>비밀번호가 일치하지 않습니다</td>
			</c:if>
			<tr>
				<td>계좌번호 :</td>
				<td><input type="password" name=accNum value="${loginInfo.accNum }"
					disabled="disabled"></td>
			</tr>
			<tr>
				<td>성별 :</td>
				<td><input type="text" name=gender value="${loginInfo.gender }"
					disabled="disabled"></td>
			</tr>
			<tr></tr>
			<tr>
				<td colspan=2><input type="radio" name=month value=3 checked>3개월
					<input type="radio" name=month value=6>6개월 <input
					type="radio" name=month value=12>12개월</td>
			</tr>
			<c:if test="${errors.balance }">잔액이 부족합니다.</c:if>
			<tr></tr>
			<tr>
				<td colspan=2 align=center><input type=submit value=구매하기>
				</td>
			</tr>
		</table>
		<footer>
			<p>&copy; 2024 헬스 웹사이트. All rights reserved.</p>
		</footer>
	</form>
</body>
</html>