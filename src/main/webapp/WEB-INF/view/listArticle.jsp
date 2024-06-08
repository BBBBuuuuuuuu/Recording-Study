<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>게시글 목록</title>
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

table {
    border: 1px solid;
    margin-top: 5%;
    width: 40%;
    text-align: center;
    margin-left: 30%;
    padding: 30px;
    border-radius: 10px;
    background-color: #FFFFFF;
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
	<table border="1">
		<tr>
			<td colspan="4"><a href="write.do">[게시글쓰기]</a></td>
		</tr>
		<tr>
			<td>번호</td>
			<td>제목</td>
			<td>작성자</td>
			<td>조회수</td>
		</tr>
		<c:if test="${articlePage.hasNoArticles()}">
			<tr>
				<td colspan="4">게시글이 없습니다.</td>
			</tr>
		</c:if>
		<c:forEach var="article" items="${articlePage.content}">
			<tr>
				<td>${article.number}</td>
				<td><a
					href="read.do?no=${article.number}&pageNo=${articlePage.currentPage}">
						<c:out value="${article.title}" />
				</a></td>
				<td>${article.writer.name}</td>
				<td>${article.readCount}</td>
			</tr>
		</c:forEach>
		<c:if test="${articlePage.hasArticles()}">
			<tr>
				<td colspan="4"><c:if test="${articlePage.startPage > 5}">
						<a href="list.do?pageNo=${articlePage.startPage - 5}">[이전]</a>
					</c:if> <c:forEach var="pNo" begin="${articlePage.startPage}"
						end="${articlePage.endPage}">
						<a href="list.do?pageNo=${pNo}">[${pNo}]</a>
					</c:forEach> <c:if test="${articlePage.endPage < articlePage.totalPages}">
						<a href="list.do?pageNo=${articlePage.startPage + 5}">[다음]</a>
					</c:if></td>
			</tr>
		</c:if>
	</table>
	<footer>
		<p>&copy; 2024 헬스 웹사이트. All rights reserved.</p>
	</footer>
</body>
</html>