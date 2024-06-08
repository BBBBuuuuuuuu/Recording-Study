<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>게시글 쓰기</title>
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
	position: fixed;
	left: 0;
	bottom: 0;
	width: 100%;
	background-color: #151515;
	color: #ffffff;
	padding: 20px 0;
	text-align: center;
	z-index: 999;
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
			<c:if test="${ empty sessionScope.authUser }">
				<li><a href="/board/login.do">로그인</a></li>
			</c:if>
			<li><a href="/board/main.do">게시판</a></li>
		</ul>
	</nav>

	<form action="write.do" method="post" >
		<p>
			제목:<br /> <input type="text" name="title" value="${param.title}">
			<c:if test="${errors.title}">제목을 입력하세요.</c:if>
		</p>
		<p>
			내용:<br />
			<textarea name="content" rows="5" cols="30">${param.title}</textarea>
		</p>
		<input type="submit" value="새 글 등록">
	</form>

	<footer>
		<p>&copy; 2024 헬스 웹사이트. All rights reserved.</p>
	</footer>
</body>
</html>