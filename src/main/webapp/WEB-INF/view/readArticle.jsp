<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags"%>
<%

%>
<!DOCTYPE html>
<html>
<head>
<title>게시글 읽기</title>
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

h2 {
	text-align: center;
	color: black;
}

table {
	margin-top: 1%;
}

input[type="submit"] {
	margin-top: 20px;
	background-color: #151515;
	color: #DBA901;
	border: none;
	padding: 5px 15px;
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
	<img src="/board/imgs/${articleData.article.filename}">
	<nav>
		<ul>
			<li><a href="/board/main.do">홈</a></li>
			<c:if test="${not empty sessionScope.authUser }"><li><a href="/board/logout.do">로그아웃</a></li></c:if>
			<c:if test="${ empty sessionScope.authUser }"><li><a href="/board/login.do">로그인</a></li></c:if>
			<li><a href="list.do">게시판</a></li>
		</ul>
	</nav>
	<h2>게시판</h2>
	<table border="1" width="100%">
		<tr>
			<td>번호</td>
			<td>${articleData.article.number}</td>
		</tr>
		<tr>
			<td>작성자</td>
			<td>${articleData.article.writer.name}</td>
		</tr>
		<tr>
			<td>제목</td>
			<td><c:out value='${articleData.article.title}' /></td>
		</tr>
		<tr>
			<td>내용</td>
			<td><u:pre value='${articleData.content}' /></td>
		</tr>
			
		<tr>
			<td colspan="2"><c:set var="pageNo"
					value="${empty param.pageNo ? '1' : param.pageNo}" /> <a
				href="list.do?pageNo=${pageNo}">[목록]</a> <c:if
					test="${authUser.id == articleData.article.writer.id}">
					<a href="modify.do?no=${articleData.article.number}">[게시글수정]</a>
					<a href="delete.do?no=${articleData.article.number}">[게시글삭제]</a>
				</c:if></td>
		</tr>
	</table>
	<p>
	<div style="text-align: center;margin-left: 40%; margin-top: 1%; width: 50%;">
		<h3 style="margin-right:60%; text-align:left;">댓글</h3>
		<c:if test="${not empty commentData }">
			<table border="1">
				<c:forEach var="comment" items="${commentData}">
					<tr>
						<td>아이디 : ${comment.id }</td>
						<td>등록일자 : ${comment.regdate }</td>
					</tr>
					<tr>
						<td colspan="2">내용<br> <u:pre
								value="${comment.content }" /></td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
		<c:if test="${empty commentData }">작성된 댓글이 없습니다.</c:if>
	</div>
	<form action="comment.do" method="post"
		style="margin-left: 46%; margin-top: 1%; width: 50%;">
		<table>
			<c:if test="${errors.notMatch }">
				<tr>
					<td>아이디, 비밀번호가 일치하지 않습니다.</td>
				</tr>
			</c:if>
			<tr>
				<td>아이디 <br> <input type=text name=commentID
					value=${param.id }> <c:if test="${errors.id }">
						<br>아이디를 입력해주세요.</c:if></td>
			</tr>
			<tr>
				<td>비밀번호 <br> <input type=password name=commentPWD
					value=${param.pwd }> <c:if test="${errors.pwd }">
						<br>비밀번호를 입력해주세요.</c:if></td>
			</tr>
			<tr>
				<td><br>내용 <input type="hidden" name=articleNum
					value=${articleData.article.number }></td>
			</tr>
			<tr>
				<td colspan=2><input type=text name=content> <c:if
						test="${errors.comment}">
						<br>내용을 입력해주세요.</c:if></td>
			</tr>
			<tr align=right>
				<td><input type=submit value=등록하기></td>
			</tr>
		</table>

		<footer>
			<p>&copy; 2024 헬스 웹사이트. All rights reserved.</p>
		</footer>
	</form>
</body>
</html>