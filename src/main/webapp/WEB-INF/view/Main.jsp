<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>DO IT</title>
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

    .container {
        display: flex;
        margin-top: 5%;
        margin-bottom: 10%;
    }

    .main {
        width: 30%;
        margin-left: 15%;
    }

    .image-container {
        width: 30%;
        display: flex;
        justify-content: center;
        align-items: center;
    }

    .image-container img {
        max-width: 100%;
        max-height: 100%;
        margin-right: -40%;
    }

.main h2 {
	font-size: 2em;
	margin-top: 1px;
	color: #000000;
	text-align: left;
}

.main b {
	color: #000000;
	font-size: larger;
}

.main a {
	background-color: #1C1C1C;
	color: #DBA901;
	margin-right: 73%;
}

.cta {
	background-color: #696969;
	color: #fff;
	padding: 10px 20px;
	text-decoration: none;
	border-radius: 5px;
	font-weight: bold;
	float : left;
	width : 130px;
}


.cta:hover {
	background-color: #cc5500;
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

/* 이미지 슬라이드쇼 스타일 */
.photo {
	position: relative; /* 자식 요소의 위치를 상대적으로 설정합니다. */
	max-width: 100%;
	height: 400px; /* 이미지 슬라이드쇼 컨테이너의 높이를 지정합니다. */
	display: flex;
	align-items: center;
	justify-content: center;
	overflow: hidden;
}

.overlay {
	position: absolute; /* 요소를 상대적인 위치를 기준으로 배치합니다. */
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	background-color: rgba(0, 0, 0, 0.7); /* 투명한 검정색 배경을 생성합니다. */
}

.mySlides {
	width: 100%;
}

img {
	width: 100%;
	height: 100%;
}
/* 이미지 전환 애니메이션 */
.fade {
	-webkit-animation-name: fade;
	-webkit-animation-duration: 1.5s;
	animation-name: fade;
	animation-duration: 1.5s;
}

@
-webkit-keyframes fade {from { opacity:.4;
	
}

to {
	opacity: 1;
}

}
@
keyframes fade {from { opacity:.4;
	
}

to {
	opacity: 1;
}
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
			<li><a href="login.do">로그인</a></li>
			<li><a href="article/list.do">게시판</a></li>
		</ul>
	</nav>
	<div class="photo">
		<div class="overlay"></div>
		<!-- 투명한 검정색 박스 -->
		<div class="mySlides fade">
			<img src="./img/main1.jpg" alt="">
		</div>
		<div class="mySlides fade">
			<img src="./img/main2.jpg" alt="">
		</div>
		<div class="mySlides fade">
			<img src="./img/main3.jpg" alt="">
		</div>
	</div>



<div class="container">
    <div class="main">
        <h2>헬스클럽 소개문구</h2>
        <b>Milon Program : 17분 30초 짧은 시간동안 경험하는 가장 효율적인 운동 개인의 신체 상태에 맞는
            운동량을 측정-세팅-분석-처방하여 짧은 시간 최대 운동효과를 낼 수 있도록 합니다. DO IT 휘트니스 전 지점에서 밀론을
            만나보세요!<br> <br><br><br><br><br><br>
        </b>
        <a href="membership.do" class="cta">멤버쉽 혜택받기</a>
    </div>
    <div class="image-container">
        <img src="./img/main4.jpg" alt="">
    </div>
</div>

<footer>
		<p>&copy; 2024 헬스 웹사이트. All rights reserved.</p>
	</footer>
	<!-- 자바스크립트 -->
	<script>
		var slideIndex = 0;
		showSlides();

		function showSlides() {
			var i;
			var slides = document.getElementsByClassName("mySlides");
			for (i = 0; i < slides.length; i++) {
				slides[i].style.display = "none";
			}
			slideIndex++;
			if (slideIndex > slides.length) {
				slideIndex = 1
			}
			slides[slideIndex - 1].style.display = "block";
			setTimeout(showSlides, 2000); // 이미지를 2초마다 전환
		}
	</script>
</body>
</html>
